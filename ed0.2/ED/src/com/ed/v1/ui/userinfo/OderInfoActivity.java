package com.ed.v1.ui.userinfo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.base.BaseFragmentActivity;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.net.bean.OrderDetailBean;
import com.ed.v1.net.bean.OrderGoodsBean;
import com.ed.v1.util.CommonUtil;


/**
 * @author djxiao
 * @create 2016-4-18下午4:21:09
 * @描述： 订单详情
 */
public class OderInfoActivity extends BaseFragmentActivity implements
OnClickListener{
	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	private TextView tvPrediteTime;//预计到达时间
/*	@Res(R.id.dom1)
	private TextView dom1;//圆点点
	@Res(R.id.dom2)
	private TextView dom2;
	@Res(R.id.dom3)
	private TextView dom3;
	@Res(R.id.dom4)
	private TextView dom4;
	@Res(R.id.dom5)
	private TextView dom5;*/
	private View line11;//圆点点之间的线
	private View line12;
	private View line21;
	private View line22;
	private View line31;
	private View line32;
	private View line41;
	private View line42;
	private TextView tvOrderStep1;//圆点点下面的说明文字
	private TextView tvOrderStep2;
	private TextView tvOrderStep3;
	private TextView tvOrderStep4;
	private TextView tvOrderStep5;
	private TextView tvUserName;//收件人姓名
	private TextView tvUserPhone;//收件人电话
	private TextView tvUserAddress;//收件人地址
	private LinearLayout llOrderAccount;//商品的总数
	private ViewPager mpager;//
	private GridView grid;
	private TextView tvRemarkMsg;//备注信息
	private TextView tvDiscountMsg;//优惠信息
	private TextView tvInvoiceMsg;//发票信息
	
	private GeneralPagerAdapter pageApdater;//商品信息适配器
	private OrderDetailAdapter gridAdapter;//grid适配器
	
	private LayoutInflater inflater;
	
	private ArrayList<OrderGoodsBean> goodsBean = new ArrayList<OrderGoodsBean>();

	@Override
	protected int getContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_order_detail;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		CommonUtil.setTranslucentStatus(this);
		/**
		 * 伪造的数据
		 */
		initTitile();
		ArrayList< OrderDetailBean> detailBean1 = new ArrayList<OrderDetailBean>();
		detailBean1.add(new OrderDetailBean(R.drawable.order_drapery, "1200", "布料名称"));
		detailBean1.add(new OrderDetailBean(R.drawable.order_accessories, "630", "配饰名称"));
		
		ArrayList< OrderDetailBean> detailBean2 = new ArrayList<OrderDetailBean>();
		detailBean2.add(new OrderDetailBean(R.drawable.order_accessories, "555", "配饰名称"));
		
		ArrayList< OrderDetailBean> detailBean3 = new ArrayList<OrderDetailBean>();
		detailBean3.add(new OrderDetailBean(R.drawable.order_drapery, "666", "布料名称"));
		
		goodsBean.add(new OrderGoodsBean(R.drawable.order_photo, "耐克 2016 夏季", "¥3325", 
				"北京站", "朴东旭", "连衣裙", "170cm/L码（罩杯c/胸围32/腰围26/臀围32）", 
				"包邮", "¥3325", "43325", detailBean1));
		goodsBean.add(new OrderGoodsBean(R.drawable.order_goos, "阿迪 2015 夏季", "¥332", 
				"上海站", "黄晓明", "连衣裙", "175cm/L码（罩杯c/胸围44/腰围66/臀围55）", 
				"包邮", "¥332", "4332", detailBean2));
		goodsBean.add(new OrderGoodsBean(R.drawable.order_photo, "李宁 2016 夏季", "¥3325", 
				"深圳站", "小龙女", "裤子", "170cm/L码（罩杯c/胸围32/腰围26/臀围32）", 
				"包邮", "¥3325", "43325", detailBean3));
	/*******************************************************************************************************/	
		
		inflater = LayoutInflater.from(this);
		registerComponent();
	}

	private void registerComponent(){
		tvPrediteTime = (TextView) findViewById(R.id.tv_predite_time);//预计到达时间

		line11 = findViewById(R.id.line11);//圆点点之间的线
		line12 = findViewById(R.id.line12);
		line21 = findViewById(R.id.line21);
		line22 = findViewById(R.id.line22);
		line31 = findViewById(R.id.line31);
		line32 = findViewById(R.id.line32);
		line41 = findViewById(R.id.line41);
		line42 = findViewById(R.id.line42);
		tvOrderStep1 = (TextView) findViewById(R.id.tv_order_step1);//圆点点下面的说明文字
		tvOrderStep2 = (TextView) findViewById(R.id.tv_order_step2);
		tvOrderStep3 = (TextView) findViewById(R.id.tv_order_step3);
		tvOrderStep4 = (TextView) findViewById(R.id.tv_order_step4);
		tvOrderStep5 = (TextView) findViewById(R.id.tv_order_step5);
		tvUserName = (TextView) findViewById(R.id.tv_user_name);//收件人姓名
		tvUserPhone = (TextView) findViewById(R.id.tv_user_phone);//收件人电话
		tvUserAddress = (TextView) findViewById(R.id.tv_user_address);//收件人地址
		llOrderAccount = (LinearLayout) findViewById(R.id.line_order_account);//商品的总数
		mpager = (ViewPager) findViewById(R.id.vPager);//
		grid = (GridView) findViewById(R.id.grid);
		tvRemarkMsg = (TextView) findViewById(R.id.tv_remark_message);//备注信息
		tvDiscountMsg = (TextView) findViewById(R.id.tv_disccount_message);//优惠信息
		tvInvoiceMsg = (TextView) findViewById(R.id.tv_invoice_message);//发票信息
		
		pageApdater = new GeneralPagerAdapter();
		mpager.setOnPageChangeListener(pageListener);
		mpager.setAdapter(pageApdater);
		
		//加载grid数据
		gridAdapter = new OrderDetailAdapter(this);
		grid.setAdapter(gridAdapter);
		
		//添加商品详情
		for (int i = 0; i < 3; i++) {
			View v = inflater.inflate(R.layout.page_order_detail, null);
			
			ImageView imgPhoto = (ImageView) v.findViewById(R.id.img_order_photo);//图片
			TextView tvOrderDesc = (TextView) v.findViewById(R.id.tv_order_desc);//描述
			TextView tvOrderAmout = (TextView) v.findViewById(R.id.tv_order_amount);//价格
			TextView tvOrderPlace = (TextView) v.findViewById(R.id.tv_order_place);//地点
			TextView tvOrderName = (TextView) v.findViewById(R.id.tv_order_sender_name);//名字
			TextView tvOrderType = (TextView) v.findViewById(R.id.tv_order_type);//类型
			TextView tvOrderInfo = (TextView) v.findViewById(R.id.tv_order_info);//具体信息，腰围之类的
			TextView tvNewMoney = (TextView) v.findViewById(R.id.tv_new_money);//折后价格
			TextView tvOldMoney = (TextView) v.findViewById(R.id.tv_old_money);//原价
			
			imgPhoto.setImageResource(goodsBean.get(i).getPhoto());
			tvOrderDesc.setText(goodsBean.get(i).getDesc());
			tvOrderAmout.setText(goodsBean.get(i).getAccount());
			tvOrderPlace.setText(goodsBean.get(i).getPlace());
			tvOrderName.setText(goodsBean.get(i).getName());
			tvOrderType.setText(goodsBean.get(i).getType());
			tvOrderInfo.setText(goodsBean.get(i).getDimens());
			tvNewMoney.setText(goodsBean.get(i).getNewMoney());
			tvOldMoney.setText(goodsBean.get(i).getOldMoney());
			
			pageApdater.addView(v);
		}
		
		gridAdapter.addItems(goodsBean.get(0).getDetailBean());
		
		//加载商品一、商品二、商品三
		for (int i = 0; i < pageApdater.getCount(); i++) {
			
			TextView tvOrder = new TextView(this);
			tvOrder.setGravity(Gravity.CENTER);
			tvOrder.setPadding(16, 5, 16, 5);
			tvOrder.setText("商品"+(i+1));
			tvOrder.setTag(i);
			if (i == 0) {//刚进来显示第一个为选中状态
				tvOrder.setBackgroundResource(R.color.able);
			}else{
				tvOrder.setBackgroundResource(R.color.gray);
			}
			LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(16, 0, 0, 0);
			llOrderAccount.addView(tvOrder,params);
			
			tvOrder.setOnClickListener(clickListener);
			
		}
		
		
		
		
		
	}
	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("订单详情");
		mBtnBack.setOnClickListener(this);

	}
	/**
	 * @author djxiao
	 * @param index
	 * @描述：  监听滑动的page的时候上面的商品一、商品二跟着变得
	 */
	private void onPageChangeAction(int index){
		int count = pageApdater.getCount();
		for (int i = 0; i < count; i++) {
			TextView tv = (TextView) llOrderAccount.getChildAt(i);
			if(i == index){
				tv.setBackgroundResource(R.color.able);
			}else{
				tv.setBackgroundResource(R.color.gray);
			}
		}
		
	}
	
	/**
	 * 为pageView增加监听事件
	 */
	private OnPageChangeListener pageListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int index) {
			onPageChangeAction(index);
			
			gridAdapter.clear();
			gridAdapter.addItems(goodsBean.get(index).getDetailBean());
			
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
			
		}
	};
	
	/**
	 * 为商品一、商品二等增加监听事件
	 */
	private OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int index = (Integer) v.getTag();
			mpager.setCurrentItem(index);
			
		}
	};


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btnBack:
			finish();
			break;
	
		}
	}

	
}
