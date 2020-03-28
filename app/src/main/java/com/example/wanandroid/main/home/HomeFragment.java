package com.example.wanandroid.main.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

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
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;

import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View,
        OnLoadMoreListener
        , SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ArticleAdapter mAdapter;
    private int mPage;
    private Banner<com.example.wanandroid.beans.Banner, MyBannerAdapter> mBanner;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_list;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
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
        mSwipeRefreshLayout.setRefreshing(true);

        mPresenter.getBanner();
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
    public void showArticlesError() {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.getLoadMoreModule().setEnableLoadMore(true);

        mAdapter.getLoadMoreModule().loadMoreFail();
    }

    @Override
    public void showBanner(List<com.example.wanandroid.beans.Banner> data) {
        mBanner = new Banner<>(mActivity);
        RecyclerView.LayoutParams layoutParams =
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        (int) BannerUtils.dp2px(200));
        mBanner.setLayoutParams(layoutParams);
        mBanner.setAdapter(new MyBannerAdapter(data));
        mBanner.setOnBannerListener(new OnBannerListener<com.example.wanandroid.beans.Banner>() {

            @Override
            public void OnBannerClick(com.example.wanandroid.beans.Banner data, int position) {
                DetailsActivity.start(mActivity, data.getTitle(), data.getUrl());
            }

            @Override
            public void onBannerChanged(int position) {

            }
        });
        mAdapter.addHeaderView(mBanner);
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

    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null) {
            mBanner.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stop();
        }
    }
}
