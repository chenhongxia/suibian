package com.example.suishouji.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

	private static BaseApplication instance;
	private Context mContext;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mContext  = this;
	}
	
	public static BaseApplication getInstance(){
		if(instance == null){
			instance = new BaseApplication();
		}
		return instance;
	}
	
	public Context getContext(){
		return mContext;
	}

}
