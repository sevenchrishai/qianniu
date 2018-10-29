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
import com.hqbb.duanzi.bean.DuanziBean;
import com.hqbb.duanzi.util.ViewHolderUtil;
import com.hqbb.duanzi.widget.glide.MyglideImageView;

import java.util.List;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class PicAdapter extends CommonAdapter<DuanziBean.DataBean> {


    public PicAdapter(Context mContext) {
        super(mContext);
    }

    public PicAdapter(Context mContext, List<DuanziBean.DataBean> valueList) {
        super(mContext, valueList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_pic, parent, false);
        }
        ImageView avator = ViewHolderUtil.get(convertView, R.id.item_avator);
        TextView time = ViewHolderUtil.get(convertView, R.id.item_time);
        TextView name = ViewHolderUtil.get(convertView, R.id.item_name);
        TextView content = ViewHolderUtil.get(convertView, R.id.item_content);
        ImageView pic = ViewHolderUtil.get(convertView, R.id.item_pic);

        final DuanziBean.DataBean bean = valueList.get(position);

        if (!TextUtils.isEmpty(bean.getProfile_image())) {
            MyglideImageView.setRoundImg(mContext, avator, bean.getProfile_image());
        }
        if (!TextUtils.isEmpty(bean.getCdn_img())) {
            MyglideImageView.setImgAsBitmap(mContext, pic, bean.getCdn_img());
        }
        time.setText(bean.getCreated_at());
        name.setText(bean.getName());
        content.setText(bean.getText());

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PictureActivity.class);
                intent.putExtra("img_url", bean.getCdn_img());
                mContext.startActivity(intent);
                ((Activity)mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        return convertView;
    }
}
