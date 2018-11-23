
package com.newyorktimesapi.example.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.gson.annotations.SerializedName;

public class Result implements Serializable {

    @SerializedName("asset_id")
    private Long mAssetId;
    @SerializedName("byline")
    private String mByline;
    @SerializedName("id")
    private Long mId;
    @SerializedName("published_date")
    private String mPublishedDate;
    @SerializedName("section")
    private String mSection;
    @SerializedName("source")
    private String mSource;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("type")
    private String mType;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("views")
    private Long mViews;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public Long getAssetId() {
        return mAssetId;
    }

    public void setAssetId(Long assetId) {
        mAssetId = assetId;
    }

    public String getByline() {
        return mByline;
    }

    public void setByline(String byline) {
        mByline = byline;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        mPublishedDate = publishedDate;
    }

    public String getSection() {
        return mSection;
    }

    public void setSection(String section) {
        mSection = section;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Long getViews() {
        return mViews;
    }

    public void setViews(Long views) {
        mViews = views;
    }


    public static Comparator<Result> ResultNewestDateComparator = new Comparator<Result>() {

        public int compare(Result r1, Result r2) {

            Date r1Date = null, r2Date = null;
            try {
                r1Date = r1.simpleDateFormat.parse(r1.getPublishedDate());
                r2Date = r2.simpleDateFormat.parse(r2.getPublishedDate());
            } catch (Exception e) {
                System.out.println(e);
            }

            return r2Date.compareTo(r1Date);
        }
    };

    public static Comparator<Result> ResultOldestDateComparator = new Comparator<Result>() {

        public int compare(Result r1, Result r2) {

            Date r1Date = null, r2Date = null;

            try {
                r1Date = r1.simpleDateFormat.parse(r1.getPublishedDate());
                r2Date = r2.simpleDateFormat.parse(r2.getPublishedDate());
            } catch (Exception e) {
                System.out.println(e);
            }

            return r1Date.compareTo(r2Date);

        }
    };
}
