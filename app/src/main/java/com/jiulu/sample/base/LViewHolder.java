package com.jiulu.sample.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhanghongjun on 2017/10/17.
 */

public class LViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    public LViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        mPosition = position;
        mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static LViewHolder get(Context context, View convertView,
                                  ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new LViewHolder(context, parent, layoutId, position);
        } else {
            LViewHolder holder = (LViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }
}
