package com.example.wanandroid.main.knowledge;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.library.app.BaseFragment;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Chapter;
import com.example.wanandroid.main.knowledge.list.ArticleListActivity;

import java.util.List;

import butterknife.BindView;


public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private KnowledgeAdapter mAdapter;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new KnowledgeAdapter();
        mAdapter.setOnLabelClickListener(new KnowledgeAdapter.OnLabelClickListener() {
            @Override
            public void onClick(int cid) {
                ArticleListActivity.start(mActivity, cid);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.getTree();
    }

    @Override
    protected KnowledgePresenter loadPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    public void showChapters(List<Chapter> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(data);
    }

    @Override
    public void showError() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.getTree();
    }
}
