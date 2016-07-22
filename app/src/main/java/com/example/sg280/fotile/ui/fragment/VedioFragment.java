package com.example.sg280.fotile.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.widget.TitleBar;

import butterknife.Bind;

/**
 * Created by sg280 on 2016-07-19.
 */
public class VedioFragment extends BaseFragment {
    @Bind(R.id.title_bar)
    TitleBar title_bar;
    @Bind(android.R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_vedio;
    }

    @Override
    protected void onInitView() {
        title_bar.setTitle("点   播");
        title_bar.setBackgroundColor(getResources().getColor(R.color.title_bg_color));
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new DecorateFragment();
                    case 1:
                        return new CreativeAdFragment();
                    case 2:
                        return new ProductVedioFragment();
                    case 3:
                        return new Product2Fragment();
                    default:
                        return new DecorateFragment();
                }


            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.title_decorate);
                    case 1:
                        return getString(R.string.title_ad);
                    case 2:
                        return getString(R.string.title_vedio);
                    case 3:
                        return "产品视频";

                    default:
                        return getString(R.string.title_decorate);
                }
            }

        });
              tabLayout.setupWithViewPager(viewPager);
    }
}
