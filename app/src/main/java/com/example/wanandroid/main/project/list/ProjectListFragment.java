package com.example.wanandroid.main.project.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.library.app.BaseFragment;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Article;
import com.example.wanandroid.beans.Page;
import com.example.wanandroid.details.DetailsActivity;

import butterknife.BindView;

public class ProjectListFragment extends BaseFragment<ProjectListPresenter> implements ProjectListContract.View,
        OnLoadMoreListener
        , SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ProjectAdapter mAdapter;
    private int mPage;
    private int mCid;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_list;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mCid = arguments.getInt("cid", -1);
        }
        if (mCid == -1) {
            return;
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        mAdapter = new ProjectAdapter();
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
        mSwipeRefreshLayout.setRefreshing(true);

        refresh();
    }

    @Override
    protected ProjectListPresenter loadPresenter() {
        return new ProjectListPresenter();
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
        mPresenter.getArticles(mPage, mCid);
    }
}
