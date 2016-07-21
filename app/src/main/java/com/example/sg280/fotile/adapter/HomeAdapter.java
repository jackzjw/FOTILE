package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.security.InvalidParameterException;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomeAdapter extends RecyclerArrayAdapter<HomeLiveList> {
    public static final int LIVE_TYPE=0;
    public static final int VEDIO_TYPE=1;
    public static final int INVALID_TYPE=2;
    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if(getItem(position).getID().equals("1")){
            return LIVE_TYPE;
        }else{
                return VEDIO_TYPE;
        }
      //  return INVALID_TYPE;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
         switch (viewType){
             case LIVE_TYPE:
                 return new HomeLiveViewHolder(parent);
             case VEDIO_TYPE:
                 return new HomeVedioViewHolder(parent);
             default:
                 throw new InvalidParameterException();
         }

    }


}
