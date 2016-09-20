package com.example.sg280.fotile.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.ui.activity.MessageCenterActivity;
import com.example.sg280.fotile.ui.activity.ModifyUserInfoActivity;
import com.example.sg280.fotile.ui.activity.MyCollectActivity;
import com.example.sg280.fotile.ui.activity.MyCouponsActivity;
import com.example.sg280.fotile.ui.activity.MyOrderActivity;
import com.example.sg280.fotile.ui.activity.ShippingAddressActivity;
import com.example.sg280.fotile.utils.IntentUtil;
import com.example.sg280.fotile.utils.LoginUtil;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.example.sg280.fotile.widget.CircleImageView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tian on 2016/8/5.
 */
public class MyAccountFragment extends BaseFragment {
    @Bind(R.id.rl_user_info)//用户信息的RelativeLayout
            RelativeLayout rl_user_info;
    @Bind(R.id.rl_my_order)//我的订单的RelativeLayout
            RelativeLayout rl_my_order;
    @Bind(R.id.rl_wait_pay)//我的订单中代付款的RelativeLayout
            RelativeLayout rl_wait_pay;
    @Bind(R.id.rl_wait_shipments)//我的订单中待发货的RelativeLayout
            RelativeLayout rl_wait_shipments;
    @Bind(R.id.rl_wait_receipt)//我的订单中待收货的RelativeLayout
            RelativeLayout rl_wait_receipt;
    @Bind(R.id.rl_deal_is_done)//我的订单中已完成的RelativeLayout
            RelativeLayout rl_deal_is_done;
    @Bind(R.id.rl_my_coupons)//我的优惠码的RelativeLayout
            RelativeLayout rl_my_coupons;
    @Bind(R.id.rl_my_collections)//我的收藏的RelativeLayout
            RelativeLayout rl_my_collections;
    @Bind(R.id.rl_msg_center)//消息中心的RelativeLayout
            RelativeLayout rl_msg_center;
    @Bind(R.id.rl_point_center)
            RelativeLayout rl_point_center;//积分中心的RelativeLayout
    @Bind(R.id.rl_my_address)
            RelativeLayout rl_my_address;//我的收获地址的RelativeLayout
    @Bind(R.id.civ_avatar_account)
            CircleImageView civ_avatar_account;//用户头像
    @Bind(R.id.tv_nickname_account)
            TextView tv_nickname_account;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_myaccount;
    }

    @Override
    protected void onInitView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        tv_nickname_account.setText(SharedPreferencesUtil.getNickName(getActivity().getApplication()));
    }


    @OnClick({R.id.rl_user_info,R.id.rl_my_order,R.id.rl_wait_pay,R.id.rl_wait_shipments,R.id.rl_wait_receipt,
            R.id.rl_deal_is_done,R.id.rl_my_coupons,R.id.rl_my_collections,R.id.rl_msg_center,R.id.rl_point_center
    ,R.id.rl_my_address})
    public void OnClick(View view){

        switch (view.getId()){
            case R.id.rl_user_info://点击用户布局跳转修改用户信息界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        IntentUtil.jumpToActivity(getActivity(), ModifyUserInfoActivity.class);
                    }
                });

                break;
            case R.id.rl_my_order://点击查看全部订单跳转到我的订单界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        IntentUtil.jumpToActivity(getActivity(), MyOrderActivity.class);
                    }
                });

                break;
            case R.id.rl_wait_pay://点击待支付跳转到我的订单待付款界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        IntentUtil.jumpToActivity(getActivity(), MyOrderActivity.class);
                    }
                });
                break;
            case R.id.rl_wait_shipments://点击待支付跳转到我的订单待发货界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        IntentUtil.jumpToActivity(getActivity(), MyOrderActivity.class);
                    }
                });
                break;
            case R.id.rl_wait_receipt://点击待支付跳转到我的订单待收货界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        IntentUtil.jumpToActivity(getActivity(), MyOrderActivity.class);
                    }
                });
                break;
            case R.id.rl_deal_is_done://点击待支付跳转到我的订单已完成界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        IntentUtil.jumpToActivity(getActivity(), MyOrderActivity.class);
                    }
                });
                break;
            case R.id.rl_my_coupons://点击我的优惠码跳转到我的优惠码界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        IntentUtil.jumpToActivity(getActivity(), MyCouponsActivity.class);
                    }
                });

                break;
            case R.id.rl_my_collections://点击我的收藏跳转到我的收藏界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        IntentUtil.jumpToActivity(getActivity(), MyCollectActivity.class);
                    }
                });

                break;
            case R.id.rl_msg_center://点击消息中心跳转到消息中心界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        IntentUtil.jumpToActivity(getActivity(), MessageCenterActivity.class);
                    }
                });

                break;
            case R.id.rl_point_center://点击积分中心跳转到积分中心界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        IntentUtil.jumpToActivity(getActivity(), MessageCenterActivity.class);
                    }
                });
                break;
            case R.id.rl_my_address://点击我的收货地址跳转到我的收货地址界面
                LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
                    @Override
                    public void onLogin() {
                        Intent intent = new Intent(getActivity(), ShippingAddressActivity.class);
                        intent.putExtra("account", "1");
                        startActivity(intent);
                    }
                });

                break;

        }
    }

}
