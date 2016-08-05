package com.example.sg280.fotile.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by sg280 on 2016-07-29.
 */
public class Live1ViewHolder extends BaseViewHolder<VedioDetailsBean> {
    private  TextView tv_date;
    private  TextView tv_name;
    private  ImageView img_big;

    public Live1ViewHolder(ViewGroup parent) {
        super(parent, R.layout.live_item1);
        img_big=$(R.id.live_img_big);
        tv_name=$(R.id.live_name);
        tv_date=$(R.id.live_date_1);
    }

    @Override
    public void setData(VedioDetailsBean data) {
        super.setData(data);
        Glides.getInstance().load(getContext(),data.getLivePixSer(),img_big);
        tv_name.setText(data.getLiveName());
        tv_date.setText(data.getStartTime());
    }
}
