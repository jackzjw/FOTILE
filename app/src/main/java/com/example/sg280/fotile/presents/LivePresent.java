package com.example.sg280.fotile.presents;

import android.content.Context;

import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.LiveViewSubject;
import com.example.sg280.fotile.presents.Interface.ILiveContacts;

/**
 * Created by sg280 on 2016-08-02.
 */
public class LivePresent implements ILiveContacts.Present {
    private Context context;
    private ILiveContacts.View mview;

    public  LivePresent(Context context,ILiveContacts.View view){
        this.context=context;
        this.mview=view;
    }
    @Override
    public void getLiveView(String type, String liveid) {
        LiveViewSubject subject=new LiveViewSubject(new ProgressSubscriber(Liveviewlistener,context),type,liveid);

    }
    HttpOnNextListener<VedioDetailsBean> Liveviewlistener=new HttpOnNextListener<VedioDetailsBean>() {
        @Override
        public void onNext(VedioDetailsBean vedioDetailsBean) {
            mview.getLiveViewSucc(vedioDetailsBean);
        }
    };
}
