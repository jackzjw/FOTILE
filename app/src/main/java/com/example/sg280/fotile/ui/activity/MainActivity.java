<<<<<<< HEAD
package com.example.sg280.fotile.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.*;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.ui.fragment.HomeFragment;
import com.example.sg280.fotile.ui.fragment.MyAccountFragment;
import com.example.sg280.fotile.ui.fragment.MyShoppingCartFragment;
import com.example.sg280.fotile.ui.fragment.ProductsFragment;
import com.example.sg280.fotile.ui.fragment.VedioFragment;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.ToastUtil;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener{

   @Bind({R.id.home_navi_img,R.id.live_navi_img,R.id.vedio_navi_img,R.id.cart_navi_img,R.id.user_navi_img})
    List<ImageView> img_navis;
    @Bind({R.id.home_navi_tv,R.id.live_navi_tv,R.id.vedio_navi_tv,R.id.cart_navi_tv,R.id.user_navi_tv})
    List<TextView> tv_navis;
    @Bind({R.id.rel_home,R.id.rel_live,R.id.rel_vedio,R.id.rel_cart,R.id.rel_user})
    List<RelativeLayout> rel_navis;
    private Fragment homefrg;
    private Fragment vediofrg;
    private Fragment profrg;
    private Fragment cartfrg;
    private Fragment userfrg;
    public static int index=0;
    private long firstBacktime=0;
    private static final String TAG = "MainActivity";
    private int[] drawables={R.drawable.navi_home_gray,R.drawable.navi_live_gray,R.drawable.navi_vod_gray,R.drawable.navi_cart_gray,R.drawable.navi_user_gray};
    private SwitchFragementBroadcast broadcast;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e(TAG, "oncreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       for(RelativeLayout navi:rel_navis){
           navi.setOnClickListener(this);
       }
        setChoiceItem(index);
        setBroadCast();
    }

    @Override
    protected void onResume() {
        super.onResume();
       LogUtil.e(TAG,"onresume");


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
                index=0;
                break;
            case R.id.rel_live:
                index=1;
                break;
            case R.id.rel_vedio:
                index=2;
                break;
            case R.id.rel_cart:
                index=3;
                break;
            case R.id.rel_user:
                index=4;
                break;
        }
        setChoiceItem(index);
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
                if(vediofrg==null){
                    vediofrg=new VedioFragment();
                    transaction.add(R.id.main_container,vediofrg);
                }else{
                    transaction.show(vediofrg);
                }
                break;
            case 2:
                img_navis.get(2).setImageResource(R.drawable.navi_vod_red);
                tv_navis.get(2).setTextColor(getResources().getColor(R.color.theme_red));
                if(profrg==null){
                    profrg=new ProductsFragment();
                    transaction.add(R.id.main_container,profrg);
                }else{
                    transaction.show(profrg);
                }
                break;
            case 3:
                img_navis.get(3).setImageResource(R.drawable.navi_cart_red);
                tv_navis.get(3).setTextColor(getResources().getColor(R.color.theme_red));
                if(cartfrg==null){
                    cartfrg=new MyShoppingCartFragment();
                    transaction.add(R.id.main_container,cartfrg);
                }else{
                    transaction.show(cartfrg);
                }
                break;
            case 4:
                img_navis.get(4).setImageResource(R.drawable.navi_user_red);
                tv_navis.get(4).setTextColor(getResources().getColor(R.color.theme_red));
                if(userfrg==null){
                    userfrg=new MyAccountFragment();
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
        if(vediofrg!=null){
            transaction.hide(vediofrg);
        }
        if (profrg!=null){
            transaction.hide(profrg);
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
    //防止InputmethodManager内存泄漏
    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String [] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (int i = 0;i < arr.length;i ++) {
            String param = arr[i];
            try{
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                       /* if (QLog.isColorLevel()) {
                            QLog.d(ReflecterHelper.class.getSimpleName(), QLog.CLR, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);
                        }*/
                        break;
                    }
                }
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
       long secondBackTime=System.currentTimeMillis();
        if(secondBackTime-firstBacktime>3000){
            ToastUtil.showLong(this,"再按一次退出应用");
            firstBacktime=secondBackTime;
        }else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        unregisterReceiver(broadcast);
        fixInputMethodManagerLeak(this);
    }
}
=======
package com.example.sg280.fotile.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.ui.fragment.HomeFragment;
import com.example.sg280.fotile.ui.fragment.MyAccountFragment;
import com.example.sg280.fotile.ui.fragment.MyShoppingCartFragment;
import com.example.sg280.fotile.ui.fragment.ProductsFragment;
import com.example.sg280.fotile.ui.fragment.VedioFragment;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener{

   @Bind({R.id.home_navi_img,R.id.live_navi_img,R.id.vedio_navi_img,R.id.cart_navi_img,R.id.user_navi_img})
    List<ImageView> img_navis;
    @Bind({R.id.home_navi_tv,R.id.live_navi_tv,R.id.vedio_navi_tv,R.id.cart_navi_tv,R.id.user_navi_tv})
    List<TextView> tv_navis;
    @Bind({R.id.rel_home,R.id.rel_live,R.id.rel_vedio,R.id.rel_cart,R.id.rel_user})
    List<RelativeLayout> rel_navis;
    private Fragment homefrg;
    private Fragment vediofrg;
    private Fragment profrg;
    private Fragment cartfrg;
    private Fragment userfrg;
    public static int index=0;
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        setChoiceItem(index);
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
                if(vediofrg==null){
                    vediofrg=new VedioFragment();
                    transaction.add(R.id.main_container,vediofrg);
                }else{
                    transaction.show(vediofrg);
                }
                break;
            case 2:
                img_navis.get(2).setImageResource(R.drawable.navi_vod_red);
                tv_navis.get(2).setTextColor(getResources().getColor(R.color.theme_red));
                if(profrg==null){
                    profrg=new ProductsFragment();
                    transaction.add(R.id.main_container,profrg);
                }else{
                    transaction.show(profrg);
                }
                break;
            case 3:
                img_navis.get(3).setImageResource(R.drawable.navi_cart_red);
                tv_navis.get(3).setTextColor(getResources().getColor(R.color.theme_red));

                    cartfrg=new MyShoppingCartFragment();
                    transaction.add(R.id.main_container,cartfrg);

                break;
            case 4:
                img_navis.get(4).setImageResource(R.drawable.navi_user_red);
                tv_navis.get(4).setTextColor(getResources().getColor(R.color.theme_red));
                if(userfrg==null){
                    userfrg=new MyAccountFragment();
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
        if(vediofrg!=null){
            transaction.hide(vediofrg);
        }
        if (profrg!=null){
            transaction.hide(profrg);
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
    //防止InputmethodManager内存泄漏
    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String [] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (int i = 0;i < arr.length;i ++) {
            String param = arr[i];
            try{
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                       /* if (QLog.isColorLevel()) {
                            QLog.d(ReflecterHelper.class.getSimpleName(), QLog.CLR, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);
                        }*/
                        break;
                    }
                }
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        unregisterReceiver(broadcast);
        fixInputMethodManagerLeak(this);
    }
}
>>>>>>> 9154ba3ab91f5fc2130025876902b38a4a7ff600
