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


    public LiveListAdapter(Context context) {
        super(context);

    }




    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
       return new Live2ViewHolder(parent);
          //  return new Live2ViewHolder(parent);



    }
}
