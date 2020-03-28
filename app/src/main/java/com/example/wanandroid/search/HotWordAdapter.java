package com.example.wanandroid.search;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.HotWord;

public class HotWordAdapter extends BaseQuickAdapter<HotWord, BaseViewHolder> {
    public HotWordAdapter() {
        super(R.layout.item_box);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HotWord hotWord) {
        baseViewHolder.setText(R.id.tv_item, hotWord.getName());
    }
}
