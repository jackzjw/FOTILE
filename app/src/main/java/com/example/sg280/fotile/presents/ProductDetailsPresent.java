package com.example.sg280.fotile.presents;

import android.content.Context;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.NoResponseResult;
import com.example.sg280.fotile.model.bean.ProductsBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.model.source.ProductSubject;
import com.example.sg280.fotile.presents.Interface.IProductContacts;
import com.example.sg280.fotile.utils.ToastUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sg280 on 2016-08-23.
 */
public class ProductDetailsPresent implements IProductContacts.Present {
    private Context mcontext;
    private IProductContacts.View mview;

    public ProductDetailsPresent(Context context,IProductContacts.View view){
        this.mcontext=context;
        this.mview=view;
    }

    @Override
    public void getProductDetails(String proid) {
        String userid= MySelfInfo.getInstance().getId()==null?"":MySelfInfo.getInstance().getId();
        ProductSubject subject=new ProductSubject(new ProgressSubscriber(listener,mcontext),userid,proid);
        FotileRetrofit.getInstance().doHttpDeal2(subject);
    }

    @Override
    public void addCart(HashMap<String, Object> map) {
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).addCart(map)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .subscribe(new ProgressSubscriber<NoResponseResult>(cartListener,mcontext));
    }

    @Override
    public void isCollect(String userid, String itemid,int type) {
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).addCollect(userid,itemid,type)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .subscribe(new ProgressSubscriber<NoResponseResult>(collectListener,mcontext));


    }
    HttpOnNextListener collectListener=new HttpOnNextListener<NoResponseResult>() {
        @Override
        public void onNext(NoResponseResult result) {
            if(result.getSuccess().equals("1")){
                 mview.collectSuccess();
            }else {
                ToastUtil.showLong(mcontext,result.getErrorMessage());
            }

        }
    };
    HttpOnNextListener cartListener=new HttpOnNextListener<NoResponseResult>() {
        @Override
        public void onNext(NoResponseResult result) {
            if(result.getSuccess().equals("1")){
              mview.addCartSuccess();
            }else {
                mview.addCartFailed(result.getErrorMessage());
            }

        }
    };
    HttpOnNextListener listener=new HttpOnNextListener<ProductsBean>() {
        @Override
        public void onNext(ProductsBean bean) {
            mview.fetchData(bean);

        }

    };
}
