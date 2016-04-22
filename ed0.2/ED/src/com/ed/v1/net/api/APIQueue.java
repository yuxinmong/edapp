package com.ed.v1.net.api;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.*;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.toolbox.*;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.ed.v1.BuildConfig;
import com.ed.v1.CLApplication;
import com.ed.v1.common.Log;
import com.ed.v1.net.JSONDeserializable;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class APIQueue {

    public static final int HTTP_TIMEOUT = 60 * 1000;
    public static final int HTTP_PORT = 80;
    public static final int HTTPS_PORT = 443;

    private static final String TAG = "API";

    private static APIQueue instance;

    public static APIQueue getInstance() {
        if (instance == null) {
            instance = new APIQueue();
        }

        return instance;
    }

    private RequestQueue queue;
    private DefaultHttpClient defaultHttpClient;
    private ImageLoader imageLoader;

    private APIQueue() {
        Log.v(TAG, "The API queue is created.");
    }

    public <T extends JSONDeserializable> void addRequest(APIRequest<T> request) {
        addRequest(TAG, request);
    }

    public <T extends JSONDeserializable> void addRequest(Object tag, APIRequest<T> request) {
        if (tag == null) {
            tag = TAG;
        }
        if (request instanceof ImageRequest) {
            ImageRequest imageRequest = (ImageRequest) request;

            try {
                ImageListener listener = ImageLoader.getImageListener(imageRequest.getImageView(), imageRequest.getDefaultImageId(),
                        imageRequest.getErrorImageId());
                this.getImageLoader().get(imageRequest.getAbsoluteURL(), listener);
                Log.v(TAG, String.format("The image request is added: %s", imageRequest.getAbsoluteURL()));
            } catch (Exception e) {
                Log.e(TAG, "Cannot load the image.", e);
            }
        } else {
            try {
            	APIResponseListener<T> listener = new APIResponseListener<T>(request);
            	StringRequest rq = null;
        		rq = new APIRequestWrapper<T>(tag, request, listener);
                this.getRequestQueue().add(rq);
                Log.v(TAG, String.format("The API request is added(tag:%s): %s", tag.toString(), rq.getCacheKey()));
            } catch (Exception e) {
                Log.e(TAG, "Cannot execute the API.", e);
            }
        }
    }

    public void cancelAll() {
        if (this.queue != null) {
            this.queue.cancelAll(new RequestFilter() {

                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
        }
    }

    public void cancel(Object tag) {
        if (this.queue != null) {
            this.queue.cancelAll(tag);
            Log.v(tag == null ? this.getClass().getSimpleName() : tag.toString(), "The API queue is cancelled. tag:" + tag);
        }
    }

    private RequestQueue getRequestQueue() {
        if (this.queue == null) {
            HttpClient httpClient = this.getHttpClient();
            this.queue = Volley.newRequestQueue(CLApplication.getInstance().getApplicationContext(), new HttpClientStack(httpClient));
        }

        return this.queue;
    }

    public ImageLoader getImageLoader() {
        if (this.imageLoader == null) {
            this.imageLoader = new ImageLoader(this.getRequestQueue(), new BitmapCache());
        }

        return this.imageLoader;
    }

    private DefaultHttpClient getHttpClient() {
        if (this.defaultHttpClient == null) {

            try {
                HttpParams params = new BasicHttpParams();

                // Set the timeout in milliseconds until a connection is
                // established.
                // The default value is zero, that means the timeout is not
                // used.
                HttpConnectionParams.setConnectionTimeout(params, APIQueue.HTTP_TIMEOUT);

                // Set the default socket timeout (SO_TIMEOUT)
                // in milliseconds which is the timeout for waiting for data.
                HttpConnectionParams.setSoTimeout(params, APIQueue.HTTP_TIMEOUT);

                X509TrustManager tm = new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };

                SchemeRegistry registry = new SchemeRegistry();

                registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), APIQueue.HTTP_PORT));

                SSLContext ctx = SSLContext.getInstance("TLS");
                ctx.init(null, new TrustManager[] { tm }, null);
                SSLSocketFactory sslSocketFactory = new MySSLSocketFactory(ctx);
                sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                registry.register(new Scheme("https", sslSocketFactory, APIQueue.HTTPS_PORT));

                ClientConnectionManager connectionManager = new ThreadSafeClientConnManager(params, registry);
                this.defaultHttpClient = new DefaultHttpClient(connectionManager, params);
            //    this.defaultHttpClient.setCookieStore();

            } catch (Exception ex) {

                this.defaultHttpClient = null;
            }
        }

        return this.defaultHttpClient;
    }

    private static final class MySSLSocketFactory extends SSLSocketFactory {

        private SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            this.sslContext.init(null, new TrustManager[] { tm }, null);
        }

        public MySSLSocketFactory(SSLContext context) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
            super(null);
            this.sslContext = context;
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
            return this.sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return this.sslContext.getSocketFactory().createSocket();
        }
    }

    private final class APIRequestWrapper<T extends JSONDeserializable> extends StringRequest {

        private APIRequest<T> request;

        public APIRequestWrapper(Object tag, APIRequest<T> request, APIResponseListener<T> listener) {
            super(request.getMethod(), request.getAbsoluteURL(), listener, listener);

            this.request = request;
            setShouldCache(request.shouldCache());
            this.setRetryPolicy(new DefaultRetryPolicy(APIQueue.HTTP_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            this.setTag(tag);
            request.setTag(tag);
            this.setAlwaysInformCache(request.alwaysInformCache());
        }
        
        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
        	// TODO Auto-generated method stub
        	return super.parseNetworkResponse(response);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return request.getParameters();
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = request.getHeaders();
            if (headers == null) {
                headers = new HashMap<String, String>();
            }
            return headers;
        }
    }

    final class APIResponseListener<T extends JSONDeserializable> implements Response.Listener<String>, Response.ErrorListener {

        private APIRequest<T> request;

        public APIResponseListener(APIRequest<T> request) {
            this.request = request;
        }

        @Override
        public void onResponse(boolean intermediate, String response) {

            if (BuildConfig.DEBUG) {
                Log.i("API", String.format(Locale.getDefault(), "Request: %s\nResponse: %s", this.request.getAbsoluteURL(), response));
            }

            APIResponse<T> resp = this.request.createResponse();

            resp.isIntermediate(intermediate);

            try {
                resp.parseResponse(response);
            } catch (JSONException e) {
                resp.parseException(e);
            }

            if (resp.getResultCode() == APIResponse.ResultCode.Login) {
            }

            this.request.onResponse(resp);
            APIDelegate<T> delegate = this.request.getDelegate();

            if (delegate != null) {
                delegate.onResponse(resp);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            if (BuildConfig.DEBUG) {
                Log.e("API", String.format(Locale.getDefault(), "Request: %s\nError: %s  Code:%s", this.request.getURL(), error.toString(),
                        error.networkResponse != null ? error.networkResponse.statusCode : "none"));
            }

            APIResponse<T> resp = this.request.createResponse();

            resp.parseException(error);

            if (resp.getResultCode() == APIResponse.ResultCode.Login) {
            }

            this.request.onResponse(resp);

            APIDelegate<T> delegate = this.request.getDelegate();

            if (delegate != null) {
                delegate.onResponse(resp);
            }
        }

    }

    private static final class BitmapCache implements ImageCache {

        private LruCache<String, Bitmap> cache;

        public BitmapCache() {

            int maxSize = 10 * 1024 * 1024;

            this.cache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return this.cache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            this.cache.put(url, bitmap);
        }
    }
}
