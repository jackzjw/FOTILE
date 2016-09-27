package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.ShippingAddressBean;
import com.example.sg280.fotile.presents.MyAddressPresent;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.example.sg280.fotile.utils.StringUtil;

import java.util.List;

/**
 * Created by Tian on 2016/8/23.
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressViewHolder> {

    private List<ShippingAddressBean> list;
    private Context context;
    private MyAddressPresent myAddressPresent;
    private final String ACTION = "modifyAddress";
    private boolean isNoIntentBack = false;
//    private boolean isNotShow;//是否不展示编辑部分

    public AddressAdapter(Context context) {
        this.context = context;
    }

    public AddressAdapter(Context context, List<ShippingAddressBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setMyAddressPresent(MyAddressPresent myAddressPresent) {
        this.myAddressPresent = myAddressPresent;
    }

    public void setList(List<ShippingAddressBean> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        if (!StringUtil.listIsNull(list)) {
            return list.size();
        }
        return 0;
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, int position) {
        holder.setData(list.get(position));

        //判断是否需要展示地址编辑的部分（设置默认，删除，编辑）
        if(!isNoIntentBack){
            holder.ll_compile.setVisibility(View.GONE);
        }else{
            holder.ll_compile.setVisibility(View.VISIBLE);
        }

        if ("1".equals(list.get(position).getIsDefault())) {
            holder.cb_address.setChecked(true);
            holder.tv_default.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.cb_address.setClickable(false);//不允许点击
        } else {
            holder.cb_address.setChecked(false);
            holder.tv_default.setTextColor(ContextCompat.getColor(context, R.color.hui_text));
            holder.cb_address.setClickable(true);
        }

        //点击item事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isNoIntentBack){
                    myAddressPresent.getClick(list.get(position));
                }
            }
        });

        //监听默认地址选择框事件
        holder.cb_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //设置成默认
                    myAddressPresent.modifyAddress(ACTION, SharedPreferencesUtil.getId(context), list.get(position).getPcr(),
                            list.get(position).getAddress(), list.get(position).getTel(), list.get(position).getPostcode(), list.get(position).getRecipients()
                            , "1", list.get(position).getID());

                }
            }
        });

        //监听编辑地址事件
        holder.rl_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAddressPresent.goEdit(list.get(position));//跳转编辑界面
            }
        });

        //监听删除地址事件
        holder.rl_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    /**
     *
     * @param isNoIntentBack 是否禁止点击item事件（返回地址）
     */
    public void setIsIntentBack(boolean isNoIntentBack){
        this.isNoIntentBack = isNoIntentBack;
    }

}
