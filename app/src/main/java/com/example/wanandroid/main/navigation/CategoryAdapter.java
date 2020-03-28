package com.example.wanandroid.main.navigation;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Navigation;

public class CategoryAdapter extends BaseQuickAdapter<Navigation, BaseViewHolder> {

    private int mCheckedIndex;

    public CategoryAdapter() {
        super(R.layout.item_list_category);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Navigation navigation) {
        baseViewHolder.setText(R.id.tv_category, navigation.getName());

        int colorLight = ContextCompat.getColor(getContext(), R.color.colorPrimaryLight);
        int colorDark = ContextCompat.getColor(getContext(), R.color.colorPrimaryDark);

        if (mCheckedIndex == baseViewHolder.getLayoutPosition()) {
            baseViewHolder.setBackgroundColor(R.id.tv_category, colorDark);
        } else {
            baseViewHolder.setBackgroundColor(R.id.tv_category, colorLight);
        }
    }

    public void setChecked(int pos) {
        mCheckedIndex = pos;
        notifyDataSetChanged();
    }
}
