package com.example.wanandroid.main.navigation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Article;

public class LabelsAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {
    public LabelsAdapter() {
        super(R.layout.item_box);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Article article) {
        baseViewHolder.setText(R.id.tv_item, article.getTitle());
    }
}
