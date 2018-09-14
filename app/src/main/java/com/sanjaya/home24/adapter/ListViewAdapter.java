package com.sanjaya.home24.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sanjaya.home24.R;
import com.sanjaya.home24.datamodel.Article;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ListViewHolder>{

    private List<Article> articleList;
    private Context context;

    @Inject
    public ListViewAdapter(Context context) {
        this.context = context;
        this.articleList = new ArrayList<>();
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList.addAll(articleList);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_adapter, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final Article article = articleList.get(position);

        holder.tvItemTitle.setText(article.getTitle());

        Glide.with(context)
                .load(article.getMedia().get(0).getUri())
                .into(holder.ivItemImg);

        if(article.isLike() || article.isDislike()) {
            Glide.with(context)
                    .applyDefaultRequestOptions(new RequestOptions().centerCrop())
                    .load(context.getResources().getDrawable(article.isLike() ?
                            R.drawable.thumb_up_select : R.drawable.thumb_down_select))
                    .into(holder.ivOutputImg);
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivItemImg, ivOutputImg;
        public TextView tvItemTitle;

        public ListViewHolder(View itemView) {
            super(itemView);
            this.ivItemImg = itemView.findViewById(R.id.item_image);
            this.ivOutputImg = itemView.findViewById(R.id.item_output);
            this.tvItemTitle = itemView.findViewById(R.id.item_title);
        }
    }
}
