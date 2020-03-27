package com.example.wanandroid.search;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Website;

public class WebsiteAdapter extends BaseQuickAdapter<Website, BaseViewHolder> {
    public WebsiteAdapter() {
        super(R.layout.item_box);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Website website) {
        baseViewHolder.setText(R.id.tv_item, website.getName());
    }
}
