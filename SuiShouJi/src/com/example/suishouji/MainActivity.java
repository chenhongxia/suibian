package com.example.suishouji;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.suishouji.base.utils.ToastUtil;
import com.example.suishouji.control.AreaSelectPopupWindow;
import com.example.suishouji.control.AreaSelectPopupWindow.OnAreaSelectListener;
import com.example.suishouji.control.CustomDialogControl;




import com.example.suishouji.control.DatePickerControl;
import com.example.suishouji.entity.AreasModel;
import com.example.suishouji.view.trees.bean.MyNodeBean;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnAreaSelectListener {

	private EditText edMoney;
	private LinearLayout llyClass;
	private TextView tvClass;
	private TextView tvChildClass;
	private LinearLayout llyMoneyStyle;
	private TextView tvMoneyStyle;
	private EditText edTips;
	private CustomDialogControl customDialogControl;
	private DatePickerControl datePickerControl;
	private AreaSelectPopupWindow areaSelectPopupWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setView();
		setValue();
		setListener();
	}

	private void setView() {
		edMoney = (EditText)findViewById(R.id.ed_money);
		llyClass = (LinearLayout)findViewById(R.id.lly_class);
		tvClass = (TextView)findViewById(R.id.tv_class);
		tvChildClass = (TextView)findViewById(R.id.tv_child_class);
		llyMoneyStyle = (LinearLayout)findViewById(R.id.lly_money_style);
		tvMoneyStyle = (TextView)findViewById(R.id.tv_money_style);
		edTips = (EditText)findViewById(R.id.ed_tips);
		
	}

	private void setValue() {
		customDialogControl = new CustomDialogControl();
		datePickerControl = new DatePickerControl(this);
		
		areaSelectPopupWindow= new AreaSelectPopupWindow(this);
		areaSelectPopupWindow.setData(initDatas());
		areaSelectPopupWindow.setOnAreaSelectListener(this);
		
	}

	private void setListener() {
		WidgetOnClickListener listener = new WidgetOnClickListener();
		llyClass.setOnClickListener(listener);
		llyMoneyStyle.setOnClickListener(listener);
		
	}
	
	private class WidgetOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.lly_class){
				/*customDialogControl.showSelectCustomControl(MainActivity.this, customDialogControl.initData());*/
				/*datePickerControl.showDatePickerDialog();*/
				areaSelectPopupWindow.showAtBottom(v, 0, 0);
			}
			
		}
		
	}
	
	public void setTimeTilte(String value){
		tvClass.setText(value);
	}

	@Override
	public void areaSelectDone(AreasModel province, AreasModel city,
			AreasModel district) {
		ToastUtil.ToastLengthLong(MainActivity.this, province.getName() + city.getName() + district.getName());
		
	}
	
	
	private List<AreasModel> initDatas(){
		List<AreasModel> list = new ArrayList<AreasModel>();
		int id = 3;
		for(int j = 0;j<2;j++){
			AreasModel parentbean = null ;
			if(j == 0){
				 parentbean = new AreasModel(j+1, 0, "福建省");
			}else if(j == 1){
				 parentbean = new AreasModel(j+1, 0, "广东省");
			}
		
			list.add(parentbean);
			for(int  i =0;i<5;i++){
				AreasModel bean = new AreasModel(id, j+1, "第"+(j+1)+"父亲第"+i+"跳数据");
				list.add(bean);
				id++;
			}	
		}
		return list;
	} 
	
}
