package com.hqbb.duanzi.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.BaseActivity;
import com.hqbb.duanzi.fragment.GankAllFragment;
import com.hqbb.duanzi.fragment.GankAndroidFragment;
import com.hqbb.duanzi.fragment.GankAppFragment;
import com.hqbb.duanzi.fragment.GankFuliFragment;
import com.hqbb.duanzi.fragment.GankIosFragment;
import com.hqbb.duanzi.fragment.GankQianduanFragment;
import com.hqbb.duanzi.fragment.GankTuijianFragment;
import com.hqbb.duanzi.fragment.GankTuozhanFragment;
import com.hqbb.duanzi.fragment.GankXiuxiFragment;
import com.hqbb.duanzi.fragment.NewsCaijingFragment;
import com.hqbb.duanzi.fragment.NewsGupiaoFragment;
import com.hqbb.duanzi.fragment.NewsJiankangFragment;
import com.hqbb.duanzi.fragment.NewsJiaoyuFragment;
import com.hqbb.duanzi.fragment.NewsJunshiFragment;
import com.hqbb.duanzi.fragment.NewsKejiFragment;
import com.hqbb.duanzi.fragment.NewsNbaFragment;
import com.hqbb.duanzi.fragment.NewsNewsFragment;
import com.hqbb.duanzi.fragment.NewsNvxingFragment;
import com.hqbb.duanzi.fragment.NewsTiyuFragment;
import com.hqbb.duanzi.fragment.NewsToutiaoFragment;
import com.hqbb.duanzi.fragment.NewsXingzuoFragment;
import com.hqbb.duanzi.fragment.NewsYuerFragment;
import com.hqbb.duanzi.fragment.NewsYuleFragment;

import java.util.ArrayList;
import java.util.List;

public class GankActivity extends BaseActivity {

    private List<Fragment> mFragmentList = new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppTabAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);

        initView();
    }
    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        mFragmentList.add(new GankAllFragment());
        mFragmentList.add(new GankAndroidFragment());
        mFragmentList.add(new GankIosFragment());
        mFragmentList.add(new GankQianduanFragment());
        mFragmentList.add(new GankTuozhanFragment());
        mFragmentList.add(new GankFuliFragment());
        mFragmentList.add(new GankXiuxiFragment());
        mFragmentList.add(new GankAppFragment());
        mFragmentList.add(new GankTuijianFragment());

        mAdapter = new AppTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }
    private class AppTabAdapter extends FragmentPagerAdapter {

        CharSequence[] TITLE = getResources().getStringArray(R.array.gank_tab);

        public AppTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }
    }
}
