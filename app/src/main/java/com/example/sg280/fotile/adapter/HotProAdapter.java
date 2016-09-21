package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.HomeHotProBean;
import com.example.sg280.fotile.utils.Glides;

import java.util.List;

/**
 * Created by sg280 on 2016/9/5.
 */
public class HotProAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final  int FOOTER_TYPE=0;
    private static final int ITEM_TYPE=1;
    private LoadStatus mloadstatus=LoadStatus.LOAD_MORE_STATUS;
    private Context mcontext;
    private List<HomeHotProBean> mlist;
    public  HotProAdapter(Context c,List<HomeHotProBean> list){
        this.mcontext=c;
        this.mlist=list;
    }
   public void setData(List<HomeHotProBean> data){
       this.mlist=data;
       notifyDataSetChanged();
   }
    public boolean isFooter(int position){
        return position+1==getItemCount();
    }
    @Override
    public int getItemViewType(int position) {
        if(position+1==getItemCount()){
            return FOOTER_TYPE;
        }else {
            return ITEM_TYPE;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==ITEM_TYPE) {
            ItemViewHolder viewholder = new ItemViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_home_hotproduct, parent, false));
       return viewholder;
        }else if(viewType==FOOTER_TYPE){
            FooterViewHolder footerViewHolder=new FooterViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.view_more,parent,false));
            return footerViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){

            case ITEM_TYPE:
                if(mlist.size()==0) return;
                ItemViewHolder iholder=(ItemViewHolder)holder;
                Glides.getInstance().load(mcontext, mlist.get(position).getProImgPPix(), iholder.img_all_buy);
                iholder.tv_product_name.setText(mlist.get(position).getProductName());
                iholder.tv_pro_price.setText("¥" + mlist.get(position).getPricePromo());
                iholder.tv_discount_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                iholder.tv_discount_price.setText("¥" + mlist.get(position).getPriceCommon());
                   break;
            case FOOTER_TYPE:
                FooterViewHolder fholder=(FooterViewHolder)holder;
                if(mloadstatus==LoadStatus.NO_DATA_STATUS){
                    fholder.footer_pb.setVisibility(View.GONE);
                    fholder.footer_tv.setText("暂无数据");
                }else
                if(mloadstatus==LoadStatus.LOAD_MORE_STATUS&&mlist.size()%10==0&&mlist.size()!=0){
                      fholder.footer_pb.setVisibility(View.VISIBLE);
                      fholder.footer_tv.setText("更多加载中...");
                  }else if(mloadstatus==LoadStatus.NO_MORE_STATUS||mlist.size()%10!=0||mlist.size()==0){
                      fholder.footer_pb.setVisibility(View.GONE);
                      fholder.footer_tv.setText("没有更多了");
                  }



        }

    }

    @Override
    public int getItemCount() {
        return mlist.size()+1;
    }
    public void setLoadStatus(LoadStatus status){
        this.mloadstatus=status;
    }


  public   class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_discount_price;
        private  TextView tv_pro_price;
        private  TextView tv_product_name;
        private ImageView img_all_buy;
        public ItemViewHolder(View itemView) {
            super(itemView);
            img_all_buy=(ImageView)itemView.findViewById(R.id.img_allbuy);
            tv_product_name=(TextView)itemView.findViewById(R.id.tv_product_name);
            tv_pro_price=(TextView)itemView.findViewById(R.id.tv_pro_price);
            tv_discount_price=(TextView)itemView.findViewById(R.id.tv_discount_price);

        }
    }
    class FooterViewHolder extends RecyclerView.ViewHolder{
     private ProgressBar footer_pb;
        private TextView footer_tv;

        public FooterViewHolder(View itemView) {
            super(itemView);
            footer_pb=(ProgressBar)itemView.findViewById(R.id.footer_pb);
            footer_tv=(TextView)itemView.findViewById(R.id.footer_tv);
        }
    }
    public enum LoadStatus{
       LOAD_MORE_STATUS,
        NO_MORE_STATUS,
        NO_DATA_STATUS
    }
}
