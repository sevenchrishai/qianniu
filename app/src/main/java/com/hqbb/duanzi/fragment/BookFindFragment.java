/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hqbb.duanzi.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.activity.SubjectBookListActivity;
import com.hqbb.duanzi.activity.TopCategoryListActivity;
import com.hqbb.duanzi.activity.TopRankActivity;
import com.hqbb.duanzi.adapter.FindAdapter;
import com.hqbb.duanzi.base.BaseBookFragment;
import com.hqbb.duanzi.base.OnRvItemClickListener;
import com.hqbb.duanzi.bean.support.FindBean;
import com.hqbb.duanzi.component.AppComponent;
import com.hqbb.duanzi.widget.SupportDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 发现
 *
 * @author yuyh.
 * @date 16/9/1.
 */
public class BookFindFragment extends BaseBookFragment implements OnRvItemClickListener<FindBean> {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private FindAdapter mAdapter;
    private List<FindBean> mList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initDatas() {
        mList.clear();
        mList.add(new FindBean("排行榜", R.mipmap.home_find_rank));
        mList.add(new FindBean("主题书单", R.mipmap.home_find_topic));
        mList.add(new FindBean("分类", R.mipmap.home_find_category));
    }

    @Override
    public void configViews() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new SupportDividerItemDecoration(mContext, LinearLayoutManager.VERTICAL, true));

        mAdapter = new FindAdapter(mContext, mList, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void onItemClick(View view, int position, FindBean data) {
        switch (position) {
            case 0:
                TopRankActivity.startActivity(activity);    // 排行榜
                break;
            case 1:
                SubjectBookListActivity.startActivity(activity);    // 主题书单
                break;
            case 2:
                startActivity(new Intent(activity, TopCategoryListActivity.class)); //分类
                break;
            default:
                break;
        }
    }

}
