package com.example.sg280.fotile.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewStub;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.HomeAdapter;
import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.presents.HomeContacts;
import com.example.sg280.fotile.presents.HomePresent;
import com.example.sg280.fotile.utils.DensityUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.List;

import butterknife.Bind;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomeFragment extends BaseFragment implements HomeContacts.View,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @Bind(R.id.network_error_layout)
    ViewStub mNetworkErrorLayout;
    private BasePresenter present;
    private HomeAdapter adapter;
    private View networkErrorView;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView() {
         present=new HomePresent(this,getActivity());
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      adapter=new HomeAdapter(getActivity());
        DividerDecoration divider = new DividerDecoration(getResources().getColor(R.color.home_divide_gray), DensityUtil.dp2px(getActivity(),20),0,0);
      recyclerView.addItemDecoration(divider);
      recyclerView.setAdapter(adapter);
        recyclerView.setRefreshListener(this);
         present.start();
    }

    @Override
    public void load(List<HomeLiveList> list) {

          adapter.clear();
            adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
              hideLoadingView();
              ToastUtil.showLong(getActivity(), error);
    }

    @Override
    public void showError() {
        hideLoadingView();
        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.VISIBLE);
            return;
        }

        networkErrorView = mNetworkErrorLayout.inflate();
    }

    @Override
    public void showNormal() {


        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.GONE);
        }
    }
    @Override
    public void onRefresh() {
        present.start();
    }
}
