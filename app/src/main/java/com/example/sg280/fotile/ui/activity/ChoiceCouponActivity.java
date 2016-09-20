package com.example.sg280.fotile.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.presents.ChoiceCouponPresent;
import com.example.sg280.fotile.presents.Interface.ChoiceCouponContacts;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 选择优惠券界面
 * Created by Tian on 2016/9/6.
 */
public class ChoiceCouponActivity extends BaseActivity implements ChoiceCouponContacts.View {

    @Bind(R.id.iv_back_title)
    ImageView iv_back_title;//标题的返回图标
    @Bind(R.id.tv_title)
    TextView tv_title;//标题栏标题
    @Bind(R.id.rv_choice_coupon)
    RecyclerView rv_choice_coupon;//优惠券的RecyclerView
    @Bind(R.id.rl_noData)
    RelativeLayout rl_noData;//没有优惠券的RelativeLayout

    private ChoiceCouponPresent choiceCouponPresent;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_choice_coupon;
    }

    @Override
    protected void onInitView() {
        tv_title.setText(R.string.choice_coupon);//修改标题栏的标题
        choiceCouponPresent = new ChoiceCouponPresent(this, this, rv_choice_coupon);
        Intent intent = getIntent();
        String shopCarID = intent.getStringExtra("shopCarId");
        if(null == shopCarID || "".equals(shopCarID)){
            choiceCouponPresent.getNowUsableCouponsAtOnce(intent.getStringExtra("productid"),
                    intent.getStringExtra("skuid"),intent.getStringExtra("productnum"));
        }else{

            choiceCouponPresent.getNowUsableCoupons(shopCarID);
        }
    }


    @Override
    public void noData() {
        rv_choice_coupon.setVisibility(View.GONE);
        rl_noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hadData() {
        rv_choice_coupon.setVisibility(View.VISIBLE);
        rl_noData.setVisibility(View.GONE);
    }

    @OnClick({R.id.iv_back_title, R.id.tv_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_title:
                onBackPressed();
                break;
        }
    }
}
