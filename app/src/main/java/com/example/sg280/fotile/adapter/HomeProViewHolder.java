package com.example.sg280.fotile.adapter;

import android.graphics.Paint;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.HomeHotProBean;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by sg280 on 2016/9/9.
 */
public class HomeProViewHolder extends BaseViewHolder<HomeHotProBean> {
    private TextView tv_discount_price;
    private  TextView tv_pro_price;
    private  TextView tv_product_name;
    private ImageView img_all_buy;
    public HomeProViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_home_hotproduct);
        img_all_buy=(ImageView)$(R.id.img_allbuy);
        tv_product_name=(TextView)$(R.id.tv_product_name);
        tv_pro_price=(TextView)$(R.id.tv_pro_price);
        tv_discount_price=(TextView)$(R.id.tv_discount_price);
    }

    @Override
    public void setData(HomeHotProBean data) {
        super.setData(data);
        Glides.getInstance().load(getContext(),data.getProImgPPix(),img_all_buy);
        tv_product_name.setText(data.getProductName());
        tv_pro_price.setText("¥" + data.getPricePromo());
        tv_discount_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_discount_price.setText("¥" + data.getPriceCommon());
    }
}
