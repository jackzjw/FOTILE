package com.example.sg280.fotile.presents;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.sg280.fotile.adapter.ShoppingCartAdapter;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.BrandBean;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.ShoppingCartGoodsBean;
import com.example.sg280.fotile.model.source.GetShoppingCartGoodsSubject;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.ModifyShoppingCartSubject;
import com.example.sg280.fotile.presents.Interface.ShoppingCartContacts;
import com.example.sg280.fotile.ui.activity.OrderSureActivity;
import com.example.sg280.fotile.ui.fragment.MyShoppingCartFragment;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.example.sg280.fotile.utils.StringUtil;
import com.example.sg280.fotile.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sg027 on 2016/8/31.
 */
public class ShoppingCartPresent implements ShoppingCartContacts.Present {

    private Context context;
    private ShoppingCartContacts.View view;
    private RecyclerView recyclerView;
    private ShoppingCartAdapter myAdapter;
    private List<ShoppingCartGoodsBean> list = new ArrayList<>();
    private CheckBox cb_is_selected;//品牌选择框
    private TextView tv_goods_price_info;//所选的全部商品的金额

    public ShoppingCartPresent(Context context, ShoppingCartContacts.View view, RecyclerView recyclerView,
                               CheckBox cb_is_selected,TextView tv_goods_price_info) {
        this.context = context;
        this.view = view;
        this.recyclerView = recyclerView;
        this.cb_is_selected = cb_is_selected;
        this.tv_goods_price_info = tv_goods_price_info;

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //监听品牌选择框状态
        cb_is_selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (MyShoppingCartFragment.i == Constants.FROM_SELF) {

                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(isChecked);
                    }
                    myAdapter.notifyDataSetChanged();
                    myAdapter.setPrice();
                }
            }
        });
    }

    @Override
    public void getShoppingCartGoods() {
        HttpOnNextListener getShopNextListener = new HttpOnNextListener<List<BrandBean>>() {
            @Override
            public void onNext(List<BrandBean> brandList) {
                if(null == brandList || brandList.size() == 0){
                    view.noData();
                }else{
                    view.hadData();
                    list = brandList.get(0).getList();
                    view.setBrandLogo(brandList.get(0).getBrandLOGOSer());
                    cb_is_selected.setChecked(false);
                    myAdapter = new ShoppingCartAdapter(list, context, cb_is_selected, tv_goods_price_info,ShoppingCartPresent.this);
                    recyclerView.setAdapter(myAdapter);
                }
            }

        };
        if (MySelfInfo.getInstance().getId()!=null) {
            view.hadData();
            GetShoppingCartGoodsSubject subject = new GetShoppingCartGoodsSubject(new ProgressSubscriber(getShopNextListener,context)
                    , SharedPreferencesUtil.getId(context));
            FotileRetrofit.getInstance().doHttpDeal(subject);
        }else{
            view.noData();
        }

    }


    /**
     * 添加，减少，删除购物车内商品数量
     * @param action addnum：添加 subnum：减少 delpro：删除（可批量删除）
     * @param position 商品的下标
     */
    @Override
    public void modifyGoodsNum(String action,int position) {
        HttpOnNextListener addOnNextListener = new HttpOnNextListener<String>() {
            @Override
            public void onNext(String success) {

                switch (action){
                    case "addnum":
                        if(success.equals("1")){
                            int i = Integer.valueOf(list.get(position).getProductQuantity());
                            i++;
                            list.get(position).setProductQuantity(""+i);
                            myAdapter.notifyDataSetChanged();
                            myAdapter.setPrice();
                        }
                        break;
                    case "subnum":
                        if(success.equals("1")){
                            int i = Integer.valueOf(list.get(position).getProductQuantity());
                            i--;
                            list.get(position).setProductQuantity(""+i);
                            myAdapter.notifyDataSetChanged();
                            myAdapter.setPrice();
                        }
                        break;
                    case "delpro":
                        if(success.equals("1")){
                            list.remove(position);
                            if(list.size() == 0){
                                view.noData();
                            }else{
                                view.hadData();
                                myAdapter.notifyDataSetChanged();
                                myAdapter.setPrice();
                            }
                            myAdapter.dismissDialog();
                        }
                        break;
                }
            }

        };

        ModifyShoppingCartSubject addSubject = new ModifyShoppingCartSubject(new ProgressSubscriber(addOnNextListener,context),
                action,list.get(position).getID(),SharedPreferencesUtil.getId(context));
        FotileRetrofit.getInstance().doHttpDealNo(addSubject);
    }


    @Override
    public void ondestory() {

    }

    /**
     * 点击结算
     */
    public void ClickGoPay(){
        if(StringUtil.listIsNull(list)){
            ToastUtil.showShort(context,"请选择您需要购买的商品");
            return;
        }

        ArrayList<ShoppingCartGoodsBean> selectedList = new ArrayList<>();
        for (int i1 = 0; i1 < list.size(); i1++) {
            if(list.get(i1).isChecked()){
                selectedList.add(list.get(i1));
            }
        }

        if(StringUtil.listIsNull(selectedList)){
            ToastUtil.showShort(context,"请选择您需要购买的商品");
            return;
        }

        Intent intent = new Intent(context, OrderSureActivity.class);
        intent.putParcelableArrayListExtra("list",selectedList);
        intent.putExtra("price",tv_goods_price_info.getText());
        intent.putExtra("which",1);
        context.startActivity(intent);
    }
}
