package com.example.suishouji.base.utils;

import com.example.suishouji.base.utils.FileUtil.FileLogUtil;

import android.content.Context;
import android.view.View;
import android.widget.Toast;


public class ToastUtil {
	/**
	 * Toast弹出信息 LENGTH_LONG
	 * 
	 * @param context
	 * @param msg
	 */
	public static void ToastLengthLong(Context context, String msg) {
		if (msg.equals("拒绝访问")) {
			return;
		}
		if (context != null) {
			FileLogUtil.writeLogtoSdcard("ToastUtil", "TAG == " + context.getClass().getSimpleName() + "  content:" + msg, true);
		}
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	/**
	 * Toast弹出信息 LENGTH_SHORT
	 * 
	 * @param context
	 * @param msg
	 */
	public static void ToastLengthShort(Context context, String msg) {
		if (msg.equals("拒绝访问")) {
			return;
		}
		if (context != null) {
			FileLogUtil.writeLogtoSdcard("ToastUtil", "TAG == " + context.getClass().getSimpleName() + "  content:" + msg, true);
		}
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

	}

	public static void ToastLengthShort(Context context, View view, int gravity) {
		Toast toast = new Toast(context);
		toast.setView(view);
		toast.setGravity(gravity, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();
	}

	public static void ToastLengthLong(Context context, View view, int gravity) {
		Toast toast = new Toast(context);
		toast.setView(view);
		toast.setGravity(gravity, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();
	}

}
