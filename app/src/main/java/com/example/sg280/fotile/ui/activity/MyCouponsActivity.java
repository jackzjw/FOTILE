package com.example.sg280.fotile.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.presents.Interface.UserCouponsContacts;
import com.example.sg280.fotile.presents.UserCouponsPresent;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tian on 2016/8/3.
 * 我的优惠码界面
 */
public class MyCouponsActivity extends BaseActivity implements UserCouponsContacts.View {

    @Bind(R.id.iv_back_title)//标题的返回图标
            ImageView iv_back_title;
    @Bind(R.id.tv_title)//标题栏标题
            TextView tv_title;
    @Bind({R.id.tv_wait_use, R.id.tv_disabled})//可使用，已失效
            List<TextView> couponsTitleList;
    @Bind({R.id.tv_wait_use_line, R.id.tv_disabled_line})//可使用，已失效下面的下划线
            List<TextView> lineList;
    @Bind(R.id.erv_my_coupons)//我的优惠码的EasyRecyclerView
            RecyclerView erv_my_coupons;

    private UserCouponsPresent userCouponsPresent;
    private String USABLE = "GetMyCoupon";//获取所有可用优惠码的Action
    private String USED = "GetMyUselessCoupon";//获取所有不可用优惠码的Action

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_coupons;
    }

    @Override
    protected void onInitView() {

        tv_title.setText(R.string.my_coupons);//修改标题栏的标题
        userCouponsPresent = new UserCouponsPresent(this,erv_my_coupons);
        clickWaitUse();//默认点击可使用

    }

    //返回上一界面
    @OnClick(R.id.iv_back_title)
    void goBack() {
        onBackPressed();
    }

    //点击可使用
    @OnClick(R.id.tv_wait_use)
    void clickWaitUse() {
        init(0);
        userCouponsPresent.getCoupons(USABLE);
    }

    //点击已失效
    @OnClick(R.id.tv_disabled)
    void clickDisabled() {
        init(1);
        userCouponsPresent.getCoupons(USED);
    }

    //根据点击的优惠码做相应的界面显示改变
    private void init(int i) {
        initTitle();
        initLine();
        couponsTitleList.get(i).setTextColor(ContextCompat.getColor(this, R.color.red));
        lineList.get(i).setVisibility(View.VISIBLE);

        if (i == 0) {
            couponsTitleList.get(i).setClickable(false);
            couponsTitleList.get(1).setClickable(true);
        } else {
            couponsTitleList.get(i).setClickable(false);
            couponsTitleList.get(0).setClickable(true);
        }
    }

    //初始化字体颜色
    private void initTitle() {
        for (TextView textView : couponsTitleList) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.hui_text));
        }
    }

    //初始化下划线的显示状态
    private void initLine() {
        for (TextView textView : lineList) {
            textView.setVisibility(View.INVISIBLE);
        }
    }

}
