package com.hqbb.duanzi.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.activity.NewsDetailActivity;
import com.hqbb.duanzi.bean.NewsBean;
import com.hqbb.duanzi.util.ViewHolderUtil;
import com.hqbb.duanzi.widget.glide.MyglideImageView;

import java.util.List;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class NewsAdapter extends CommonAdapter<NewsBean.ResultBeanX.ResultBean.ListBean> {


    public NewsAdapter(Context mContext) {
        super(mContext);
    }

    public NewsAdapter(Context mContext, List<NewsBean.ResultBeanX.ResultBean.ListBean> valueList) {
        super(mContext, valueList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_news, parent, false);
        }
        LinearLayout layout = ViewHolderUtil.get(convertView, R.id.ll_content);
        ImageView avator = ViewHolderUtil.get(convertView, R.id.item_avator);
        TextView time = ViewHolderUtil.get(convertView, R.id.item_time);
        TextView name = ViewHolderUtil.get(convertView, R.id.item_name);
        TextView content = ViewHolderUtil.get(convertView, R.id.item_content);
        final ImageView pic = ViewHolderUtil.get(convertView, R.id.item_pic);

        final NewsBean.ResultBeanX.ResultBean.ListBean bean = valueList.get(position);
        MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_newspaper);
        if (!TextUtils.isEmpty(bean.getPic())) {
            MyglideImageView.setImgAsBitmap(mContext, pic, bean.getPic());
        } else {
            pic.setVisibility(View.GONE);
        }
        time.setText(bean.getTime());
        if (!TextUtils.isEmpty(bean.getSrc())) {
            name.setText(bean.getSrc());
        } else {
            name.setText("news");
        }
        content.setText(bean.getTitle());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("url", bean.getUrl());
                intent.putExtra("title", "新闻");
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
