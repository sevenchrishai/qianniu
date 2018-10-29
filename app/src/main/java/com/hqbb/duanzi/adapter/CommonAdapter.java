package com.hqbb.duanzi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共Adapter精简聚合代码
 * Created by ylh on 2018/1/8 0008.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext;

    protected List<T> valueList;

    protected LayoutInflater mInflater;
    public CommonAdapter(Context mContext) {
        this.mContext = mContext;
        this.valueList = new ArrayList<>();
        mInflater = LayoutInflater.from(mContext);
    }

    public CommonAdapter(Context mContext, List<T> valueList) {
        this.mContext = mContext;
        this.valueList = valueList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return valueList == null? 0 : valueList.size();
    }

    @Override
    public T getItem(int position) {
        return valueList == null ? null : valueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public List<T> getValueList() {
        return valueList;
    }

    public void setValueList(List<T> valueList) {
        this.valueList = valueList;
    }
}
