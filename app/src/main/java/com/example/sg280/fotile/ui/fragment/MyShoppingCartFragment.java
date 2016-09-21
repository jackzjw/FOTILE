package com.example.sg280.fotile.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.presents.Interface.ShoppingCartContacts;
import com.example.sg280.fotile.presents.ShoppingCartPresent;
import com.example.sg280.fotile.utils.Glides;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 购物车界面
 * Created by Tian on 2016-08-15.
 */
public class MyShoppingCartFragment extends BaseFragment implements ShoppingCartContacts.View{

    @Bind(R.id.iv_back_title)//标题的返回图标
            ImageView iv_back_title;
    @Bind(R.id.tv_title)//标题栏标题
            TextView tv_title;
    @Bind(R.id.tv_batch_remove)//批量删除
            TextView tv_batch_remove;
    @Bind(R.id.cb_is_selected)//品牌选择框
            CheckBox cb_is_selected;
    @Bind(R.id.tv_goods_price_info)//所选的全部商品的金额
            TextView tv_goods_price_info;
    @Bind(R.id.tv_settlement)//结算
            TextView tv_settlement;
    @Bind(R.id.erv_goods_shoppingCart)//购物车商品的easyRecyclerView
            RecyclerView erv_goods_shoppingCart;
    @Bind(R.id.rl_brand)
            RelativeLayout rl_brand;//品牌RelativeLayout
    @Bind(R.id.rl_noData)
    RelativeLayout rl_noData;//没有数据RelativeLayout
    @Bind(R.id.iv_brand_logo)//品牌的logo
            ImageView iv_brand_logo;

    public static int i = Constants.FROM_SELF;
    private ShoppingCartPresent shoppingCartPresent;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    protected void onInitView() {
        tv_title.setText(R.string.shopping_cart);//修改标题栏的标题
        iv_back_title.setVisibility(View.GONE);//隐藏返回键
        shoppingCartPresent = new ShoppingCartPresent(getContext(),this,erv_goods_shoppingCart,cb_is_selected,tv_goods_price_info);


    }

    @Override
    public void onResume() {
        super.onResume();
        shoppingCartPresent.getShoppingCartGoods();
    }

    //点击结算，跳转订单确定界面
    @OnClick(R.id.tv_settlement)
    void goSureOrder(){
        shoppingCartPresent.ClickGoPay();
    }

    //设置品牌的logo
    @Override
    public void setBrandLogo(String logoUrl) {
        Glides.getInstance().load(getContext(),logoUrl,iv_brand_logo);
    }

    @Override
    public void noData() {
        rl_brand.setVisibility(View.GONE);
        rl_noData.setVisibility(View.VISIBLE);
        erv_goods_shoppingCart.setVisibility(View.GONE);
        tv_goods_price_info.setText("￥ 0");
    }

    @Override
    public void hadData() {
        rl_brand.setVisibility(View.VISIBLE);
        rl_noData.setVisibility(View.GONE);
        erv_goods_shoppingCart.setVisibility(View.VISIBLE);
    }
}
