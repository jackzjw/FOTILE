package com.example.sg280.fotile.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.presents.Interface.MyCollectContacts;
import com.example.sg280.fotile.presents.MyCollectPresent;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tian on 2016/8/3.
 * 我的收藏界面
 */
public class MyCollectActivity extends BaseActivity implements MyCollectContacts.View {

    @Bind(R.id.iv_back_title)//标题的返回图标
            ImageView iv_back_title;
    @Bind(R.id.tv_title)//标题栏标题
            TextView tv_title;
    @Bind({R.id.tv_live_or_demand, R.id.tv_goods})//直播/点播，商品
            List<TextView> collectionTitleList;
    @Bind({R.id.tv_live_or_demand_line, R.id.tv_goods_line})//直播/点播，商品下面的下划线
            List<TextView> lineList;
    @Bind(R.id.erv_myCollection)//我的收藏的RecyclerView
            RecyclerView erv_myCollection;

    private MyCollectPresent myCollectPresent;//商品的Adapter



    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void onInitView() {

        tv_title.setText(R.string.my_collections);//修改标题栏的标题
        myCollectPresent = new MyCollectPresent(this,erv_myCollection);
        getCollectLiveOrDemand();//默认点击的直播点播

    }

    //返回上一界面
    @OnClick(R.id.iv_back_title)
    void goBack() {
        onBackPressed();
    }

    //点击直播/点播
    @OnClick(R.id.tv_live_or_demand)
    void getCollectLiveOrDemand() {
        init(0);
        myCollectPresent.getMyCollectList(1);

    }

    //点击商品
    @OnClick(R.id.tv_goods)
    void getCollectProduct() {
        init(1);
        myCollectPresent.getMyCollectList(2);
    }


    //根据点击的收藏类型做相应的界面显示改变
    private void init(int i) {
        initTitle();
        initLine();
        collectionTitleList.get(i).setTextColor(ContextCompat.getColor(this, R.color.red));
        lineList.get(i).setVisibility(View.VISIBLE);
    }

    //初始化字体颜色
    private void initTitle() {
        for (TextView textView : collectionTitleList) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.hui_text));
        }
    }

    //初始化下划线的显示状态
    private void initLine() {
        for (TextView textView : lineList) {
            textView.setVisibility(View.INVISIBLE);
        }
    }

}
