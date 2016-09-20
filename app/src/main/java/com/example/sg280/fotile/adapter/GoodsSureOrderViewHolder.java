package com.example.sg280.fotile.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.ShoppingCartGoodsBean;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by Tian on 2016/8/18.
 */
public class GoodsSureOrderViewHolder extends BaseViewHolder<ShoppingCartGoodsBean> {
    private ImageView iv_product_my_order;
    private TextView tv_product_name;
    private TextView tv_product_price;
    private TextView tv_product_number;
    private TextView tv_skuName;
    private RelativeLayout rl_goods_my_order;

    public GoodsSureOrderViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_goods_my_orders);
        iv_product_my_order = $(R.id.iv_product_my_order);
        tv_product_name = $(R.id.tv_product_name);
        tv_product_price = $(R.id.tv_product_price);
        tv_product_number = $(R.id.tv_product_number);
        rl_goods_my_order = $(R.id.rl_goods_my_order);
        tv_skuName = $(R.id.tv_skuName);
        //设置高宽的大小
//        ViewGroup.LayoutParams layoutParams = rl_goods_my_order.getLayoutParams();
//        layoutParams.width = ScreenUtil.getScreenWidth(getContext());
//        layoutParams.height = DensityUtil.dp2px(getContext(),120);
//        rl_goods_my_order.setLayoutParams(layoutParams);
    }

    @Override
    public void setData(ShoppingCartGoodsBean data) {
        super.setData(data);
        tv_product_name.setText(data.getProductName());
        tv_product_price.setText("￥ "+data.getPricePromo());
        tv_product_number.setText("x"+data.getProductQuantity());
        tv_skuName.setText("规格："+data.getSkuName());
        Glides.getInstance().load(getContext(),data.getSkuImageSer(),iv_product_my_order);
    }
}
