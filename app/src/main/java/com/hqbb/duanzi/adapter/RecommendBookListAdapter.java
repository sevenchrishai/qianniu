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
package com.hqbb.duanzi.adapter;

import android.content.Context;
import android.view.View;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.Constant;
import com.hqbb.duanzi.base.OnRvItemClickListener;
import com.hqbb.duanzi.bean.RecommendBookList;
import com.hqbb.duanzi.manager.SettingManager;
import com.hqbb.duanzi.util.NoDoubleClickListener;
import com.hqbb.easyadapterlibrary.recyclerview.EasyRVAdapter;
import com.hqbb.easyadapterlibrary.recyclerview.EasyRVHolder;

import java.util.List;

/**
 * @author lfh.
 * @date 16/8/7.
 */
public class RecommendBookListAdapter extends EasyRVAdapter<RecommendBookList.RecommendBook> {

    private OnRvItemClickListener itemClickListener;

    public RecommendBookListAdapter(Context context, List<RecommendBookList.RecommendBook> list,
                                    OnRvItemClickListener listener) {
        super(context, list, R.layout.item_book_detail_recommend_book_list);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(final EasyRVHolder holder, final int position, final RecommendBookList.RecommendBook item) {
        if (!SettingManager.getInstance().isNoneCover()) {
            holder.setRoundImageUrl(R.id.ivBookListCover, Constant.IMG_BASE_URL + item.cover, R.mipmap.cover_default);
        }

        holder.setText(R.id.tvBookListTitle, item.title)
                .setText(R.id.tvBookAuthor, item.author)
                .setText(R.id.tvBookListTitle, item.title)
                .setText(R.id.tvBookListDesc, item.desc)
                .setText(R.id.tvBookCount, String.format(mContext.getString(R.string
                        .book_detail_recommend_book_list_book_count), item.bookCount))
                .setText(R.id.tvCollectorCount, String.format(mContext.getString(R.string
                        .book_detail_recommend_book_list_collector_count), item.collectorCount));
        holder.setOnItemViewClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                itemClickListener.onItemClick(holder.getItemView(), position, item);
            }
        });
    }

}