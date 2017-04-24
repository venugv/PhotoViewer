package com.example.photo.network;

/**
 * Created by vvenkatraman on 4/22/17.
 */

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by vvenkatraman on 04/22/17.
 */

public class NetworkUtil {
    private static final String BASE_URL = "https://photomaton.herokuapp.com/";
    public static PhotoApiEndpointInterface apiService;
    static {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        apiService = retrofit.create(PhotoApiEndpointInterface.class);
    }

}
