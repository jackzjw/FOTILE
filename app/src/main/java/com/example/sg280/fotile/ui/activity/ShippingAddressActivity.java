package com.example.sg280.fotile.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.AddressAdapter;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.presents.Interface.MyAddressContacts;
import com.example.sg280.fotile.presents.MyAddressPresent;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tian on 2016/8/22.
 */
public class ShippingAddressActivity extends BaseActivity implements MyAddressContacts.View{
    @Bind(R.id.iv_back_title)
    ImageView ivBackTitle;//返回
    @Bind(R.id.tv_title)
    TextView tvTitle;//标题
    @Bind(R.id.rv_address)
    RecyclerView rvAddress;//地址的RecyclerView
    @Bind(R.id.rl_add_address)
    RelativeLayout rlAddAddress;//添加新地址

    private MyAddressPresent myAddressPresent;
    private AddressAdapter myAddressAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_shipping_address;
    }

    @Override
    protected void onInitView() {

        tvTitle.setText(R.string.shipping_address);//修改标题为收货地址

        String str = (null == getIntent().getStringExtra("account"))?"0":getIntent().getStringExtra("account");

        rvAddress.setLayoutManager(new LinearLayoutManager(this));
        myAddressAdapter = new AddressAdapter(this);
        if("1".equals(str)){
            myAddressAdapter.setIsIntentBack(true);
        }
        rvAddress.setAdapter(myAddressAdapter);
        myAddressPresent = new MyAddressPresent(this,this,myAddressAdapter);
        myAddressAdapter.setMyAddressPresent(myAddressPresent);

    }


    @Override
    protected void onResume() {
        super.onResume();
        myAddressPresent.getAddress();//获取地址列表
    }

    //监听点击事件
    @OnClick({R.id.iv_back_title, R.id.rl_add_address})
    public void onClick(View view) {
        switch (view.getId()) {
            //返回上一层
            case R.id.iv_back_title:
                onBackPressed();
                break;
            //添加新地址
            case R.id.rl_add_address:
                Intent intent = new Intent(this,AddNewAddressActivity.class);
                intent.putExtra("which",Constants.ADD_ONLY);
                startActivity(intent);
                break;
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
