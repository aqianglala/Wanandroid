package com.example.wanandroid.main.knowledge;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Chapter;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class KnowledgeAdapter extends BaseQuickAdapter<Chapter, BaseViewHolder> {
    public KnowledgeAdapter() {
        super(R.layout.item_list_knowledge);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Chapter chapter) {
        baseViewHolder.setText(R.id.tv_title, chapter.getName());
        FlexboxLayout flexboxLayout = baseViewHolder.getView(R.id.flexboxLayout);
        flexboxLayout.removeAllViews();
        List<Chapter> children = chapter.getChildren();
        if (children != null && children.size() > 0) {
            for (Chapter child : children) {
                TextView box =
                        (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_box,
                                flexboxLayout, false);
                box.setText(child.getName());
                if (mListener != null) {
                    box.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.onClick(child.getName(), child.getId());
                        }
                    });
                }
                flexboxLayout.addView(box);
            }
        }
    }

    private OnLabelClickListener mListener;

    public void setOnLabelClickListener(OnLabelClickListener listener) {
        mListener = listener;
    }

    interface OnLabelClickListener {
        void onClick(String title, int cid);
    }
}
