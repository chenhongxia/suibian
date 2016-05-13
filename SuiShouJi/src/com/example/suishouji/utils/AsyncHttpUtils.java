package com.example.suishouji.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.suishouji.base.BaseApplication;
import com.example.suishouji.base.utils.FileUtil.FileLogUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class AsyncHttpUtils {
	protected static final String TAG = AsyncHttpUtils.class.getSimpleName();

	private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	static{
		asyncHttpClient.setTimeout(1000*60);
		asyncHttpClient.setConnectTimeout(1000*10);
		/*asyncHttpClient.addHeader("token", "cdcd70d5-01d3-4933-b857-6e60cf260d93");*///澶栫綉娴嬭瘯鐜锛歝dcd70d5-01d3-4933-b857-6e60cf260d93  寮�彂鐜锛�ecc88ba9-aa95-4503-aa30-0a5d4a037266

	}
	
	public static AsyncHttpClient getAsyncHttpClient() {
		return asyncHttpClient;
	}
	
	public static void addHeader(String header, String value){
		asyncHttpClient.addHeader(header, value);
	}
	private static String stoken;
	public static String getToken(){
		return stoken;
	}
	public static void setToken(String token){
		stoken = token;
		addHeader("token", token);
	}
	
	public static void post(String url, RequestParams params,TextHttpResponseHandler responseHandler){
		asyncHttpClient.post(url, params, responseHandler);
	}
	
	public static void post(final String url,final RequestParams params,final Class classz,final HttpResponseHandler responseHandler){
		String token  = ContantSharedPreferendeUtil.getToken(BaseApplication.getInstance().getContext());
		if(!TextUtils.isEmpty(token)){
			setToken(token);
		}
		asyncHttpClient.post(url, params, new TextHttpResponseHandler() {
			@Override
			public void onFinish() {
				super.onFinish();
				responseHandler.onFinish();
			}

			@Override
			public void onStart() {
				super.onStart();
				if(params!=null){
					Log.d(TAG, "[onStart]"+url+":"+params.toString());
					FileLogUtil.writeLogtoSdcard(TAG, "[onStart]"+url+":"+params.toString(), true);
				}
				responseHandler.onStart();
			}

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(int code, Header[] header, String result) {
				Log.d(TAG, "[onSuccess]" + url + ":" + result);
				FileLogUtil.writeLogtoSdcard(TAG, "[onSuccess]" + url + ":" + result, true);
				try {
					Object obj = new Gson().fromJson(result, classz);
					responseHandler.onSuccess(code,obj);
				}catch (Exception e){
					responseHandler.onFailure(code,"");
				}


			}
			
			@Override
			public void onFailure(int code, Header[] header, String result, Throwable throwable) {
				Log.d(TAG, "[onFailure]"+result);
				FileLogUtil.writeLogtoSdcard(TAG, "[onFailure]"+result, true);
				responseHandler.onFailure(code,result);
			}
		});
		
	}

	public static void get(final String url,final RequestParams params,final Class classz,final HttpResponseHandler responseHandler){
		String token  = ContantSharedPreferendeUtil.getToken(BaseApplication.getInstance().getContext());
		if(!TextUtils.isEmpty(token)){
			setToken(token);
		}
		asyncHttpClient.get(url, params, new TextHttpResponseHandler() {
			@Override
			public void onFinish() {
				super.onFinish();
				responseHandler.onFinish();
			}

			@Override
			public void onStart() {
				super.onStart();
				if(params!=null){
					Log.d(TAG, "[onStart]"+url+":"+params.toString());
					FileLogUtil.writeLogtoSdcard(TAG, "[onStart]"+url+":"+params.toString(), true);
				}
				responseHandler.onStart();
			}

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(int code, Header[] header, String result) {
				Log.d(TAG, "[onSuccess]"+url+":"+result);
				FileLogUtil.writeLogtoSdcard(TAG, "[onSuccess]"+url+":"+result, true);
				try {
					responseHandler.onSuccess(code, new Gson().fromJson(result, classz));
				} catch (Exception e) {
					Log.d(TAG, "[Exception]"+e.toString());
				}
			}

			@Override
			public void onFailure(int code, Header[] header, String result, Throwable throwable) {
				Log.d(TAG, "[onFailure]"+result);
				FileLogUtil.writeLogtoSdcard(TAG, "[onFailure]"+result, true);
				responseHandler.onFailure(code,result);
			}
		});

	}


}
