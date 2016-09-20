package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.CollectLiveBean;
import com.example.sg280.fotile.model.bean.CollectProductBean;
import com.example.sg280.fotile.utils.StringUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by Tian on 2016/8/10.
 */
public class LiveOrDemandAdapter extends RecyclerView.Adapter<LiveOrDemandViewHolder> {

    private Context context;
    private List<CollectLiveBean> list;//直播点播list

    public LiveOrDemandAdapter(Context context, List<CollectLiveBean> list) {
        this.context = context;
        this.list = list;
    }

    public LiveOrDemandAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<CollectLiveBean> list) {
        this.list = list;
    }

    public List<CollectLiveBean> getList() {
        return list;
    }

    @Override
    public LiveOrDemandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LiveOrDemandViewHolder(parent,context);
    }

    @Override
    public void onBindViewHolder(LiveOrDemandViewHolder holder, int position) {
        holder.setData(list.get(position));

        switch (list.get(position).getClassType()){
            case "1"://直播
            case "3"://第三方直播
                holder.rl_living.setVisibility(View.VISIBLE);
                holder.tv_time_video.setVisibility(View.VISIBLE);
                holder.tv_vod_my_collect.setVisibility(View.GONE);
                holder.rl_watch_comment_number.setVisibility(View.GONE);
                holder.tv_time_my_collect.setVisibility(View.GONE);

                if("1".equals(list.get(position).getStatusCode())){//正在直播
                    holder.tv_time_video.setText(list.get(position).getStartTime());

                }else if("2".equals(list.get(position).getStatusCode())){//即将开始
                    holder.iv_living_my_collect.setImageResource(R.drawable.live_icon_prepare);
                    holder.rl_living.setBackgroundResource(R.drawable.shape_living_gray_my_collect);
                    holder.tv_living_my_collect.setTextColor(ContextCompat.getColor(context,R.color.white));
                    holder.tv_time_video.setText(list.get(position).getPreStartTime());
                }else if("3".equals(list.get(position).getStatusCode())){//直播完毕
                    holder.tv_time_video.setText(list.get(position).getEndTime());
                }


                break;
            case "2"://点播
                holder.rl_living.setVisibility(View.GONE);
                holder.tv_time_video.setVisibility(View.GONE);
                holder.tv_vod_my_collect.setVisibility(View.VISIBLE);
                holder.rl_watch_comment_number.setVisibility(View.VISIBLE);
                holder.tv_time_my_collect.setVisibility(View.VISIBLE);

                holder.tv_watch_number.setText(list.get(position).getHitCount());
                holder.tv_comment_number.setText(list.get(position).getCommentCount());
                break;

            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (!StringUtil.listIsNull(list)) {
            return list.size();
        }
        return 0;
    }


}
