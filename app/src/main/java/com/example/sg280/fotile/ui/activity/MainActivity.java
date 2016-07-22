package com.example.sg280.fotile.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.ui.fragment.HomeFragment;
import com.example.sg280.fotile.ui.fragment.LiveFragment;
import com.example.sg280.fotile.ui.fragment.MyFragment;
import com.example.sg280.fotile.ui.fragment.ShoppingCartFragement;
import com.example.sg280.fotile.ui.fragment.VedioFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

   @Bind({R.id.home_navi_img,R.id.live_navi_img,R.id.vedio_navi_img,R.id.cart_navi_img,R.id.user_navi_img})
    List<ImageView> img_navis;
    @Bind({R.id.home_navi_tv,R.id.live_navi_tv,R.id.vedio_navi_tv,R.id.cart_navi_tv,R.id.user_navi_tv})
    List<TextView> tv_navis;
    @Bind({R.id.rel_home,R.id.rel_live,R.id.rel_vedio,R.id.rel_cart,R.id.rel_user})
    List<RelativeLayout> rel_navis;
    private Fragment homefrg;
    private Fragment livefrg;
    private Fragment vodfrg;
    private Fragment cartfrg;
    private Fragment userfrg;
    private int[] drawables={R.drawable.navi_home_gray,R.drawable.navi_live_gray,R.drawable.navi_vod_gray,R.drawable.navi_cart_gray,R.drawable.navi_user_gray};
    private SwitchFragementBroadcast broadcast;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       for(RelativeLayout navi:rel_navis){
           navi.setOnClickListener(this);
       }
        setBroadCast();
      setChoiceItem(0);



    }
//广播，点击首页的右箭头，跳转到点播/直播的Fragment;
    private void setBroadCast() {
        broadcast=new SwitchFragementBroadcast();
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(Constants.SWITCH_FRAGMENT_LIVE);
        intentfilter.addAction(Constants.SWITCH_FRAGMENT_VEDIO);
        registerReceiver(broadcast, intentfilter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rel_home:
                setChoiceItem(0);
                break;
            case R.id.rel_live:
                setChoiceItem(1);
                break;
            case R.id.rel_vedio:
                setChoiceItem(2);
                break;
            case R.id.rel_cart:
                setChoiceItem(3);
                break;
            case R.id.rel_user:
                setChoiceItem(4);
                break;
            default:
                break;


        }
    }
class SwitchFragementBroadcast extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
           String action=intent.getAction();
        if(action.equals(Constants.SWITCH_FRAGMENT_LIVE)){
            setChoiceItem(1);
        }else if(action.equals(Constants.SWITCH_FRAGMENT_VEDIO)){
            setChoiceItem(2);
        }
    }
}
    private void setChoiceItem(int index) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        clearChoice();
        hideFragments(transaction);
        switch (index){
            case 0:
                img_navis.get(0).setImageResource(R.drawable.navi_home_red);
                tv_navis.get(0).setTextColor(getResources().getColor(R.color.theme_red));
                if(homefrg==null){
                    homefrg=new HomeFragment();
                    transaction.add(R.id.main_container,homefrg);
                }else{
                    transaction.show(homefrg);
                }
                break;
            case 1:
                img_navis.get(1).setImageResource(R.drawable.navi_live_red);
                tv_navis.get(1).setTextColor(getResources().getColor(R.color.theme_red));
                if(livefrg==null){
                    livefrg=new LiveFragment();
                    transaction.add(R.id.main_container,livefrg);
                }else{
                    transaction.show(livefrg);
                }
                break;
            case 2:
                img_navis.get(2).setImageResource(R.drawable.navi_vod_red);
                tv_navis.get(2).setTextColor(getResources().getColor(R.color.theme_red));
                if(vodfrg==null){
                    vodfrg=new VedioFragment();
                    transaction.add(R.id.main_container,vodfrg);
                }else{
                    transaction.show(vodfrg);
                }
                break;
            case 3:
                img_navis.get(3).setImageResource(R.drawable.navi_cart_red);
                tv_navis.get(3).setTextColor(getResources().getColor(R.color.theme_red));
                if(cartfrg==null){
                    cartfrg=new ShoppingCartFragement();
                    transaction.add(R.id.main_container,cartfrg);
                }else{
                    transaction.show(cartfrg);
                }
                break;
            case 4:
                img_navis.get(4).setImageResource(R.drawable.navi_user_red);
                tv_navis.get(4).setTextColor(getResources().getColor(R.color.theme_red));
                if(userfrg==null){
                    userfrg=new MyFragment();
                    transaction.add(R.id.main_container,userfrg);
                }else{
                    transaction.show(userfrg);
                }
                break;
        }
        transaction.commit();

    }

    private void hideFragments(android.support.v4.app.FragmentTransaction transaction) {
        if(homefrg!=null){
            transaction.hide(homefrg);
        }
        if(livefrg!=null){
            transaction.hide(livefrg);
        }
        if (vodfrg!=null){
            transaction.hide(vodfrg);
        }
        if (cartfrg!=null){
            transaction.hide(cartfrg);
        }
          if (userfrg!=null){
              transaction.hide(userfrg);
          }


    }

    private void clearChoice() {
        for(int i=0;i<img_navis.size();i++){
            img_navis.get(i).setImageResource(drawables[i]);
        }
        for(TextView tv:tv_navis){
            tv.setTextColor(getResources().getColor(R.color.theme_gray));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        unregisterReceiver(broadcast);
    }
}
