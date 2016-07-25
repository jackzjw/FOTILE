package com.example.sg280.fotile.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomeVedioViewHolder extends BaseViewHolder<HomeLiveList> {


    private  TextView mlive_title;
    private  ImageView img_arrow;
    private ImageView img_vedio_1;
    private  TextView tv_name_1;
    private ImageView img_vedio_2;
    private  TextView tv_name_2;
    private ImageView img_vedio_3;
    private  TextView tv_name_3;
    private ImageView img_vedio_4;
    private  TextView tv_name_4;

    public HomeVedioViewHolder(ViewGroup parent) {
        super(parent, R.layout.home_vedio_item);
        mlive_title=$(R.id.title_name);
        img_vedio_1=$(R.id.vedio_img_1);
        tv_name_1=$(R.id.vedio_name_1);
        img_vedio_2=$(R.id.vedio_img_2);
        tv_name_2=$(R.id.vedio_name_2);
        img_vedio_3=$(R.id.vedio_img_3);
        tv_name_3=$(R.id.vedio_name_3);
        img_vedio_4=$(R.id.vedio_img_4);
        tv_name_4=$(R.id.vedio_name_4);
        img_arrow=$(R.id.goto_navi);

    }

    @Override
    public void setData(HomeLiveList data) {
        super.setData(data);
        mlive_title.setText("点播/"+data.getClassName());
        if (!data.getLiveList().isEmpty()){Glides.getInstance().load(getContext(), data.getLiveList().get(0).getLivePixSer(), img_vedio_1);
        tv_name_1.setText(data.getLiveList().get(0).getLiveName());
        Glides.getInstance().load(getContext(), data.getLiveList().get(1).getLivePixSer(), img_vedio_2);
        tv_name_2.setText(data.getLiveList().get(1).getLiveName());
        Glides.getInstance().load(getContext(), data.getLiveList().get(2).getLivePixSer(), img_vedio_3);
        tv_name_3.setText(data.getLiveList().get(2).getLiveName());
        Glides.getInstance().load(getContext(), data.getLiveList().get(3).getLivePixSer(), img_vedio_4);
        tv_name_4.setText(data.getLiveList().get(3).getLiveName());
            img_arrow.setOnClickListener(view ->
                    getContext().sendBroadcast(new Intent(Constants.SWITCH_FRAGMENT_VEDIO)));
    }
    }
}
