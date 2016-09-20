package com.example.sg280.fotile.presents;

import android.content.Context;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.LiveProductBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.SeeBuySubject;
import com.example.sg280.fotile.presents.Interface.ISeeBuyContacts;

import java.util.List;

/**
 * Created by sg280 on 2016-08-11.
 */
public class SeeBuyPresent implements ISeeBuyContacts.Present {
    private Context mcontext;
    private ISeeBuyContacts.View mview;
    public SeeBuyPresent(Context context,ISeeBuyContacts.View view){
        this.mcontext=context;
        this.mview=view;

    }
    @Override
    public void loadData(String action, String liveid, int page) {
        SeeBuySubject subject=new SeeBuySubject(new ProgressSubscriber(nextListener,mcontext,false),liveid,action,page,10);
        FotileRetrofit.getInstance().doHttpDeal(subject);
    }
    HttpOnNextListener nextListener=new HttpOnNextListener<List<LiveProductBean>>() {
        @Override
        public void onNext(List<LiveProductBean> data) {
            mview.fetchSuccess(data);
        }
    };
}
