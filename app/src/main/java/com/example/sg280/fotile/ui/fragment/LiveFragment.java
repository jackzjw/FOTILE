package com.example.sg280.fotile.ui.fragment;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.AdInfoAdapter;
import com.example.sg280.fotile.adapter.LiveListAdapter;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.bean.HomeAdBean;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.presents.Interface.ILiveVedioContacts;
import com.example.sg280.fotile.presents.LvListPresent;
import com.example.sg280.fotile.ui.activity.LiveActivity;
import com.example.sg280.fotile.utils.DensityUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sg280 on 2016-07-19.
 */
public class LiveFragment extends BaseFragment implements ILiveVedioContacts.View,SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener{

    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private LiveListAdapter adapter;
    private LvListPresent present;
   private int page=1;
    private List<VedioDetailsBean> data;
    private List<HomeAdBean> imgList;
    private AdInfoAdapter imgAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_live;
    }

    @Override
    protected void onInitView() {
        data=new ArrayList<VedioDetailsBean>();
        present=new LvListPresent(getActivity(),this, Constants.NEW_LIVE_NAME);
        GridLayoutManager gridlayout = new GridLayoutManager(getActivity(), 2);
        adapter=new LiveListAdapter(getActivity());
        gridlayout.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(2));
        recyclerView.setLayoutManager(gridlayout);
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.setRefreshListener(this);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        present.getVedioResource(page, 10, false);
        present.getAdInfo();//广告栏
     //   setRollViewPagerAdapter();
       adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
           @Override
           public View onCreateView(ViewGroup parent) {
               RollPagerView rollPagerView = new RollPagerView(getActivity());
               rollPagerView.setHintView(new ColorPointHintView(getActivity(),
                       getResources().getColor(R.color.dot_black60), getResources().getColor(R.color.dot_black20)));
               rollPagerView.setPlayDelay(5000);
               rollPagerView.setAnimationDurtion(1000);
               rollPagerView.setHintPadding(0, 0, 0, DensityUtil.dp2px(getActivity(), 8));
               rollPagerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                       DensityUtil.dp2px(getActivity(), 200)));
               imgList = new ArrayList<HomeAdBean>();
               imgAdapter = new AdInfoAdapter(getActivity(), imgList);
               rollPagerView.setAdapter(imgAdapter);
               return rollPagerView;
           }

           @Override
           public void onBindView(View headerView) {

           }
       });
        adapter.setOnItemClickListener((position -> {
           Intent intent= LiveActivity.newIntent(getActivity(),1,data.get(position).getID());
            startActivity(intent);
        }));
    }

    @Override
    public void refresh(List<VedioDetailsBean> result) {

        data.clear();
        data.addAll(result);
        adapter.clear();
        adapter.addAll(result);
        adapter.notifyDataSetChanged();
        if(result.size()%10!=0){
            adapter.pauseMore();
        }
    }

    @Override
    public void fetchSucc(List<VedioDetailsBean> datas) {
     //   LogUtil.e(datas.toString());
            data.addAll(datas);
            adapter.addAll(datas);
            adapter.notifyDataSetChanged();
        if(datas.size()%10!=0){
            adapter.pauseMore();
        }

    }


    public void loadAdInfo(List<HomeAdBean> beans) {
        imgList.clear();
        imgList.addAll(beans);
        imgAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
           present.getVedioResource(1,10,true);
           page=1;
    }

    @Override
    public void onLoadMore() {
        if(data.size()%10==0){
            page++;
            present.getVedioResource(page,10,false);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        present.ondestory();
    }
}
