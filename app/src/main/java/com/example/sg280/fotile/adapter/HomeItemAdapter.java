package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.VedioBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.security.InvalidParameterException;

/**
 * Created by sg280 on 2016-08-12.
 */
public class HomeItemAdapter extends RecyclerArrayAdapter<VedioBean> {
    public static final int LIVE_TYPE=0;
    public static final int VEDIO_TYPE=1;
    public static final int INVALID_TYPE=2;

    public HomeItemAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
       if("1".equals(getItem(position).getClassID())){
           return  LIVE_TYPE;
       }else {
           return VEDIO_TYPE;
       }
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case LIVE_TYPE:
                return new HomeLiveItemViewHolder(parent);
            case VEDIO_TYPE:
                return new HomeVedioViewHolder(parent);
            default:
                throw new InvalidParameterException();
        }
    }
}
