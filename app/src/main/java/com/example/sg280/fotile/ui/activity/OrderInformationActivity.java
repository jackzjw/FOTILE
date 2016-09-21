package com.example.sg280.fotile.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.UserOrderInfoBean;
import com.example.sg280.fotile.presents.Interface.UserOrderInfoContacts;
import com.example.sg280.fotile.presents.UserOrderInfoPresent;
import com.example.sg280.fotile.utils.ConvertUtil;
import com.example.sg280.fotile.utils.Glides;
import com.example.sg280.fotile.utils.StringUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 订单详情页
 * Created by Tian on 2016/9/13.
 */
public class OrderInformationActivity extends BaseActivity implements UserOrderInfoContacts.View {


    @Bind(R.id.iv_back_title)
    ImageView ivBackTitle;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_order_number_info)
    TextView tvOrderNumberInfo;
    @Bind(R.id.tv_order_state)
    TextView tvOrderState;
    @Bind(R.id.tv_consignee_info)
    TextView tvConsigneeInfo;
    @Bind(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @Bind(R.id.tv_delivery_address_info)
    TextView tvDeliveryAddressInfo;
    @Bind(R.id.tv_postcode_info)
    TextView tvPostcodeInfo;
//    @Bind(R.id.tv_leave_a_message_info)
//    TextView tvLeaveAMessageInfo;
    @Bind(R.id.iv_point_1)
    ImageView ivPoint1;
    @Bind(R.id.tv_line_1)
    TextView tvLine1;
    @Bind(R.id.iv_point_2)
    ImageView ivPoint2;
    @Bind(R.id.tv_line_3)
    TextView tvLine3;
    @Bind(R.id.tv_line_2)
    TextView tvLine2;
    @Bind(R.id.iv_point_3)
    ImageView ivPoint3;
    @Bind(R.id.tv_line_5)
    TextView tvLine5;
    @Bind(R.id.tv_line_4)
    TextView tvLine4;
    @Bind(R.id.iv_point_4)
    ImageView ivPoint4;
    @Bind(R.id.tv_line_6)
    TextView tvLine6;
    @Bind(R.id.tv_commit_order)
    TextView tvCommitOrder;
    @Bind(R.id.tv_commit_order_info)
    TextView tvCommitOrderInfo;
    @Bind(R.id.tv_pay_time)
    TextView tvPayTime;
    @Bind(R.id.tv_pay_time_info)
    TextView tvPayTimeInfo;
    @Bind(R.id.tv_shipments_time)
    TextView tvShipmentsTime;
    @Bind(R.id.tv_shipments_time_info)
    TextView tvShipmentsTimeInfo;
    @Bind(R.id.tv_receipt_time)
    TextView tvReceiptTime;
    @Bind(R.id.tv_receipt_time_info)
    TextView tvReceiptTimeInfo;
    @Bind(R.id.iv_brand_logo)
    ImageView ivBrandLogo;
    @Bind(R.id.rv_goods)
    RecyclerView rvGoods;
    @Bind(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @Bind(R.id.tv_freight)
    TextView tvFreight;
    @Bind(R.id.tv_point_money)
    TextView tvPointMoney;
    @Bind(R.id.tv_coupon_price)
    TextView tvCouponPrice;
    @Bind(R.id.tv_order_money)
    TextView tvOrderMoney;
    @Bind(R.id.tv_pay_way)
    TextView tvPayWay;
    @Bind(R.id.tv_leave_another_message_info)
    TextView tvLeaveAnotherMessageInfo;
    @Bind(R.id.tv_logistics_company_info)
    TextView tvLogisticsCompanyInfo;
    @Bind(R.id.tv_waybill_number_info)
    TextView tvWaybillNumberInfo;
    @Bind(R.id.rl_logistics_info)
    RelativeLayout rlLogisticsInfo;
//    @Bind(R.id.tv_leave_a_message)
//    TextView tvLeaveAMessage;

    private UserOrderInfoPresent userOrderInfoPresent;
    private String orderId;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_order_info;
    }

    @Override
    protected void onInitView() {
        tvTitle.setText(R.string.order_info);//修改标题栏的标题

        orderId = getIntent().getStringExtra("orderId");
        userOrderInfoPresent = new UserOrderInfoPresent(this, this, rvGoods);
        userOrderInfoPresent.getUserOrderInfo(orderId);
    }

    @OnClick({R.id.iv_back_title, R.id.tv_order_number})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_title://返回上一界面
                onBackPressed();
                break;
        }
    }

    @Override
    public void setInfo(UserOrderInfoBean userOrderInfoBean) {
        tvOrderNumberInfo.setText(userOrderInfoBean.getID());
        tvOrderState.setText(ConvertUtil.getOrderStatus(userOrderInfoBean.getOrderStatus()));
        tvConsigneeInfo.setText(userOrderInfoBean.getRecipients());
        tvPhoneNumber.setText(userOrderInfoBean.getTel());
        tvDeliveryAddressInfo.setText(userOrderInfoBean.getAddress());
        tvPostcodeInfo.setText(userOrderInfoBean.getPostcode());
        tvLeaveAnotherMessageInfo.setText(userOrderInfoBean.getOrderRemark());

        if (null != userOrderInfoBean.getOrderDealLog()) {
            for (int i = 0; i < userOrderInfoBean.getOrderDealLog().size(); i++) {
                if (i == 0) {
                    tvCommitOrder.setText(userOrderInfoBean.getOrderDealLog().get(i).getOrderStatusName());
                    tvCommitOrderInfo.setText(userOrderInfoBean.getOrderDealLog().get(i).getDealTime());
                } else if (i == 1) {
                    tvPayTime.setText(userOrderInfoBean.getOrderDealLog().get(i).getOrderStatusName());
                    tvPayTimeInfo.setText(userOrderInfoBean.getOrderDealLog().get(i).getDealTime());
                } else if (i == 2) {
                    tvShipmentsTime.setText(userOrderInfoBean.getOrderDealLog().get(i).getOrderStatusName());
                    tvShipmentsTimeInfo.setText(userOrderInfoBean.getOrderDealLog().get(i).getDealTime());
                } else if (i == 3) {
                    tvReceiptTime.setText(userOrderInfoBean.getOrderDealLog().get(i).getOrderStatusName());
                    tvReceiptTimeInfo.setText(userOrderInfoBean.getOrderDealLog().get(i).getDealTime());
                }
            }
        }

        Glides.getInstance().load(this, userOrderInfoBean.getBrandLogo(), ivBrandLogo);
        tvGoodsPrice.setText("￥ " + StringUtil.format(Double.valueOf(userOrderInfoBean.getProductAmount())));
        if ( "0.00".equals(userOrderInfoBean.getDiscountAmount()) &&!"0".equals(userOrderInfoBean.getFreight())) {
            tvFreight.setText("￥ " + StringUtil.format(Double.valueOf(userOrderInfoBean.getFreight())));
        }
        if ("0.00".equals(userOrderInfoBean.getDiscountAmount()) && !"0".equals(userOrderInfoBean.getDiscountAmount())) {
            tvCouponPrice.setText("￥ " + StringUtil.format(Double.valueOf(userOrderInfoBean.getDiscountAmount())));
        }
        if ("0.00".equals(userOrderInfoBean.getIntegralAmount()) && !"0".equals(userOrderInfoBean.getIntegralAmount())) {
            tvPointMoney.setText("￥ " + StringUtil.format(Double.valueOf(userOrderInfoBean.getIntegralAmount())));
        }
        tvOrderMoney.setText("￥ " + StringUtil.format(Double.valueOf(userOrderInfoBean.getPayAmount())));
        tvPayWay.setText(userOrderInfoBean.getPayTypeName());
    }
}
