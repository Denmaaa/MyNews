package com.denma.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.denma.mynews.Models.ArticleSearchAPI.ArticleSearchArticles;
import com.denma.mynews.Models.MostPopularAPI.MostPopularArticles;
import com.denma.mynews.Models.TopStoriesAPI.TopStoriesArticles;
import com.denma.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.fragment_recycle_item_image)
    ImageView image;
    @BindView(R.id.fragment_recycle_item_category)
    TextView category;
    @BindView(R.id.fragment_recycle_item_date)
    TextView date;
    @BindView(R.id.fragment_recycle_item_title)
    TextView title;


    public ArticlesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithTopStories(TopStoriesArticles topStoriesArticles, RequestManager glide){
        try{
            glide.load(topStoriesArticles.getMultimedia().get(0).getUrl()).into(image);
        } catch (IndexOutOfBoundsException e){
            //Log.e("TAG","no media");
        }

        this.category.setText(topStoriesArticles.getSection() + " > " + topStoriesArticles.getSubsection());
        this.date.setText(topStoriesArticles.getUpdatedDate().substring(0, 10));
        this.title.setText(topStoriesArticles.getTitle());
    }

    public void updateWithMostPopular(MostPopularArticles mostPopularArticles, RequestManager glide){
        try{
            glide.load(mostPopularArticles.getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(image);
        } catch (IndexOutOfBoundsException e){
            //Log.e("TAG","no media");
        }

        this.category.setText(mostPopularArticles.getSection());
        this.date.setText(mostPopularArticles.getPublishedDate());
        this.title.setText(mostPopularArticles.getTitle());
    }

    public void updateWithArticleSearch(ArticleSearchArticles articleSearchArticle, RequestManager glide){
        try{
            glide.load("https://static01.nyt.com/" + articleSearchArticle.getMultimedia().get(2).getUrl()).into(image);
        } catch (IndexOutOfBoundsException e){
            //Log.e("TAG","no media");
        }
        this.category.setText(articleSearchArticle.getSectionName());
        try {
            this.date.setText(articleSearchArticle.getPubDate().substring(0, 10));
        }catch (Exception e){

        }
        this.title.setText(articleSearchArticle.getSnippet());
    }
}
