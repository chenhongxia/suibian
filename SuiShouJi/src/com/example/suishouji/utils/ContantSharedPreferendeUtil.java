package com.example.suishouji.utils;

import com.example.suishouji.base.utils.SharedPreferenceUtil;

import android.content.Context;

public class ContantSharedPreferendeUtil {

	
   private static final String TOKEN ="token";
   private static final String USERNAME="user_name";
   
   
   public static void setToken (Context context ,String value){
	   SharedPreferenceUtil.setStringValue(context, TOKEN, value);
   }
   
   public static String getToken(Context context){
	   return SharedPreferenceUtil.getString(context, TOKEN, "");
   }
   
   public static String getUsernName(Context context){
	   return SharedPreferenceUtil.getString(context, USERNAME, "");
   } 
   public static void setUserName(Context context ,String name){
	   SharedPreferenceUtil.setStringValue(context, USERNAME, name);
   }
}
