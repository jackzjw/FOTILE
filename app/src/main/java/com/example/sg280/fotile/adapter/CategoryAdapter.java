package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.ProductCategoryBean;

import java.util.List;

/**
 * Created by sg280 on 2016/9/26.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context mcontext;
    private List<ProductCategoryBean> mlist;
    private int selectindex;
    public CategoryAdapter(Context c,List<ProductCategoryBean> l){
        this.mcontext=c;
        this.mlist=l;

    }
    public void refresh(List<ProductCategoryBean> beans){
        this.mlist=beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setSelect(int temp){
        this.selectindex=temp;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder VH=null;
        if(convertView==null){
            VH=new ViewHolder();
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.item_pro_category,null);
            VH.tv_name=(TextView)convertView.findViewById(R.id.tv_procategory_name);
            VH.baseline=(View)convertView.findViewById(R.id.v_baseline);
            convertView.setTag(VH);
        }
        VH=(ViewHolder)convertView.getTag();
        VH.tv_name.setText(mlist.get(position).getClassName());
        if(position==selectindex){
            VH.tv_name.setTextColor(mcontext.getResources().getColor(R.color.theme_red));
            VH.baseline.setBackgroundColor(mcontext.getResources().getColor(R.color.theme_red));
        }else {
            VH.tv_name.setTextColor(mcontext.getResources().getColor(R.color.theme_gray));
            VH.baseline.setBackgroundColor(mcontext.getResources().getColor(R.color.hui_bg));
        }

        return convertView;
    }

class ViewHolder{
    private TextView tv_name;
    private View baseline;
}
}
