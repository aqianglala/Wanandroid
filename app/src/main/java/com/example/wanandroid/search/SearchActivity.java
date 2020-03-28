package com.example.wanandroid.search;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.library.app.BaseActivity;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.HotWord;
import com.example.wanandroid.beans.Website;
import com.example.wanandroid.details.DetailsActivity;
import com.example.wanandroid.search.result.HistoryAdapter;
import com.example.wanandroid.search.result.SearchResultsActivity;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.tv_title_history)
    TextView mTvTitleHistory;
    @BindView(R.id.tv_title_hot)
    TextView mTvTitleHot;
    @BindView(R.id.tv_title_website)
    TextView mTvTitleWebsite;
    @BindView(R.id.rv_history)
    RecyclerView mRvHistory;
    @BindView(R.id.rv_hot)
    RecyclerView mRvHot;
    @BindView(R.id.rv_website)
    RecyclerView mRvWebsite;
    private HotWordAdapter mHotWordAdapter;
    private WebsiteAdapter mWebsiteAdapter;
    private HistoryAdapter mHistoryAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FlexboxItemDecoration flexboxItemDecoration = new FlexboxItemDecoration(mActivity);
        flexboxItemDecoration.setDrawable(ContextCompat.getDrawable(mActivity,
                R.drawable.divider_flex_box_layout));
        flexboxItemDecoration.setOrientation(FlexboxItemDecoration.BOTH);

        mRvHistory.setLayoutManager(new FlexboxLayoutManager(this));
        mRvHistory.addItemDecoration(flexboxItemDecoration);
        mHistoryAdapter = new HistoryAdapter();
        mHistoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view,
                                    int position) {
                SearchResultsActivity.start(mActivity, (String) adapter.getItem(position));
            }
        });
        mRvHistory.setAdapter(mHistoryAdapter);

        mRvHot.setLayoutManager(new FlexboxLayoutManager(this));
        mRvHot.addItemDecoration(flexboxItemDecoration);
        mHotWordAdapter = new HotWordAdapter();
        mHotWordAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view,
                                    int position) {
                HotWord hotWord = (HotWord) adapter.getItem(position);
                SearchResultsActivity.start(mActivity, hotWord.getName());
            }
        });
        mRvHot.setAdapter(mHotWordAdapter);

        mRvWebsite.setLayoutManager(new FlexboxLayoutManager(this));
        mRvWebsite.addItemDecoration(flexboxItemDecoration);
        mWebsiteAdapter = new WebsiteAdapter();
        mWebsiteAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view,
                                    int position) {
                Website website = (Website) adapter.getItem(position);
                DetailsActivity.start(mActivity, website.getName(), website.getLink());
            }
        });
        mRvWebsite.setAdapter(mWebsiteAdapter);

        mPresenter.getHotWords();
        mPresenter.getWebsites();
    }

    @Override
    protected SearchPresenter loadPresenter() {
        return new SearchPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(searchableInfo);
        searchView.onActionViewExpanded();

        return true;
    }

    @Override
    public void showHistory(List<String> data) {
        mTvTitleHistory.setVisibility(View.VISIBLE);
        mRvHistory.setVisibility(View.VISIBLE);
        mHistoryAdapter.setNewData(data);
    }

    @Override
    public void showWebsites(List<Website> data) {
        mTvTitleWebsite.setVisibility(View.VISIBLE);
        mRvWebsite.setVisibility(View.VISIBLE);
        mWebsiteAdapter.setNewData(data);
    }

    @Override
    public void showHotWords(List<HotWord> data) {
        mTvTitleHot.setVisibility(View.VISIBLE);
        mRvHot.setVisibility(View.VISIBLE);
        mHotWordAdapter.setNewData(data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getHistory();
    }
}
