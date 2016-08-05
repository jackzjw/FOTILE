package com.example.sg280.fotile.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.VedioAdapter;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.presents.Interface.ILiveVedioContacts;
import com.example.sg280.fotile.presents.LvListPresent;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sg280 on 2016-07-21.
 */
public class DecorateFragment extends BaseFragment implements ILiveVedioContacts.View,SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener{
    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private List<VedioDetailsBean> data;
    private String classid="0";
    private LvListPresent present;
    private VedioAdapter adapter;
    private int page=1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_decorate;
    }

    @Override
    protected void onInitView() {
        data=new ArrayList<VedioDetailsBean>();
        if(MySelfInfo.getInstance().getVedioCategory()!=null){
            classid=MySelfInfo.getInstance().getVedioCategory().get(0);
        }
          present=new LvListPresent(getActivity(),this,classid);
          recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
          adapter=new VedioAdapter(getActivity());
          recyclerView.setAdapterWithProgress(adapter);
          adapter.setMore(R.layout.view_more, this);
          adapter.setNoMore(R.layout.view_nomore);
          present.start();

    }

    @Override
    public void refresh(List<VedioDetailsBean> datas) {
        data.clear();
        data.addAll(datas);
        adapter.clear();
        adapter.addAll(datas);
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
