package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.CollectLiveBean;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * 我的收藏的直播点播实体类
 * Created by Tian on 2016/8/10.
 */
public class LiveOrDemandViewHolder extends BaseViewHolder<CollectLiveBean> {

    public RelativeLayout rl_living;//正在直播的RelativeLayout
    public TextView tv_vod_my_collect;//点播
    public TextView tv_time_my_collect;//即将开始直播或者点播的时间
    public TextView tv_title_video;//直播点播名称
    public TextView tv_time_video;//正在直播的时间
    public TextView tv_watch_number;//观看数
    public TextView tv_comment_number;//评论数
    public RelativeLayout rl_watch_comment_number;//点击数与评论数的RelativeLayout
    public ImageView iv_living_my_collect;//正在直播，即将开始等icon
    public TextView tv_living_my_collect;//正在直播，即将开始等的文字
    public ImageView iv_image;//图片
    private Context context;


    public LiveOrDemandViewHolder(ViewGroup parent,Context context) {
        super(parent, R.layout.item_live_my_collect);
        this.context = context;
        rl_living = $(R.id.rl_living);
        iv_image = $(R.id.iv_image);
        tv_vod_my_collect = $(R.id.tv_vod_my_collect);
        tv_time_my_collect = $(R.id.tv_time_my_collect);
        tv_title_video = $(R.id.tv_title_video);
        tv_time_video = $(R.id.tv_time_video);
        tv_watch_number = $(R.id.tv_watch_number);
        tv_comment_number = $(R.id.tv_comment_number);
        rl_watch_comment_number = $(R.id.rl_watch_comment_number);
        iv_living_my_collect = $(R.id.iv_living_my_collect);
        tv_living_my_collect = $(R.id.tv_living_my_collect);
    }

    @Override
    public void setData(CollectLiveBean data) {
        super.setData(data);
        tv_title_video.setText(data.getLiveName());
        Glides.getInstance().load(context,data.getLivePixSer(),iv_image);
        tv_living_my_collect.setText(data.getStatusName());

    }
}
