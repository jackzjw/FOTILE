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
    ImageView ivBackTitle;//返回键
    @Bind(R.id.tv_title)
    TextView tvTitle;//标题
    @Bind(R.id.tv_order_number_info)
    TextView tvOrderNumberInfo;//订单号
    @Bind(R.id.tv_order_state)
    TextView tvOrderState;//订单状态
    @Bind(R.id.tv_consignee_info)
    TextView tvConsigneeInfo;//收货人
    @Bind(R.id.tv_phone_number)
    TextView tvPhoneNumber;//收货人电话
    @Bind(R.id.tv_delivery_address_info)
    TextView tvDeliveryAddressInfo;//收货人地址信息
    @Bind(R.id.tv_postcode_info)
    TextView tvPostcodeInfo;//收货人邮编
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
    TextView tvCommitOrder;//订单跟踪第一个名称
    @Bind(R.id.tv_commit_order_info)
    TextView tvCommitOrderInfo;//订单跟踪第一个时间
    @Bind(R.id.tv_pay_time)
    TextView tvPayTime;//订单跟踪第二个名称
    @Bind(R.id.tv_pay_time_info)
    TextView tvPayTimeInfo;//订单跟踪第二个时间
    @Bind(R.id.tv_shipments_time)
    TextView tvShipmentsTime;//订单跟踪第三个名称
    @Bind(R.id.tv_shipments_time_info)
    TextView tvShipmentsTimeInfo;//订单跟踪第三个时间
    @Bind(R.id.tv_receipt_time)
    TextView tvReceiptTime;//订单跟踪第四个名称
    @Bind(R.id.tv_receipt_time_info)
    TextView tvReceiptTimeInfo;//订单跟踪第四个时间
    @Bind(R.id.iv_brand_logo)
    ImageView ivBrandLogo;//品牌logo地址
    @Bind(R.id.rv_goods)
    RecyclerView rvGoods;//订单商品的RecyclerView
    @Bind(R.id.tv_goods_price)
    TextView tvGoodsPrice;//商品金额
    @Bind(R.id.tv_freight)
    TextView tvFreight;//运费
    @Bind(R.id.tv_point_money)
    TextView tvPointMoney;//积分
    @Bind(R.id.tv_coupon_price)
    TextView tvCouponPrice;//优惠券
    @Bind(R.id.tv_order_money)
    TextView tvOrderMoney;//订单金额
    @Bind(R.id.tv_pay_way)
    TextView tvPayWay;//支付方式
    @Bind(R.id.tv_leave_another_message_info)
    TextView tvLeaveAnotherMessageInfo;//留言
    @Bind(R.id.tv_logistics_company_info)
    TextView tvLogisticsCompanyInfo;//物流公司名称
    @Bind(R.id.tv_waybill_number_info)
    TextView tvWaybillNumberInfo;//物流公司订单号
    @Bind(R.id.rl_logistics_info)
    RelativeLayout rlLogisticsInfo;
