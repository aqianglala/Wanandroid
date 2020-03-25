package com.example.wanandroid.main.home;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.library.utils.DateUtils;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Article;
import com.youth.banner.Banner;

public class ArticleAdapter extends BaseQuickAdapter<Article, BaseViewHolder> implements LoadMoreModule {
    public ArticleAdapter() {
        super(R.layout.item_list_article);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Article article) {
        String author = article.getAuthor();
        String chapterName = article.getChapterName();
        baseViewHolder.setGone(R.id.tv_author, TextUtils.isEmpty(author));
        baseViewHolder.setGone(R.id.tv_category, TextUtils.isEmpty(chapterName));

        baseViewHolder.setText(R.id.tv_title, article.getTitle());
        baseViewHolder.setText(R.id.tv_author, author);
        baseViewHolder.setText(R.id.tv_category, chapterName);
        baseViewHolder.setText(R.id.tv_time, DateUtils.formatTimestamp(article.getPublishTime()));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder.getAdapterPosition() == 0) {
            if (getHeaderLayoutCount() > 0) {
                Banner banner = (Banner) getHeaderLayout().getChildAt(0);
                banner.stop();
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.getAdapterPosition() == 0) {
            if (getHeaderLayoutCount() > 0) {
                Banner banner = (Banner) getHeaderLayout().getChildAt(0);
                banner.start();
            }
        }
    }
}
