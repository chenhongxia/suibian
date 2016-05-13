package com.example.suishouji.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import com.example.suishouji.R;
import com.example.suishouji.view.trees.tree.Node;
import com.example.suishouji.view.trees.tree.TreeListViewAdapter;

public class MyTreeListViewAdapter<T> extends TreeListViewAdapter<T> {

	public MyTreeListViewAdapter(ListView mTree, Context context,
			List<T> datas, int defaultExpandLevel, boolean isHide)
			throws IllegalArgumentException, IllegalAccessException {
		super(mTree, context, datas, defaultExpandLevel, isHide,true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getConvertView(Node node, int position, View convertView,
			ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.screening_list_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.label = (TextView) convertView.findViewById(R.id.id_treenode_name);
			viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.id_treeNode_check);
			viewHolder.icon = (ImageView)convertView.findViewById(R.id.id_treenode_icon);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if(node.isLeaf()){
			viewHolder.label.setTextColor(mContext.getResources().getColor(R.color.color_666666));
			viewHolder.checkBox.setClickable(false);
			
		}else{
			viewHolder.label.setTextColor(mContext.getResources().getColor(R.color.color_2d2d2d));
			viewHolder.checkBox.setClickable(true);
		}
		if (node.getIcon() == -1) {
			viewHolder.icon.setVisibility(View.INVISIBLE);
		} else {
			viewHolder.icon.setVisibility(View.VISIBLE);
			viewHolder.icon.setImageResource(node.getIcon());
		}

//		if (node.isHideChecked()) {
//			viewHolder.checkBox.setVisibility(View.INVISIBLE);
//		} else {
//			viewHolder.checkBox.setVisibility(View.VISIBLE);
//			setCheckBoxBg(viewHolder.checkBox, node);
//		}
		setCheckBoxBg(viewHolder.checkBox, node);
		
		viewHolder.label.setText(node.getName());

		return convertView;
	}

	private final class ViewHolder {
		ImageView icon;
		TextView label;
		CheckBox checkBox;
	}

	/**
	 * checkbox是否显示
	 * @param cb
	 * @param isChecked
	 */
	private void setCheckBoxBg(CheckBox cb,Node node) {
//		if(node.isLeaf() ){
//			if (node.isChecked()) {
//				cb.setBackgroundResource(R.drawable.i_yun_file_checked);
//			} else {
//				cb.setBackgroundResource(R.drawable.i_yun_file_normal);
//			}
//		}else{
////			if (node.isChecked()) {
////				cb.setBackgroundResource(R.drawable.i_half_pre);
////			} else {
////				cb.setBackgroundResource(R.drawable.i_yun_file_normal);
////			}
//			cb.setBackgroundDrawable(null);
//		}
		
	/*	if (node.isChecked()) {
			cb.setBackgroundResource(R.drawable.i_yun_file_checked);
		} else {
			cb.setBackgroundResource(R.drawable.i_yun_file_normal);
		}
*/
	}
}
