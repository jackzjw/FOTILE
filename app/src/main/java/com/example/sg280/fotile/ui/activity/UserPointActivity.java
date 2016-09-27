package com.example.sg280.fotile.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.presents.Interface.UserPointContacts;
import com.example.sg280.fotile.presents.UserPointPresent;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 用户积分界面
 * Created by Tian on 2016/9/19.
 */
public class UserPointActivity extends BaseActivity implements UserPointContacts.View{


    @Bind(R.id.iv_back_title)
    ImageView ivBackTitle;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_point_num)
    TextView tvPointNum;
    @Bind(R.id.tv_all_point_info)
    TextView tvAllPointInfo;
    @Bind(R.id.tv_used_point_info)
    TextView tvUsedPointInfo;
    @Bind(R.id.iv_point_info)
    ImageView ivPointInfo;
    @Bind(R.id.rl_point_info)
    RelativeLayout rlPointInfo;

    private UserPointPresent userPointPresent;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_user_point;
    }

    @Override
    protected void onInitView() {
        tvTitle.setText(R.string.my_point);//修改标题栏的标题
        userPointPresent = new UserPointPresent(this,this);
        userPointPresent.getPoint();
    }

    @OnClick({R.id.iv_back_title, R.id.rl_point_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_title:
                onBackPressed();
                break;
            case R.id.rl_point_info:

                break;
        }
    }

    @Override
    public void setPoint(String totalPoint, String usedPoint) {
        tvAllPointInfo.setText(totalPoint);
        tvUsedPointInfo.setText(usedPoint);
        tvPointNum.setText(""+(Integer.valueOf(totalPoint)-Integer.valueOf(usedPoint)));
    }
}
