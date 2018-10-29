package com.hqbb.duanzi.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.BaseBookActivity;
import com.hqbb.duanzi.component.AppComponent;
import com.hqbb.duanzi.component.DaggerFindComponent;
import com.hqbb.duanzi.fragment.SubRankFragment;
import com.hqbb.duanzi.widget.RVPIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class SubRankActivity extends BaseBookActivity {

    public static final String INTENT_WEEK = "_id";
    public static final String INTENT_MONTH = "month";
    public static final String INTENT_ALL = "all";
    public static final String INTENT_TITLE = "title";

    public static void startActivity(Context context, String week, String month, String all, String title) {
        context.startActivity(new Intent(context, SubRankActivity.class)
                .putExtra(INTENT_WEEK, week)
                .putExtra(INTENT_MONTH, month)
                .putExtra(INTENT_ALL, all)
                .putExtra(INTENT_TITLE, title));
    }

    private String week;
    private String month;
    private String all;
    private String title;

    @BindView(R.id.indicatorSubRank)
    RVPIndicator mIndicator;
    @BindView(R.id.viewpagerSubRank)
    ViewPager mViewPager;

    private List<Fragment> mTabContents;
    private FragmentPagerAdapter mAdapter;
    private List<String> mDatas;


    @Override
    public int getLayoutId() {
        return R.layout.activity_sub_rank;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFindComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
        week = getIntent().getStringExtra(INTENT_WEEK);
        month = getIntent().getStringExtra(INTENT_MONTH);
        all = getIntent().getStringExtra(INTENT_ALL);

        title = getIntent().getStringExtra(INTENT_TITLE).split(" ")[0];
        mCommonToolbar.setTitle(title);
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mDatas = Arrays.asList(getResources().getStringArray(R.array.sub_rank_tabs));

        mTabContents = new ArrayList<>();
        mTabContents.add(SubRankFragment.newInstance(week));
        mTabContents.add(SubRankFragment.newInstance(month));
        mTabContents.add(SubRankFragment.newInstance(all));

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
    }

    @Override
    public void configViews() {
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mIndicator.setViewPager(mViewPager, 0);
    }
}
