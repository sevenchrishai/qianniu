package com.hqbb.duanzi.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.adapter.GankAdapter;
import com.hqbb.duanzi.base.BaseFragment;
import com.hqbb.duanzi.bean.GankBean;
import com.hqbb.duanzi.retrofit2.RetrofitUtil;
import com.hqbb.duanzi.widget.LoadMoreListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.hqbb.duanzi.base.AppApplication.PAGE_SIZE;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class GankFuliFragment extends BaseFragment{

    private GankAdapter mAdapter;
    private LoadMoreListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Button mBackTop;
    private List<GankBean.ResultsBean> mList = new ArrayList<>();

    private Boolean hasMore = true;
    private int curPage = 0;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_gank_all, container, false);

        initView();

        return mView;
    }

    private void initView() {
        final String[] TITLE = getResources().getStringArray(R.array.gank_tab);

        mAdapter = new GankAdapter(mContext, mList);

        mListView = mView.findViewById(R.id.loadMorelistView);
        mListView.setOnLastItemVisibleListener(new LoadMoreListView.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                curPage = curPage + 1;
                if (!hasMore) {
                    mListView.setFooter(LoadMoreListView.Mode.NOMORE);
                    return;
                }
                requestDataMore(TITLE[5], curPage);
                mListView.setFooter(LoadMoreListView.Mode.LOAD);
                mBackTop.setVisibility(View.VISIBLE);
            }
        });
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
                requestData(TITLE[5], 0);

            }
        });

        requestData(TITLE[5], 0);
    }

    private void requestDataMore(String type, int page) {
        Observable<GankBean> dzObservableMore = RetrofitUtil.getInstance().getGank(type, PAGE_SIZE, page);
        dzObservableMore
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankBean value) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mList.addAll(value.getResults());
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

    private void requestData(String type, int page) {
        mList.clear();
        mBackTop.setVisibility(View.GONE);

        Observable<GankBean> dzObservable = RetrofitUtil.getInstance().getGank(type, PAGE_SIZE, page);
        dzObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankBean value) {
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        mSwipeRefreshLayout.setRefreshing(false);

                        mList.addAll(value.getResults());
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
