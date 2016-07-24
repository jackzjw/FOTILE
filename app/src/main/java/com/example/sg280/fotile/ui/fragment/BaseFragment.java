package com.example.sg280.fotile.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sg280.fotile.widget.LoadingView;

import butterknife.ButterKnife;



public abstract class BaseFragment extends Fragment {
    protected View rootView;
    private LoadingView mLoginView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, rootView);
        mLoginView = new LoadingView(getActivity());
        onInitView();
        return rootView;
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();

    public void showLoadingView() {
        mLoginView.show();
    }

    public void hideLoadingView() {
        mLoginView.hide();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
