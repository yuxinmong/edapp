package com.android.volley.toolbox;

import android.content.Context;

import com.ed.v1.common.Log;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangcheng on 15/1/27.
 */
public class RequestStatisticsUtil {

    private static String stsFilePath;

    private volatile static int count;

    private static Statistics statistics;
    private static Statistics lastStatistics;

    public static void init(Context context) {
        stsFilePath = context.getFilesDir() + "/api_statistics";
        lastStatistics = loadStatistics();
        statistics = new Statistics();
    }

    public static void append(String url, long length) {
        count++;
        statistics.append(url, length);
        // TODO 为减少IO操作，每10次API调用保存一次统计数据
        if (count % 10 == 0) {
            saveStatistics(statistics);
        }
    }

    public static Statistics getLastRequestStatistics() {
        return lastStatistics;
    }

    private static <T> T loadStatistics() {
        T t = null;
        File file = new File(stsFilePath);
        if (file.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                t = (T) ois.readObject();
            } catch (Exception e) {
                Log.logStackTrace(e);
            } finally {
                closeStream(ois);
                closeStream(fis);
                file.delete();
            }
        }
        return t;
    }

    private static void saveStatistics(Object o) {
        File file = new File(stsFilePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
        } catch (IOException e) {
            Log.logStackTrace(e);
        }

    }

    private static void closeStream(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                Log.logStackTrace(e);
            }
        }
    }

    public static class Statistics implements Serializable {

        private Map<String, Long> requestCountMap = new ConcurrentHashMap<String, Long>();
        private Map<String, Long> requestAmountMap = new ConcurrentHashMap<String, Long>();

        void append(String url, long length) {
            Long count = requestCountMap.get(url);
            requestCountMap.put(url, count == null ? 0 : ++count);
            Long amount = requestAmountMap.get(url);
            requestAmountMap.put(url, amount == null ? 0 : amount + length);
        }

        public Map<String, Long> getRequestCountMap() {
            return requestCountMap;
        }

        public Map<String, Long> getRequestAmountMap() {
            return requestAmountMap;
        }
    }
}
