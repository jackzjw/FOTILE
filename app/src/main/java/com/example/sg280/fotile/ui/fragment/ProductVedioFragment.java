package com.example.sg280.fotile.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.VedioAdapter;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.presents.Interface.ILiveVedioContacts;
import com.example.sg280.fotile.presents.LvListPresent;
import com.example.sg280.fotile.ui.activity.LiveActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sg280 on 2016-07-21.
 */
public class ProductVedioFragment extends BaseFragment implements ILiveVedioContacts.View,SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener{
    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private List<VedioDetailsBean> data;
    private LvListPresent present;
    private VedioAdapter adapter;
    private int page=1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_product_vedio;
    }

    @Override
    protected void onInitView() {

          data=new ArrayList<VedioDetailsBean>();
          present=new LvListPresent(getActivity(),this, Constants.PRODUCT_VEDIO_NAME);
          recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
          adapter=new VedioAdapter(getActivity());
          recyclerView.setAdapterWithProgress(adapter);
          adapter.setMore(R.layout.view_more, this);
          adapter.setNoMore(R.layout.view_nomore);
           present.getVedioResource(page,10,false);
          adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //2表示点播
                Intent intent= LiveActivity.newIntent(getActivity(),2,data.get(position).getID());
                startActivity(intent);
            }
        });

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
