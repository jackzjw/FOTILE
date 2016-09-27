package com.example.sg280.fotile.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.CommitOrderBackMsgBean;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 支付成功界面
 * Created by Tian on 2016/9/27.
 */
public class PaySuccessfulActivity extends BaseActivity {


    @Bind(R.id.iv_back_title)
    ImageView ivBackTitle;//返回键
    @Bind(R.id.tv_title)
    TextView tvTitle;//标题
    @Bind(R.id.tv_order_number_info)
    TextView tvOrderNumberInfo;//订单号
    @Bind(R.id.tv_pay_price_info)
    TextView tvPayPriceInfo;//订单价格
    @Bind(R.id.rv_hotGoods)
    RecyclerView rvHotGoods;//热门商品

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_pay_successful;
    }

    @Override
    protected void onInitView() {

        tvTitle.setText(R.string.pay_successful);
        CommitOrderBackMsgBean bean = getIntent().getParcelableExtra("msg");
        tvOrderNumberInfo.setText(bean.getOrderNo());
        tvPayPriceInfo.setText(bean.getPayAmount());

    }

    //点击事件
    @OnClick({R.id.iv_back_title, R.id.tv_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_title:
                onBackPressed();
                break;

        }
    }
}
