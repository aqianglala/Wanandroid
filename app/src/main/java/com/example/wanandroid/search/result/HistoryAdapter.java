package com.example.wanandroid.search.result;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wanandroid.R;

public class HistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HistoryAdapter() {
        super(R.layout.item_box);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String text) {
        baseViewHolder.setText(R.id.tv_item, text);
    }
}
