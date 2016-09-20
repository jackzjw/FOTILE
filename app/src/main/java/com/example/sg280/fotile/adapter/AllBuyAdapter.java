package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.ProductsBean;
import com.example.sg280.fotile.utils.Glides;

import java.util.List;

/**
 * Created by sg280 on 2016-08-23.
 */
public class AllBuyAdapter extends RecyclerView.Adapter<AllBuyAdapter.MyViewHolder> {

    private Context mcontext;
    private List<ProductsBean.SkuListBean> mlist;
   public  AllBuyAdapter(Context c,List<ProductsBean.SkuListBean> list){
       this.mcontext=c;
       this.mlist=list;
   }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewholder = new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_all_buy, parent, false));
        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glides.getInstance().load(mcontext, mlist.get(position).getSkuImageSer(), holder.img_all_buy);
        holder.tv_product_name.setText(mlist.get(position).getSkuName());
        holder.tv_pro_price.setText("¥" + mlist.get(position).getPricePromo());
        holder.tv_discount_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_discount_price.setText("¥" + mlist.get(position).getPriceCommon());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_discount_price;
        private  TextView tv_pro_price;
        private  TextView tv_product_name;
        private ImageView img_all_buy;
        public MyViewHolder(View itemView) {
            super(itemView);
            img_all_buy=(ImageView)itemView.findViewById(R.id.img_allbuy);
            tv_product_name=(TextView)itemView.findViewById(R.id.tv_product_name);
            tv_pro_price=(TextView)itemView.findViewById(R.id.tv_pro_price);
            tv_discount_price=(TextView)itemView.findViewById(R.id.tv_discount_price);

        }
    }
}
