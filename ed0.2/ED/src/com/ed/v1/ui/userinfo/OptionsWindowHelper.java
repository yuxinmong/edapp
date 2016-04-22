package com.ed.v1.ui.userinfo;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.ed.v1.R.string;

import cn.jeesoft.widget.pickerview.CharacterPickerView;
import cn.jeesoft.widget.pickerview.CharacterPickerView.OnOptionChangedListener;
import cn.jeesoft.widget.pickerview.CharacterPickerWindow;

/**
 * 地址选择器
 *
 * @version 0.1 king 2015-10
 */
public class OptionsWindowHelper {

    private static List<String> options1Items = null;
    private static List<String> optionstime2Items = null;
    public static  ArrayList datas = null;
    private static List<String> optionstime3Items = null;

    private static List<List<String>> options2Items = null;
    private static List<List<List<String>>> options3Items = null;
	private static int yearstar=1880;
	private static int yearend=2016;
	private static int daystar=1;
	private static int dayend=30;
	private static int monthstar=1;
	private static int monthend=12;
private static String year="年";
private static String month="月";
private static String day="日";
private static ArrayList<String> optionstime1Items;
    public static interface OnOptionsSelectListener {
        public void onOptionsSelect(String province, String city, String area);
    }

    private OptionsWindowHelper() {
    }

    /**
     * 初始化选项数据
     * @return 
     */
    public static void setPickerData(CharacterPickerView view,final OnOptionsSelectListener listener) {
   
        if (options1Items == null) {
            optionstime1Items = new ArrayList<String>();
            optionstime2Items = new ArrayList<String>();
            optionstime3Items = new ArrayList<String>();

        }
        for(int i=yearend-2;i<=yearend;i++){
        	optionstime1Items.add(i+year);
        }
        for(int i=yearstar;i<=yearend-3;i++){
        	optionstime1Items.add(i+year);
        }
       
        	
       
        for(int i=monthend-2;i<=monthend;i++){
        	optionstime2Items.add(i+month);
        }
        for(int i=1;i<=monthend-3;i++){
        	optionstime2Items.add(i+month);
        }
      
        
        for(int i=dayend-2;i<=dayend;i++){
        	optionstime3Items.add(i+day);
        }
        
        for(int i=1;i<=dayend-3;i++){
        	optionstime3Items.add(i+day);
        }
        
        //三级联动效果
        view.setTimePicker(optionstime1Items, optionstime2Items, optionstime3Items);

        view.setOnOptionChangedListener(new OnOptionChangedListener() {
			
			@Override
			public void onOptionChanged(CharacterPickerView view, int option1,
					int option2, int option3) {
				// TODO Auto-generated method stub
		
				String year=optionstime1Items.get(option(option1,optionstime1Items.size()));
				String month=optionstime2Items.get(option(option2,optionstime2Items.size()));
				String day=optionstime3Items.get(option(option3,optionstime3Items.size()));
	          
				listener.onOptionsSelect(year, month, day);
			}
		}) ;
		
    }

	protected static int option(int option, int size) {
		// TODO Auto-generated method stub
		int y=option+3;
		int ylast = size-1;

		if(y>ylast){
			switch (y-ylast) {
			case 1:
				y=0;
				break;
			case 2:
				y=1;
				break;
			case 3:
				y=2;
				break;
			default:
				break;
			}
		}
		return y;
	}

}
