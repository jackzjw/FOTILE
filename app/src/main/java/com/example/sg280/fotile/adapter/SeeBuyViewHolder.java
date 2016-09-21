package com.example.sg280.fotile.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.LiveProductBean;
import com.example.sg280.fotile.ui.activity.ProductsActivity;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by sg280 on 2016-08-11.
 */
public class SeeBuyViewHolder extends BaseViewHolder<LiveProductBean> {
    private  Button btn_pro_details;
    private  TextView tv_pro_price_before;
    private  TextView tv_product_price;
    private  TextView tv_product_name;
    private  ImageView img_product;

    public SeeBuyViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_see_and_buy);
          img_product=$(R.id.img_product);
         tv_product_name=$(R.id.tv_product_name);
        tv_product_price=$(R.id.tv_product_price);
        tv_pro_price_before=$(R.id.tv_product_price_before);
        btn_pro_details=$(R.id.btn_prodetails);
    }

    @Override
    public void setData(LiveProductBean data) {
        super.setData(data);
        Glides.getInstance().load(getContext(), data.getProductUrl(), img_product);
        tv_pro_price_before.setText(data.getPriceCommon());
        tv_product_name.setText(data.getProductName());
        tv_product_price.setText(data.getPricePromo());
        btn_pro_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProductsActivity.newIntent(getContext(), data.getID());
                getContext().startActivity(intent);
            }
        });
    }
}
