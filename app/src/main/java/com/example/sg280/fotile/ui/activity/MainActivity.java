package com.example.sg280.fotile.ui.activity;

import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.utils.LogUtil;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
     @Bind(R.id.tvi)
     TextView tv_set;


    @Override
         protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {


     tv_set.setText("fafa");
        tv_set.setOnClickListener(view -> {
            LogUtil.d("都是加工方式广泛收到束带结发");

        });
    }
}
