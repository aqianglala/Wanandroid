package com.example.wanandroid.main.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.library.app.BaseFragment;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Page;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, OnLoadMoreListener
        , SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ArticleAdapter mAdapter;
    private int mPage;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new ArticleAdapter();
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    @Override
    protected HomePresenter loadPresenter() {
        return new HomePresenter();
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
    public void showError() {
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
}
