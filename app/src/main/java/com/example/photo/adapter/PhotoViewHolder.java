package com.example.photo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.signature.StringSignature;
import com.example.photo.R;
import com.example.photo.activity.ImageViewActivity;
import com.example.photo.model.Photo;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.example.photo.activity.ImageViewActivity.IMAGE_UUID;

/**
 * Created by vvenkatraman on 4/22/17.
 */

public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView ivPhoto;
    private TextView tvCaption;
    private String imageUUID;

    public PhotoViewHolder(View itemView) {
        super(itemView);
        this.ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
        this.tvCaption = (TextView) itemView.findViewById(R.id.tvCaption);
        this.itemView.setOnClickListener(this);
    }

    public void bindView(Photo photo) {
        this.imageUUID = UUID.randomUUID().toString();
        this.tvCaption.setText(photo.getTitle());
        if (photo.getImage() != null) {
            this.ivPhoto.setImageBitmap(photo.getImage());
        } else {
            Glide.with(ivPhoto.getContext())
                    .load(photo.getPhoto())
                    .asBitmap()
                    .placeholder(R.raw.placeholder)
                    .centerCrop()
                    .signature(new StringSignature(imageUUID))
                    .into(new BitmapImageViewTarget(ivPhoto) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            super.onResourceReady(resource, glideAnimation);
                            photo.setImage(resource);
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        Intent imageViewIntent = new Intent(ivPhoto.getContext(), ImageViewActivity.class);
        imageViewIntent.putExtra(ImageViewActivity.CLICK_POSITION, getAdapterPosition());
        imageViewIntent.putExtra(IMAGE_UUID, imageUUID);
        ivPhoto.getContext().startActivity(imageViewIntent);
    }
}
