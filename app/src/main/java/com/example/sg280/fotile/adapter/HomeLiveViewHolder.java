package com.example.sg280.fotile.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomeLiveViewHolder extends BaseViewHolder<HomeLiveList> {


    private  TextView mlive_title;
    private  ImageView img_1;
    private  TextView tv_status1;
    private  TextView tv_name1;
    private  TextView tv_date1;
    private  ImageView img_2;
    private  TextView tv_status2;
    private  TextView tv_name2;
    private  TextView tv_date2;

    public HomeLiveViewHolder(ViewGroup parent) {
        super(parent, R.layout.home_live_item);
           mlive_title=$(R.id.title_name);
           img_1=$(R.id.live_img1);
           tv_status1=$(R.id.live_status1);
           tv_name1=$(R.id.live_name_1);
           tv_date1=$(R.id.live_date_1);
            img_2=$(R.id.live_img2);
           tv_status2=$(R.id.live_status2);
           tv_name2=$(R.id.live_name_2);
            tv_date2=$(R.id.live_date_2);
    }

    @Override
    public void setData(HomeLiveList data) {
        super.setData(data);
        mlive_title.setText(data.getClassName());
        Glides.getInstance().load(getContext(),data.getLiveList().get(0).getLivePixSer(),img_1);
        tv_status1.setText(data.getLiveList().get(0).getStatusName());
        tv_name1.setText(data.getLiveList().get(0).getLiveName());
        tv_date1.setText(data.getLiveList().get(0).getStartTime());
        Glides.getInstance().load(getContext(), data.getLiveList().get(1).getLivePixSer(), img_2);
        tv_status2.setText(data.getLiveList().get(1).getStatusName());
        tv_name2.setText(data.getLiveList().get(1).getLiveName());
        tv_date2.setText(data.getLiveList().get(1).getStartTime());

    }
}
