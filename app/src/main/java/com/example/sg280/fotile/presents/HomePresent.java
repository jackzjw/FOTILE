package com.example.sg280.fotile.presents;

import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.model.source.HomeDataSouceImp;
import com.example.sg280.fotile.model.source.HomeDataSource;

import java.util.List;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomePresent implements HomeContacts.Present {

    private HomeContacts.View mview;
    private HomeDataSource source;
    public HomePresent(HomeContacts.View view){
        this.mview=view;
        source=new HomeDataSouceImp();
    }

    @Override
    public void start() {

        source.getHomeData(new HomeDataSource.CallBack() {
            @Override
            public void onHomeLoad(List<HomeLiveList> data) {
                 mview.load(data);
                 mview.showNormal();
            }

            @Override
            public void onDataNotAvaiable() {
                    mview.showError();
            }

            @Override
            public void onError(String msg) {
               mview.onError(msg);
            }
        });
    }

    @Override
    public void ondestory() {
           mview=null;
           source=null;
    }
}
