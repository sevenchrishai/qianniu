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

public class NewsActivity extends BaseActivity {

    private List<Fragment> mFragmentList = new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppTabAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initView();
    }
    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        mFragmentList.add(new NewsToutiaoFragment());
        mFragmentList.add(new NewsNewsFragment());
        mFragmentList.add(new NewsCaijingFragment());
        mFragmentList.add(new NewsTiyuFragment());
        mFragmentList.add(new NewsYuleFragment());
        mFragmentList.add(new NewsJunshiFragment());
        mFragmentList.add(new NewsJiaoyuFragment());
        mFragmentList.add(new NewsKejiFragment());
        mFragmentList.add(new NewsNbaFragment());
        mFragmentList.add(new NewsGupiaoFragment());
        mFragmentList.add(new NewsXingzuoFragment());
        mFragmentList.add(new NewsNvxingFragment());
        mFragmentList.add(new NewsJiankangFragment());
        mFragmentList.add(new NewsYuerFragment());

        mAdapter = new AppTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }
    private class AppTabAdapter extends FragmentPagerAdapter {

        CharSequence[] TITLE = getResources().getStringArray(R.array.news_tab);

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
