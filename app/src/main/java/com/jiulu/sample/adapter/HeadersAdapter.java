package com.jiulu.sample.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiulu.lotus.core.Lotus;
import com.jiulu.sample.R;
import com.jiulu.sample.base.LBaseAdapter;
import com.jiulu.sample.base.LViewHolder;
import com.jiulu.sample.bean.HeaderBean;

import java.util.List;

/**
 * Created by zhanghongjun on 2017/11/22.
 */

public class HeadersAdapter extends LBaseAdapter<HeaderBean>{
    public HeadersAdapter(Context context, List<HeaderBean> datas, int layoutID) {
        super(context, datas, layoutID);
    }

    @Override
    public void convert(LViewHolder holder, HeaderBean item, int position) {
        ImageView ivHeader = holder.getView(R.id.iv_header);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvAddress = holder.getView(R.id.tv_address);

        Lotus.with(mContext).load(item.header).into(ivHeader);
        tvName.setText(item.name);
        tvAddress.setText(item.address);
    }
}
