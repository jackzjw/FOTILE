package com.example.sg280.fotile.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.VedioBean;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by sg280 on 2016-08-12.
 */
public class HomeLiveItemViewHolder extends BaseViewHolder<VedioBean> {
    private ImageView img_1;
    private TextView tv_status1;
    private  TextView tv_name1;
    private  TextView tv_date1;
    private ImageView img_status;
    public HomeLiveItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.home_live_item_item);
        img_1=$(R.id.live_img1);
        tv_status1=$(R.id.live_status1);
        tv_name1=$(R.id.live_name_1);
        tv_date1=$(R.id.live_date_1);
        img_status=$(R.id.img_status);

    }

    @Override
    public void setData(VedioBean data) {
        super.setData(data);
        Glides.getInstance().load(getContext(), data.getLivePixSer(), img_1);
        tv_status1.setText(data.getStatusName());
        tv_name1.setText(data.getLiveName());
        if (data.getStatusName().equals("正在直播")) {
            img_status.setImageResource(R.drawable.home_icon_playing);
        }else {
            img_status.setImageResource(R.drawable.home_icon_prepare);
        }
        tv_date1.setText(data.getStartTime());
    }
}
