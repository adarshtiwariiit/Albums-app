package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        ArrayList<File> images = fetchImages(Environment.getExternalStorageDirectory());
                        Toast.makeText(MainActivity.this, "search is completed",Toast.LENGTH_SHORT ).show();
                        gridView = (GridView) findViewById(R.id.myGridView);
                        gridView.setAdapter(new Adapter(MainActivity.this, images));
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            intent.putExtra("image_Path", images.get(i).getAbsolutePath());
                            startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }
    ArrayList<File> fetchImages(File file) {

        ArrayList<File> toreturn = new ArrayList<File>();


        File[] files = file.listFiles();
        if (files != null ) {
            for (File f : files) {
                if (toreturn.size() < 50) {
                    if (!f.isHidden() && (f.getName().endsWith(".jpg") || f.getName().endsWith(".JPG")) && !f.isDirectory() && f != null) {
                        toreturn.add(f);

                    } else if (!f.isHidden() && f.isDirectory())
                        toreturn.addAll(fetchImages(f));
                }
            }
        }
        return toreturn;

    }
}