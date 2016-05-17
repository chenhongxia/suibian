/**
 * 
 */
package com.example.suishouji.control;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.suishouji.R;
import com.example.suishouji.entity.AreasModel;
import com.example.suishouji.view.wheel.OnWheelChangedListener;
import com.example.suishouji.view.wheel.WheelView;
import com.example.suishouji.view.wheel.adapter.ArrayWheelAdapter;

/**
 * @description:
 * @date: 2015-7-2 下午2:55:40
 * @author: huangqp
 */
public class AreaSelectPopupWindow extends PopupWindow implements OnDismissListener , OnClickListener , OnWheelChangedListener
{
	public static final String INIT_COMPLETE_ACTION = "com.txtw.green_one.init_complete";
	private WheelView wvProvince; // 省滚轮
	private WheelView wvCity; // 市滚轮
	private WheelView wvDistrict; // 区滚轮
	private TextView tvSelectDone;// 选择完成

	private List<AreasModel> mProvinceDatas; // 所有省
	private Map<Integer, List<AreasModel>> mCitisDatasMap = new HashMap<Integer, List<AreasModel>>(); // key-省，value-市
	private Map<Integer, List<AreasModel>> mDistrictDatasMap = new HashMap<Integer, List<AreasModel>>(); // key-市，value-区

	private AreasModel mCurrentProvince; // 当前省
	private AreasModel mCurrentCity;// 当前市
	private AreasModel mCurrentDistrict;// 当前区

	private Activity activity;
	private View mMenuView;
	private List<AreasModel> allProviceList = new ArrayList<AreasModel>();

	public boolean initCompleted = false;

