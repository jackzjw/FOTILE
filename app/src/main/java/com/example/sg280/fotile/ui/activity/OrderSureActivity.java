package com.example.sg280.fotile.ui.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.GoodsSureOrderAdapter;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.bean.CouponsBean;
import com.example.sg280.fotile.model.bean.ShippingAddressBean;
import com.example.sg280.fotile.model.bean.ShoppingCartGoodsBean;
import com.example.sg280.fotile.presents.Interface.OrderSureContacts;
import com.example.sg280.fotile.presents.OrderSurePresent;
import com.example.sg280.fotile.utils.ConvertUtil;
import com.example.sg280.fotile.utils.DensityUtil;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.example.sg280.fotile.utils.StringUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 订单确认界面
 * Created by Tian on 2016/8/18.
 */
public class OrderSureActivity extends BaseActivity implements OrderSureContacts.View {


    @Bind(R.id.iv_back_title)
    ImageView ivBackTitle;//标题的返回图标
    @Bind(R.id.tv_title)
    TextView tvTitle;//标题栏标题
    @Bind(R.id.tv_consignee_info)
    TextView tvConsigneeInfo;//收货人
    @Bind(R.id.tv_phone_number)
    TextView tvPhoneNumber;//收货人手机号
    @Bind(R.id.tv_delivery_address_info)
    TextView tvDeliveryAddressInfo;//收货地址
    @Bind(R.id.tv_postcode_info)
    TextView tvPostcodeInfo;//邮政编码
    @Bind(R.id.et_leave_another_message_info)
    EditText etLeaveAnotherMessageInfo;//用户的留言
    @Bind(R.id.rl_add_address)
    RelativeLayout rlAddAddress;//添加新地址的布局
    @Bind(R.id.rl_address)
    RelativeLayout rlAddress;//收货地址布局
    @Bind(R.id.rv_goods)
    RecyclerView rvGoods;//订单内商品的RecyclerView
    @Bind(R.id.tv_goods_price)
    TextView tvGoodsPrice;//商品价格
    @Bind(R.id.tv_coupon_price)
    TextView tvCouponPrice;//优惠价格
    @Bind(R.id.tv_order_price)
    TextView tvOrderPrice;//订单价格
    @Bind(R.id.tv_usablePointNum)
    TextView tv_usablePointNum;//用户所有可用积分
    @Bind(R.id.et_usePoint)
    EditText et_usePoint;//用户填写使用的积分数
    @Bind(R.id.tv_couponUsableNum)
    TextView tv_couponUsableNum;//当前可使用优惠券数量
    @Bind(R.id.iv_payOnDelivery)
    ImageView ivPayOnDelivery;//货到付款图标
    @Bind(R.id.iv_payWeChat)
    ImageView ivPayWeChat;//微信支付图标
    @Bind(R.id.btn_sure)
    Button btnSure;//确定（提交）
    @Bind(R.id.rl_payOnDelivery)
    RelativeLayout rlPayOnDelivery;
    @Bind(R.id.rl_payWeChat)
    RelativeLayout rlPayWeChat;
    @Bind(R.id.rl_coupons)
    RelativeLayout rlCoupons;//优惠码布局

