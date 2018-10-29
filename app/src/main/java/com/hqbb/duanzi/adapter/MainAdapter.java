package com.hqbb.duanzi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.text.TextUtils;
import android.util.Log;
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

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class MainAdapter extends CommonAdapter<DuanziBean.DataBean> {


    public MainAdapter(Context mContext) {
        super(mContext);
    }

    public MainAdapter(Context mContext, List<DuanziBean.DataBean> valueList) {
        super(mContext, valueList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_all, parent, false);
        }
        ImageView avator = ViewHolderUtil.get(convertView, R.id.item_avator);
        TextView time = ViewHolderUtil.get(convertView, R.id.item_time);
        TextView name = ViewHolderUtil.get(convertView, R.id.item_name);
        TextView content = ViewHolderUtil.get(convertView, R.id.item_content);
        ImageView pic = ViewHolderUtil.get(convertView, R.id.item_pic);
        JZVideoPlayerStandard jzVideoPlayerStandard = ViewHolderUtil.get(convertView, R.id.videoPlayer);

        final DuanziBean.DataBean bean = valueList.get(position);

        String type = bean.getType();
//        Log.e("TAG", "type: " + type);
        switch (type) {
            //段子
            case "29":
                pic.setVisibility(View.GONE);
                jzVideoPlayerStandard.setVisibility(View.GONE);
                break;
            //图片
            case "10":
                pic.setVisibility(View.VISIBLE);
                jzVideoPlayerStandard.setVisibility(View.GONE);
                break;
            //视频
            case "41":
                pic.setVisibility(View.GONE);
                jzVideoPlayerStandard.setVisibility(View.VISIBLE);
                break;
            default:
                pic.setVisibility(View.VISIBLE);
                jzVideoPlayerStandard.setVisibility(View.GONE);
                break;
        }

        if (!TextUtils.isEmpty(bean.getProfile_image())) {
            MyglideImageView.setRoundImg(mContext, avator, bean.getProfile_image());
        }
        if (!TextUtils.isEmpty(bean.getCdn_img())) {
            MyglideImageView.setImgAsBitmap(mContext, pic, bean.getCdn_img());
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PictureActivity.class);
                    intent.putExtra("title", "趣图");
                    intent.putExtra("img_url", bean.getCdn_img());
                    mContext.startActivity(intent);
                    //过渡动画
                    ((Activity)mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            });
        }
        if (!TextUtils.isEmpty(bean.getVideouri())) {
            //禁止手机自动重力感应旋转
            JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR;
            jzVideoPlayerStandard.setUp(
                    bean.getVideouri(),
                    JZVideoPlayerStandard.SCREEN_WINDOW_LIST,
                    bean.getText());
            MyglideImageView.setImgAsBitmap(mContext, jzVideoPlayerStandard.thumbImageView, bean.getCdn_img());
        }
        time.setText(bean.getCreated_at());
        name.setText(bean.getName());
        content.setText(bean.getText());

        return convertView;
    }
}
