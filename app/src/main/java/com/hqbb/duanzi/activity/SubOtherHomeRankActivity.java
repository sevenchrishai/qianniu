package com.hqbb.duanzi.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.BaseRVActivity;
import com.hqbb.duanzi.bean.BooksByCats;
import com.hqbb.duanzi.component.AppComponent;
import com.hqbb.duanzi.component.DaggerFindComponent;
import com.hqbb.duanzi.contract.SubRankContract;
import com.hqbb.duanzi.easyadapter.SubCategoryAdapter;
import com.hqbb.duanzi.presenter.SubRankPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 别人家的排行榜
 * Created by lfh on 2016/10/15.
 */
public class SubOtherHomeRankActivity extends BaseRVActivity<BooksByCats.BooksBean> implements SubRankContract.View {


    public final static String BUNDLE_ID = "_id";
    public static final String INTENT_TITLE = "title";
    private String id;
    private String title;

    public static void startActivity(Context context, String id, String title) {
        context.startActivity(new Intent(context, SubOtherHomeRankActivity.class)
                .putExtra(INTENT_TITLE, title).putExtra(BUNDLE_ID, id));
    }

    @Inject
    SubRankPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sub_other_home_rank;
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
        title = getIntent().getStringExtra(INTENT_TITLE).split(" ")[0];
        id = getIntent().getStringExtra(BUNDLE_ID);

        mCommonToolbar.setTitle(title);
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        initAdapter(SubCategoryAdapter.class, true, false);
        mPresenter.attachView(this);
        onRefresh();
    }

    @Override
    public void showRankList(BooksByCats data) {
        mAdapter.clear();
        mAdapter.addAll(data.books);
    }

    @Override
    public void showError() {
        loaddingError();
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void onItemClick(int position) {
        BookDetailActivity.startActivity(this, mAdapter.getItem(position)._id);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getRankList(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
