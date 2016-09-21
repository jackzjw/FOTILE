package com.example.sg280.fotile.ui.fragment;


import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.example.sg280.fotile.R;

import butterknife.Bind;


/**
 * Created by sg280 on 2016/9/1.
 */
public class VedioFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.rbtn_live)
    RadioButton btn_live;
    @Bind(R.id.rbtn_vod)
    RadioButton btn_vod;
    private LiveFragment livefrg;
    private VODFragment vodfrg;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_vedio;
    }

    @Override
    protected void onInitView() {
      btn_live.setChecked(true);
        setLiveShow(0);
        btn_live.setOnClickListener(this);
        btn_vod.setOnClickListener(this);
    }
   private void setLiveShow(int index){
       FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
       switch (index){
           case 0:
               if (vodfrg != null) {
                   transaction.hide(vodfrg);
               }
               if(null==livefrg){
                   livefrg=new LiveFragment();
                   transaction.add(R.id.vedio_containerId,livefrg);
               }else {

                   transaction.show(livefrg);
               }
               break;
                   case 1:
                       if(livefrg!=null){
                           transaction.hide(livefrg);
                       }
               if(null==vodfrg){
                   vodfrg=new VODFragment();
                   transaction.add(R.id.vedio_containerId,vodfrg);
               }else {
                   transaction.show(vodfrg);
               }
               break;
       }
               transaction.commit();


   }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbtn_live:
                setLiveShow(0);
                break;
            case R.id.rbtn_vod:
                setLiveShow(1);
                break;
        }

    }
}
