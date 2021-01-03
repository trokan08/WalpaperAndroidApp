package com.example.walpaper;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.util.DisplayMetrics;

import java.io.IOException;
import java.util.Random;

public class MyService extends Service {
    private  Handler handler = new Handler();
    private Runnable runnable;

    private Context context;
    public MyService() {
    }



    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        context = this;

        handler.post( runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                randomview();
                handler.postDelayed(this, 30000);
            }
        });


        return Service.START_STICKY;
    }


    @Override
    public void onDestroy() {
        System.out.println("sonuc");
        handler.removeCallbacks(runnable);
    }

    public void randomview(){
        System.out.println("2");
        Random rand = new Random();

        int category = rand.nextInt(3);
        category +=1;
        int photo,photoNum = 0;
        if(category == 1){
            photo = rand.nextInt(13);
            photoNum = Image.imageId[photo];
        }
        else if(category == 2){
            photo = rand.nextInt(5);
            photoNum = Image.flower[photo];
        }
        else if(category == 3){
            photo = rand.nextInt(3);
            photoNum = Image.anime[photo];
        }
        else if(category == 4){
            photo = rand.nextInt(3);
            photoNum = Image.cat[photo];
        }

        set(photoNum);

    }
    public  void set(int photoNum){
        //System.out.println("3");
        Bitmap bmap2 = BitmapFactory.decodeStream(getResources().openRawResource(photoNum));
        //System.out.println("nedeeen");
        //System.out.println("4");
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        //System.out.println("6");

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
