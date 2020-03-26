package com.example.wanandroid.main.knowledge.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.library.app.BaseActivity;
import com.example.library.app.BasePresenter;
import com.example.wanandroid.R;

public class ArticleListActivity extends BaseActivity {

    private static final String KEY_CID = "cid";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_list;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null) {
            int cid = intent.getIntExtra(KEY_CID, -1);
            if (cid != -1) {
                KnowledgeListFragment fragment = new KnowledgeListFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("cid", cid);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment).commit();
            }
        }
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    public static void start(Context context, int cid) {
        Intent intent = new Intent(context, ArticleListActivity.class);
        intent.putExtra(KEY_CID, cid);
        context.startActivity(intent);
    }

}
