package com.example.sg280.fotile.adapter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.ui.activity.LiveActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


/**
 * Created by sg280 on 2016-07-19.
 */
public class HomeLiveViewHolder extends BaseViewHolder<HomeLiveList> {




    private  TextView mlive_title;
    private EasyRecyclerView mrecyleview;
    private ImageView img_arrow;

    public HomeLiveViewHolder(ViewGroup parent) {
        super(parent, R.layout.home_live_item);
           mlive_title=$(R.id.title_name);
          mrecyleview=$(R.id.recyclerView);
          img_arrow=$(R.id.goto_navi);
    }

    @Override
    public void setData(HomeLiveList data) {
        super.setData(data);

        if(data.getClassType().equals("1")){
            mlive_title.setText(data.getClassName());
        }else{
            mlive_title.setText("点播/"+data.getClassName());

        }
       if(data.getLiveList()!=null&data.getLiveList().size()>0){
           HomeItemAdapter adapter = new HomeItemAdapter(getContext());
           mrecyleview.setLayoutManager(new GridLayoutManager(getContext(),2));
           mrecyleview.setAdapter(adapter);
           mrecyleview.setVisibility(View.VISIBLE);
           adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
               @Override
               public void onItemClick(int position) {
                //   if("3".equals(data.getClassType())){
                       Intent intent= LiveActivity.newIntent(getContext(), 1, data.getLiveList().get(position).getID());
                       getContext().startActivity(intent);
                  /* }else {
                       Intent intent=LiveActivity.newIntent(getContext(), 2, data.getLiveList().get(position).getID());
                       getContext().startActivity(intent);
                   }*/
               }
           });
           adapter.addAll(data.getLiveList());
       }else{
           mrecyleview.setVisibility(View.GONE);
       }

        img_arrow.setOnClickListener(view->
        {
            if(data.getClassType().equals("1")) {
                getContext().sendBroadcast(new Intent(Constants.SWITCH_FRAGMENT_LIVE));
            }else {
                getContext().sendBroadcast(new Intent(Constants.SWITCH_FRAGMENT_VEDIO));
            }
    });
}
}
