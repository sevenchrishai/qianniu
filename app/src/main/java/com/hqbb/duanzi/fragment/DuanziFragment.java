package com.hqbb.duanzi.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.adapter.AppTabAdapter;
import com.hqbb.duanzi.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class DuanziFragment extends BaseFragment {

    private List<Fragment> mFragmentList = new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppTabAdapter mAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_duanzi, container, false);

        initView();

        return mView;
    }

    private void initView() {
        tabLayout = (TabLayout) mView.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) mView.findViewById(R.id.viewPager);

        mFragmentList.add(new DuanziMainFragment());
        mFragmentList.add(new DuanziJokeFragment());
        mFragmentList.add(new DuanziPicFragment());
        mFragmentList.add(new DuanziMmFragment());
        mFragmentList.add(new DuanziVideoFragemnt());

        //注意： getChildFragmentManager()
        mAdapter = new AppTabAdapter(getChildFragmentManager(), mContext, R.array.tab, mFragmentList);
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            JZVideoPlayer.releaseAllVideos();
        }
    }

    @Override
    public void refreshView() {

    }
}
