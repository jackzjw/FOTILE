package com.example.sg280.fotile.ui.fragment;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.AdInfoAdapter;
import com.example.sg280.fotile.adapter.HomeAdapter;
import com.example.sg280.fotile.adapter.HomeProAdapter;
import com.example.sg280.fotile.model.bean.HomeAdBean;
import com.example.sg280.fotile.model.bean.HomeHotProBean;
import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.presents.HomePresent;
import com.example.sg280.fotile.presents.Interface.IHomeContacts;
import com.example.sg280.fotile.ui.activity.ProductsActivity;
import com.example.sg280.fotile.utils.DensityUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomeFragment extends BaseFragment implements IHomeContacts.View {
    @Bind(R.id.recyclerView1)
    EasyRecyclerView VediorecyclerView;
    @Bind(R.id.network_error_layout)
    ViewStub mNetworkErrorLayout;
    @Bind(R.id.recyclerView2)
    RecyclerView hotRecycleView;
    private HomePresent present;
    private HomeAdapter vedioadapter;
    private View networkErrorView;

    private List<HomeAdBean> imgList;
    private AdInfoAdapter imgAdapter;
    private List<HomeHotProBean> hotProList;
    private HomeProAdapter hotAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView() {
        present = new HomePresent(this, getActivity());
        setVedioAdapter();
        setHotProAdapter();
        present.loadHomeData();
    }

    private void setHotProAdapter() {
        hotRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        hotProList=new ArrayList<HomeHotProBean>();
        hotAdapter=new HomeProAdapter(getActivity());
        hotRecycleView.setAdapter(hotAdapter);
        SpaceDecoration itemDecoration = new SpaceDecoration(DensityUtil.dp2px(getActivity(),2));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        hotRecycleView.addItemDecoration(itemDecoration);
        hotAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = ProductsActivity.newIntent(getActivity(), hotProList.get(position).getID());
                getActivity().startActivity(intent);
            }
        });

    }

    private void setVedioAdapter() {
        VediorecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        vedioadapter = new HomeAdapter(getActivity());
        DividerDecoration vdivider = new DividerDecoration(getResources().getColor(R.color.home_divide_gray),
                DensityUtil.dp2px(getActivity(), 20), 0, 0);
        VediorecyclerView.addItemDecoration(vdivider);
        VediorecyclerView.setAdapter(vedioadapter);
        vedioadapter.addHeader(new RecyclerArrayAdapter.ItemView() {
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
    }

    @Override
    public void loadContent(List<HomeLiveList> list) {
        vedioadapter.clear();
        vedioadapter.addAll(list);
        vedioadapter.notifyDataSetChanged();
    }

    @Override
    public void loadAdInfo(List<HomeAdBean> mlist) {
       // LogUtil.e(mlist.toString());
        imgList.clear();
        imgList.addAll(mlist);
        imgAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadHotPro(List<HomeHotProBean> proBeans) {
    //    LogUtil.e("热销商品"+proBeans.toString());
        hotProList.clear();
        hotProList.addAll(proBeans);
        hotAdapter.addAll(proBeans);
        hotAdapter.notifyDataSetChanged();
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


}
