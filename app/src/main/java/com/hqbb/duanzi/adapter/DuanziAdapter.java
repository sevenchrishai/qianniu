package com.hqbb.duanzi.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.bean.DuanziBean;
import com.hqbb.duanzi.util.ViewHolderUtil;
import com.hqbb.duanzi.widget.glide.MyglideImageView;

import java.util.List;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class DuanziAdapter extends CommonAdapter<DuanziBean.DataBean> {


    public DuanziAdapter(Context mContext) {
        super(mContext);
    }

    public DuanziAdapter(Context mContext, List<DuanziBean.DataBean> valueList) {
        super(mContext, valueList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_duanzi, parent, false);
        }
        ImageView avator = ViewHolderUtil.get(convertView, R.id.item_avator);
        TextView time = ViewHolderUtil.get(convertView, R.id.item_time);
        TextView name = ViewHolderUtil.get(convertView, R.id.item_name);
        TextView content = ViewHolderUtil.get(convertView, R.id.item_content);

        DuanziBean.DataBean bean = valueList.get(position);
        if (!TextUtils.isEmpty(bean.getProfile_image())) {
            MyglideImageView.setRoundImg(mContext, avator, bean.getProfile_image());
        }
        time.setText(bean.getCreated_at());
        name.setText(bean.getName());
        content.setText(bean.getText());

        return convertView;
    }
}
