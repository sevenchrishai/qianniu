package com.hqbb.duanzi.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylh on 2018/2/27 0027.
 */

public class AppTabAdapter extends FragmentPagerAdapter {

    protected Context mContext;

    private Integer arrayId;

    private List<Fragment> mFragmentList;

    private CharSequence[] TITLE;

    public AppTabAdapter(FragmentManager fm) {
        super(fm);
    }

    public AppTabAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
        this.mFragmentList = new ArrayList<>();
    }

    public AppTabAdapter(FragmentManager fm, Context mContext, Integer arrayId, List<Fragment> mFragmentList) {
        super(fm);
        this.mContext = mContext;
        this.arrayId = arrayId;
        this.mFragmentList = mFragmentList;
        this.TITLE = mContext.getResources().getStringArray(arrayId);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList == null ? null : mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return TITLE == null ? 0: TITLE.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE == null ? "" : TITLE[position];
    }
}
