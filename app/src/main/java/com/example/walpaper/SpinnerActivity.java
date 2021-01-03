package com.example.walpaper;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpinnerActivity extends AppCompatActivity {
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        addItemsSpinner();
    }

    public void addItemsSpinner(){
        List<String> categories = new ArrayList<String>();
        categories.add("Kategoriler");
        categories.add("Araba");
        categories.add("Çiçek");
        categories.add("Anime");
        categories.add("Kedi");
        Button button = (Button) findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    if( pos != 0) {
                        Intent intent = new Intent(SpinnerActivity.this, MainActivity.class);
                        intent.putExtra("position", pos);
                        System.out.println("positonn; " + pos);
                        startActivity(intent);
                    }


            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(SpinnerActivity.this, MyService.class);
                if(isChecked){

                    startService(intent);
                }else{
                    System.out.println("bitir");

                    stopService(intent);
                }

            }
        });
    }

    public void random(View v){
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
        Bitmap bmap2 = BitmapFactory.decodeStream(getResources().openRawResource(photoNum));
        System.out.println(bmap2);
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