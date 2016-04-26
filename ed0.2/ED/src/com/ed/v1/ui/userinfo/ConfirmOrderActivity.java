package com.ed.v1.ui.userinfo;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ed.v1.R;
import com.ed.v1.common.viewholder.Res;
import com.ed.v1.util.CommonUtil;

/**
 * 
 */

public class ConfirmOrderActivity extends Activity implements OnClickListener {

	private View vBack;              // ����
	private View vInputAddress;      // ��д�ջ���ַ
	private View vInvoiceOn;         // ����Ʊ����
	private View vInvoiceOff;        // ������Ʊ����
	private TextView txvUnitPrice;   // ����
	private TextView txvFreight;     // �˷�
	private TextView txvSubTotal;    // С��
	private TextView txvCoupon;      // �Ż�ȯ
	private TextView txvTotalPrice;  // �ϼ�
	private TextView txvSubmit;      // �ύ����
	private EditText edtMessage;     // ����
	private EditText edtInvoice;     // ��Ʊ̧ͷ
	private ImageView imgInvoice;    // ��Ʊ
	private RadioGroup radioGroup;   // 
	private RadioButton rbPersonal;  // ����
	private RadioButton rbCompany;   // ��ҵ
	private ListView listview;
	
	private AlertDialog altDlgCreateAddress;
	private AlertDialog altDlgNoGoods;
	
	private GoodAdapter adapter;
	private ArrayList<Good> list;
	private boolean isInvoiceOn;
	private boolean isInvoicePersonal;

