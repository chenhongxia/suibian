package com.example.suishouji;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.suishouji.control.CustomDialogControl;




import com.example.suishouji.control.DatePickerControl;

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

public class MainActivity extends FragmentActivity {

	private EditText edMoney;
	private LinearLayout llyClass;
	private TextView tvClass;
	private TextView tvChildClass;
	private LinearLayout llyMoneyStyle;
	private TextView tvMoneyStyle;
	private EditText edTips;
	private CustomDialogControl customDialogControl;
	private DatePickerControl datePickerControl;

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
				datePickerControl.showDatePickerDialog();
			}
			
		}
		
	}
	
	
	
}
