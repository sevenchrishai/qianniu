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
import com.hqbb.duanzi.base.OnRvItemClickListener;
import com.hqbb.duanzi.bean.support.FindBean;
import com.hqbb.easyadapterlibrary.recyclerview.EasyRVAdapter;
import com.hqbb.easyadapterlibrary.recyclerview.EasyRVHolder;

import java.util.List;

/**
 * @author lfh.
 * @date 16/8/16.
 */
public class FindAdapter extends EasyRVAdapter<FindBean> {
    private OnRvItemClickListener itemClickListener;

    public FindAdapter(Context context, List<FindBean> list, OnRvItemClickListener
            listener) {
        super(context, list, R.layout.item_find);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(final EasyRVHolder holder, final int position, final FindBean item) {

        holder.setText(R.id.tvTitle, item.getTitle());
        holder.setImageResource(R.id.ivIcon,item.getIconResId());

        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(holder.getItemView(), position, item);
            }
        });
    }
}
