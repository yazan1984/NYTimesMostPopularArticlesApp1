package com.yazanjayousi.nytimesmostpopulararticles.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.yazanjayousi.nytimesmostpopulararticles.R;
import com.yazanjayousi.nytimesmostpopulararticles.activities.DetailsActivity;
import com.yazanjayousi.nytimesmostpopulararticles.models.Article;
import com.yazanjayousi.nytimesmostpopulararticles.utils.Constants;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    ArrayList<Article> articleArrayList,fillterList;
    Context ctx;
    public static  ArrayList<Article> articlesArrayList;

    public ArticleAdapter(ArrayList<Article> articleArrayList, Context ctx) {
        this.articleArrayList = articleArrayList;
        this.ctx = ctx;
        fillterList=articleArrayList;
        articlesArrayList=fillterList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_article, viewGroup, false);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ItemViewHolder holder= (ItemViewHolder) viewHolder;
        holder.tvTitle.setText(fillterList.get(i).getTitle());
        holder.tvByLine.setText(fillterList.get(i).getByline());
        holder.tvSection.setText(fillterList.get(i).getSection());
        holder.tvPDate.setText(fillterList.get(i).getPublishedDate());
        holder.tvNo.setText(String.valueOf(i+1));
        Log.e("PositionKey ad",i+" "+fillterList.get(i).getTitle());
        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articlesArrayList=fillterList;
                Log.e("PositionKey onclk",i+" "+fillterList.get(i).getTitle());
                Intent intent=new Intent(ctx, DetailsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString(Constants.IdKey, String.valueOf(fillterList.get(i).getId()));
                bundle.putString(Constants.TitleKey,fillterList.get(i).getTitle());
                bundle.putString(Constants.AbstractKey,fillterList.get(i).get_abstract());
                bundle.putString(Constants.SectionKey,fillterList.get(i).getSection());
                bundle.putString(Constants.PDateKey,fillterList.get(i).getPublishedDate());
                bundle.putString(Constants.ByLineKey,fillterList.get(i).getByline());
                bundle.putString(Constants.UrlKey,fillterList.get(i).getUrl());
                bundle.putString(Constants.ImgUrlKey,fillterList.get(i).getImageUrl());
                bundle.putString(Constants.CaptionKey,fillterList.get(i).getCaption());
                Log.e("iitionKey adclck",fillterList.get(i).getPosition()+"");
                bundle.putInt(Constants.PositionKey,i);
                bundle.putInt(Constants.ListSizeKey,fillterList.size());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constants.Key,bundle);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fillterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    fillterList = articleArrayList;
                } else {
                    ArrayList<Article> filteredList = new ArrayList<>();
                    for (Article row : articleArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.get_abstract().toLowerCase().contains(charString.toLowerCase())
                                || row.getAdxKeywords().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    fillterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = fillterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        };
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvByLine;
        TextView tvSection;
        TextView tvPDate;
        TextView tvNo;
        CardView cardItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvArticleTitle);
            tvByLine = itemView.findViewById(R.id.tvArticleByLine);
            tvSection = itemView.findViewById(R.id.tvArticleSection);
            tvPDate = itemView.findViewById(R.id.tvArticlePublishedDate);
            tvNo = itemView.findViewById(R.id.tvArticleNo);
            cardItem=itemView.findViewById(R.id.card_view);
        }
    }
}
