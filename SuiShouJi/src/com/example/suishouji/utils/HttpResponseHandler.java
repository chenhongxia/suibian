package com.example.suishouji.utils;

public interface HttpResponseHandler<T extends Object>{
	void onSuccess(int httpCode,T obj);
	void onFailure(int httpCode,String resultMsg);
	void onFinish();
	void onStart();
}