	public AreaSelectPopupWindow(Activity activity)
	{

		super();
		this.activity = activity;
		mMenuView = View.inflate(activity, R.layout.area_select_popupwindow, null);
		this.setContentView(mMenuView);
		// 点击屏幕其他地方（或者按下返回键）关闭popuwindow
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SharePopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		setFocusable(true);
		setUpAnim();
		setOnDismissListener(this);
		setBackgroundDrawable(new PaintDrawable(Color.TRANSPARENT));
		setView(mMenuView);
		setListener();
		mMenuView.setOnTouchListener(new OnTouchListener()
		{

			public boolean onTouch(View v, MotionEvent event)
			{

				int height = mMenuView.findViewById(R.id.rl_menu).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP)
				{
					if (y < height)
					{
						dismiss();
					}
				}
				return true;
			}

		});
	}

	private void setView(View view)
	{
		wvProvince = (WheelView) view.findViewById(R.id.wv_province_select);
		wvCity = (WheelView) view.findViewById(R.id.wv_city_select);
		wvDistrict = (WheelView) view.findViewById(R.id.wv_district_select);
		tvSelectDone = (TextView) view.findViewById(R.id.tv_select_done);
	}


	public void setData(List<AreasModel> provinceList){
		if (provinceList == null ||provinceList.size() <= 0 )
			return;
		allProviceList = provinceList;
		setValue(getAreasByParentId(provinceList,0));
	}

	private List<AreasModel> getAreasByParentId(List<AreasModel> provinceList,int parentId){
		List<AreasModel> lists = new ArrayList<AreasModel>();
		for (int i = 0; i < provinceList.size(); i++) {
			if(provinceList.get(i).getParentId() == parentId)
				lists.add(provinceList.get(i));
		}
		return lists;

	}
	public void setValue(List<AreasModel> provinceList)
	{
		Log.i("SchoolSelectActivity","provinceList) === " +provinceList.size());
		if (provinceList != null && provinceList.size() > 0) // 本地有数据
		{
			initDatas(provinceList);
			setUpDatas();
		}
	}

	private void setUpAnim()
	{
		setAnimationStyle(R.style.popWindowAnim);
	}

	private void setListener()
	{
		tvSelectDone.setOnClickListener(this);
		wvProvince.addChangingListener(this);
		wvCity.addChangingListener(this);
		wvDistrict.addChangingListener(this);
	}

	@Override
	public void onDismiss()
	{
		// popwindow对话框消失后，恢复背景透明度
		backgroundAlpha(1.0f);
	}

	private void backgroundAlpha(float bgAlpha)
	{
		WindowManager.LayoutParams params = activity.getWindow().getAttributes();
		params.alpha = bgAlpha;
		activity.getWindow().setAttributes(params);
	}

	public void showAtBottom(View parent, int offsetX, int offsetY)
	{
		showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, offsetX, offsetY);
		// 修改背景activity的透明度
		backgroundAlpha(0.7f);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.tv_select_done:
			if (areaSelectListener != null)
			{
				if (mCurrentProvince != null && mCurrentCity != null && mCurrentDistrict != null)
					areaSelectListener.areaSelectDone(mCurrentProvince, mCurrentCity, mCurrentDistrict);
			}
			dismiss();
			break;
		}
	}

	private void initDatas(List<AreasModel> provinceList)
	{
		mProvinceDatas = provinceList;// 省数据填充
		for (int i = 0; i < provinceList.size(); i++)
		{
			Log.i("SchoolSelectActivity","provinceList.get(i).getId() === " +provinceList.get(i).getId());
			List<AreasModel> cities = getAreasByParentId(allProviceList,provinceList.get(i).getId());
			if (cities != null)
			{
				mCitisDatasMap.put(provinceList.get(i).getId(), cities); // 市数据填充
				for (int j = 0; j < cities.size(); j++)
				{
					List<AreasModel> distrcts = getAreasByParentId(allProviceList, cities.get(j).getId());
					mDistrictDatasMap.put(cities.get(j).getId(), distrcts); // 区数据填充
				}
			} else
			{
				break;
			}
		}
	}

	private void setUpDatas()
	{
		wvProvince.setViewAdapter(new ArrayWheelAdapter<AreasModel>(activity, mProvinceDatas));
		wvProvince.setVisibleItems(3);
		wvCity.setVisibleItems(3);
		wvDistrict.setVisibleItems(3);
		updateCities();
	}

	/**
	 * 
	 * @description: 根据当前的省，更新市WheelView的信息
	 * @date: 2015-7-2 上午9:40:08
	 * @author： huangqp
	 */
	private void updateCities()
	{
		int pCurrent = wvProvince.getCurrentItem();
		mCurrentProvince = mProvinceDatas.get(pCurrent);
		List<AreasModel> cities = mCitisDatasMap.get(mCurrentProvince.getId());
		wvCity.setViewAdapter(new ArrayWheelAdapter<AreasModel>(activity, cities));
		wvCity.setCurrentItem(0);
		updateAreas();
	}

	/**
	 * 
	 * @description: 根据当前的市，更新区WheelView的信息
	 * @date: 2015-7-2 上午9:51:57
	 * @author： huangqp
	 */
	private void updateAreas()

	{
		int pCurrent = wvCity.getCurrentItem();
		if (mCitisDatasMap.get(mCurrentProvince.getId()) != null && mCitisDatasMap.get(mCurrentProvince.getId()).size()>0)
		{
			mCurrentCity = mCitisDatasMap.get(mCurrentProvince.getId()).get(pCurrent);
			List<AreasModel> areas = mDistrictDatasMap.get(mCurrentCity.getId());
			wvDistrict.setViewAdapter(new ArrayWheelAdapter<AreasModel>(activity, areas));
			wvDistrict.setCurrentItem(0);
			if (mDistrictDatasMap.get(mCurrentCity.getId()) != null && mDistrictDatasMap.get(mCurrentCity.getId()).size() > 0 )
				mCurrentDistrict = mDistrictDatasMap.get(mCurrentCity.getId()).get(0);
		} else
		{
			wvDistrict.setViewAdapter(new ArrayWheelAdapter<AreasModel>(activity, null));
		}
		initCompleted = true;
		sendBroadCast();
	}

	private void sendBroadCast(){
		Intent intent = new Intent();
		intent.setAction(INIT_COMPLETE_ACTION);
		activity.sendBroadcast(intent);
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue)
	{
		if (wheel == wvProvince)
		{
			updateCities();
		} else if (wheel == wvCity)
		{
			updateAreas();
		} else if (wheel == wvDistrict)
		{
			mCurrentDistrict = mDistrictDatasMap.get(mCurrentCity.getId()).get(newValue);
		}
	}

	public interface OnAreaSelectListener
	{
		void areaSelectDone(AreasModel province, AreasModel city, AreasModel district);
	}

	private OnAreaSelectListener areaSelectListener;

	public void setOnAreaSelectListener(OnAreaSelectListener areaSelectListener)
	{
		this.areaSelectListener = areaSelectListener;
	}

	/**
	 * 
	 * @param province
	 * @param city
	 * @param district
	 * @description:
	 * @date: 2015-7-2 下午7:08:32
	 * @author： huangqp
	 */
	public void position(String province, String city, String district)
	{
		if(mProvinceDatas == null || mCitisDatasMap == null || mDistrictDatasMap == null) {
			return;
		}
		
		for (int i = 0; i < mProvinceDatas.size(); i++)
		{
			if (mProvinceDatas.get(i).getName().equals(province))
			{
				wvProvince.setCurrentItem(i);
				List<AreasModel> cities = mCitisDatasMap.get(mProvinceDatas.get(i).getId());
				for (int j = 0; j < cities.size(); j++)
				{
					if (cities.get(j).getName().equals(city))
					{
						wvCity.setCurrentItem(j);
						List<AreasModel> districts = mDistrictDatasMap.get(cities.get(j).getId());
						if(districts != null && districts.size() > 0) {
							for (int k = 0; k < districts.size(); k++)
							{
								if (districts.get(k).getName().equals(district))
								{
									wvDistrict.setCurrentItem(k);
									break;
								}
							}
						}
						break;
					}
				}
				break;
			}
		}

	}
	
	/**
	 * 根据省市区获取指定区域数据
	 * @param province
	 * @param city
	 * @param district
	 * @return
	 */
	public AreasModel getDistrict(String province, String city, String district) {
		if(TextUtils.isEmpty(province) || TextUtils.isEmpty(city) || TextUtils.isEmpty(district)) {
			return null;
		}
		if(mProvinceDatas == null || mCitisDatasMap == null || mDistrictDatasMap == null) {
			return null;
		}
		for (AreasModel provinceModel : mProvinceDatas) {
			if(provinceModel.getName().equals(province)) {
				List<AreasModel> cities = mCitisDatasMap.get(provinceModel.getId());
				if(cities != null && cities.size() > 0) {
					for (AreasModel cityModel : cities) {
						if(cityModel.getName().equals(city)) {
							List<AreasModel> districts = mDistrictDatasMap.get(cityModel.getId());
							if(districts != null && districts.size() > 0) {
								for (AreasModel districtModel : districts) {
									if(districtModel.getName().equals(district)) {
										return districtModel;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
}
