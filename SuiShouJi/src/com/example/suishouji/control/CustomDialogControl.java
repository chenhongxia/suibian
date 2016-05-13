package com.example.suishouji.control;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.suishouji.R;
import com.example.suishouji.adapter.MyTreeListViewAdapter;
import com.example.suishouji.utils.CommonUtils;
import com.example.suishouji.view.dalog.CustomDialog;
import com.example.suishouji.view.trees.bean.MyNodeBean;
import com.example.suishouji.view.trees.tree.Node;
import com.example.suishouji.view.trees.tree.TreeListViewAdapter.OnTreeNodeClickListener;


public class CustomDialogControl {
	private CustomDialog dialog;


	public  List<MyNodeBean> initData(){
		List<MyNodeBean>  list = new ArrayList<MyNodeBean>();
		int id = 4;
		for(int j = 0;j<4;j++){
			MyNodeBean parentbean = new MyNodeBean(j, -1, "第 -1 父亲第"+j+"跳数据");
			list.add(parentbean);
			for(int  i =0;i<5;i++){
				MyNodeBean bean = new MyNodeBean(id, j, "第"+j+"父亲第"+i+"跳数据");
				list.add(bean);
				id++;
			}	
		}
        for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId()  + "   "+ list.get(i).getPid() +"   "+list.get(i).getName());
		}

		return list;
		
		
	}


	public  void showSelectCustomControl(final Context context ,final List<MyNodeBean> mDatas){
		CustomDialog.Builder build = new CustomDialog.Builder(context);
		build.setTitle(R.string.str_classes);
		View view = LayoutInflater.from(context).inflate(R.layout.classes_layout, null);
		build.setContentView(view);
		build.setCommitLayVisiblity(false);
		ListView listView = (ListView)view.findViewById(R.id.tree_lv);
		MyTreeListViewAdapter<MyNodeBean> adapter = null;
		try {
			adapter = new MyTreeListViewAdapter<MyNodeBean>(listView, context, mDatas, 0, false);
			adapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
				@Override
				public void onClick(Node node, int position) {
					if (node.isLeaf()) {
						Toast.makeText(context, node.getName(), Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onCheckChange(Node node, int position, List<Node> checkedNodes) {
					StringBuffer sb = new StringBuffer();
					for (Node n : checkedNodes) {
						int pos = n.getId() - 1;
						sb.append(mDatas.get(pos).getName()).append("---").append(pos + 1).append(";");

					}
					Toast.makeText(context, sb.toString(),Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onCheckChange(Node node, int position, Node checkedNode) {
					Toast.makeText(context, checkedNode.getId() +checkedNode.getName(),Toast.LENGTH_SHORT).show();
					/*checkedId = checkedNode.getId();*/
				}
			});
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		listView.setAdapter(adapter);
        CommonUtils.setListViewHeight(listView);
		dialog = build.create();
		dialog.show();
	}
}
