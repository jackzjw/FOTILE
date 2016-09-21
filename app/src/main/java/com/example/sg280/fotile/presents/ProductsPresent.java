package com.example.sg280.fotile.presents;

import android.content.Context;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.HomeAdBean;
import com.example.sg280.fotile.model.bean.HomeHotProBean;
import com.example.sg280.fotile.model.bean.HttpResult;
import com.example.sg280.fotile.model.bean.ProductCategoryBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.presents.Interface.IProductsContacts;
import com.example.sg280.fotile.utils.NetUtil;
import com.example.sg280.fotile.utils.ToastUtil;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sg280 on 2016/9/5.
 */
public class ProductsPresent implements IProductsContacts.Present {
    private Context mcontext;
    private IProductsContacts.View mview;


    public ProductsPresent(Context c,IProductsContacts.View view){
        this.mcontext=c;
        this.mview=view;
    }
    @Override
    public void getAdInfo() {
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).getAdlnfo("PRO-KV")
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<HttpResult<List<HomeAdBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                NetUtil.onError(mcontext,e);
            }

            @Override
            public void onNext(HttpResult<List<HomeAdBean>> listHttpResult) {
                  if(listHttpResult.getSuccess().equals("1")){
                      mview.loadAdInfo(listHttpResult.getRows());
                  }else {
                      ToastUtil.showLong(mcontext,listHttpResult.getErrorMessage());
                  }
            }
        });

    }
HttpOnNextListener<HttpResult<List<ProductCategoryBean>>> categorylistener=new HttpOnNextListener<HttpResult<List<ProductCategoryBean>>>() {
    @Override
    public void onNext(HttpResult<List<ProductCategoryBean>> homeHotProBeans) {
        if(homeHotProBeans.getSuccess().equals("1")){
            mview.loadCategoryList(homeHotProBeans.getRows());
        }else{
            ToastUtil.showLong(mcontext,homeHotProBeans.getErrorMessage());
        }

    }
};
    @Override
    public void getProCategory() {

    FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).getProCategory()
    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).subscribe(new ProgressSubscriber(categorylistener,mcontext,true));
    }

    @Override
    public void getCategoryPro(String classid,int start,int limit) {
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).getProCategoryList(classid,start,limit)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .subscribe(new ProgressSubscriber<HttpResult<List<HomeHotProBean>>>(proListener,mcontext,true));

    }
    HttpOnNextListener<HttpResult<List<HomeHotProBean>>> proListener=new HttpOnNextListener<HttpResult<List<HomeHotProBean>>>() {
        @Override
        public void onNext(HttpResult<List<HomeHotProBean>> listHttpResult) {
             if(listHttpResult.getSuccess().equals("1")){
                 mview.loadProList(listHttpResult.getRows());
             }else {
                 ToastUtil.showLong(mcontext,listHttpResult.getErrorMessage());
             }
        }
    };
}
