package com.hqbb.duanzi.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.adapter.SubjectTagsAdapter;
import com.hqbb.duanzi.base.BaseBookActivity;
import com.hqbb.duanzi.base.OnRvItemClickListener;
import com.hqbb.duanzi.bean.BookListTags;
import com.hqbb.duanzi.bean.support.TagEvent;
import com.hqbb.duanzi.component.AppComponent;
import com.hqbb.duanzi.component.DaggerFindComponent;
import com.hqbb.duanzi.contract.SubjectBookListContract;
import com.hqbb.duanzi.fragment.SubjectFragment;
import com.hqbb.duanzi.presenter.SubjectBookListPresenter;
import com.hqbb.duanzi.util.ToastUtils;
import com.hqbb.duanzi.widget.RVPIndicator;
import com.hqbb.duanzi.widget.ReboundScrollView;
import com.hqbb.duanzi.widget.SupportDividerItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import de.greenrobot.event.EventBus;

public class SubjectBookListActivity extends BaseBookActivity implements SubjectBookListContract.View, OnRvItemClickListener<String> {

    @BindView(R.id.indicatorSubject)
    RVPIndicator mIndicator;
    @BindView(R.id.viewpagerSubject)
    ViewPager mViewPager;
    @BindView(R.id.rsvTags)
    ReboundScrollView rsvTags;

    @BindView(R.id.rvTags)
    RecyclerView rvTags;
    private SubjectTagsAdapter mTagAdapter;
    private List<BookListTags.DataBean> mTagList = new ArrayList<>();

    private List<Fragment> mTabContents;
    private FragmentPagerAdapter mAdapter;
    private List<String> mDatas;

    @Inject
    SubjectBookListPresenter mPresenter;

    private String currentTag = "";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SubjectBookListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_subject_book_list;
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
        mCommonToolbar.setTitle(R.string.subject_book_list);
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mDatas = Arrays.asList(getResources().getStringArray(R.array.subject_tabs));

        mTabContents = new ArrayList<>();
        mTabContents.add(SubjectFragment.newInstance("", 0));
        mTabContents.add(SubjectFragment.newInstance("", 1));
        mTabContents.add(SubjectFragment.newInstance("", 2));

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
        mIndicator.setViewPager(mViewPager, 0);

        rvTags.setHasFixedSize(true);
        rvTags.setLayoutManager(new LinearLayoutManager(this));
        rvTags.addItemDecoration(new SupportDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mTagAdapter = new SubjectTagsAdapter(this, mTagList);
        mTagAdapter.setItemClickListener(this);
        rvTags.setAdapter(mTagAdapter);

        mPresenter.attachView(this);
        mPresenter.getBookListTags();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_subject, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_tags) {
            if (isVisible(rsvTags)) {
                hideTagGroup();
            } else {
                showTagGroup();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isVisible(rsvTags)) {
            hideTagGroup();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showBookListTags(BookListTags data) {
        mTagList.clear();
        mTagList.addAll(data.data);
        mTagAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {
    }

    @Override
    public void onItemClick(View view, int position, String data) {
        hideTagGroup();
        currentTag = data;
        EventBus.getDefault().post(new TagEvent(currentTag));
    }

    private void showTagGroup() {
        if (mTagList.isEmpty()) {
            ToastUtils.showToast(getString(R.string.network_error_tips));
            return;
        }
        Animation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(400);
        rsvTags.startAnimation(mShowAction);
        rsvTags.setVisibility(View.VISIBLE);
    }

    private void hideTagGroup() {
        Animation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f);
        mHiddenAction.setDuration(400);
        rsvTags.startAnimation(mHiddenAction);
        rsvTags.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
