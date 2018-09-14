package com.sanjaya.home24.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sanjaya.home24.R;
import com.sanjaya.home24.datamodel.Article;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.GridViewHolder>{

    private List<Article> articleList;
    private Context context;

    public GridViewAdapter(Context context) {
        this.context = context;
        this.articleList = new ArrayList<>();
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_view_adapter, parent, false);
        return new GridViewAdapter.GridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        final Article article = this.articleList.get(position);

        Glide.with(context)
                .load(article.getMedia().get(0).getUri())
                .into(holder.ivItemImg);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class GridViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivItemImg;

        public GridViewHolder(View itemView) {
            super(itemView);
            this.ivItemImg = itemView.findViewById(R.id.grid_item);
        }
    }
}
