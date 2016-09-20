package com.example.sg280.fotile.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.AdInfoAdapter;
import com.example.sg280.fotile.adapter.HotProAdapter;
import com.example.sg280.fotile.model.bean.HomeAdBean;
import com.example.sg280.fotile.model.bean.HomeHotProBean;
import com.example.sg280.fotile.model.bean.ProductCategoryBean;
import com.example.sg280.fotile.presents.Interface.IProductsContacts;
import com.example.sg280.fotile.presents.ProductsPresent;
import com.example.sg280.fotile.ui.activity.ProductsActivity;
import com.example.sg280.fotile.utils.DensityUtil;
import com.example.sg280.fotile.widget.ItemClickSupport;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sg280 on 2016/9/5.
 */
public class ProductsFragment extends BaseFragment implements IProductsContacts.View{
  @Bind(R.id.roll_viewpager)
    RollPagerView rollPagerView;
    @Bind(R.id.gridview)
    GridView gridView;
    @Bind(R.id.pro_ercv)
    RecyclerView proRecyclerView;
  private ProductsPresent present;
  private List<HomeAdBean> imgList;
  private AdInfoAdapter imgAdapter;
  private List<ProductCategoryBean> categorysList;
  private CategoryAdapter caAdapter;
  private List<ProductCategoryBean> shinklist;
  private boolean isShink=true;
    private boolean loading=false;
    private List<HomeHotProBean> ProsList;
    private HotProAdapter proAdapter;
    private String classid;
    private int page=1;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_products;
    }

    @Override
    protected void onInitView() {
     present=new ProductsPresent(getActivity(),this);
     present.getAdInfo();
      present.getProCategory();
      setAdInfoAdapter();
      setCategoryAdapter();
      setProDetailsAdapter();
    }
//产品adapter
    private void setProDetailsAdapter() {
        GridLayoutManager gridlayout = new GridLayoutManager(getActivity(), 2);
        ProsList = new ArrayList<HomeHotProBean>();
        proAdapter = new HotProAdapter(getActivity(), ProsList);
        proRecyclerView.setAdapter(proAdapter);

        proRecyclerView.setLayoutManager(gridlayout);
        gridlayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
           return proAdapter.isFooter(position)?gridlayout.getSpanCount():1;
        }
    });
       proRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
               GridLayoutManager layoutmanage = (GridLayoutManager)recyclerView.getLayoutManager();
               int totalCount=layoutmanage.getItemCount();
               int lastVisiblePosition=layoutmanage.findLastVisibleItemPosition();
               if(!loading&&totalCount<lastVisiblePosition+2){
                   if(ProsList.size()%10==0) {
                       page++;
                       present.getCategoryPro(classid, page, 10);
                       proAdapter.setLoadStatus(HotProAdapter.LoadStatus.LOAD_MORE_STATUS);
                   }
                   loading=true;
               }

           }
       });
        ItemClickSupport.addTo(proRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent= ProductsActivity.newIntent(getActivity(),ProsList.get(position).getID());
                getActivity().startActivity(intent);
            }
        });
    }

    //产品分类adapter
  private void setCategoryAdapter() {
    categorysList=new ArrayList<ProductCategoryBean>();
    shinklist=new ArrayList<ProductCategoryBean>();
    caAdapter=new CategoryAdapter(getActivity(),categorysList);
    gridView.setAdapter(caAdapter);
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        caAdapter.setSelect(position);
        if(shinklist.size()!=0&&position==4&&isShink){
            categorysList.get(4).setClassName("收起");
          caAdapter.refresh(categorysList);
          isShink=false;
        }else if(shinklist.size()!=0&&position==4&&!isShink){
            shinklist.get(4).setClassName("更多");
          caAdapter.refresh(shinklist);
          isShink=true;
        }else {
            page=1;
            classid=categorysList.get(position).getID();
            ProsList.clear();
            present.getCategoryPro(classid,page,10);
        }
      }
    });
  }
//广告adapter
  private void setAdInfoAdapter() {
    rollPagerView.setHintView(new ColorPointHintView(getActivity(),
            getResources().getColor(R.color.dot_black60), getResources().getColor(R.color.dot_black20)));
    rollPagerView.setPlayDelay(5000);
    rollPagerView.setAnimationDurtion(1000);
    rollPagerView.setHintPadding(0, 0, 0, DensityUtil.dp2px(getActivity(), 8));
    imgList = new ArrayList<HomeAdBean>();
    imgAdapter = new AdInfoAdapter(getActivity(), imgList);
    rollPagerView.setAdapter(imgAdapter);

  }

  @Override
  public void loadAdInfo(List<HomeAdBean> adBeans) {
    imgList.addAll(adBeans);
    imgAdapter.notifyDataSetChanged();
  }

  @Override
  public void loadCategoryList(List<ProductCategoryBean> categoryBeans) {
    if(categoryBeans==null||categoryBeans.size()==0) return;
      classid=categoryBeans.get(0).getID();
    if(categoryBeans.size()>5){
      ProductCategoryBean toggle=new ProductCategoryBean();
      toggle.setClassName("更多");
      categoryBeans.add(4,toggle);
      for (int i=0;i<5;i++){
        shinklist.add(categoryBeans.get(i));
      }
    }
    categorysList.addAll(categoryBeans);
    caAdapter.refresh(shinklist);
      caAdapter.setSelect(0);
      present.getCategoryPro(classid,page,10);
  }

  @Override
  public void loadProList(List<HomeHotProBean> homeHotProBeans) {
      if(homeHotProBeans.size()==0&&page==1) proAdapter.setLoadStatus(HotProAdapter.LoadStatus.NO_DATA_STATUS);
      ProsList.addAll(homeHotProBeans);
      proAdapter.notifyDataSetChanged();
      loading=false;
  }





    class CategoryAdapter extends BaseAdapter{
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
  }
  class ViewHolder{
    private TextView tv_name;
    private View baseline;
  }
}
