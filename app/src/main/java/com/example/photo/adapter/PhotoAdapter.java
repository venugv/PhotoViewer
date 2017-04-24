package com.example.photo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photo.R;
import com.example.photo.activity.PhotoActivity;
import com.example.photo.application.PhotoApplication;
import com.example.photo.model.Photo;

/**
 * Created by vvenkatraman on 4/22/17.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewHolder> {
    PhotoActivity activity;
    LayoutInflater inflater;

    public PhotoAdapter(PhotoActivity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item_photo, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Photo photo = ((PhotoApplication)activity.getApplication()).getPhotoList().get(position);
        holder.bindView(photo);
    }

    @Override
    public int getItemCount() {
        return ((PhotoApplication)activity.getApplication()).getPhotoCount();
    }
}
