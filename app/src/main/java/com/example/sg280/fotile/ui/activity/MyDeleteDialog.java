package com.example.sg280.fotile.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.sg280.fotile.R;

/**
 * 是否删除的dialog
 * Created by Tian on 2016/8/17.
 */
public class MyDeleteDialog extends Dialog implements View.OnClickListener{

	private Context context;
	private MyDialogListener myListener;
	private Button btn_yes_dialog,btn_no_dialog;
	private TextView tv_title_dialog;
	private String title,button_rightName,button_leftName;

	//定义一节接口，用来实现点击监听
	public interface MyDialogListener{   
        public void onClick(View view);   
    }
	
	public MyDeleteDialog(Context context) {
		super(context);
		this.context = context;
	}


	/**
	 *
	 * @param context
	 * @param theme dialog的样式（style）
	 * @param title dialog的title语句（如：确认删除吗？）
	 * @param button_rightName dialog的右边按钮的显示的语句（如：确认）
	 * @param myListener 用法：要new MyDeleteDialog.MyDialogListener()重写里面的onclick方法
	 * 注意：只需要重写点击btn_yes_dialog按钮的点击事件就可以，btn_no_dialog默认是让dialog消失,不用重写
	 */
	public MyDeleteDialog(Context context, int theme,String title,String button_rightName,MyDialogListener myListener) {
		super(context, theme);
		this.context = context;
		this.myListener = myListener;
		this.title = title;
		this.button_rightName = button_rightName;
	}

	/**
	 *
	 * @param context
	 * @param theme dialog的样式（style）
	 * @param title dialog的title语句（如：确认删除吗？）
	 * @param button_leftName dialog的左边按钮的显示的语句（如：取消）
	 * @param button_rightName dialog的右边按钮的显示的语句（如：确认）
	 * @param myListener 用法：要new MyDeleteDialog.MyDialogListener()重写里面的onclick方法
	 * 注意：只需要重写点击btn_yes_dialog按钮的点击事件就可以，btn_no_dialog默认是让dialog消失,不用重写
	 */
	public MyDeleteDialog(Context context, int theme,String title,String button_leftName,String button_rightName,MyDialogListener myListener) {
		super(context, theme);
		this.context = context;
		this.myListener = myListener;
		this.title = title;
		this.button_rightName = button_rightName;
		this.button_leftName = button_leftName;
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog_is_delete);
		
		btn_yes_dialog = (Button) findViewById(R.id.btn_yes_dialog);
		btn_no_dialog = (Button) findViewById(R.id.btn_no_dialog);
		tv_title_dialog = (TextView) findViewById(R.id.tv_title_dialog);
		tv_title_dialog.setText(title);
		btn_no_dialog.setText(null == button_leftName?"取消":button_leftName);
		btn_yes_dialog.setText(button_rightName);
		
		btn_yes_dialog.setOnClickListener(this);
//		btn_no_dialog.setOnClickListener(this);

		//设置点击btn_no_dialog按钮取消事件
		btn_no_dialog.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	
	}

	//点击事件
	@Override
	public void onClick(View v) {
		myListener.onClick(v);
	}

	
}
