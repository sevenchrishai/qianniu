package com.hqbb.duanzi.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.bean.DuanziBean;
import com.hqbb.duanzi.util.ViewHolderUtil;
import com.hqbb.duanzi.widget.glide.MyglideImageView;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class VideoAdapter extends CommonAdapter<DuanziBean.DataBean> {


    public VideoAdapter(Context mContext) {
        super(mContext);
    }

    public VideoAdapter(Context mContext, List<DuanziBean.DataBean> valueList) {
        super(mContext, valueList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_video, parent, false);
        }
        ImageView avator = ViewHolderUtil.get(convertView, R.id.item_avator);
        TextView time = ViewHolderUtil.get(convertView, R.id.item_time);
        TextView name = ViewHolderUtil.get(convertView, R.id.item_name);
        TextView content = ViewHolderUtil.get(convertView, R.id.item_content);
        JZVideoPlayerStandard jzVideoPlayerStandard = ViewHolderUtil.get(convertView, R.id.videoPlayer);

        final DuanziBean.DataBean bean = valueList.get(position);

        if (!TextUtils.isEmpty(bean.getProfile_image())) {
            MyglideImageView.setRoundImg(mContext, avator, bean.getProfile_image());
        }
        if (!TextUtils.isEmpty(bean.getVideouri())) {

            jzVideoPlayerStandard.setUp(
                    bean.getVideouri(),
                    JZVideoPlayerStandard.SCREEN_WINDOW_LIST,
                    bean.getText());
            MyglideImageView.setImgAsBitmap(mContext, jzVideoPlayerStandard.thumbImageView, bean.getCdn_img());
        }
        time.setText(bean.getCreated_at());
        name.setText(bean.getName());
        content.setText(bean.getText());

//        pic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, PictureActivity.class);
//                intent.putExtra("img_url", bean.getCdn_img());
//                mContext.startActivity(intent);
//            }
//        });

        return convertView;
    }
}
