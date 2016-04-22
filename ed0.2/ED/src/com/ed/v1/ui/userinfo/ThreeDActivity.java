package com.ed.v1.ui.userinfo;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.BaseBundle;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.SimpleAdapter.ViewBinder;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.CommonUtils;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.ui.buyer.UPToTopActivity;
import com.ed.v1.ui.main.HomeTabActivity;
import com.ed.v1.util.CommonUtil;

public class ThreeDActivity extends BaseFragmentActivity implements
		OnClickListener, OnSeekBarChangeListener {
	private GridView gridView1; // 网格显示缩略图
	private final int IMAGE_OPEN = 1; // 打开图片标记
	private String pathImage; // 选择图片路径
	private ArrayList<String> data;
	private ArrayList<String> inputData;;
	private Bitmap bmp;
	private ImageView addview;
	private GridAdapter grideAdapter;
	@Res(R.id.itemlv)
	LinearLayout itemlv;
	ArrayList<SeekBar> seekbars;
	@Res(R.id.three_d_cancel)
	ImageView three_d_cancel;
	@Res(R.id.three_d_save)
	ImageView three_d_save;
	@Res(R.id.lvtext1)
	RelativeLayout lvtext1;
	@Res(R.id.lvtext2)
	RelativeLayout lvtext2;
	@Res(R.id.lvtext3)
	RelativeLayout lvtext3;
	@Res(R.id.lvtext4)
	RelativeLayout lvtext4;
	@Res(R.id.lvtext5)
	RelativeLayout lvtext5;
	@Res(R.id.lvtext6)
	RelativeLayout lvtext6;
	View currentV = null;;
	@Res(R.id.seekBar1)
	SeekBar seekBar1;
	@Res(R.id.seekBar2)
	SeekBar seekBar2;
	@Res(R.id.seekBar3)
	SeekBar seekBar3;
	@Res(R.id.seekBar4)
	SeekBar seekBar4;
	@Res(R.id.seekBar5)
	SeekBar seekBar5;
	@Res(R.id.seekBar6)
	SeekBar seekBar6;
	@Res(R.id.text1)
	TextView text1;
	@Res(R.id.text2)
	TextView text2;
	@Res(R.id.text3)
	TextView text3;
	@Res(R.id.text4)
	TextView text4;
	@Res(R.id.text5)
	TextView text5;
	@Res(R.id.text6)
	TextView text6;
	@Res(R.id.textsize1)
	TextView textsize1;
	@Res(R.id.textsize2)
	TextView textsize2;
	@Res(R.id.textsize3)
	TextView textsize3;
	@Res(R.id.textsize4)
	TextView textsize4;
	@Res(R.id.textsize5)
	TextView textsize5;
	@Res(R.id.textsize6)
	TextView textsize6;
	private int ablecolor;
	private int disablecolor;
	//初始化mm值为1，表示此时加载tag标签还没到最大上项53.当加载到最大上项时，mm为0.用于解决当到达上第5个tag时，不能响应select状态的bug
	private int mm=1;
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_three_d;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(this);
		ablecolor = getResources().getColor(R.color.able);
		disablecolor = getResources().getColor(R.color.black_text);
		seekbars = new ArrayList<SeekBar>();
		seekbars.add(seekBar1);
		seekbars.add(seekBar2);
		seekbars.add(seekBar3);
		seekbars.add(seekBar4);
		seekbars.add(seekBar5);
		seekbars.add(seekBar6);

		seekBar1.setOnSeekBarChangeListener(this);
		seekBar2.setOnSeekBarChangeListener(this);
		seekBar3.setOnSeekBarChangeListener(this);
		seekBar4.setOnSeekBarChangeListener(this);
		seekBar5.setOnSeekBarChangeListener(this);
		seekBar6.setOnSeekBarChangeListener(this);
		three_d_cancel.setOnClickListener(this);
		three_d_save.setOnClickListener(this);
		for (int i = 0; i < 6; i++) {
			initSeekBar(seekbars.get(i));
		}
		initAddImag();
	}

	private void initSeekBar(SeekBar seekbar) {
		switch (seekbar.getId()) {
		case R.id.seekBar5:
			seekbar.setMax(200);
			break;
		default:
			seekbar.setMax(100);
			break;
		}
	}

	private void initAddImag() {
		// TODO Auto-generated method stub
		// 获取控件对象
		gridView1 = (GridView) findViewById(R.id.gridView1);
		data = new ArrayList<String>();
		inputData = new ArrayList<String>();
		data.add("add");
		inputData.add("add");
		grideAdapter = new GridAdapter();
		gridView1.setAdapter(grideAdapter);
		gridView1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				gridView1.setFocusable(true);
				gridView1.setFocusableInTouchMode(true);
				gridView1.requestFocus();
				itemlv.setVisibility(View.VISIBLE);
				return false;
			}
		});

		gridView1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (position == data.size() - 1) { // 点击图片位置为+
					
					data.add(data.size() - 1, "unselected");
					inputData.add(inputData.size() - 1, "-1");
					// data.add("add");
					// 初始化tag时没有输入过文字，默认为-1
                    if(data.size()==6){
                    	data.remove(5);
                    	inputData.remove(5);
                    	mm=0;
					}
					
					grideAdapter.notifyDataSetChanged();
				} else {

				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.three_d_cancel:
			this.finish();
			break;
		case R.id.three_d_save:
			this.finish();
			break;
		default:
			break;
		}
	}

	public class GridAdapter extends BaseAdapter {
		ViewHold viewhold = null;
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			View contentview = convertView;
			
			if (contentview == null) {
				contentview = LayoutInflater.from(ThreeDActivity.this).inflate(
						R.layout.item_add_three_d, null);
				viewhold = new ViewHold();
				viewhold.addview = (ImageView) contentview
						.findViewById(R.id.addView);
				viewhold.editView1 = (EditText) contentview
						.findViewById(R.id.editView1);
				// 设置标志，说明是刚创建的标签，没有编辑过
				viewhold.editView1.setTag("first");
				contentview.setTag(viewhold);

			} else {
				viewhold = (ViewHold) contentview.getTag();
			}
			if (data.get(position).equals("add")) {
				viewhold.addview.setVisibility(View.VISIBLE);
				viewhold.editView1.setVisibility(View.GONE);
			} else {
				String ss = data.get(position);
				if (data.get(position).equals("selected")) {
					viewhold.editView1.setSelected(true);
				} else {
					viewhold.editView1.setSelected(false);
				}
				viewhold.editView1
						.setOnFocusChangeListener(new OnFocusChangeListener() {

							

							@Override
							public void onFocusChange(View v, boolean hasFocus) {
								// TODO Auto-generated method stub
								EditText ed = (EditText) v;
								String tag = (String) ed.getTag();
								if (!hasFocus) {
									if (tag.equals("first")) {
										//ed.setOnKeyListener(null);
										InputMethodManager imm = (InputMethodManager) getSystemService(ThreeDActivity.this.INPUT_METHOD_SERVICE);
										imm.hideSoftInputFromWindow(ed.getWindowToken(), 0);
										ed.setInputType(0);										
										ed.setTag("nofirst");
									}

								} else {
									if (!tag.equals("first")) {
										for (int i = 0; i < data.size()-mm; i++) {
											data.set(i, "unselected");
										}
										data.set(position, "selected");

										grideAdapter.notifyDataSetChanged();
										
									} 
								}
							}
						});
				viewhold.addview.setVisibility(View.GONE);
				viewhold.editView1.setVisibility(View.VISIBLE);
				if (!inputData.get(position).equals("-1")) {
					//viewhold.editView1.setText(inputData.get(position));

				}

			}
			return contentview;
		}

	}

	private class ViewHold {
		ImageView addview;
		EditText editView1;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		switch (seekBar.getId()) {
		case R.id.seekBar1:
			switch (progress) {
			case 20:
				textsize1.setText("A");
				break;
			case 40:
				textsize1.setText("B");
				break;
			case 60:
				textsize1.setText("C");
				break;
			case 80:
				textsize1.setText("D");
				break;
			case 100:
				textsize1.setText("E");
				break;
			default:
				break;
			}
			break;
		case R.id.seekBar2:
			textsize2.setText(progress + "cm");
			break;
		case R.id.seekBar3:
			textsize3.setText(progress + "cm");
			break;
		case R.id.seekBar4:
			textsize4.setText(progress + "cm");
			break;
		case R.id.seekBar5:
			textsize5.setText(progress + "cm");
			break;
		case R.id.seekBar6:
			textsize6.setText(progress + "kg");
			break;
		default:
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		switch (seekBar.getId()) {
		case R.id.seekBar1:
			text1.setTextColor(ablecolor);
			textsize1.setTextColor(ablecolor);
			break;
		case R.id.seekBar2:
			text2.setTextColor(ablecolor);
			textsize2.setTextColor(ablecolor);
			break;
		case R.id.seekBar3:
			text3.setTextColor(ablecolor);
			textsize3.setTextColor(ablecolor);
			break;
		case R.id.seekBar4:
			text4.setTextColor(ablecolor);
			textsize4.setTextColor(ablecolor);
			break;
		case R.id.seekBar5:
			text5.setTextColor(ablecolor);
			textsize5.setTextColor(ablecolor);
			break;
		case R.id.seekBar6:
			text6.setTextColor(ablecolor);
			textsize6.setTextColor(ablecolor);
			break;
		default:
			break;
		}
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		switch (seekBar.getId()) {
		case R.id.seekBar1:
			text1.setTextColor(disablecolor);
			textsize1.setTextColor(disablecolor);
			break;
		case R.id.seekBar2:
			text2.setTextColor(disablecolor);
			textsize2.setTextColor(disablecolor);
			break;
		case R.id.seekBar3:
			text3.setTextColor(disablecolor);
			textsize3.setTextColor(disablecolor);
			break;
		case R.id.seekBar4:
			text4.setTextColor(disablecolor);
			textsize4.setTextColor(disablecolor);
			break;
		case R.id.seekBar5:
			text5.setTextColor(disablecolor);
			textsize5.setTextColor(disablecolor);
			break;
		case R.id.seekBar6:
			text6.setTextColor(disablecolor);
			textsize6.setTextColor(disablecolor);
			break;
		default:
			break;
		}
	}
}
