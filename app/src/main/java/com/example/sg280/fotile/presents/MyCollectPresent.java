package com.example.sg280.fotile.presents;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.CollectionProductAdapter;
import com.example.sg280.fotile.adapter.LiveOrDemandAdapter;
import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.CollectLiveBean;
import com.example.sg280.fotile.model.bean.CollectProductBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.MyCollectSubject;
import com.example.sg280.fotile.presents.Interface.MyCollectContacts;
import com.example.sg280.fotile.utils.DensityUtil;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tian on 2016/8/26.
 */
public class MyCollectPresent implements MyCollectContacts.present {

    private Context context;
    private CollectionProductAdapter productAdapter;//商品的Adapter
    private LiveOrDemandAdapter liveOrDemandAdapter;//直播点播的Adapter
    private List<CollectLiveBean> liveList = new ArrayList<>();//直播点播list
    private List<CollectProductBean> productList = new ArrayList<>();//产品list
    private RecyclerView erv_myCollection;

    public MyCollectPresent(Context context,RecyclerView erv_myCollection) {
        this.context = context;
        this.erv_myCollection = erv_myCollection;
    }

    @Override
    public void getMyCollectList(int itemType) {

        if(1 == itemType){//点播直播
            MyCollectSubject subject1 = new MyCollectSubject(new ProgressSubscriber(getLiveOnNextListener,context),
                    SharedPreferencesUtil.getId(context),1,"10",1);
            FotileRetrofit.getInstance().doHttpDeal(subject1);
        }else{//商品
            MyCollectSubject subject2 = new MyCollectSubject(new ProgressSubscriber(getProductsOnNextListener,context),
                    SharedPreferencesUtil.getId(context),2,"10",1);
            FotileRetrofit.getInstance().doHttpDeal(subject2);
        }
    }

    //商品的HttpOnNextListener
    private HttpOnNextListener getProductsOnNextListener = new HttpOnNextListener<List<CollectProductBean>>() {
        @Override
        public void onNext(List<CollectProductBean> collectProductBeanList) {

            erv_myCollection.setLayoutManager(new LinearLayoutManager(context));

            if(null == productList || productList.size() == 0){
                productList = collectProductBeanList;
                //添加分割线
                DividerDecoration divider = new DividerDecoration(ContextCompat.getColor(context, R.color.hui_bg), DensityUtil.dp2px(context, 1), 0, 0);
                erv_myCollection.addItemDecoration(divider);
            }else{
                productList.addAll(collectProductBeanList);
            }
            productAdapter = new CollectionProductAdapter(context,productList);
            erv_myCollection.setAdapter(productAdapter);
        }
    };

    //直播点播的HttpOnNextListener
    private HttpOnNextListener getLiveOnNextListener = new HttpOnNextListener<List<CollectLiveBean>>() {

        @Override
        public void onNext(List<CollectLiveBean> collectLiveList) {

            erv_myCollection.setLayoutManager(new GridLayoutManager(context, 2));
            if(null == liveList || liveList.size() == 0){
                liveList = collectLiveList;
            }
            liveOrDemandAdapter = new LiveOrDemandAdapter(context,liveList);
            erv_myCollection.setAdapter(liveOrDemandAdapter);
        }
    };

    @Override
    public void ondestory() {

    }
}
