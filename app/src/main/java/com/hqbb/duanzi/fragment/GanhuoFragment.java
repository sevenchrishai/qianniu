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

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class GanhuoFragment extends BaseFragment {

    private List<Fragment> mFragmentList = new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppTabAdapter mAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_gank, container, false);

        initView();

        return mView;
    }

    private void initView() {
        tabLayout = (TabLayout) mView.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) mView.findViewById(R.id.viewPager);

        mFragmentList.add(new GankAllFragment());
        mFragmentList.add(new GankAndroidFragment());
        mFragmentList.add(new GankIosFragment());
        mFragmentList.add(new GankQianduanFragment());
        mFragmentList.add(new GankTuozhanFragment());
        mFragmentList.add(new GankFuliFragment());
        mFragmentList.add(new GankXiuxiFragment());
        mFragmentList.add(new GankAppFragment());
        mFragmentList.add(new GankTuijianFragment());

        mAdapter = new AppTabAdapter(getChildFragmentManager(),mContext,R.array.gank_tab,mFragmentList);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }
    @Override
    public void refreshView() {

    }
}
