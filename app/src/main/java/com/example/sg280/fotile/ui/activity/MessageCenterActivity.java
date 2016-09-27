package com.example.sg280.fotile.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tian on 2016/8/4.
 */
public class MessageCenterActivity extends BaseActivity {

    @Bind(R.id.iv_back_title)
    ImageView ivBackTitle;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_msg_center;
    }

    @Override
    protected void onInitView() {
        tvTitle.setText(R.string.message_center);//更改标题
    }

    @OnClick(R.id.iv_back_title)
    public void onClick() {
        finish();
    }
}
