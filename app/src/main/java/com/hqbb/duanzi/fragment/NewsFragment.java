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

public class NewsFragment extends BaseFragment {

    private List<Fragment> mFragmentList = new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppTabAdapter mAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_news, container, false);

        initView();

        return mView;
    }

    private void initView() {
        tabLayout = (TabLayout) mView.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) mView.findViewById(R.id.viewPager);

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

        mAdapter = new AppTabAdapter(getChildFragmentManager(), mContext, R.array.news_tab, mFragmentList);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @Override
    public void refreshView() {

    }
}
