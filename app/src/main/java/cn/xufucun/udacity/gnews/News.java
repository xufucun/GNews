package cn.xufucun.udacity.gnews;

/**
 * Created by xufuc on 2017/12/7.
 */

public class News {

    private String newsTitle;         //标题
    private String newsAuthor;        //作者
    private String newsUrl;

    public News( String title ,String author,String url){
        newsTitle = title;
        newsAuthor = author;
        newsUrl = url;
    }

    public News(String title ){
        newsTitle = title;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}
