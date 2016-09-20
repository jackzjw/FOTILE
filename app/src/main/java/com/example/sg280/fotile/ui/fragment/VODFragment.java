package com.example.sg280.fotile.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;

import butterknife.Bind;

/**
 * Created by sg280 on 2016-07-19.
 */
public class VODFragment extends BaseFragment {

    @Bind(android.R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_vod;
    }

    @Override
    protected void onInitView() {
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new ProductVedioFragment();
                    case 1:
                        return new PublicVedioFragment();
                    case 2:
                        return new FotileAdFragment();

                    default:
                        return new ProductVedioFragment();
                }


            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return Constants.PRODUCT_VEDIO_NAME;
                    case 1:
                        return Constants.PUBLIC_VEDIO_NAME;
                    case 2:
                        return Constants.FOTILE_VEDIO_NAME;

                    default:
                        return Constants.PRODUCT_VEDIO_NAME;
                }
            }

        });
              tabLayout.setupWithViewPager(viewPager);
    }


}
