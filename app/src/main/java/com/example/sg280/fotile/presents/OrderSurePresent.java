package com.example.sg280.fotile.presents;

import android.content.Context;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.CommitOrderBackMsgBean;
import com.example.sg280.fotile.model.bean.CouponsNumBean;
import com.example.sg280.fotile.model.bean.ShippingAddressBean;
import com.example.sg280.fotile.model.bean.UserInfoBean;
import com.example.sg280.fotile.model.source.CommitOrderSubject;
import com.example.sg280.fotile.model.source.GetCouponsNumSubject;
import com.example.sg280.fotile.model.source.GetDefaultAddressSubject;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.UserInfoSubject;
import com.example.sg280.fotile.presents.Interface.OrderSureContacts;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;

/**
 * Created by Tian on 2016/8/24.
 */
public class OrderSurePresent implements OrderSureContacts.present {
    private final String DEFAULT_ACTION = "geteditAddress";
    private Context context;
    private OrderSureContacts.View view;

    public OrderSurePresent(Context context, OrderSureContacts.View view) {
        this.context = context;
        this.view = view;
    }

    //获取默认收货地址
    @Override
    public void getDefaultAddress() {

        HttpOnNextListener getDefaultOnNextListener = new HttpOnNextListener<ShippingAddressBean>() {
            @Override
            public void onNext(ShippingAddressBean shippingAddressBean) {
                //判断是否有默认地址
                if (null != shippingAddressBean) {
                    view.setDefault(shippingAddressBean);
                }
            }
        };

        GetDefaultAddressSubject subject = new GetDefaultAddressSubject(new ProgressSubscriber(getDefaultOnNextListener, context), "1", SharedPreferencesUtil.getId(context), DEFAULT_ACTION);
        FotileRetrofit.getInstance().doHttpDeal2(subject);
    }

    //提交订单
    @Override
    public void commitOrder(String shopCarId, String userId, String couponcardid, String pcr, String address, String tel, String postcode, String recipients, String paytype, String remark, String invoicestatus, String integral, String isliji) {

        HttpOnNextListener commitOrderNextListener = new HttpOnNextListener<CommitOrderBackMsgBean>() {
            @Override
            public void onNext(CommitOrderBackMsgBean bean) {
                view.commitSuc();

            }
        };


        CommitOrderSubject commitOrderSubject = new CommitOrderSubject(new ProgressSubscriber(commitOrderNextListener,context),
                shopCarId,userId,couponcardid,pcr,address,tel,postcode,recipients,paytype,remark,invoicestatus,integral,isliji);
        FotileRetrofit.getInstance().doHttpDeal2(commitOrderSubject);
    }

    //提交订单(立即购买)
    @Override
    public void commitOrderAtOnce(String productid, String skuid, String productnum, String userId, String couponcardid, String pcr, String address, String tel, String postcode, String recipients, String paytype, String remark, String invoicestatus, String integral, String isliji) {
        HttpOnNextListener commitOrderNextListener = new HttpOnNextListener<CommitOrderBackMsgBean>() {
            @Override
            public void onNext(CommitOrderBackMsgBean bean) {
                view.commitSuc();

            }
        };

        CommitOrderSubject commitOrderSubject = new CommitOrderSubject(new ProgressSubscriber(commitOrderNextListener,context),
                productid,skuid,productnum,userId,couponcardid,pcr,address,tel,postcode,recipients,paytype,remark,invoicestatus,integral,isliji);
        FotileRetrofit.getInstance().doHttpDeal2(commitOrderSubject);
    }

    //获取当前可用优惠券数量
    @Override
    public void getCouponsNum(String action,String shopCarId) {
        HttpOnNextListener couponsNumNextListener = new HttpOnNextListener<CouponsNumBean>() {
            @Override
            public void onNext(CouponsNumBean couponsNumBean) {
                view.setCouponsNum(couponsNumBean.getCount());
            }
        };

        GetCouponsNumSubject couponsNumSubject = new GetCouponsNumSubject(new ProgressSubscriber(couponsNumNextListener,context),
                SharedPreferencesUtil.getId(context),action,shopCarId);
        FotileRetrofit.getInstance().doHttpDeal2(couponsNumSubject);

    }

    //获取当前可用优惠券数量(立即购买)
    @Override
    public void getCouponsNumAtOnce(String action, String productid, String skuid, String productnum) {
        HttpOnNextListener couponsNumNextListener = new HttpOnNextListener<CouponsNumBean>() {
            @Override
            public void onNext(CouponsNumBean couponsNumBean) {
                view.setCouponsNum(couponsNumBean.getCount());
            }
        };

        GetCouponsNumSubject couponsNumSubject = new GetCouponsNumSubject(new ProgressSubscriber(couponsNumNextListener,context),
                SharedPreferencesUtil.getId(context),action,productid,skuid,productnum);
        FotileRetrofit.getInstance().doHttpDeal2(couponsNumSubject);
    }

    //获取可用积分
    @Override
    public void getPoint() {

        HttpOnNextListener pointNextListener = new HttpOnNextListener<UserInfoBean>() {
            @Override
            public void onNext(UserInfoBean userInfoBean) {
                String pointAll = (null == userInfoBean.getIntegralTotal())?"0":userInfoBean.getIntegralTotal();
                String pointUsed = (null == userInfoBean.getIntegralUsed())?"0":userInfoBean.getIntegralUsed();
                view.setPoint(String.valueOf(Integer.valueOf(pointAll)-Integer.valueOf(pointUsed)));
            }
        };
        UserInfoSubject userInfoSubject = new UserInfoSubject(new ProgressSubscriber(pointNextListener,context),
                SharedPreferencesUtil.getId(context));
        FotileRetrofit.getInstance().doHttpDeal2(userInfoSubject);

    }


    @Override
    public void ondestory() {

    }
}
