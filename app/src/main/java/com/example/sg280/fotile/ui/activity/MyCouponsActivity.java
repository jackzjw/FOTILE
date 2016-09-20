package com.example.sg280.fotile.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.MyCouponsAdapter;
import com.example.sg280.fotile.model.bean.CouponsBean;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tian on 2016/8/3.
 * 我的优惠码界面
 */
public class MyCouponsActivity extends BaseActivity implements RecyclerArrayAdapter.OnItemLongClickListener{

    @Bind(R.id.iv_back_title)//标题的返回图标
            ImageView iv_back_title;
    @Bind(R.id.tv_title)//标题栏标题
            TextView tv_title;
    @Bind({R.id.tv_wait_use,R.id.tv_disabled})//可使用，已失效
            List<TextView> couponsTitleList;
    @Bind({R.id.tv_wait_use_line,R.id.tv_disabled_line})//可使用，已失效下面的下划线
            List<TextView> lineList;
    @Bind(R.id.erv_my_coupons)//我的优惠码的EasyRecyclerView
            EasyRecyclerView erv_my_coupons;

    private List<CouponsBean> list;

    private MyCouponsAdapter couponsAdapter;
    private String USABLE = "GetMyCoupon";//获取所有可用优惠码的Action
    private String USED = "GetMyUselessCoupon";//获取所有不可用优惠码的Action

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_coupons;
    }

    @Override
    protected void onInitView() {

        tv_title.setText(R.string.my_coupons);//修改标题栏的标题
        erv_my_coupons.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<CouponsBean>();
        clickWaitUse();//默认点击可使用

    }

    //返回上一界面
    @OnClick(R.id.iv_back_title)
    void goBack() {
        finish();
//        onBackPressed();
    }

    //点击可使用
    @OnClick(R.id.tv_wait_use)
    void clickWaitUse(){
        init(0);

        //添加假数据测试
        couponsAdapter = new MyCouponsAdapter(this);
        erv_my_coupons.setAdapter(couponsAdapter);
        couponsAdapter.setOnItemLongClickListener(this);

        list.clear();

        CouponsBean couponsBean = new CouponsBean();
        couponsBean.setIsUsed("0");
        couponsBean.setValValue("20");
        couponsBean.setCouponCode("sg1234");
        couponsBean.setCouponInfo("亲爱的\n凭你悟性\n为何没想到珍惜");
        couponsBean.setCouponName("一电商");
        couponsBean.setValType("1");
        couponsBean.setLimitAmount("300");
        couponsBean.setStartTime("2016-8-1");
        couponsBean.setEndTime("2016-8-20");

        CouponsBean couponsBean1 = new CouponsBean();
        couponsBean1.setIsUsed("0");
        couponsBean1.setValValue("200");
        couponsBean1.setCouponCode("sg1234");
        couponsBean1.setCouponInfo("亲爱的\n凭你悟性\n为何没想到珍惜");
        couponsBean1.setCouponName("二电商");
        couponsBean1.setValType("1");
        couponsBean1.setLimitAmount("3000");
        couponsBean1.setStartTime("2016-8-1");
        couponsBean1.setEndTime("2016-8-20");

        CouponsBean couponsBean2 = new CouponsBean();
        couponsBean2.setIsUsed("0");
        couponsBean2.setValValue("2");
        couponsBean2.setCouponCode("sg1234");
        couponsBean2.setCouponInfo("亲爱的\n凭你悟性\n为何没想到珍惜");
        couponsBean2.setCouponName("三电商");
        couponsBean2.setValType("2");
        couponsBean2.setLimitAmount("300");
        couponsBean2.setStartTime("2016-8-1");
        couponsBean2.setEndTime("2016-8-20");

        CouponsBean couponsBean3 = new CouponsBean();
        couponsBean3.setIsUsed("0");
        couponsBean3.setValValue("20");
        couponsBean3.setCouponCode("sg1234");
        couponsBean3.setCouponInfo("亲爱的\n凭你悟性\n为何没想到珍惜");
        couponsBean3.setCouponName("四电商");
        couponsBean3.setValType("1");
        couponsBean3.setLimitAmount("300");
        couponsBean3.setStartTime("2016-8-1");
        couponsBean3.setEndTime("2016-8-20");

        list.add(couponsBean);
        list.add(couponsBean1);
        list.add(couponsBean2);
        list.add(couponsBean3);

        couponsAdapter.addAll(list);
    }

    //点击已失效
    @OnClick(R.id.tv_disabled)
    void clickDisabled(){
        init(1);

        //添加假数据测试
        couponsAdapter = new MyCouponsAdapter(this);
        erv_my_coupons.setAdapter(couponsAdapter);
        couponsAdapter.setOnItemLongClickListener(this);
//        couponsAdapter.clear();
        list.clear();

        CouponsBean couponsBean = new CouponsBean();
        couponsBean.setIsUsed("1");
        couponsBean.setValValue("20");
        couponsBean.setCouponCode("sg1234");
        couponsBean.setCouponInfo("亲爱的\n凭你悟性\n为何没想到珍惜");
        couponsBean.setCouponName("1电商");
        couponsBean.setValType("1");
        couponsBean.setLimitAmount("300");
        couponsBean.setStartTime("2016-8-1");
        couponsBean.setEndTime("2016-8-20");

        CouponsBean couponsBean1 = new CouponsBean();
        couponsBean1.setIsUsed("1");
        couponsBean1.setValValue("200");
        couponsBean1.setCouponCode("sg1234");
        couponsBean1.setCouponInfo("亲爱的\n凭你悟性\n为何没想到珍惜");
        couponsBean1.setCouponName("2电商");
        couponsBean1.setValType("1");
        couponsBean1.setLimitAmount("3000");
        couponsBean1.setStartTime("2016-8-1");
        couponsBean1.setEndTime("2016-8-20");

        CouponsBean couponsBean2 = new CouponsBean();
        couponsBean2.setIsUsed("1");
        couponsBean2.setValValue("2");
        couponsBean2.setCouponCode("sg1234");
        couponsBean2.setCouponInfo("亲爱的\n凭你悟性\n为何没想到珍惜");
        couponsBean2.setCouponName("3电商");
        couponsBean2.setValType("2");
        couponsBean2.setLimitAmount("300");
        couponsBean2.setStartTime("2016-8-1");
        couponsBean2.setEndTime("2016-8-20");

        CouponsBean couponsBean3 = new CouponsBean();
        couponsBean3.setIsUsed("1");
        couponsBean3.setValValue("20");
        couponsBean3.setCouponCode("sg1234");
        couponsBean3.setCouponInfo("亲爱的\n凭你悟性\n为何没想到珍惜");
        couponsBean3.setCouponName("4电商");
        couponsBean3.setValType("1");
        couponsBean3.setLimitAmount("300");
        couponsBean3.setStartTime("2016-8-1");
        couponsBean3.setEndTime("2016-8-20");

        list.add(couponsBean);
        list.add(couponsBean1);
        list.add(couponsBean2);
        list.add(couponsBean3);

        couponsAdapter.addAll(list);
    }

    //根据点击的优惠码做相应的界面显示改变
    private void init(int i){
        initTitle();
        initLine();
        couponsTitleList.get(i).setTextColor(ContextCompat.getColor(this,R.color.red));
        lineList.get(i).setVisibility(View.VISIBLE);

        if(i == 0){
            couponsTitleList.get(i).setClickable(false);
            couponsTitleList.get(1).setClickable(true);
        }else{
            couponsTitleList.get(i).setClickable(false);
            couponsTitleList.get(0).setClickable(true);
        }
    }

    //初始化字体颜色
    private void initTitle(){
        for (TextView textView : couponsTitleList) {
            textView.setTextColor(ContextCompat.getColor(this,R.color.hui_text));
        }
    }

    //初始化下划线的显示状态
    private void initLine(){
        for (TextView textView : lineList) {
            textView.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public boolean onItemClick(int position) {
        Log.e("点击点击点击点击","点击"+position);
        couponsAdapter.remove(position);
        couponsAdapter.notifyDataSetChanged();
        return true;
    }
}
