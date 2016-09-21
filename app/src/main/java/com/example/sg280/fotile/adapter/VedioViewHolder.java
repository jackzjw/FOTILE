package com.example.sg280.fotile.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import retrofit2.http.HEAD;

/**
 * Created by sg280 on 2016-07-30.
 */
public class VedioViewHolder extends BaseViewHolder<VedioDetailsBean> {
    private  TextView tv_comment;
    private  TextView tv_playnum;
    private  TextView tv_decorate;

    private  ImageView img_pic;

    public VedioViewHolder(ViewGroup parent) {
        super(parent, R.layout.vedio_item);
        img_pic=$(R.id.img_pic);
        tv_decorate=$(R.id.decorate_name);
        tv_playnum=$(R.id.play_num);
        tv_comment=$(R.id.tv_comment);
    }

    @Override
    public void setData(VedioDetailsBean data) {
        super.setData(data);
        Glides.getInstance().load(getContext(),data.getLivePixSer(),img_pic);
        tv_decorate.setText(data.getLiveName());
        tv_comment.setText(data.getCommentCount());
        tv_playnum.setText(data.getHitCount());


    }
}
