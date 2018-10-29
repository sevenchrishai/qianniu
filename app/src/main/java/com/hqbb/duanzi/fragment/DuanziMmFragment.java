package com.hqbb.duanzi.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.adapter.MainAdapter;
import com.hqbb.duanzi.adapter.MmAdapter;
import com.hqbb.duanzi.base.AppApplication;
import com.hqbb.duanzi.base.BaseFragment;
import com.hqbb.duanzi.bean.DuanziBean;
import com.hqbb.duanzi.bean.MmBean;
import com.hqbb.duanzi.retrofit2.RetrofitUtil;
import com.hqbb.duanzi.widget.LoadMoreListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class DuanziMmFragment extends BaseFragment {

    private MmAdapter mAdapter;
    private LoadMoreListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Button mBackTop;
    private List<MmBean.DataBean> mList = new ArrayList<>();

    private Boolean hasMore = true;
    private int curPage = 0;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_voice, container, false);

        initView();

        return mView;
    }

    private void initView() {
        mAdapter = new MmAdapter(mContext, mList);

        mListView = mView.findViewById(R.id.loadMorelistView);
//        mListView.setOnLastItemVisibleListener(new LoadMoreListView.OnLastItemVisibleListener() {
//            @Override
//            public void onLastItemVisible() {
//                curPage = curPage + 1;
//                if (!hasMore) {
//                    mListView.setFooter(LoadMoreListView.Mode.NOMORE);
//                    return;
//                }
//                requestDataMore(curPage);
//                mListView.setFooter(LoadMoreListView.Mode.LOAD);
//                mBackTop.setVisibility(View.VISIBLE);
//            }
//        });
        mListView.setAdapter(mAdapter);

        mBackTop = mView.findViewById(R.id.back_top);
        mBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListView.setSelectionAfterHeaderView();
            }
        });

        mSwipeRefreshLayout = mView.findViewById(R.id.swiperRefresh);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.blue), Color.RED, Color.GREEN);
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新，设置当前为刷新状态
                //swipeRefreshLayout.setRefreshing(true);
                requestData(0);

            }
        });

        requestData(0);
    }

    private void requestDataMore(int page) {
        Observable<MmBean> mmObservableMore = RetrofitUtil.getInstance().getMm(page);
        mmObservableMore
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MmBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MmBean value) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mList.addAll(value.getData());
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void requestData(int page) {
        mList.clear();
        mBackTop.setVisibility(View.GONE);

        Observable<MmBean> mmObservable = RetrofitUtil.getInstance().getMm(page);
        mmObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MmBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MmBean value) {
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        mSwipeRefreshLayout.setRefreshing(false);

                        mList.addAll(value.getData());
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void refreshView() {

    }
}