//    @Bind(R.id.tv_leave_a_message)
//    TextView tvLeaveAMessage;
//    @Bind(R.id.tv_leave_a_message_info)
//    TextView tvLeaveAMessageInfo;

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

    //将获取到的网络数据显示到界面上
    @Override
    public void setInfo(UserOrderInfoBean userOrderInfoBean) {
        tvOrderNumberInfo.setText(userOrderInfoBean.getID());
        tvOrderState.setText(ConvertUtil.getOrderStatus(userOrderInfoBean.getOrderStatus()));
        tvConsigneeInfo.setText(userOrderInfoBean.getRecipients());
        tvPhoneNumber.setText(userOrderInfoBean.getTel());
        tvDeliveryAddressInfo.setText(userOrderInfoBean.getAddress());
        tvPostcodeInfo.setText(userOrderInfoBean.getPostcode());
        tvLeaveAnotherMessageInfo.setText(userOrderInfoBean.getOrderRemark());

        //显示订单的一些具体状态信息（生成订单，后台审核等等）
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
        tvGoodsPrice.setText(getResources().getString(R.string.rmb) + StringUtil.saveTwoDouble(userOrderInfoBean.getProductAmount()));
        tvOrderMoney.setText(getResources().getString(R.string.rmb) + StringUtil.saveTwoDouble((userOrderInfoBean.getPayAmount())));
        tvFreight.setText(getResources().getString(R.string.rmb) + StringUtil.saveTwoDouble(userOrderInfoBean.getFreight()));
        tvCouponPrice.setText(getResources().getString(R.string.rmb) + StringUtil.saveTwoDouble(userOrderInfoBean.getDiscountAmount()));
        tvPointMoney.setText(getResources().getString(R.string.rmb) + StringUtil.saveTwoDouble(userOrderInfoBean.getIntegralAmount()));
        tvPayWay.setText(userOrderInfoBean.getPayTypeName());

        initLineAndRedPoint(userOrderInfoBean.getOrderStatus());
        tvLogisticsCompanyInfo.setText(userOrderInfoBean.getExpressName());
        tvWaybillNumberInfo.setText(userOrderInfoBean.getExpressNo());
    }

    //根据订单状态显示红线跟红点
    private void initLineAndRedPoint(String status) {

        switch (status) {
            case "1"://待付款
                ivPoint1.setImageResource(R.drawable.shape_red_point_order_info);
                ivPoint2.setImageResource(R.drawable.shape_gray_point_order_info);
                ivPoint3.setImageResource(R.drawable.shape_gray_point_order_info);
                ivPoint4.setImageResource(R.drawable.shape_gray_point_order_info);
                tvLine1.setBackgroundResource(R.color.hui_text);
                tvLine2.setBackgroundResource(R.color.hui_text);
                tvLine3.setBackgroundResource(R.color.hui_text);
                tvLine4.setBackgroundResource(R.color.hui_text);
                tvLine5.setBackgroundResource(R.color.hui_text);
                tvLine6.setBackgroundResource(R.color.hui_text);
                rlLogisticsInfo.setVisibility(View.GONE);
                break;
            case "0"://订单取消
            case "2"://待发货
                ivPoint1.setImageResource(R.drawable.shape_red_point_order_info);
                ivPoint2.setImageResource(R.drawable.shape_red_point_order_info);
                ivPoint3.setImageResource(R.drawable.shape_gray_point_order_info);
                ivPoint4.setImageResource(R.drawable.shape_gray_point_order_info);
                tvLine1.setBackgroundResource(R.color.red);
                tvLine2.setBackgroundResource(R.color.red);
                tvLine3.setBackgroundResource(R.color.hui_text);
                tvLine4.setBackgroundResource(R.color.hui_text);
                tvLine5.setBackgroundResource(R.color.hui_text);
                tvLine6.setBackgroundResource(R.color.hui_text);
                rlLogisticsInfo.setVisibility(View.GONE);
                break;
            case "3"://待收货
                ivPoint1.setImageResource(R.drawable.shape_red_point_order_info);
                ivPoint2.setImageResource(R.drawable.shape_red_point_order_info);
                ivPoint3.setImageResource(R.drawable.shape_red_point_order_info);
                ivPoint4.setImageResource(R.drawable.shape_gray_point_order_info);
                tvLine1.setBackgroundResource(R.color.red);
                tvLine2.setBackgroundResource(R.color.red);
                tvLine3.setBackgroundResource(R.color.red);
                tvLine4.setBackgroundResource(R.color.red);
                tvLine5.setBackgroundResource(R.color.hui_text);
                tvLine6.setBackgroundResource(R.color.hui_text);
                rlLogisticsInfo.setVisibility(View.VISIBLE);
                break;
            case "4"://交易完成
                ivPoint1.setImageResource(R.drawable.shape_red_point_order_info);
                ivPoint2.setImageResource(R.drawable.shape_red_point_order_info);
                ivPoint3.setImageResource(R.drawable.shape_red_point_order_info);
                ivPoint4.setImageResource(R.drawable.shape_red_point_order_info);
                tvLine1.setBackgroundResource(R.color.red);
                tvLine2.setBackgroundResource(R.color.red);
                tvLine3.setBackgroundResource(R.color.red);
                tvLine4.setBackgroundResource(R.color.red);
                tvLine5.setBackgroundResource(R.color.red);
                tvLine6.setBackgroundResource(R.color.red);
                rlLogisticsInfo.setVisibility(View.VISIBLE);
                break;
        }
    }
}
