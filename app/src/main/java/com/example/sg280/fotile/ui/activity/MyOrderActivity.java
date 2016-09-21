package com.example.sg280.fotile.ui.activity;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.MyOrderAdapter;
import com.example.sg280.fotile.presents.Interface.MyOrderContacts;
import com.example.sg280.fotile.presents.MyOrderPresent;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tian on 2016/8/3.
 * 我的订单界面
 */
public class MyOrderActivity extends BaseActivity implements MyOrderContacts.View{

    @Bind(R.id.iv_back_title)//标题的返回图标
            ImageView iv_back_title;
    @Bind(R.id.tv_title)//标题栏标题
            TextView tv_title;

    //订单的类型选择：全部，待付款，待发货，待收货，已完成
    @Bind({R.id.tv_all_order, R.id.tv_wait_pay, R.id.tv_wait_shipments, R.id.tv_wait_receipt, R.id.tv_deal_is_done})
    List<TextView> orderTypeList;

    //订单类型下面的下划线
    @Bind({R.id.tv_line_all_order, R.id.tv_line_wait_pay, R.id.tv_line_wait_shipments,
            R.id.tv_line_wait_receipt, R.id.tv_line_deal_is_done})
    List<TextView> orderLineList;

    @Bind(R.id.erv_myOrder)
    RecyclerView erv_myOrder;

    private MyOrderPresent myOrderPresent;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_orders;
    }

    @Override
    protected void onInitView() {

        tv_title.setText(R.string.my_order);//修改标题栏的标题

        myOrderPresent = new MyOrderPresent(this,this,erv_myOrder);
        allOrder();

    }

    //返回上一界面
    @OnClick(R.id.iv_back_title)
    void goBack() {
        onBackPressed();
    }

    //点击全部
    @OnClick(R.id.tv_all_order)
    void allOrder() {
        init(0);
        myOrderPresent.getMyOrder("");
    }

    //点击待付款
    @OnClick(R.id.tv_wait_pay)
    void waitPay() {
        init(1);
        myOrderPresent.getMyOrder("1");
    }

    //点击待发货
    @OnClick(R.id.tv_wait_shipments)
    void waitShipments() {
        init(2);
        myOrderPresent.getMyOrder("2");
    }

    //点击待收货
    @OnClick(R.id.tv_wait_receipt)
    void waitReceipt() {
        init(3);
        myOrderPresent.getMyOrder("3");
    }

    //点击已完成
    @OnClick(R.id.tv_deal_is_done)
    void orderIsDone() {
        init(4);
        myOrderPresent.getMyOrder("4");
    }


    //初始化字体颜色为灰色
    private void initTextColor() {
        for (TextView textView : orderTypeList) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.hui_text));
        }
    }

    //初始化下划线为不显示
    private void initLineVisible() {
        for (TextView textView : orderLineList) {
            textView.setVisibility(View.INVISIBLE);
        }
    }

    //根据点击的订单类型改变字体颜色，下划线等等
    private void init(int i) {
        initTextColor();
        initLineVisible();
        orderTypeList.get(i).setTextColor(ContextCompat.getColor(this, R.color.red));
        orderLineList.get(i).setVisibility(View.VISIBLE);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

}
