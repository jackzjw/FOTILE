package com.example.sg280.fotile.adapter;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.ShippingAddressBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Tian on 2016/8/23.
 */
public class AddressViewHolder extends BaseViewHolder<ShippingAddressBean> {

    public CheckBox cb_address;//默认地址选择框
    private TextView tv_consignee_info;//收货人
    private TextView tv_phone_number;//电话
    private TextView tv_delivery_address_info;//地址信息
    private TextView tv_postcode_info;//邮编
    public RelativeLayout rl_edit;//编辑
    public RelativeLayout rl_delete;//删除
    public TextView tv_default;
    public LinearLayout ll_compile;

    public AddressViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_shipping_address);
        cb_address = $(R.id.cb_address);
        tv_consignee_info = $(R.id.tv_consignee_info);
        tv_phone_number = $(R.id.tv_phone_number);
        tv_delivery_address_info = $(R.id.tv_delivery_address_info);
        tv_postcode_info = $(R.id.tv_postcode_info);
        rl_edit = $(R.id.rl_edit);
        rl_delete = $(R.id.rl_delete);
        tv_default = $(R.id.tv_default);
        ll_compile = $(R.id.ll_compile);
    }

    @Override
    public void setData(ShippingAddressBean data) {
        super.setData(data);
        tv_consignee_info.setText(data.getRecipients());
        tv_phone_number.setText(data.getTel());
        tv_delivery_address_info.setText(data.getPcr()+data.getAddress());
        tv_postcode_info.setText(data.getPostcode());

    }
}
