package com.example.photo.network;

import com.example.photo.model.Photo;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by vvenkatraman on 4/22/17.
 */

public interface PhotoApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("/api/photo")
    public Observable<List<Photo>> getPhotoList();
}