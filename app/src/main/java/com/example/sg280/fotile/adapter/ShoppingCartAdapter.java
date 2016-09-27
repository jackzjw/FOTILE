package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.bean.ArithmeticUtil;
import com.example.sg280.fotile.model.bean.ShoppingCartGoodsBean;
import com.example.sg280.fotile.presents.ShoppingCartPresent;
import com.example.sg280.fotile.ui.activity.MyDeleteDialog;
import com.example.sg280.fotile.ui.activity.ProductsActivity;
import com.example.sg280.fotile.ui.fragment.MyShoppingCartFragment;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.StringUtil;
import com.example.sg280.fotile.utils.ToastUtil;

import java.util.List;

import retrofit2.http.HEAD;


/**
 * 购物车的Adapter
 * Created by Tian on 2016/8/15.
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartGoodsViewHolder> {

    private Context context;//上下文本
    private List<ShoppingCartGoodsBean> allList;//所有的数据
    private CheckBox brandCheckBox;//品牌的checkBox
    private TextView priceView;//总金额
    private MyDeleteDialog myDialog;//是否删除产品的dialog
    private ShoppingCartPresent present;
    private final String add ="addnum";
    private final String sub ="subnum";
    private final String del ="delpro";

    public ShoppingCartAdapter(List<ShoppingCartGoodsBean> allList, Context context, CheckBox brandCheckBox,
                               TextView priceView, ShoppingCartPresent present) {
        this.allList = allList;
        this.context = context;
        this.brandCheckBox = brandCheckBox;
        this.priceView = priceView;
        this.present = present;
    }

    @Override
    public int getItemCount() {
        if (null != allList) {
            return allList.size();
        }
        return 0;
    }

    @Override
    public ShoppingCartGoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShoppingCartGoodsViewHolder(parent,context);
    }

    @Override
    public void onBindViewHolder(ShoppingCartGoodsViewHolder holder, int position) {

        ShoppingCartGoodsBean bean = allList.get(position);
        holder.setData(allList.get(position));
        holder.cb_cart.setChecked(bean.isChecked());

        //相当于OnItemClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(ProductsActivity.newIntent(context,allList.get(position).getProductID()));

            }
        });

        //点击产品选择框监听事件
        holder.cb_cart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                bean.setChecked(isChecked);
                MyShoppingCartFragment.i = Constants.FROM_ADAPTER;//设置是来自adapter的点击事件
                brandCheckBox.setChecked(isShow());
                setPrice();
                MyShoppingCartFragment.i = Constants.FROM_SELF;//还原成activity的点击事件
            }
        });

        //点击添加产品数量监听事件
        holder.tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                present.modifyGoodsNum(add,position);

            }
        });

        //点击减少产品数量监听事件
        holder.tv_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(holder.tv_amount.getText().toString());
                if(i == 1){
                    return;
                }

                present.modifyGoodsNum(sub,position);

            }
        });

        //点击删除监听事件
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建dialog
                myDialog = new MyDeleteDialog(context, R.style.myDialog,"确定要删除吗？","确定", new MyDeleteDialog.MyDialogListener() {
                    @Override
                    public void onClick(View view) {

                        present.modifyGoodsNum(del,position);

                    }
                });
                myDialog.show();

            }
        });

        holder.setData(allList.get(position));
    }

    //品牌选择框选择状态
    public boolean isShow() {
        if (null == allList || allList.size() == 0) {
            return false;
        }

        for (int i = 0; i < allList.size(); i++) {
            if (allList.get(i).isChecked() == false) {
                return false;
            }
        }
        return true;
    }

    //显示总价
    public void setPrice() {
        double price = ArithmeticUtil.getResultWithBeansAdd(allList);//获取总价

        priceView.setText("￥ " + StringUtil.format(price));
    }

    public void dismissDialog(){
        myDialog.dismiss();
    }
}
