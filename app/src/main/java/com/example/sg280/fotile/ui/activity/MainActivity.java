package com.example.sg280.fotile.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.ui.fragment.HomeFragment;
import com.example.sg280.fotile.ui.fragment.LiveFragment;
import com.example.sg280.fotile.ui.fragment.MyFragment;
import com.example.sg280.fotile.ui.fragment.ShoppingCartFragement;
import com.example.sg280.fotile.ui.fragment.VedioFragment;
import com.example.sg280.fotile.widget.TabStripView;

public class MainActivity extends FragmentActivity {

    private TabStripView navigateTabBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigateTabBar = (TabStripView) findViewById(R.id.navigateTabBar);
        //对应xml中的containerId
        navigateTabBar.setFrameLayoutId(R.id.main_container);
        //对应xml中的navigateTabTextColor
        navigateTabBar.setTabTextColor(getResources().getColor(R.color.theme_gray));
        //对应xml中的navigateTabSelectedTextColor
        navigateTabBar.setSelectedTabTextColor(getResources().getColor(R.color.theme_red));

        //恢复选项状态
        navigateTabBar.onRestoreInstanceState(savedInstanceState);

        navigateTabBar.addTab(HomeFragment.class, new TabStripView.TabParam(R.drawable.navi_home_gray, R.drawable.navi_home_red, R.string.shouye));
        navigateTabBar.addTab(LiveFragment.class, new TabStripView.TabParam(R.drawable.navi_live_gray, R.drawable.navi_live_red, R.string.zhibo));
        navigateTabBar.addTab(VedioFragment.class, new TabStripView.TabParam(R.drawable.navi_vod_gray, R.drawable.navi_vod_red, R.string.dianbo));
         navigateTabBar.addTab(ShoppingCartFragement.class,new TabStripView.TabParam(R.drawable.navi_cart_gray,R.drawable.navi_cart_red,R.string.gouwuche));
        navigateTabBar.addTab(MyFragment.class,new TabStripView.TabParam(R.drawable.navi_user_gray,R.drawable.navi_user_red,R.string.wode));


    }




}
