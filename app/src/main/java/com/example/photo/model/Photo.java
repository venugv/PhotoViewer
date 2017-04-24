
package com.example.photo.model;

import android.graphics.Bitmap;

import com.example.photo.utils.DateUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Photo {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("comment")
    @Expose
    private String comment;

    private Date publishedDate = null;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    private Bitmap image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
        DateUtils dateUtils = new DateUtils();
        this.publishedDate = dateUtils.getDateLong(publishedAt);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

}
