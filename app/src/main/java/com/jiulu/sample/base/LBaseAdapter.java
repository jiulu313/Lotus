package com.jiulu.sample.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhanghongjun on 2017/10/17.
 */

public abstract class LBaseAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mDatas;
    protected int mLayoutID;
    protected LViewHolder mViewHolder;

    public LBaseAdapter(Context context, List<T> datas, int layoutID) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDatas = datas;
        mLayoutID = layoutID;
    }

    public LBaseAdapter(Context context) {
        this(context, null, 0);
    }

    public LBaseAdapter(Context context, List<T> datas) {
        this(context, datas, 0);
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void setDatas(List<T> datas) {
        mDatas = datas;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setLayoutID(int layoutID) {
        mLayoutID = layoutID;
    }

    public LViewHolder getViewHolder() {
        return mViewHolder;
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDatas != null ? mDatas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mViewHolder = LViewHolder.get(mContext, convertView, parent, mLayoutID, position);
        convert(mViewHolder, (T) getItem(position), position);
        return mViewHolder.getConvertView();
    }

    public abstract void convert(LViewHolder holder, T item, int position);
}
