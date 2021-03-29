package com.example.mynews;

import android.util.Log;

import java.util.ArrayList;

public class News {
    private String reason;

    public String getReason() {
        return reason;
    }

    public Result getResult() {
        return result;
    }

    private Result result;
    public class Result{
        String stat;
        ArrayList<Data> data;
        String page;

        public String getStat() {
            return stat;
        }

        public ArrayList<Data> getData() {
            return data;
        }

        public String getPage() {
            return page;
        }

        public String getPageSize() {
            return pageSize;
        }

        String pageSize;
    }
    public class Data{
        String uniquekey;
        String title;
        String date;
        String category;
        String author_name;
        String url;
        String thumbnail_pic_s;

        public String getUniquekey() {
            return uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

        public String getCategory() {
            return category;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public String getUrl() {
            return url;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }
    }
    public void show(){
        Log.d("mtest", reason);
        Log.d("mtest", result.pageSize);
    }
}
