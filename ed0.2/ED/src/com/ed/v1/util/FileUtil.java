package com.ed.v1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.util.EncodingUtils;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
/**
 * 
 * @author liup
 *
 */
public class FileUtil {
    /**
     * sdcard exist?
     * @return
     */
    public static boolean sdCardExist() {
        return Environment.getExternalStorageState()   
                .equals(Environment.MEDIA_MOUNTED);
    }
    /**
     * get sdcard root dir
     * @return
     */
    public static String getSDPath(){ 
        File sdDir = null; 
        if (sdCardExist()) {                               
          sdDir = Environment.getExternalStorageDirectory();//获取跟目录 
          return sdDir.toString();
        }
        return "";   
    }
    /**
     * 根据文件路径，获取文件中的缓存数据
     * @param requestPath
     * @return
     */
    public static String getFileFromLocal(String requestPath) {
        File file = new File(requestPath);
        String result = "";
        if (file.exists()) {
            FileInputStream fileIn;
            try {
                fileIn = new FileInputStream(file);
                int length = fileIn.available();
                byte[] buffer = new byte[length];
                fileIn.read(buffer);
                result = EncodingUtils.getString(buffer, "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        return "";
    }
    
    public static void saveFileForLocal(String requestPath, String result) {
        // TODO Auto-generated method stub
        File file = new File(requestPath);
        if (!file.exists()) {
            try {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file.createNewFile();
                FileOutputStream fout = new FileOutputStream(file);
                byte[] buffer = result.getBytes();
                fout.write(buffer);
                fout.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
   /**
    * 删除指定文件夹下所有文件
    * @name： wangdakuan
    * @time： 2014年9月28日下午12:25:12
    * @param path 文件夹完整绝对路径
    * @return
    */
	public static boolean delAllFile(String path){
		boolean flag = false;
		File file = new File(path);
		if(!file.exists()){
			return flag;
		}
		if(!file.isDirectory()){
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for(int i = 0;i < tempList.length;i++){
			if(path.endsWith(File.separator)){
				temp = new File(path + tempList[i]);
			}
			else{
				temp = new File(path + File.separator + tempList[i]);
			}
			if(temp.isFile()){
				temp.delete();
			}
			if(temp.isDirectory()){
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				flag = true;
			}
		}
		return flag;
	}
	
	public static Intent openFile(String filePath){
        File file = new File(filePath);
        if(!file.exists()) return null;
        /* 取得扩展名 */
        String end=file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase(); 
        /* 依扩展名的类型决定MimeType */
        if(end.equals("m4a")||end.equals("mp3")||end.equals("mid")||
                end.equals("xmf")||end.equals("ogg")||end.equals("wav")){
            return getAudioFileIntent(filePath);
        }else if(end.equals("3gp")||end.equals("mp4")){
            return getAudioFileIntent(filePath);
        }else if(end.equals("jpg")||end.equals("gif")||end.equals("png")||
                end.equals("jpeg")||end.equals("bmp")){
            return getImageFileIntent(filePath);
        }else if(end.equals("apk")){
            return getApkFileIntent(filePath);
        }else if(end.equals("ppt")){
            return getPptFileIntent(filePath);
        }else if(end.equals("xls")){
            return getExcelFileIntent(filePath);
        }else if(end.equals("doc")){
            return getWordFileIntent(filePath);
        }else if(end.equals("pdf")){
            return getPdfFileIntent(filePath);
        }else if(end.equals("chm")){
            return getChmFileIntent(filePath);
        }else if(end.equals("txt")){
            return getTextFileIntent(filePath,false);
        }else{
            return getAllIntent(filePath);
        }
    }
    
    //Android获取一个用于打开APK文件的intent
    public static Intent getAllIntent(String param) {

        Intent intent = new Intent();  
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
        intent.setAction(android.content.Intent.ACTION_VIEW);  
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri,"*/*"); 
        return intent;
    }
    //Android获取一个用于打开APK文件的intent
    public static Intent getApkFileIntent( String param ) {

        Intent intent = new Intent();  
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
        intent.setAction(android.content.Intent.ACTION_VIEW);  
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri,"application/vnd.android.package-archive"); 
        return intent;
    }

    //Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent( String param ) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    //Android获取一个用于打开AUDIO文件的intent
    public static Intent getAudioFileIntent( String param ){

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    //Android获取一个用于打开Html文件的intent   
    public static Intent getHtmlFileIntent( String param ){

        Uri uri = Uri.parse(param ).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param ).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    //Android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent( String param ) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    //Android获取一个用于打开PPT文件的intent   
    public static Intent getPptFileIntent( String param ){  

        Intent intent = new Intent("android.intent.action.VIEW");   
        intent.addCategory("android.intent.category.DEFAULT");   
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
        Uri uri = Uri.fromFile(new File(param ));   
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");   
        return intent;   
    }   

    //Android获取一个用于打开Excel文件的intent   
    public static Intent getExcelFileIntent( String param ){  

        Intent intent = new Intent("android.intent.action.VIEW");   
        intent.addCategory("android.intent.category.DEFAULT");   
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
        Uri uri = Uri.fromFile(new File(param ));   
        intent.setDataAndType(uri, "application/vnd.ms-excel");   
        return intent;   
    }   

    //Android获取一个用于打开Word文件的intent   
    public static Intent getWordFileIntent( String param ){  

        Intent intent = new Intent("android.intent.action.VIEW");   
        intent.addCategory("android.intent.category.DEFAULT");   
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
        Uri uri = Uri.fromFile(new File(param ));   
        intent.setDataAndType(uri, "application/msword");   
        return intent;   
    }   

    //Android获取一个用于打开CHM文件的intent   
    public static Intent getChmFileIntent( String param ){   

        Intent intent = new Intent("android.intent.action.VIEW");   
        intent.addCategory("android.intent.category.DEFAULT");   
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
        Uri uri = Uri.fromFile(new File(param ));   
        intent.setDataAndType(uri, "application/x-chm");   
        return intent;   
    }   

    //Android获取一个用于打开文本文件的intent   
    public static Intent getTextFileIntent( String param, boolean paramBoolean){   

        Intent intent = new Intent("android.intent.action.VIEW");   
        intent.addCategory("android.intent.category.DEFAULT");   
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
        if (paramBoolean){   
            Uri uri1 = Uri.parse(param );   
            intent.setDataAndType(uri1, "text/plain");   
        }else{   
            Uri uri2 = Uri.fromFile(new File(param ));   
            intent.setDataAndType(uri2, "text/plain");   
        }   
        return intent;   
    }  
    //Android获取一个用于打开PDF文件的intent   
    public static Intent getPdfFileIntent( String param ){   

        Intent intent = new Intent("android.intent.action.VIEW");   
        intent.addCategory("android.intent.category.DEFAULT");   
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
        Uri uri = Uri.fromFile(new File(param ));   
        intent.setDataAndType(uri, "application/pdf");   
        return intent;   
    }
}
