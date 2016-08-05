package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by sg280 on 2016-07-28.
 */
public class LiveListAdapter extends RecyclerArrayAdapter<VedioDetailsBean>{
    private static final int LIVEING_TYPE=0;
    private static final int PREPARE_TYPE=1;
    public LiveListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if(getItem(position).getStatusName().equals("正在直播")){
            return LIVEING_TYPE;
        }else{
            return PREPARE_TYPE;
        }

    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case LIVEING_TYPE:
                return new Live1ViewHolder(parent);

            case PREPARE_TYPE:
                return new Live2ViewHolder(parent);

            default:
                return new Live2ViewHolder(parent);
        }
    }
}
