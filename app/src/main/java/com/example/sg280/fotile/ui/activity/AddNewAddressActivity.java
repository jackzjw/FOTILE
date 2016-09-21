package com.example.sg280.fotile.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.bean.ShippingAddressBean;
import com.example.sg280.fotile.presents.AddNewAddressPresent;
import com.example.sg280.fotile.presents.Interface.AddNewAddressContacts;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.example.sg280.fotile.utils.StringUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加收货地址界面
 * Created by Tian on 2016/8/19.
 */
public class AddNewAddressActivity extends BaseActivity implements AddNewAddressContacts.View {

    @Bind(R.id.iv_back_title)
    ImageView ivBackTitle;//返回图标
    @Bind(R.id.tv_title)
    TextView tvTitle;//标题
    @Bind(R.id.et_write_name)
    EditText etWriteName;//输入用户名
    @Bind(R.id.et_write_phone)
    EditText etWritePhone;//输入手机号
    @Bind(R.id.tv_province_city)
    TextView tvProvinceCity;//省市区
    @Bind(R.id.rl_province_city)
    RelativeLayout rlProvinceCity;//省市区布局
    @Bind(R.id.et_address)
    EditText etAddress;//详细地址
    @Bind(R.id.et_postcode)
    EditText etPostcode;//邮政编码
    @Bind(R.id.btn_sure)
    Button btnSure;//确定
    private final String ADD_ACTION = "addAddress";
    private final String MODIFY_ACTION = "modifyAddress";
    private AddNewAddressPresent present;
    private String name, phone, prc, address, postcode;
    private Intent intent;
    private ShippingAddressBean addressBean;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_add_shipping_address;
    }

    @Override
    protected void onInitView() {
        present = new AddNewAddressPresent(this, this);

        intent = getIntent();
        if(Constants.MODIFY.equals(intent.getStringExtra("which"))){
            tvTitle.setText(R.string.modify_shipping_address);//编辑收货地址
            addressBean = intent.getParcelableExtra("address");
            etWriteName.setText(addressBean.getRecipients());
            etWritePhone.setText(addressBean.getTel());
            tvProvinceCity.setText(addressBean.getPcr());
            etAddress.setText(addressBean.getAddress());
            etPostcode.setText(addressBean.getPostcode());
        }else if(Constants.ADD.equals(intent.getStringExtra("which"))){
            tvTitle.setText(R.string.add_shipping_address);//添加收货地址
        }else if(Constants.ADD_ONLY.equals(intent.getStringExtra("which"))){
            tvTitle.setText(R.string.add_shipping_address);//添加收货地址
        }

    }


    //监听点击事件
    @OnClick({R.id.iv_back_title, R.id.rl_province_city, R.id.btn_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            //返回上一层
            case R.id.iv_back_title:
                onBackPressed();
                break;
            //选择省市区
            case R.id.rl_province_city:
                break;
            //确定
            case R.id.btn_sure:
                if (isOK()) {
                    if(Constants.ADD.equals(intent.getStringExtra("which"))){
                        //添加地址网络请求(默认)
                        present.addAddress(ADD_ACTION, SharedPreferencesUtil.getId(getApplication()), "".equals(prc)?"上海市黄浦区":prc, address, phone, postcode
                                , name, "1");
                    }else if(Constants.MODIFY.equals(intent.getStringExtra("which"))){
                        //修改地址网络请求
                        present.modifyAddress(MODIFY_ACTION,SharedPreferencesUtil.getId(getApplication()), "".equals(prc)?"上海市黄浦区":prc, address, phone, postcode
                                , name, addressBean.getIsDefault(),addressBean.getID());
                    }else if(Constants.ADD_ONLY.equals(intent.getStringExtra("which"))){
                        //添加地址网络请求(非默认)
                        present.addAddress(ADD_ACTION, SharedPreferencesUtil.getId(getApplication()), "".equals(prc)?"上海市黄浦区":prc, address, phone, postcode
                                , name, "0");
                    }
                }
                break;
        }
    }

    //添加成功
    @Override
    public void addSuc() {
        ToastUtil.showShort(this, "添加成功");

        if(Constants.ADD.equals(intent.getStringExtra("which"))){
            //返回数据
            Intent backIntent = new Intent();
            ShippingAddressBean addressBean = new ShippingAddressBean();
            addressBean.setRecipients(name);
            addressBean.setAddress(address);
            addressBean.setPcr("".equals(prc)?"上海市黄浦区":prc);
            addressBean.setTel(phone);
            addressBean.setPostcode(postcode);
            addressBean.setIsDefault("1");
            backIntent.putExtra("address",addressBean);
            setResult(RESULT_OK,backIntent);
        }
        finish();
    }

    //编辑成功
    @Override
    public void modifySuc() {
        ToastUtil.showShort(this, "修改成功");
        finish();
    }

    //判断参数是否为空等
    private boolean isOK() {

        name = etWriteName.getText().toString().trim();
        phone = etWritePhone.getText().toString().trim();
        prc = tvProvinceCity.getText().toString().trim();
        address = etAddress.getText().toString().trim();
        postcode = etPostcode.getText().toString().trim();

        if ("".equals(name)) {
            ToastUtil.showShort(this, "请填写收货人");
            return false;
        }
        if (!StringUtil.isMobileNO(phone)) {
            ToastUtil.showShort(this, "请填写正确的收货人手机号");
            return false;
        }
//        if ("".equals(prc)) {
//            ToastUtil.showShort(this, "请选择省市区");
//            return false;
//        }
        if ("".equals(address)) {
            ToastUtil.showShort(this, "请填详细地址");
            return false;
        }
        if ("".equals(postcode)) {
            ToastUtil.showShort(this, "请填写邮政编码");
            return false;
        }
        return true;
    }
}
