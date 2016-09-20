package com.example.sg280.fotile.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.ProductsBean;
import com.example.sg280.fotile.model.bean.ShoppingCartGoodsBean;
import com.example.sg280.fotile.presents.ProductDetailsPresent;
import com.example.sg280.fotile.ui.activity.OrderSureActivity;
import com.example.sg280.fotile.utils.DensityUtil;
import com.example.sg280.fotile.utils.Glides;
import com.example.sg280.fotile.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sg280 on 2016/9/6.
 */
public class AddCartDialog extends Dialog {

    private  String skuid;
    @Bind(R.id.img_sku)
    ImageView img_sku;
    @Bind(R.id.tv_sku_price)
    TextView tv_sku_price;
    @Bind(R.id.tv_num)
    TextView tv_num;
    @Bind(R.id.img_jian)
    ImageView img_jian;
    @Bind(R.id.img_add)
    ImageView img_add;
    @Bind(R.id.radiogroup)
    RadioGroup radioGroup;
    @Bind(R.id.bt_sure)
    Button bt_sure;
    private int num=1;
    private String imgurl;
    private String proname;
    private String skuPrice;
    public AddCartDialog(Context context,ProductsBean Beans, ProductDetailsPresent present,int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_add_cart);
        ButterKnife.bind(this);
        List<ProductsBean.SkuListBean> skuListBeans=Beans.getSkuList();
       if(skuListBeans==null) return;
        String brandid = Beans.getBrandID();
        imgurl=skuListBeans.get(0).getSkuImageSer();
        proname=skuListBeans.get(0).getSkuName();
        skuPrice=skuListBeans.get(0).getPricePromo();
        Glides.getInstance().load(context, imgurl, img_sku);
        tv_sku_price.setText("¥" + skuPrice);
        tv_num.setText(num + "");
        skuid = skuListBeans.get(0).getID();
        for ( int i=0;i<skuListBeans.size();i++){
            RadioButton button=new RadioButton(context);
            RadioGroup.LayoutParams params=new RadioGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, DensityUtil.dp2px(context,20));
            params.setMargins(10, 0, 0, 0);
            button.setLayoutParams(params);
            button.setTextSize(11);
            button.setText(skuListBeans.get(i).getSkuName());
            button.setTextColor(context.getResources().getColorStateList(R.color.selector_comment_color));
            button.setBackgroundResource(R.drawable.select_comment_setting);
            button.setButtonDrawable(android.R.color.transparent);
            button.setTag(skuListBeans.get(i));
            button.setBackground(context.getResources().getDrawable(R.drawable.select_comment_setting));
              radioGroup.addView(button);
            if(i==0) radioGroup.check(button.getId());
        }
       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               RadioButton radioButton=(RadioButton)findViewById(checkedId);
               ProductsBean.SkuListBean data = (ProductsBean.SkuListBean) radioButton.getTag();
               imgurl=data.getSkuImageSer();
               Glides.getInstance().load(context,imgurl, img_sku);
               proname=data.getSkuName();
               skuPrice=data.getPricePromo();
               tv_sku_price.setText("¥" + StringUtil.format(Double.parseDouble(skuPrice) * num));
               skuid = data.getID();
           }
       });
        img_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == 1) return;
                tv_num.setText(--num+"");
                tv_sku_price.setText("¥" +StringUtil.format(num * Double.parseDouble(skuPrice)));
            }
        });
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_num.setText(++num+"");
                tv_sku_price.setText("¥" +StringUtil.format(num * Double.parseDouble(skuPrice)));
            }
        });

        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bt_sure.getText().equals("确定")) {
                    HashMap<String, Object> map = new HashMap<>();
                    String userid = MySelfInfo.getInstance().getId() == null ? "" : MySelfInfo.getInstance().getId();
                    map.put("userid", userid);
                    map.put("brandid", brandid);
                    map.put("productid", Beans.getID());
                    map.put("skuid", skuid);
                    map.put("productnum", num);
                    present.addCart(map);
                }else if(bt_sure.getText().equals("立即购买")){

                    Intent intent=new Intent(context, OrderSureActivity.class);
                    ShoppingCartGoodsBean bean=new ShoppingCartGoodsBean();
                    bean.setProductQuantity(num+"");
                    bean.setPricePromo(skuPrice);
                    bean.setProductID(Beans.getID());
                    bean.setSkuID(skuid);
                    bean.setProductName(Beans.getProductName());
                    bean.setSkuImageSer(imgurl);
                    bean.setSkuName(proname);
                   intent.putExtra("product", bean);
                    intent.putExtra("which",0);
                    context.startActivity(intent);
                    dismiss();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);
        this.setCanceledOnTouchOutside(true);

    }
   public void setBtText(String type){
       bt_sure.setText(type);
   }
    public void dismiss(){
       super.dismiss();
   }
    public void show(){
        super.show();
    }
}
