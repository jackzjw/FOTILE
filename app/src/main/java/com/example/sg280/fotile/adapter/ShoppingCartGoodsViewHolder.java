package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.ShoppingCartGoodsBean;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Tian on 2016/8/15.
 */
public class ShoppingCartGoodsViewHolder extends BaseViewHolder<ShoppingCartGoodsBean> {

    public CheckBox cb_cart;//
    private TextView tv_goods_name;//产品名
    private ImageView iv_goods_cart;//产品的图片
    private TextView tv_standard;//规格
    private TextView tv_goods_price;//产品价格
    public ImageView iv_delete;//删除
    public TextView tv_subtract;//减
    public TextView tv_add;//加
    public TextView tv_amount;//产品数量
    private Context context;



    public ShoppingCartGoodsViewHolder(ViewGroup parent,Context context) {
        super(parent, R.layout.item_goods_shopping_cart);
        this.context = context;
        cb_cart = $(R.id.cb_cart);
        tv_goods_name = $(R.id.tv_goods_name);
        iv_goods_cart = $(R.id.iv_goods_cart);
        tv_standard = $(R.id.tv_standard);
        tv_goods_price = $(R.id.tv_goods_price);
        iv_delete = $(R.id.iv_delete);
        tv_subtract = $(R.id.tv_subtract);
        tv_add = $(R.id.tv_add);
        tv_amount = $(R.id.tv_amount);
    }

    @Override
    public void setData(ShoppingCartGoodsBean data) {
        super.setData(data);
        tv_goods_name.setText(data.getProductName());
        tv_standard.setText("规格："+data.getSkuName());
        tv_goods_price.setText("￥ "+data.getPricePromo());
        tv_amount.setText(data.getProductQuantity());
        Glides.getInstance().load(context,data.getSkuImageSer(),iv_goods_cart);

    }


}
