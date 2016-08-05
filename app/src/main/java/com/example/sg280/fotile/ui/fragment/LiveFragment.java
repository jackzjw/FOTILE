package com.example.sg280.fotile.ui.fragment;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.LiveListAdapter;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.presents.Interface.ILiveVedioContacts;
import com.example.sg280.fotile.presents.LvListPresent;
import com.example.sg280.fotile.ui.activity.LiveActivity;
import com.example.sg280.fotile.widget.TitleBar;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sg280 on 2016-07-19.
 */
public class LiveFragment extends BaseFragment implements ILiveVedioContacts.View,SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener{
    @Bind(R.id.title_bar)
    TitleBar toobar;
    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private String classid="1";
    private LiveListAdapter adapter;
    private LvListPresent present;
   private int page=1;
    private List<VedioDetailsBean> data;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_live;
    }

    @Override
    protected void onInitView() {
        data=new ArrayList<VedioDetailsBean>();
        toobar.setTitle("直   播");
        toobar.setBackgroundColor(getResources().getColor(R.color.title_bg_color));
        //获取直播分类list中默认为0位置的id
        if(MySelfInfo.getInstance().getLiveCategory()!=null){
            classid=MySelfInfo.getInstance().getLiveCategory().get(0);
        }
        present=new LvListPresent(getActivity(),this,classid);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new LiveListAdapter(getActivity());
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.setRefreshListener(this);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        present.start();
        adapter.setOnItemClickListener((position -> {
            Intent intent=new Intent(getActivity(), LiveActivity.class);
            intent.putExtra(LiveActivity.LIVE_ID,data.get(position).getID());
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
    }

    @Override
    public void fetchSucc(List<VedioDetailsBean> datas) {
            data.addAll(datas);
            adapter.addAll(datas);
            adapter.notifyDataSetChanged();
        if(datas.size()%10!=0){
            adapter.pauseMore();
        }

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
}
