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
public class Live2ViewHolder extends BaseViewHolder<VedioDetailsBean>{

    private  TextView tv_status;
    private  ImageView img_status;
    private  ImageView img_pic;
    private  TextView tv_name;
    private  TextView tv_date;
    public Live2ViewHolder(ViewGroup parent) {
        super(parent, R.layout.live_item2);
        img_pic=$(R.id.live_img1);
        img_status=$(R.id.img_status);
        tv_status=$(R.id.live_status);
        tv_name=$(R.id.live_name);
        tv_date=$(R.id.live_date_1);
    }

    @Override
    public void setData(VedioDetailsBean data) {
        super.setData(data);
        Glides.getInstance().load(getContext(),data.getLivePixSer(),img_pic);

        if(data.getStatusName().equals("正在直播")){
            img_status.setImageResource(R.drawable.home_icon_playing);
        }else {
            img_status.setImageResource(R.drawable.home_icon_prepare);
        }
        tv_status.setText(data.getStatusName());
        tv_date.setText(data.getStartTime());
        tv_name.setText(data.getLiveName());
    }
}
