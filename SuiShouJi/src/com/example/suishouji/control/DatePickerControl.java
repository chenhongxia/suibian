package com.example.suishouji.control;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.example.suishouji.R;
import com.example.suishouji.base.utils.ToastUtil;
import com.example.suishouji.view.slidedatetimepicker.SlideDateTimeListener;
import com.example.suishouji.view.slidedatetimepicker.SlideDateTimePicker;

public class DatePickerControl {

	private long timingTime;
	private long endTime;
	
	private Context mContext;
	


	public DatePickerControl(Context mContext) {
		super();
		this.mContext = mContext;
	}

	/**
	 * 
	 * @description: 显示日期选择对话框
	 * @date: 2015-5-27 下午8:36:30
	 * @author: yems
	 */
	public void showDatePickerDialog()
	{
		new SlideDateTimePicker.Builder(((FragmentActivity) mContext).getSupportFragmentManager()).setListener(listener).setInitialDate(new Date(endTime)).setMinDate(new Date(System.currentTimeMillis())).setIs24HourTime(true).setTitle(mContext.getString(R.string.str_time_set_finish_date)).build().show();
	}

	protected void showTimingPickerDialog()
	{
		new SlideDateTimePicker.Builder(((FragmentActivity) mContext).getSupportFragmentManager()).setListener(timingListener).setInitialDate(new Date(timingTime)).setMinDate(new Date(System.currentTimeMillis())).setIs24HourTime(true).setTitle(mContext.getString(R.string.str_time_set_timing_date)).build().show();
	}
	
	protected SlideDateTimeListener listener = new SlideDateTimeListener()
	{



		@Override
		public void onDateTimeSet(Date date)
		{
			endTime = date.getTime();
			ToastUtil.ToastLengthShort(mContext, "endtime ==== "+ getStringDate(endTime));
		}

		// Optional cancel listener
		@Override
		public void onDateTimeCancel()
		{

		}
	};

	protected SlideDateTimeListener timingListener = new SlideDateTimeListener()
	{


		@Override
		public void onDateTimeSet(Date date)
		{
			timingTime = date.getTime();
			ToastUtil.ToastLengthShort(mContext, "timingTime ==== "+ getStringDate(timingTime));
		}

		// Optional cancel listener
		@Override
		public void onDateTimeCancel()
		{

		}
	};

	/**
	 * @param date
	 * @return
	 * @description: 将长时间格式字符串转换为时间 yyyy-MM-dd
	 * @date: 2015-5-27 下午8:24:18
	 * @author: yems
	 */
	public static String getStringDate(Long date)
	{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}

}
