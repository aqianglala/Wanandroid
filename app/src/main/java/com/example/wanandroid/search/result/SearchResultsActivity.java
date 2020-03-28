package com.example.wanandroid.search.result;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.library.app.BaseActivity;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Article;
import com.example.wanandroid.beans.Page;
import com.example.wanandroid.details.DetailsActivity;
import com.example.wanandroid.main.home.ArticleAdapter;

import butterknife.BindView;

public class SearchResultsActivity extends BaseActivity<SearchResultsPresenter> implements SearchResultsContract.View, OnLoadMoreListener
        , SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ArticleAdapter mAdapter;
    private int mPage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_results;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,
                DividerItemDecoration.VERTICAL));
        mAdapter = new ArticleAdapter();
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(this);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view,
                                    int position) {
                Article item = (Article) adapter.getItem(position);
                DetailsActivity.start(mActivity, item.getTitle(), item.getLink());
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);


        handleIntent(getIntent());
    }

    @Override
    protected SearchResultsPresenter loadPresenter() {
        return new SearchResultsPresenter();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (intent != null) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            setSupportActionBar(mToolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(query);
            }
            mPresenter.saveSearchHistory(query);

            mSwipeRefreshLayout.setRefreshing(true);
            refresh();
        }
    }

    @Override
    public void showArticles(Page page) {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.getLoadMoreModule().setEnableLoadMore(true);

        if (mPage == 0) {
            mAdapter.setNewData(page.getDatas());
        } else {
            //不是第一页，则用add
            mAdapter.addData(page.getDatas());
        }

        if (mPage + 1 >= page.getPageCount()) {
            mAdapter.getLoadMoreModule().loadMoreEnd();
        } else {
            mAdapter.getLoadMoreModule().loadMoreComplete();
        }
        mPage++;
    }

    @Override
    public void showArticlesError() {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.getLoadMoreModule().setEnableLoadMore(true);

        mAdapter.getLoadMoreModule().loadMoreFail();
    }

    @Override
    public void onLoadMore() {
        request();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        mAdapter.getLoadMoreModule().setEnableLoadMore(false);
        mPage = 0;
        request();
    }

    private void request() {
        mPresenter.getArticles(mPage);
    }

    public static void start(Context context, String query) {
        Intent intent = new Intent(context, SearchResultsActivity.class);
        intent.putExtra(SearchManager.QUERY, query);
        context.startActivity(intent);
    }
}
