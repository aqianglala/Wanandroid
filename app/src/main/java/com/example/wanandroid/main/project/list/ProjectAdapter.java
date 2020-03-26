package com.example.wanandroid.main.project.list;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.library.utils.DateUtils;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Article;

public class ProjectAdapter extends BaseQuickAdapter<Article, BaseViewHolder> implements LoadMoreModule {
    public ProjectAdapter() {
        super(R.layout.item_list_project);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Article article) {
        baseViewHolder.setText(R.id.tv_title, article.getTitle());
        baseViewHolder.setText(R.id.tv_dec, article.getDesc());
        baseViewHolder.setText(R.id.tv_author, article.getAuthor());
        baseViewHolder.setText(R.id.tv_time, DateUtils.formatTimestamp(article.getPublishTime()));

        ImageView imageView = baseViewHolder.getView(R.id.imageView);
        Glide.with(imageView).load(article.getEnvelopePic()).into(imageView);
    }

}
