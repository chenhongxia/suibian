package com.example.suishouji.view.dalog;



import com.example.suishouji.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * @description: 自定义dialog(带有“确定”和“取消”按钮)
 * @date: 2015-5-22 下午4:02:02
 * @author: yems
 */
public class CustomDialog extends Dialog
{
	/*** 时间轮视图组件 */
	private static View datePickerView;

	public CustomDialog(Context context, int theme)
	{
		super(context, theme);
	}

	public CustomDialog(Context context)
	{
		super(context);
	}

	/**
	 * Helper class for creating a custom dialog
	 */
	public static class Builder
	{

		private Context context;
		private String title;
		private int titleVisibility = View.VISIBLE;
		private boolean commitLayVisiblity = true;
		private boolean isCanceledOnTouchOutside = false;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private View contentView;

		private DialogInterface.OnClickListener positiveButtonClickListener, negativeButtonClickListener;
		private RelativeLayout rlTitle;

		public Builder(Context context)
		{
			this.context = context;
		}

		/**
		 * Set the Dialog message from String
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(String message)
		{
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(int message)
		{
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title)
		{
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title)
		{
			this.title = title;
			return this;
		}

		public Builder setTitleVisibility(int visibility)
		{
			this.titleVisibility = visibility;
			return this;
		}

		/**
		 * Set a custom content view for the Dialog. If a message is set, the
		 * contentView is not added to the Dialog...
		 * 
		 * @param v
		 * @return
		 */
		public Builder setContentView(View v)
		{
			this.contentView = v;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener)
		{
			this.positiveButtonText = (String) context.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the positive button text and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener)
		{
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button resource and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener)
		{
			this.negativeButtonText = (String) context.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button text and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener)
		{
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * 自定义提交按钮
		 * @param isVisible 设置为false可以隐藏布局中默认的按钮
		 * @return
		 */
		public Builder setCommitLayVisiblity(boolean isVisible)
		{
			this.commitLayVisiblity = isVisible;
			return this;
		}
		
		public Builder setCanceledOnTouchOutside(boolean isCanceledOnTouchOutside)
		{
			this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
			return this;
		}
		
		/**
		 * Create the custom dialog
		 */
		public CustomDialog create()
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final CustomDialog dialog = new CustomDialog(context, R.style.customDialog);
			dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
			View layout = inflater.inflate(R.layout.custom_dialog_layout, null);
			dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			// set the dialog title
			rlTitle = (RelativeLayout) layout.findViewById(R.id.rl_title);
			rlTitle.setVisibility(titleVisibility);

			if (titleVisibility == View.VISIBLE)
				((TextView) layout.findViewById(R.id.tv_title)).setText(title);

			if (datePickerView != null)
			{
				RelativeLayout rlContent = (RelativeLayout) layout.findViewById(R.id.tv_request_tip_msg);
				rlContent.removeAllViews();
				rlContent.addView(datePickerView);
			}

			if(!commitLayVisiblity)
			{
				layout.findViewById(R.id.default_commit_lay).setVisibility(View.GONE);
			} else
			{
				// set the confirm button
				if (positiveButtonText != null)
				{
					((Button) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
					if (positiveButtonClickListener != null)
					{
						((Button) layout.findViewById(R.id.positiveButton)).setOnClickListener(new View.OnClickListener()
						{
							public void onClick(View v)
							{
								positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
								dialog.dismiss();
							}
						});
					}
				} else
				{
					// if no confirm button just set the visibility to GONE
					layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
				}

				// set the cancel button
				if (negativeButtonText != null)
				{
					((Button) layout.findViewById(R.id.negativeButton)).setText(negativeButtonText);
					if (negativeButtonClickListener != null)
					{
						((Button) layout.findViewById(R.id.negativeButton)).setOnClickListener(new View.OnClickListener()
						{
							public void onClick(View v)
							{
								negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
								dialog.dismiss();
							}
						});
					}
				} else
				{
					// if no confirm button just set the visibility to GONE
					layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
				}
			}

			// set the content message
			if (message != null)
			{
				TextView messageView = (TextView) layout.findViewById(R.id.message);
				messageView.setVisibility(View.VISIBLE);
				messageView.setText(message);
			} else if (contentView != null)
			{
				// if no message set
				// add the contentView to the dialog body
				((RelativeLayout) layout.findViewById(R.id.tv_request_tip_msg)).removeAllViews();
				((RelativeLayout) layout.findViewById(R.id.tv_request_tip_msg)).addView(contentView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			}
			dialog.setContentView(layout);
			return dialog;
		}
	}

/*	*//**
	 * @param mDateTimePicker2
	 * @description: 设置时间轮视图
	 * @date: 2015-5-27 下午7:20:41
	 * @author: yems
	 *//*
	protected void setDatePickerView(DateTimePicker dateTimePicker)
	{
		this.datePickerView = dateTimePicker;
	}*/
}
