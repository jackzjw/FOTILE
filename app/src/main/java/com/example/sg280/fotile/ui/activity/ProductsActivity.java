package com.example.sg280.fotile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.AllBuyAdapter;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.ProductsBean;
import com.example.sg280.fotile.presents.Interface.IProductContacts;
import com.example.sg280.fotile.presents.ProductDetailsPresent;
import com.example.sg280.fotile.ui.fragment.ProductIntroduceFragment;
import com.example.sg280.fotile.ui.fragment.ProductStandardFragment;
import com.example.sg280.fotile.utils.DensityUtil;
import com.example.sg280.fotile.utils.Glides;
import com.example.sg280.fotile.utils.LoginUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.example.sg280.fotile.widget.AddCartDialog;
import com.example.sg280.fotile.widget.BadgeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sg280 on 2016-08-22.
 */
public class ProductsActivity extends BaseFragmentActivity implements IProductContacts.View{
    @Bind(R.id.roll_viewpager)
    RollPagerView rollPagerView;
    @Bind(R.id.tv_product_name)
    TextView tv_pro_name;
    @Bind(R.id.tv_pro_introduce)
    TextView tv_pro_introduce;
    @Bind(R.id.tv_pro_price)
    TextView tv_pro_price;
    @Bind(R.id.tv_discount_price)
    TextView tv_discount_price;
    @Bind(R.id.easyrecycleview)
    RecyclerView easyRecyclerView;
    @Bind(R.id.img_home)
    ImageView img_home;
    @Bind(R.id.img_cart_bottom)
    ImageView img_cart_bottom;
    @Bind(R.id.tv_add_cart)
    TextView tv_add_cart;
    @Bind(R.id.tv_buy_now)
    TextView tv_buy_now;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.img_collect)
    ImageView img_collect;
    @Bind(R.id.img_cart)
    ImageView img_topCart;
    private static String PRODUCT_ID="productid";
    private ProductDetailsPresent present;
    private List<ProductsBean.ImageListBean> imgurls;
    private ImageAdapter imgadapter;
    private AllBuyAdapter allbuyAdapter;
    private List<ProductsBean.SkuListBean> skulist;
    private  ProductsBean beans;
    private AddCartDialog addcartDialog;
    private boolean isCollected=true;
    private BadgeView badgeview;
    private int cart_count;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        String product_id=getIntent().getStringExtra(PRODUCT_ID);
         present=new ProductDetailsPresent(this,this);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        setViewPager();
        setRollPageView();
        setSkuAdapter();
         present.getProductDetails(product_id);
            badgeview=new BadgeView(this);
        sp = getSharedPreferences(Constants.USER_INFO, 0);
        cart_count=sp.getInt(Constants.USER_CART_NUM,0);
        badgeview.setTargetView(img_topCart);
        badgeview.setBadgeCount(cart_count);

    }

    private void setSkuAdapter() {
        skulist=new ArrayList<ProductsBean.SkuListBean>();
        easyRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        allbuyAdapter=new AllBuyAdapter(this,skulist);
        easyRecyclerView.setAdapter(allbuyAdapter);
    }

    private void setRollPageView() {
        rollPagerView.setHintView(new ColorPointHintView(this,
                getResources().getColor(R.color.dot_black60),getResources().getColor(R.color.dot_black20)));
        rollPagerView.setPlayDelay(3000);
        rollPagerView.setAnimationDurtion(1000);
        rollPagerView.setHintPadding(0, 0, 0, DensityUtil.dp2px(this, 8));
        imgurls=new ArrayList<ProductsBean.ImageListBean>();
        WeakReference<Context> weakReference=new WeakReference<Context>(this);
        imgadapter=new ImageAdapter(weakReference,imgurls);
        rollPagerView.setAdapter(imgadapter);
    }

    private void setViewPager() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                     return new ProductIntroduceFragment();
                    case 1:
                        return new ProductStandardFragment();
                    default:
                        return new ProductIntroduceFragment();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
               switch (position){
                   case 0:
                       return "图文详情";
                   case 1:
                       return "规格参数";
                   default:
                       return "图文详情";
               }
            }
        });
     tabLayout.setupWithViewPager(viewPager);
    }

    public static Intent newIntent(Context context,String productid){
        Intent intent=new Intent(context,ProductsActivity.class);
        intent.putExtra(PRODUCT_ID, productid);
        return intent;
    }

    @Override
    public void fetchData(ProductsBean bean) {
       this.beans=bean;
        imgurls.addAll(bean.getImglist());
        skulist.addAll(bean.getSkuList());
        imgadapter.notifyDataSetChanged();
        allbuyAdapter.notifyDataSetChanged();
        tv_pro_name.setText(bean.getProductName());
        tv_pro_introduce.setText(bean.getProductSubhead());
        tv_pro_price.setText("¥"+bean.getPricePromo());
        tv_discount_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_discount_price.setText("¥" + bean.getPriceCommon());
        if(bean.getIsFav()!=null&&bean.getIsFav().equals("1")){
            img_collect.setImageResource(R.drawable.user_icon_collection_gray);
            isCollected=false;
        }else {
            img_collect.setImageResource(R.drawable.live_icon_collection_red);
            isCollected=true;
        }


    }

    @Override
    public void addCartSuccess() {
        addcartDialog.dismiss();
        ToastUtil.showLong(this, "添加购物车成功");
        cart_count++;
        badgeview.setBadgeCount(cart_count);

        sp.edit().putInt(Constants.USER_CART_NUM,cart_count).commit();

    }

    @Override
    public void addCartFailed(String msg) {
        addcartDialog.dismiss();
        ToastUtil.showLong(this, msg);
    }

    @Override
    public void collectSuccess() {
        if(isCollected){
            ToastUtil.showLong(this,"收藏成功");
            img_collect.setImageResource(R.drawable.user_icon_collection_gray);
            isCollected=false;
        }else {
            ToastUtil.showLong(this,"取消收藏");
            img_collect.setImageResource(R.drawable.live_icon_collection_red);
            isCollected=true;
        }
    }

    private void showDialog(){
       if (beans == null) return;
       if (addcartDialog == null) {
           addcartDialog = new AddCartDialog(ProductsActivity.this, beans, present, R.style.addcartdialog);
       }
       addcartDialog.show();
   }
    //加入购物车
    @OnClick(R.id.tv_add_cart)
    void addCart(){
        LoginUtil.checkLogin(this, new LoginUtil.LoginCallback() {
            @Override
            public void onLogin() {
                showDialog();
                addcartDialog.setBtText("确定");
            }
        });
            }
    @OnClick(R.id.tv_buy_now)
        void buyNow(){
        LoginUtil.checkLogin(this, new LoginUtil.LoginCallback() {
            @Override
            public void onLogin() {
                showDialog();
                addcartDialog.setBtText("立即购买");
            }
        });
    }
    //点击购物车图标
    @OnClick(R.id.img_cart)
    void clickTopCart(){
        MainActivity.index=3;
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    //收藏
    @OnClick(R.id.img_collect)
    void collect(){
       LoginUtil.checkLogin(this, new LoginUtil.LoginCallback() {
           @Override
           public void onLogin() {
               present.isCollect(MySelfInfo.getInstance().getId(),beans.getID(),2);
           }
       });

    }
   public void back(View v){
       finish();
   }


            static class ImageAdapter extends StaticPagerAdapter {
                private Context mcontext;
                private List<ProductsBean.ImageListBean> mlist;

                public ImageAdapter(WeakReference<Context> context, List<ProductsBean.ImageListBean> list) {
                    this.mcontext = context.get();
                    this.mlist = list;
                }

                @Override
                public View getView(ViewGroup container, int position) {
                    ImageView imageView = new ImageView(mcontext);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glides.getInstance().load(mcontext, mlist.get(position).getImageUrlSer(), imageView);
                    return imageView;
                }

                @Override
                public int getCount() {
                    return mlist.size();
                }
            }

            @Override
            protected void onDestroy() {
                super.onDestroy();
                ButterKnife.unbind(this);
            }
        }
