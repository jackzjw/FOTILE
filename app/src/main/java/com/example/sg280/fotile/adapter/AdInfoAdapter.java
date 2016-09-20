package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sg280.fotile.model.bean.HomeAdBean;
import com.example.sg280.fotile.utils.Glides;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by sg280 on 2016/9/5.
 */
public class AdInfoAdapter extends StaticPagerAdapter {

        private WeakReference<Context> mcontext;
        private List<HomeAdBean> mlist;
        public AdInfoAdapter(Context c,List<HomeAdBean> list){
            this.mlist=list;
            mcontext=new WeakReference<Context>(c);
        }
        @Override
        public View getView(ViewGroup container, int position) {
            ImageView imageView=new ImageView(mcontext.get());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glides.getInstance().load(mcontext.get(),mlist.get(position).getFileName(),imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return mlist.size();
        }

}
