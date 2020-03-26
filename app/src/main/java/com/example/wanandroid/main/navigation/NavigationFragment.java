package com.example.wanandroid.main.navigation;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.library.app.BaseFragment;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Article;
import com.example.wanandroid.beans.Navigation;
import com.example.wanandroid.details.DetailsActivity;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import butterknife.BindView;


public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {


    @BindView(R.id.rv_category)
    RecyclerView mRvCategory;

    @BindView(R.id.rv_labels)
    RecyclerView mRvLabels;
    private CategoryAdapter mCategoryAdapter;
    private LabelsAdapter mLabelsAdapter;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRvCategory.setLayoutManager(new LinearLayoutManager(mActivity));
        mCategoryAdapter = new CategoryAdapter();
        mCategoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view,
                                    int position) {
                mCategoryAdapter.setChecked(position);
                List<Article> articles = ((Navigation) adapter.getItem(position)).getArticles();
                mLabelsAdapter.setNewData(articles);
            }
        });
        mRvCategory.setAdapter(mCategoryAdapter);

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(mActivity);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);

        FlexboxItemDecoration flexboxItemDecoration = new FlexboxItemDecoration(mActivity);
        flexboxItemDecoration.setDrawable(ContextCompat.getDrawable(mActivity,
                R.drawable.divider_flex_box_layout));
        flexboxItemDecoration.setOrientation(FlexboxItemDecoration.BOTH);

        mRvLabels.setLayoutManager(flexboxLayoutManager);
        mRvLabels.addItemDecoration(flexboxItemDecoration);
        mLabelsAdapter = new LabelsAdapter();
        mLabelsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view,
                                    int position) {
                Article item = (Article) adapter.getItem(position);
                DetailsActivity.start(mActivity, item.getTitle(), item.getLink());
            }
        });
        mRvLabels.setAdapter(mLabelsAdapter);

        mPresenter.getNavigation();
    }

    @Override
    protected NavigationPresenter loadPresenter() {
        return new NavigationPresenter();
    }

    @Override
    public void showNavigation(List<Navigation> data) {
        mCategoryAdapter.setNewData(data);

        mLabelsAdapter.setNewData(data.get(0).getArticles());
    }

    @Override
    public void showError() {
        showToast("获取失败");
    }
}
