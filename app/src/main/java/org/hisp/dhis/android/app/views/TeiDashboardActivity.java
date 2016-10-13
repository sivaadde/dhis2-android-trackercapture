package org.hisp.dhis.android.app.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.hisp.dhis.android.app.R;

public class TeiDashboardActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tei_dashboard);

        final ViewPager viewPager = ((ViewPager) findViewById(R.id.view_pager));
        viewPager.setAdapter(new DashboardPageAdapter(
                getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                (tabLayout)));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private class DashboardPageAdapter extends FragmentPagerAdapter {

        public DashboardPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TeiEventFragment();
                case 1:
                    return new TeiProfileFragment();
                case 2:
                    return new TeiWidgetFragment();
                default:
                    return new TeiEventFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

    }
}
