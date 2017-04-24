package com.example.photo.application;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.photo.model.Photo;

import java.util.List;

/**
 * Created by vvenkatraman on 4/22/17.
 */

public class PhotoApplication extends Application {
    List<Photo> photoList;
    private static final String PHOTO_COUNT = "photo_count";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public int getPhotoCount() {
        if (photoList != null) {
            return photoList.size();
        }

        return 0;
    }
}
