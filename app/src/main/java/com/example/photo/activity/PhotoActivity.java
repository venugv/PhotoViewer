package com.example.photo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.photo.R;
import com.example.photo.adapter.PhotoAdapter;
import com.example.photo.application.PhotoApplication;
import com.example.photo.network.NetworkUtil;

public class PhotoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewSwitcher simpleViewSwitcher;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Loading...");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener((View view) -> {
            startActivityForResult(new Intent(this, AddPicActivity.class), REQUEST_CODE);
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        simpleViewSwitcher=(ViewSwitcher)findViewById(R.id.viewSwither); // initiate a ViewSwitcher
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left); // load an animation
        simpleViewSwitcher.setInAnimation(in); // set in Animation for ViewSwitcher
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right); // load an animation
        simpleViewSwitcher.setOutAnimation(out); // set out Animation for ViewSwitcher
        loadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            recyclerView.getAdapter().notifyItemInserted(0);
            recyclerView.scrollToPosition(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loadData() {
        final PhotoApplication application = (PhotoApplication) getApplication();
        final Handler handler = new Handler(Looper.getMainLooper());
        if (application.getPhotoCount() > 0) {
            initAdapter();
        }
        NetworkUtil.apiService.getPhotoList().subscribe(
                value -> {
                    application.setPhotoList(value);
                    handler.post(this::initAdapter);
                },
                throwable -> {
                    handler.post(() -> Toast.makeText(this, "Unable to fetch photos", Toast.LENGTH_SHORT).show());
                },
                () -> {}
        );
    }

    private void initAdapter() {
        if (simpleViewSwitcher.getDisplayedChild() == 0) {
            simpleViewSwitcher.showNext();
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Photos");
        }
        PhotoAdapter adapter = new PhotoAdapter(PhotoActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
