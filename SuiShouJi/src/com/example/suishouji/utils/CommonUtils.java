package com.example.suishouji.utils;

import com.example.suishouji.base.utils.ScreenUtil;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


public class CommonUtils {

	public static int[] setListViewHeight(ListView listview) {
		int widthHeight []=new int[2];
		ListAdapter adapter = listview.getAdapter();
		if(adapter.getCount() == 0){
			return widthHeight;
		}
		int headCount = listview.getHeaderViewsCount();
		int totalHeight = 0;
		View listItem = adapter.getView(headCount, null, listview);
		listItem.measure(0, 0);
		totalHeight = listItem.getMeasuredHeight() * (adapter.getCount()- headCount);
		ViewGroup.LayoutParams params = listview.getLayoutParams();
		params.height = totalHeight + listview.getDividerHeight() * (adapter.getCount() - 1-headCount) + ScreenUtil.getSizeOfDip(listview.getContext(), 10);
		listview.setLayoutParams(params);
		widthHeight [0] = params.width;
		widthHeight [1] = params.height;
		return widthHeight;
	}
}
