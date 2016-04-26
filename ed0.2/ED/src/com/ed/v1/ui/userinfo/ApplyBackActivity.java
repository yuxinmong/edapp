package com.ed.v1.ui.userinfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.common.widget.wheel.ActionSheet;
import com.ed.v1.common.widget.wheel.ActionSheet.ActionSheetListener;
import com.ed.v1.common.widget.wheel.NumericWheelAdapter;
import com.ed.v1.util.CommonUtil;
import com.ed.v1.util.FileUtil;

public class ApplyBackActivity extends BaseFragmentActivity implements
		OnClickListener {
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	@Res(R.id.infolv)
	LinearLayout infolv;
	@Res(R.id.apply_back_save)
	TextView apply_back_save;
	@Res(R.id.input_count)
	TextView input_count;
	@Res(R.id.input_view)
	EditText input_view;
	private GridView gridView1; // 网格显示缩略图
	private final int IMAGE_OPEN = 1; // 打开图片标记
	private String pathImage; // 选择图片路径
	private ArrayList<HashMap<String, Object>> imageItem;
	private SimpleAdapter simpleAdapter; // 适配器
	private HashMap<String, Object> map;
	private HashMap<String, Object> addmap;

	private String[] header = new String[] { "拍照", "从相册选择" };

	/**
	 * 跳转拍照标识
	 */
	private final int RESULT_CAPTURE_IMAGE = 1;
	public static final String action = "userImg.broadcast.action";
	/**
	 * 跳转浏览相册标识
	 */
	private int INTENT_LOAD_IMAGE = 2;

	/**
	 * 跳转图片剪辑标识
	 */
	private final int INTENT_CROP = 3;
	// 拍照后图片名
	private String takePicName = null;
	// 图片路径
	private String bigPicPath;
	private Bitmap bmp; // 导入临时图片

	private String token;
	private String imageUrl;
	private NumericWheelAdapter dayAdapter;
	private Bitmap addbmp;
	private View items_popup_content;
	private PopupWindow popupWindow;
	public static String userImage = FileUtil.getSDPath() + "/ed/cache/image/";
	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_applyback;
	}

	private void initTitile() {
		// TODO Auto-generated method stub
		
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("申请退货");
		mBtnBack.setOnClickListener(this);
		apply_back_save.setOnClickListener(this);
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(this);
		initTitile();
		initAddImag();
		initEdit();
		initLv();
	}

	private void initLv() {
		// TODO Auto-generated method stub
		getRootView().setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				v.requestFocus();
				return false;
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btnBack:
			finish();
			break;
		case R.id.apply_back_save:
			finish();
			break;
		default:
			break;
		}
	}
	private void initEdit(){
		input_view.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
					infolv.setVisibility(View.GONE);
					apply_back_save.setVisibility(View.GONE);
				}else{
					infolv.setVisibility(View.VISIBLE);
					apply_back_save.setVisibility(View.VISIBLE);


				}
			}
		});
		input_view.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int inputLengh = s.length();
				input_count.setText(inputLengh+"/200");
			
                
			}
		});
	
	}
	private void initAddImag() {
		// TODO Auto-generated method stub

		// 获取控件对象
		gridView1 = (GridView) findViewById(R.id.gridView1);

		/*
		 * 载入默认图片添加图片加号 通过适配器实现 SimpleAdapter参数imageItem为数据源
		 * R.layout.griditem_addpic为布局
		 */
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.add_img); // 加号
		imageItem = new ArrayList<HashMap<String, Object>>();
		addmap = new HashMap<String, Object>();
		addmap.put("itemImage", bmp);
		imageItem.add(addmap);
		simpleAdapter = new SimpleAdapter(this, imageItem,
				R.layout.item_show_addpic, new String[] { "itemImage" },
				new int[] { R.id.imageView1 });
		/*
		 * HashMap载入bmp图片在GridView中不显示,但是如果载入资源ID能显示 如 map.put("itemImage",
		 * R.drawable.img); 解决方法: 1.自定义继承BaseAdapter实现 2.ViewBinder()接口实现 参考
		 * http://blog.csdn.net/admin_/article/details/7257901
		 */
		simpleAdapter.setViewBinder(new ViewBinder() {
			@Override
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				// TODO Auto-generated method stub
				if (view instanceof ImageView && data instanceof Bitmap) {
					ImageView i = (ImageView) view;
					i.setImageBitmap((Bitmap) data);
					return true;
				}
				return false;
			}
		});
		gridView1.setAdapter(simpleAdapter);

		/*
		 * 监听GridView点击事件 报错:该函数必须抽象方法 故需要手动导入import android.view.View;
		 */
		gridView1.setOnItemLongClickListener(new OnItemLongClickListener() {// 设置事件监听(长按)

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {

						dialog(position);
						// Toast.makeText(MainActivity.this, "点击第" + (position +
						// 1) + " 号图片",
						return true;
						// Toast.LENGTH_SHORT).show();

					}

				});

		gridView1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (imageItem.size() == 4) { // 第一张为默认图片
					Toast.makeText(ApplyBackActivity.this, "图片数3张已满",
							Toast.LENGTH_SHORT).show();
				} else if (position == imageItem.size() - 1) { // 点击图片位置为+
					bigPicPath = userImage;
					// changeDialog();
					setTheme(R.style.ActionSheetStyleiOS7);
					showHeaderSheet();

				} else {
					poupImag(v);
				}

			}
		});
	}
	public void poupImag(View v) {
		ImageView bmpImage = (ImageView) v.findViewById(R.id.imageView1);
		items_popup_content = LayoutInflater.from(this).inflate(
				R.layout.items_popup_content, null);
		ImageView ss = (ImageView) items_popup_content.findViewById(R.id.img);
		if (bmpImage.getDrawable() != null) {
			ss.setImageDrawable(bmpImage.getDrawable());

		}
		if (popupWindow != null) {
			popupWindow.dismiss();
		}
		popupWindow = new PopupWindow(items_popup_content,
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		popupWindow.showAtLocation(getRootView(), Gravity.NO_GRAVITY, 0, 0);

	}
	/**
	 * 拍照/相册
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		File file = new File(bigPicPath);
		// 如果目标目录不存在，则创建
		if (!file.exists()) {
			file.mkdirs();
		}

		// Toast.makeText(this, bigPicPath, Toast.LENGTH_SHORT).show();
		if (requestCode == INTENT_LOAD_IMAGE && resultCode == RESULT_OK
				&& data != null) {
			Uri uri = data.getData();
			if (!TextUtils.isEmpty(uri.getAuthority())) {
				// 查询选择图片
				Cursor cursor = getContentResolver().query(uri,
						new String[] { MediaStore.Images.Media.DATA }, null,
						null, null);
				// 返回 没找到选择图片
				if (null == cursor) {
					return;
				}
				// 光标移动至开头 获取图片路径
				cursor.moveToFirst();
				pathImage = cursor.getString(cursor
						.getColumnIndex(MediaStore.Images.Media.DATA));
			}
		} else if (requestCode == RESULT_CAPTURE_IMAGE
				&& resultCode == RESULT_OK && data != null) {
			try {
				String sdStatus = Environment.getExternalStorageState();
				if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
					Log.i("TestFile",
							"SD card is not avaiable/writeable right now.");
					return;
				}
				new DateFormat();
				String name = DateFormat.format("yyyyMMdd_hhmmss",
						Calendar.getInstance(Locale.CHINA))
						+ ".jpg";
				Toast.makeText(this, name, Toast.LENGTH_LONG).show();
				Bundle bundle = data.getExtras();
				Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

				FileOutputStream b = null;
				String fileName = userImage + name;
				try {
					b = new FileOutputStream(fileName);
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					try {
						b.flush();
						b.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				pathImage = fileName;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	// 刷新图片
	@Override
	public void onResume() {
		super.onResume();
		if (!TextUtils.isEmpty(pathImage)) {
			Bitmap addbmp = BitmapFactory.decodeFile(pathImage);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", addbmp);
			imageItem.add(imageItem.size() - 1, map);
			simpleAdapter = new SimpleAdapter(this, imageItem,
					R.layout.item_show_addpic, new String[] { "itemImage" },
					new int[] { R.id.imageView1 });
			simpleAdapter.setViewBinder(new ViewBinder() {
				@Override
				public boolean setViewValue(View view, Object data,
						String textRepresentation) {
					// TODO Auto-generated method stub
					if (view instanceof ImageView && data instanceof Bitmap) {
						ImageView i = (ImageView) view;
						i.setScaleType(ImageView.ScaleType.CENTER_CROP);
						i.setImageBitmap((Bitmap) data);
						return true;
					}
					return false;
				}
			});
			gridView1.setAdapter(simpleAdapter);
			simpleAdapter.notifyDataSetChanged();
			// 刷新后释放防止手机休眠后自动添加
			pathImage = null;
		}
	}
	private void showHeaderSheet() {
		// TODO Auto-generated method stub
		ActionSheet.createBuilder(this, getSupportFragmentManager())
				.setCancelButtonTitle(R.string.messagebox_cancel)
				.setOtherButtonTitles(header[0], header[1])
				.setCancelableOnTouchOutside(true)
				.setListener(new actionSheetListener()).show();
	}

	public class actionSheetListener implements ActionSheetListener {

		@Override
		public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onOtherButtonClick(ActionSheet actionSheet, int index) {
			// TODO Auto-generated method stub
			switch (index) {
			case 0:
				Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(it, RESULT_CAPTURE_IMAGE);

				break;
			case 1:
				Intent intent2 = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent2, INTENT_LOAD_IMAGE);

				break;
			default:
				break;
			}
		}

	}

	/*
	 * Dialog对话框提示用户删除操作 position为删除图片位置
	 */
	protected void dialog(final int position) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("确认移除已添加图片吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				imageItem.remove(position);
				simpleAdapter.notifyDataSetChanged();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	private void dismissPopupWindow() {
		// TODO Auto-generated method stub
		if (popupWindow != null) {
			popupWindow.dismiss();
		}
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		dismissPopupWindow();
		super.onStop();

	}

	
}
