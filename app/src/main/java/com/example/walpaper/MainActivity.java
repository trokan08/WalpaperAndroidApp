package com.example.walpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.liste);
        int position = getIntent().getExtras().getInt("position");
        System.out.println("position2:" + position);
        ViewAdapter adapter;

            if(position == 1) {
                System.out.println("burdasın");
                adapter = new ViewAdapter(this, R.layout.pictures, Image.imageId, 1);
                listView.setAdapter(adapter);
            }
            if(position == 2) {
                System.out.println("burda değilsin");
                adapter = new ViewAdapter(this, R.layout.pictures, Image.flower, 2);
                listView.setAdapter(adapter);
            }
        if(position == 3 ) {
            System.out.println("burda değilsin");
            adapter = new ViewAdapter(this, R.layout.pictures, Image.anime, 3);
            listView.setAdapter(adapter);
        }
        if(position == 4) {
            System.out.println("burda değilsin");
            adapter = new ViewAdapter(this, R.layout.pictures, Image.cat, 4);
            listView.setAdapter(adapter);
        }

    }






    public void click(View v) {
        ImageView image = (ImageView) v;
        Integer a = (Integer) image.getTag();
        Bitmap bmap2 =BitmapFactory.decodeStream(getResources().openRawResource(a));
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        Bitmap bitmap = Bitmap.createScaledBitmap(bmap2, width, height, true);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        wallpaperManager.setWallpaperOffsetSteps(1, 1);
       // wallpaperManager.suggestDesiredDimensions(width, height);
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}