    private GoodsSureOrderAdapter adapter;
    private List<ShoppingCartGoodsBean> list;
    private String goodsPrice;
    private final int ADD_REQUEST_CODE = 1;//跳转到添加新地址的请求码
    private final int ADDRESS_REQUEST_CODE = 2;//跳转收货地址请求码
    private final int COUPON_REQUEST_CODE = 3;//跳转选择优惠券请求码
    private OrderSurePresent orderSurePresent;
    private final String ACTION = "GetCouponCount";//获取当前可用优惠券数量的action
    private String shopCarId;//购物车ID
    private ShippingAddressBean shippingAddressBean;
    private String payType = "2";//默认货到付款 1：微信支付 2：货到付款
    private String usablePoint = "0";//可用积分
    private String couponCardId;//优惠券ID
    private int which = -1;//0--立即购买，1--从购物车购买
    private ShoppingCartGoodsBean bean;
    private CouponsBean couponsBean;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_order_sure;
    }

    @Override
    protected void onInitView() {
        tvTitle.setText(R.string.sure_order);//设置标题（订单确认）
        orderSurePresent = new OrderSurePresent(this, this);
        orderSurePresent.getDefaultAddress();

        Intent intent = getIntent();
        which = intent.getIntExtra("which", -1);
        //判断数据来自哪个界面：0：立即购买，1：购物车
        if (0 == which) {
            bean = intent.getParcelableExtra("product");
            list = new ArrayList<>();
            list.add(bean);
            //获取可用优惠券数量
            orderSurePresent.getCouponsNumAtOnce(ACTION, bean.getProductID(), bean.getSkuID(), bean.getProductQuantity());
            goodsPrice = "￥ " + StringUtil.format(Double.valueOf(bean.getProductQuantity()) * Double.valueOf(bean.getPricePromo()));
        } else if (1 == which) {

            list = intent.getParcelableArrayListExtra("list");
            if (list.size() == 1) {
                shopCarId = list.get(0).getID();
            } else if (list.size() > 1) {
                List<String> stringList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    stringList.add(list.get(i).getID());
                }
                shopCarId = ConvertUtil.jointComma(stringList);
            }
            //获取可用优惠券数量
            orderSurePresent.getCouponsNum(ACTION, shopCarId);
            goodsPrice = intent.getStringExtra("price");
        }

        orderSurePresent.getPoint();//获取可用积分
        tvGoodsPrice.setText(goodsPrice);
        tvOrderPrice.setText(goodsPrice);
        rvGoods.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerView的间距
        DividerDecoration divider = new DividerDecoration(ContextCompat.getColor(this, R.color.white), DensityUtil.dp2px(this, 1), 0, 0);
        rvGoods.addItemDecoration(divider);
        adapter = new GoodsSureOrderAdapter(this, list);
        rvGoods.setAdapter(adapter);

    }


    //点击事件
    @OnClick({R.id.rl_payOnDelivery, R.id.rl_payWeChat, R.id.btn_sure, R.id.iv_back_title,
            R.id.rl_add_address, R.id.rl_coupons, R.id.rl_address})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rl_payOnDelivery://货到付款

                setWhichWay(0);
                payType = "2";

                break;
            case R.id.rl_payWeChat://微信支付

                setWhichWay(1);
                payType = "1";

                break;
            case R.id.btn_sure://确认订单

                String remark = etLeaveAnotherMessageInfo.getText().toString().trim();
                String integral = et_usePoint.getText().toString().trim();

                if (null == shippingAddressBean) {
                    ToastUtil.showShort(this, "请添加收货地址");
                    break;
                }

                if (pointIsOk(integral)) {
                    integral = "".equals(integral) ? "0" : integral;
                    if (which == 1) {
                        orderSurePresent.commitOrder(shopCarId, SharedPreferencesUtil.getId(this), couponCardId,
                                shippingAddressBean.getPcr(), shippingAddressBean.getAddress(), shippingAddressBean.getTel(),
                                shippingAddressBean.getPostcode(), shippingAddressBean.getRecipients(), payType, remark, "0", integral, "2");
                    } else {

                        orderSurePresent.commitOrderAtOnce(bean.getProductID(), bean.getSkuID(), bean.getProductQuantity(), SharedPreferencesUtil.getId(this), couponCardId,
                                shippingAddressBean.getPcr(), shippingAddressBean.getAddress(), shippingAddressBean.getTel(),
                                shippingAddressBean.getPostcode(), shippingAddressBean.getRecipients(), payType, remark, "0", integral, "1");
                    }
                }

                break;
            case R.id.iv_back_title://返回
                onBackPressed();
                break;
            case R.id.rl_add_address://添加新地址
                intent = new Intent(this, AddNewAddressActivity.class);
                intent.putExtra("which", Constants.ADD);
                startActivityForResult(intent, ADD_REQUEST_CODE);
                break;
            case R.id.rl_coupons://优惠码

                intent = new Intent(this, ChoiceCouponActivity.class);
                if (1 == which) {
                    intent.putExtra("shopCarId", shopCarId);
                    startActivityForResult(intent, COUPON_REQUEST_CODE);
                } else {
                    intent.putExtra("productid", bean.getProductID());
                    intent.putExtra("skuid", bean.getSkuID());
                    intent.putExtra("productnum", bean.getProductQuantity());
                    startActivityForResult(intent, COUPON_REQUEST_CODE);
                }

                break;
            case R.id.rl_address://收货地址
                intent = new Intent(this, ShippingAddressActivity.class);
                startActivityForResult(intent, ADDRESS_REQUEST_CODE);
                break;
        }
    }

    private void setWhichWay(int i) {
        if (i == 0) {
            ivPayOnDelivery.setImageResource(R.drawable.cart_icon_choice_selected_red);
            ivPayWeChat.setImageResource(R.drawable.cart_icon_choice_unselected_gray);
        } else if (i == 1) {
            ivPayOnDelivery.setImageResource(R.drawable.cart_icon_choice_unselected_gray);
            ivPayWeChat.setImageResource(R.drawable.cart_icon_choice_selected_red);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_REQUEST_CODE://从添加收货地址界面跳转过来的
                case ADDRESS_REQUEST_CODE:
                    rlAddAddress.setVisibility(View.GONE);
                    rlAddress.setVisibility(View.VISIBLE);
                    setAddressInfo(data.getParcelableExtra("address"));
                    break;

                case COUPON_REQUEST_CODE:
                    couponsBean = data.getParcelableExtra("coupon");
                    couponCardId = couponsBean.getID();
                    setCouponInfo(Integer.valueOf(couponsBean.getValType()));
                    break;
                default:
                    break;
            }
        }
    }

    //设置默认地址
    @Override
    public void setDefault(ShippingAddressBean shippingAddressBean) {
        rlAddAddress.setVisibility(View.GONE);
        rlAddress.setVisibility(View.VISIBLE);
        setAddressInfo(shippingAddressBean);
    }

    //没有默认地址
    @Override
    public void noDefault() {
        rlAddAddress.setVisibility(View.VISIBLE);
        rlAddress.setVisibility(View.GONE);
    }


    @Override
    public void setPoint(String usablePoint) {
        this.usablePoint = usablePoint;
        tv_usablePointNum.setText("可用" + usablePoint + "积分");
    }

    @Override
    public void setCouponsNum(String couponsNum) {
        tv_couponUsableNum.setText("（可用" + couponsNum + "张）");
    }

    @Override
    public void commitSuc() {
        ToastUtil.showShort(this, "提交订单成功");
        finish();
    }

    //设置地址信息
    private void setAddressInfo(ShippingAddressBean shippingAddressBean) {
        this.shippingAddressBean = shippingAddressBean;
        tvConsigneeInfo.setText(shippingAddressBean.getRecipients());
        tvPhoneNumber.setText(shippingAddressBean.getTel());
        tvDeliveryAddressInfo.setText(shippingAddressBean.getPcr() + shippingAddressBean.getAddress());
        tvPostcodeInfo.setText(shippingAddressBean.getPostcode());
    }

    /**
     * 判断用户输入的积分是否符合规则
     *
     * @param point 输入的积分
     * @return
     */
    private boolean pointIsOk(String point) {
        if (null == point || "".equals(point)) {
            return true;
        }
        if (Integer.valueOf(point) > Integer.valueOf(usablePoint)) {
            ToastUtil.showShort(this, "您输入的积分超出了可用积分");
            return false;
        }
        if (Integer.valueOf(point) > Integer.valueOf(goodsPrice.substring(2))) {
            ToastUtil.showShort(this, "您输入的积分超出了商品金额");
            return false;
        }
        return true;
    }

    /**
     * 限时优惠信息（减免多少或者折扣）
     * @param i
     */
    private void setCouponInfo(int i) {
        switch (i) {
            case 1:
                tv_couponUsableNum.setText("(优惠￥"+couponsBean.getValValue()+")");
                tvOrderPrice.setText("￥ "+ StringUtil.format(Double.valueOf(goodsPrice.substring(2))-Double.valueOf(couponsBean.getValValue())));
                break;
            case 2:
                tv_couponUsableNum.setText("(打"+(Double.valueOf(couponsBean.getValValue()) / 10.00)+"折)");
                tvOrderPrice.setText("￥ "+StringUtil.format(Double.valueOf(goodsPrice.substring(2)) * (Double.valueOf(couponsBean.getValValue()) / 100.00)));
                break;
        }
    }
}
