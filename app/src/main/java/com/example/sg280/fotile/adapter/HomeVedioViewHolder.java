package com.example.sg280.fotile.adapter;


import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.VedioBean;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by sg280 on 2016-07-19.
 */
public class HomeVedioViewHolder extends BaseViewHolder<VedioBean> {



    private ImageView img_vedio_1;
    private  TextView tv_name_1;


    public HomeVedioViewHolder(ViewGroup parent) {
        super(parent, R.layout.home_vedio_item);

        img_vedio_1=$(R.id.vedio_img_1);
        tv_name_1=$(R.id.vedio_name_1);


    }

    @Override
    public void setData(VedioBean data) {
        super.setData(data);

        Glides.getInstance().load(getContext(), data.getLivePixSer(), img_vedio_1);
        tv_name_1.setText(data.getLiveName());


    }
}
