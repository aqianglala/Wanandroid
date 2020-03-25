package com.example.wanandroid.main.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Banner;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;


public class MyBannerAdapter extends BannerAdapter<Banner, MyBannerAdapter.BannerViewHolder> {


    public MyBannerAdapter(List<Banner> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_banner
                , parent, false);
        return new BannerViewHolder(inflate);
    }

    @Override
    public void onBindView(BannerViewHolder holder, Banner data, int position, int size) {
        Glide.with(holder.imageView).load(data.getImagePath()).into(holder.imageView);
        holder.title.setText(data.getTitle());
        holder.indicator.setText((position + 1) + "/" + size);
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView indicator;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.tv_title);
            indicator = itemView.findViewById(R.id.tv_numIndicator);
        }
    }
}