	@Res(R.id.nav_title)
	TextView mText_Titleinfo;
	@Res(R.id.info_finish)
	TextView mText_TitleFinish;
	@Res(R.id.btnBack)
	LinearLayout mBtnBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_order);
		CommonUtil.setTranslucentStatus(this);
		initTitile();

		init_ui();
		set_listener();
		
		isInvoiceOn = false;
		imgInvoice.setBackgroundResource(R.drawable.invoice_off);
		
		list = new ArrayList<Good>();
		Good good = new Good();
		good.setName("test beautiful clothes");
		good.setNumber("1");
		list.add(good);
	}
	private void initTitile() {
		// TODO Auto-generated method stub
		mText_TitleFinish.setOnClickListener(this);
		mText_Titleinfo.setText("确认订单");
		mBtnBack.setOnClickListener(this);

	}
	private void init_ui() {
		vInputAddress = findViewById(R.id.activity_confirm_order_v_input_address);
		vInvoiceOn = findViewById(R.id.activity_confirm_order_v_invoice_on);
		vInvoiceOff = findViewById(R.id.activity_confirm_order_v_invoice_off);
		
		txvUnitPrice = (TextView) findViewById(R.id.activity_confirm_order_txv_unitprice);
		txvFreight = (TextView) findViewById(R.id.activity_confirm_order_txv_freight);
		txvSubTotal = (TextView) findViewById(R.id.activity_confirm_order_txv_subtotal);
		txvCoupon = (TextView) findViewById(R.id.activity_confirm_order_txv_coupon);
		txvTotalPrice = (TextView) findViewById(R.id.activity_confirm_order_txv_totalprice);
		txvSubmit = (TextView) findViewById(R.id.activity_confirm_order_txv_submit);
		
		edtMessage = (EditText) findViewById(R.id.activity_confirm_order_edt_message);
		edtInvoice = (EditText) findViewById(R.id.activity_confirm_order_edt_invoice_name);
		
		imgInvoice = (ImageView) findViewById(R.id.activity_confirm_order_img_invoice);
		
		radioGroup = (RadioGroup) findViewById(R.id.activity_confirm_order_radiogroup);
		rbPersonal = (RadioButton) findViewById(R.id.activity_confirm_order_rb_personal);
		rbCompany = (RadioButton) findViewById(R.id.activity_confirm_order_rb_company);
	}
	
	private void set_listener() {
		vBack.setOnClickListener(this);
		vInputAddress.setOnClickListener(this);
		txvCoupon.setOnClickListener(this);
		imgInvoice.setOnClickListener(this);
		txvSubmit.setOnClickListener(this);
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup froup, int id) {
				switch (id) {
				case R.id.activity_confirm_order_rb_personal:
					isInvoicePersonal = true;
					showMsgS("删除");
					break;
				case R.id.activity_confirm_order_rb_company:
					isInvoicePersonal = false;
					showMsgS("删除");
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	
		case R.id.activity_confirm_order_v_input_address:  // ��д��ַ
			break;
		case R.id.activity_confirm_order_txv_coupon:  // �Ż�ȯ
			break;
		case R.id.activity_confirm_order_img_invoice:  // ����Ʊ
			if (isInvoiceOn) {
				isInvoiceOn = false;
				imgInvoice.setBackgroundResource(R.drawable.invoice_off);
				vInvoiceOn.setVisibility(View.GONE);
				vInvoiceOff.setVisibility(View.VISIBLE);
			} else {
				isInvoiceOn = true;
				imgInvoice.setBackgroundResource(R.drawable.invoice_on);
				vInvoiceOn.setVisibility(View.VISIBLE);
				vInvoiceOff.setVisibility(View.GONE);
			}
			break;
		case R.id.activity_confirm_order_txv_submit:  // �ύ����
//			function_1();
			function_2();
			break;
		case R.id.btnBack:
			finish();
		default:
			break;
		}
	}
	
	private void function_1() {
		altDlgCreateAddress = new AlertDialog.Builder(ConfirmOrderActivity.this).create();
		altDlgCreateAddress.setCancelable(true);
		altDlgCreateAddress.show();
		altDlgCreateAddress.getWindow().setContentView(R.layout.alertdialog_create_address); 
		altDlgCreateAddress.getWindow().findViewById(R.id.alertdialog_create_address_txv_create).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showMsgS("�����ַ��û�ţ�");
			}
		});
	}
	
	private void function_2() {
		altDlgNoGoods = new AlertDialog.Builder(ConfirmOrderActivity.this).create();
		altDlgNoGoods.setCancelable(true);
		altDlgNoGoods.show();
		altDlgNoGoods.getWindow().setContentView(R.layout.alertdialog_no_goods); 
		altDlgNoGoods.getWindow().findViewById(R.id.alertdialog_no_goods_txv_delete).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showMsgS("�Ƴ��ˡ���");
			}
		});
		adapter = new GoodAdapter(ConfirmOrderActivity.this, list);
		listview = (ListView)altDlgNoGoods.getWindow().findViewById(R.id.alertdialog_no_goods_listview);
		listview.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	private void showMsgS(String msg) {
		Toast.makeText(ConfirmOrderActivity.this, msg, Toast.LENGTH_SHORT).show();
	}
	
	
	public class GoodAdapter extends BaseAdapter {
		private Context context;
		private ArrayList<Good> goodList;
		private LayoutInflater inflater;
		
		public GoodAdapter(Context context,ArrayList<Good> goodList) { 
			this.goodList = goodList;
			this.context = context;
			if (context != null) {
				inflater = LayoutInflater.from(context);
			}
		}

		class ViewHolder {
			private TextView  txvName;
			private TextView  txvNumber;
			private ImageView imgGood;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.listview_item_good, null);
				holder = new ViewHolder();
				holder.txvName   = (TextView) convertView.findViewById(R.id.listview_item_good_txv_name);
				holder.txvNumber   = (TextView) convertView.findViewById(R.id.listview_item_good_txv_number);
				holder.imgGood  = (ImageView) convertView.findViewById(R.id.listview_item_good_img);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.txvName.setText(goodList.get(position).getName() == null ? "" : goodList.get(position).getName());
			holder.txvNumber.setText(goodList.get(position).getNumber());
			return convertView;
		}

		@Override
		public int getCount() {
			return goodList == null ? 0 : goodList.size();
		}

		@Override
		public Object getItem(int arg0) {
			return goodList.get(arg0);
		}
	}
}
