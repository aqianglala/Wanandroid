package com.example.wanandroid.search.result;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.library.app.BaseActivity;
import com.example.wanandroid.R;

public class SearchResultsActivity extends BaseActivity<SearchResultsPresenter> implements SearchResultsContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_results;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState) {
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
            mPresenter.saveSearchHistory(query);
        }
    }

    public static void start(Context context, String query) {
        Intent intent = new Intent(context, SearchResultsActivity.class);
        intent.putExtra(SearchManager.QUERY, query);
        context.startActivity(intent);
    }
}
