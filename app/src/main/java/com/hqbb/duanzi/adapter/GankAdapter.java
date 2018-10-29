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
import com.hqbb.duanzi.bean.GankBean;
import com.hqbb.duanzi.util.StringUtil;
import com.hqbb.duanzi.util.ViewHolderUtil;
import com.hqbb.duanzi.widget.glide.MyglideImageView;

import java.util.List;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class GankAdapter extends CommonAdapter<GankBean.ResultsBean> {


    public GankAdapter(Context mContext) {
        super(mContext);
    }

    public GankAdapter(Context mContext, List<GankBean.ResultsBean> valueList) {
        super(mContext, valueList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_gank, parent, false);
        }
        LinearLayout layout = ViewHolderUtil.get(convertView, R.id.ll_content);
        ImageView avator = ViewHolderUtil.get(convertView, R.id.item_avator);
        TextView time = ViewHolderUtil.get(convertView, R.id.item_time);
        TextView name = ViewHolderUtil.get(convertView, R.id.item_name);
        TextView content = ViewHolderUtil.get(convertView, R.id.item_content);
        final ImageView pic = ViewHolderUtil.get(convertView, R.id.item_pic);

        final GankBean.ResultsBean bean = valueList.get(position);

        pic.setVisibility(View.GONE);

        final String type = bean.getType();
        switch (type) {
            case "Android":
                MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_android);
                break;
            case "iOS":
                MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_ios);
                break;
            case "前端":
                MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_qianduan);
                break;
            case "拓展资源":
                MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_tuozhan);
                break;
            case "福利":
                MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_fuli);
                pic.setVisibility(View.VISIBLE);
                MyglideImageView.setImgAsBitmap(mContext, pic, bean.getUrl());
                break;
            case "休息视频":
                MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_xiuxi);
                break;
            case "App":
                MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_app);
                break;
            case "瞎推荐":
                MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_tuijian);
                break;
            default:
                MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_xiuxi);
                break;
        }

        time.setText(StringUtil.dataFormat(bean.getPublishedAt()));
        name.setText(bean.getWho());
        content.setText(bean.getDesc());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("url", bean.getUrl());
                intent.putExtra("title", type);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
