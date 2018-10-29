package com.hqbb.duanzi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.activity.PictureActivity;
import com.hqbb.duanzi.bean.MmBean;
import com.hqbb.duanzi.util.StringUtil;
import com.hqbb.duanzi.util.ViewHolderUtil;
import com.hqbb.duanzi.widget.glide.MyglideImageView;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class MmAdapter extends CommonAdapter<MmBean.DataBean> {


    public MmAdapter(Context mContext) {
        super(mContext);
    }

    public MmAdapter(Context mContext, List<MmBean.DataBean> valueList) {
        super(mContext, valueList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_mm, parent, false);
        }
        ImageView avator = ViewHolderUtil.get(convertView, R.id.item_avator);
        TextView time = ViewHolderUtil.get(convertView, R.id.item_time);
        TextView name = ViewHolderUtil.get(convertView, R.id.item_name);
        TextView content = ViewHolderUtil.get(convertView, R.id.item_content);
        ImageView pic = ViewHolderUtil.get(convertView, R.id.item_pic);
        JZVideoPlayerStandard jzVideoPlayerStandard = ViewHolderUtil.get(convertView, R.id.videoPlayer);

        final MmBean.DataBean bean = valueList.get(position);

        MyglideImageView.setLocalRoundImg(mContext, avator, R.mipmap.ic_mm_avator);
        if (!TextUtils.isEmpty(bean.getUrl())) {
            MyglideImageView.setImgAsBitmap(mContext, pic, bean.getUrl());
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PictureActivity.class);
                    intent.putExtra("title", "MM");
                    intent.putExtra("img_url", bean.getUrl());
                    mContext.startActivity(intent);
                    ((Activity)mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            });
        }
        time.setText(StringUtil.dataFormat(bean.getCreatedAt()));
        name.setText(bean.getType());
        content.setText(bean.getType());

        return convertView;
    }
}
