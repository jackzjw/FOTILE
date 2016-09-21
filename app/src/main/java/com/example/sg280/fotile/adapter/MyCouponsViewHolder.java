package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.CouponsBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * 我的优惠码的ViewHolder
 * Created by Tian on 2016/8/11.
 */
public class MyCouponsViewHolder extends BaseViewHolder<CouponsBean> {
    private Context context;

    private TextView tv_title_type;//优惠码上方的波浪线
    private TextView tv_ec_name;//优惠码名称
    private TextView tv_coupons_number;//具体的优惠码
    private TextView tv_coupons_user_time;//优惠码的有效期
    private TextView tv_money_sign;//人民币的符号￥
    private TextView tv_privilege_number;//优惠的金额
    private TextView tv_privilege_describe;//优惠券使用的限制(条件)
    public TextView tv_use_rule_describe;//使用规则的描述
    private ImageView iv_is_used;//已过期等的图片
    public RelativeLayout rl_use_rule;//使用规则的RelativeLayout
    public ImageView iv_arrow_down;//使用规则的小箭头


    public MyCouponsViewHolder(ViewGroup parent,Context context) {
        super(parent, R.layout.item_coupons_disabled);
        this.context = context;
        tv_title_type = $(R.id.tv_title_type);
        tv_ec_name = $(R.id.tv_ec_name);
        tv_coupons_number = $(R.id.tv_coupons_number);
        tv_coupons_user_time = $(R.id.tv_coupons_user_time);
        tv_money_sign = $(R.id.tv_money_sign);
        tv_privilege_number = $(R.id.tv_privilege_number);
        tv_privilege_describe = $(R.id.tv_privilege_describe);
        tv_use_rule_describe = $(R.id.tv_use_rule_describe);
        iv_is_used = $(R.id.iv_is_used);
        rl_use_rule = $(R.id.rl_use_rule);
        iv_arrow_down = $(R.id.iv_arrow_down);
    }



    @Override
    public void setData(CouponsBean data) {

        super.setData(data);
        if("0".equals(data.getIsUsed())){//未使用
            getRed();
        }else{
            getGary();
        }
        tv_ec_name.setText(data.getCouponName());
        tv_coupons_number.setText("优惠码："+data.getCouponCode());
        tv_coupons_user_time.setText(data.getStartTime()+" -- "+data.getEndTime());
        tv_privilege_number.setText(data.getValValue());
        if("1".equals(data.getValType())){//减免
            tv_privilege_describe.setText("满"+data.getLimitAmount()+"元减"+data.getValValue()+"元");
        }else if("2".equals(data.getValType())){//折扣
            tv_privilege_describe.setText(data.getValValue()+"折");
        }
        tv_use_rule_describe.setText(data.getCouponInfo());

        //根据是否点击显示布局
        if(data.isRuleIsClicked()){
            tv_use_rule_describe.setVisibility(View.VISIBLE);
            iv_arrow_down.setImageResource(R.drawable.user_icon_arrow_upward_gray);
        }else {
            tv_use_rule_describe.setVisibility(View.GONE);
            iv_arrow_down.setImageResource(R.drawable.user_icon_arrow_down_gray);
        }

        rl_use_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!data.isRuleIsClicked()){
                    tv_use_rule_describe.setVisibility(View.VISIBLE);
                    iv_arrow_down.setImageResource(R.drawable.user_icon_arrow_upward_gray);
                    data.setRuleIsClicked(true);
                }else if(data.isRuleIsClicked()){
                    tv_use_rule_describe.setVisibility(View.GONE);
                    iv_arrow_down.setImageResource(R.drawable.user_icon_arrow_down_gray);
                    data.setRuleIsClicked(false);
                }
            }
        });

    }

    //改变格式成未使用优惠券的格式
    private void getRed(){
        tv_title_type.setBackgroundResource(R.drawable.user_img_discount_red);
        tv_money_sign.setTextColor(ContextCompat.getColor(context,R.color.red));
        tv_privilege_number.setTextColor(ContextCompat.getColor(context,R.color.red));
        tv_privilege_describe.setTextColor(ContextCompat.getColor(context,R.color.red));
        iv_is_used.setVisibility(View.GONE);

    }

    //改变格式成未使用优惠券的格式
    private void getGary(){
        tv_title_type.setBackgroundResource(R.drawable.user_img_discount_gray);
        tv_money_sign.setTextColor(ContextCompat.getColor(context,R.color.hui_text));
        tv_privilege_number.setTextColor(ContextCompat.getColor(context,R.color.hui_text));
        tv_privilege_describe.setTextColor(ContextCompat.getColor(context,R.color.hui_text));
        iv_is_used.setVisibility(View.VISIBLE);

    }

}
