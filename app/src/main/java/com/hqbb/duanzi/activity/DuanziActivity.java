package com.hqbb.duanzi.activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.BaseActivity;
import com.hqbb.duanzi.fragment.DuanziJokeFragment;
import com.hqbb.duanzi.fragment.DuanziMainFragment;
import com.hqbb.duanzi.fragment.DuanziMmFragment;
import com.hqbb.duanzi.fragment.DuanziPicFragment;
import com.hqbb.duanzi.fragment.DuanziVideoFragemnt;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;

public class DuanziActivity extends BaseActivity {

    private List<Fragment> mFragmentList = new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppTabAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duanzi);

        initView();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        mFragmentList.add(new DuanziMainFragment());
        mFragmentList.add(new DuanziJokeFragment());
        mFragmentList.add(new DuanziPicFragment());
        mFragmentList.add(new DuanziMmFragment());
        mFragmentList.add(new DuanziVideoFragemnt());

        mAdapter = new AppTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                Log.e("TAG", "onPageSelected()");
                JZVideoPlayer.releaseAllVideos();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    private class AppTabAdapter extends FragmentPagerAdapter {

        CharSequence[] TITLE = getResources().getStringArray(R.array.tab);

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

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
