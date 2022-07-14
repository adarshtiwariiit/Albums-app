package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String s = intent.getExtras().getString("image_Path");
        imageView = (ImageView) findViewById(R.id.myImageView);
//        imageView.setImageBitmap(BitmapFactory.decodeFile(s));
        try {
            InputStream is = new FileInputStream(s);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            Bitmap preview_bitmap = BitmapFactory.decodeStream(is,null, options);
            imageView.setImageBitmap(preview_bitmap);
        } catch (FileNotFoundException e) {
            Toast.makeText(MainActivity2.this, "too large file", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }

    }
}