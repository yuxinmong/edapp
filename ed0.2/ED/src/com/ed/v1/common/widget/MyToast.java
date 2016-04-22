package com.ed.v1.common.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ed.v1.R;

public class MyToast {
	 private static Toast mToast = null;
	    /**
	     * 自定义消息框
	     * @param c
	     * Created by hexiang on 2014/12/10.
	     */
	    @SuppressWarnings("deprecation")
	    private static Toast showCustomerToast(final Context c,String msg,int duration)
	    {

	        //获取LayoutInflater对象，该对象可以将布局文件转换成与之一致的view对象
	        LayoutInflater inflater=LayoutInflater.from(c);
	        //将布局文件转换成相应的View对象
	        View layout=inflater.inflate(R.layout.common_toast,null);
	      //  layout.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.common_toast));
	        //从layout中按照id查找TextView对象
	        TextView textView=(TextView)layout.findViewById(R.id.toast_text);
	        //设置TextView的text内容
	        textView.setText(msg);
	        //实例化一个Toast对象
	        Toast toast=new Toast(c.getApplicationContext());
	        toast.setDuration(duration);
	        toast.setGravity(Gravity.CENTER,0,0);
	        toast.setView(layout);
	        return toast;

	    }
	    @SuppressWarnings("deprecation")
	    private static Toast showCustomerToast(final Context c,String msg,int duration,int view)
	    {

	        //获取LayoutInflater对象，该对象可以将布局文件转换成与之一致的view对象
	        LayoutInflater inflater=LayoutInflater.from(c);
	        //将布局文件转换成相应的View对象
	        View layout=inflater.inflate(view,null);
	      //  layout.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.common_toast));
	        //从layout中按照id查找TextView对象
	        TextView textView=(TextView)layout.findViewById(R.id.toast_text);
	        //设置TextView的text内容
	        textView.setText(msg);
	        //实例化一个Toast对象
	        Toast toast=new Toast(c.getApplicationContext());
	        toast.setDuration(duration);
	        toast.setGravity(Gravity.CENTER,0,0);
	        toast.setView(layout);
	        return toast;

	    }
	    @SuppressWarnings("deprecation")
	    private static Toast showCustomerToast(final Context c,int msg,int duration,int view)
	    {

	        //获取LayoutInflater对象，该对象可以将布局文件转换成与之一致的view对象
	        LayoutInflater inflater=LayoutInflater.from(c);
	        //将布局文件转换成相应的View对象
	        View layout=inflater.inflate(view,null);
	      //  layout.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.common_toast));
	        //从layout中按照id查找TextView对象
	        TextView textView=(TextView)layout.findViewById(R.id.toast_text);
	        //设置TextView的text内容
	        textView.setText(msg);
	        //实例化一个Toast对象
	        Toast toast=new Toast(c.getApplicationContext());
	        toast.setDuration(duration);
	        toast.setGravity(Gravity.CENTER,0,0);
	        toast.setView(layout);
	        return toast;

	    }
	    /**
	     * 长消息框 指的的是时间长短
	     * Created by hexiang on 2014/12/10.
	     * @param c 上下文
	     * @param msg 消息内容
	     */
	    @SuppressWarnings("deprecation")
	    public static void showCustomerToastLong(final Context c,String msg)
	    {   //避免重复弹出消息
	        if (mToast == null) {
	            mToast =showCustomerToast(c,msg,Toast.LENGTH_LONG);
	        }
	        else {
	            TextView txt_msg=(TextView) mToast.getView().findViewById(R.id.toast_text);
	            txt_msg.setText(msg);
	            mToast.setDuration(Toast.LENGTH_LONG);
	        }

	        mToast.show();

	    }
	    
	    @SuppressWarnings("deprecation")
	    public static void showCustomerToastShot(final Context c,String msg,int view)
	    {   //避免重复弹出消息
	        if (mToast == null) {
	            mToast =showCustomerToast(c,msg,Toast.LENGTH_LONG,view);
	        }
	        else {
	            TextView txt_msg=(TextView) mToast.getView().findViewById(R.id.toast_text);
	            txt_msg.setText(msg);
	            mToast.setDuration(Toast.LENGTH_LONG);
	        }

	        mToast.show();

	    }
	    
	    @SuppressWarnings("deprecation")
	    public static void showCustomerToastShot(final Context c,int msg,int view)
	    {   //避免重复弹出消息
	        if (mToast == null) {
	            mToast =showCustomerToast(c,msg,Toast.LENGTH_LONG,view);
	        }
	        else {
	            TextView txt_msg=(TextView) mToast.getView().findViewById(R.id.toast_text);
	            txt_msg.setText(msg);
	            mToast.setDuration(Toast.LENGTH_LONG);
	        }

	        mToast.show();

	    }
	    /**
	     * 短消息框 指的的是时间长短 mToast.setDuration(Toast.LENGTH_SHORT);
	     * @param c 上下文
	     * @param msg 消息内容
	     * Created by hexiang on 2014/12/10.
	     */
	    @SuppressWarnings("deprecation")
	    public static void showCustomerToastShot(final Context c,String msg)
	    {   //避免重复弹出消息
	        if (mToast == null) {
	            mToast =showCustomerToast(c,msg,Toast.LENGTH_SHORT);
	        }
	        else {
	            TextView txt_msg=(TextView) mToast.getView().findViewById(R.id.toast_text);
	            txt_msg.setText(msg);
	            mToast.setDuration(Toast.LENGTH_SHORT);
	        }

	        mToast.show();
	    }
}
