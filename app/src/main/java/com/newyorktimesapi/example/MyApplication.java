package com.newyorktimesapi.example;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));

        Picasso picasso = builder.build();
        picasso.setIndicatorsEnabled(false);
        picasso.setLoggingEnabled(true);

        Picasso.setSingletonInstance(picasso);

    }
}
