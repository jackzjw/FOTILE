package com.example.sg280.fotile.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.RelativeLayout;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.SeeBuyAdapter;
import com.example.sg280.fotile.model.bean.LiveProductBean;
import com.example.sg280.fotile.presents.Interface.ISeeBuyContacts;
import com.example.sg280.fotile.presents.SeeBuyPresent;
import com.example.sg280.fotile.ui.activity.LiveActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.Bind;

/**
 * Created by sg280 on 2016-08-09.
 */
public class SeeAndBuyFragment extends BaseFragment implements ISeeBuyContacts.View,RecyclerArrayAdapter.OnLoadMoreListener{
    @Bind(R.id.rel_discount)
    RelativeLayout rel_discount;
    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private SeeBuyPresent present;
    private SeeBuyAdapter adapter;
    private int page=1;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_see_and_buy;
    }

    @Override
    protected void onInitView() {
         present=new SeeBuyPresent(getActivity(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         adapter=new SeeBuyAdapter(getActivity());
         recyclerView.setAdapter(adapter);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);

        present.loadData("GetLivePro", LiveActivity.liveid,1);

    }

    @Override
    public void fetchSuccess(List<LiveProductBean> data) {
        adapter.addAll(data);
   
    }

    @Override
    public void onLoadMore() {
        page++;
      present.loadData("GetLivePro", LiveActivity.liveid,page);
    }
}
