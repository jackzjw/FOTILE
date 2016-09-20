package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.ProductBean;
import com.example.sg280.fotile.utils.DensityUtil;
import com.example.sg280.fotile.utils.Glides;
import com.example.sg280.fotile.utils.ScreenUtil;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * 产品的ViewHolder
 * Created by Tian on 2016/8/8.
 */
public class MyProductViewHolder extends BaseViewHolder<ProductBean> {

    private ImageView iv_product_my_order;//产品图片
    private TextView tv_product_name;//产品名称
    private TextView tv_product_price;//产品价格
    private TextView tv_product_number;//产品数量
    private TextView tv_skuName;//产品规格
    private RelativeLayout rl_goods_my_order;
    private Context context;

    public MyProductViewHolder(ViewGroup parent,Context context) {
        super(parent, R.layout.item_goods_my_orders);
        this.context = context;

        rl_goods_my_order = (RelativeLayout) itemView.findViewById(R.id.rl_goods_my_order);
        //设置宽高
        ViewGroup.LayoutParams layoutParams = rl_goods_my_order.getLayoutParams();
        layoutParams.width = ScreenUtil.getScreenWidth(context);
        layoutParams.height = DensityUtil.dp2px(context,120);
        rl_goods_my_order.setLayoutParams(layoutParams);

        iv_product_my_order = $(R.id.iv_product_my_order);
        tv_product_name = $(R.id.tv_product_name);
        tv_product_price = $(R.id.tv_product_price);
        tv_product_number = $(R.id.tv_product_number);
        tv_skuName = $(R.id.tv_skuName);
    }

    @Override
    public void setData(ProductBean data) {
        super.setData(data);
        tv_product_name.setText(data.getProductName());
        tv_product_price.setText("￥"+data.getPriceCommon());
        tv_product_number.setText("x"+data.getProductQuantity());
        tv_skuName.setText("规格："+data.getSkuName());
        Glides.getInstance().load(context,data.getSkuImg(),iv_product_my_order);
    }


}
