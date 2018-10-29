package com.hqbb.duanzi.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.BaseRVActivity;
import com.hqbb.duanzi.bean.BookSource;
import com.hqbb.duanzi.component.AppComponent;
import com.hqbb.duanzi.component.DaggerBookComponent;
import com.hqbb.duanzi.contract.BookSourceContract;
import com.hqbb.duanzi.easyadapter.BookSourceAdapter;
import com.hqbb.duanzi.presenter.BookSourcePresenter;

import java.util.List;

import javax.inject.Inject;

public class BookSourceActivity extends BaseRVActivity<BookSource> implements BookSourceContract.View {

    public static final String INTENT_BOOK_ID = "bookId";

    public static void start(Activity activity, String bookId, int reqId) {
        activity.startActivityForResult(new Intent(activity, BookSourceActivity.class)
                .putExtra(INTENT_BOOK_ID, bookId), reqId);
    }

    @Inject
    BookSourcePresenter mPresenter;

    private String bookId = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_recyclerview;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerBookComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
        bookId = getIntent().getStringExtra(INTENT_BOOK_ID);
        mCommonToolbar.setTitle("选择来源");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        initAdapter(BookSourceAdapter.class, false, false);
    }

    @Override
    public void configViews() {
        mPresenter.attachView(this);
        mPresenter.getBookSource("summary", bookId);

//        new AlertDialog.Builder(this)
//                .setMessage("换源功能暂未实现，后续更新...")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).create().show();
    }

    @Override
    public void onItemClick(int position) {
        BookSource data = mAdapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra("source", data);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void showBookSource(List<BookSource> list) {
        mAdapter.clear();
        mAdapter.addAll(list);
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
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
