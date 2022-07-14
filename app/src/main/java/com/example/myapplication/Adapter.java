package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    Context context;
    ArrayList<File> images;


    Adapter(Context context, ArrayList<File> images) {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
//        Bitmap bitmap =BitmapFactory.decodeFile(images.get(i).getAbsolutePath());
      Bitmap bitmap = Bitmap.createScaledBitmap( BitmapFactory.decodeFile(images.get(i).getAbsolutePath()),190,200,true);

        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(220, 220));
        return imageView;
    }
}

