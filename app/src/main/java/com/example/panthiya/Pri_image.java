package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Pri_image extends AppCompatActivity {

    private Uri imageUri;
    public ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_pri_image);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            imageUri = Uri.parse(extras.getString("IMAGE_pri"));
        }

        image = findViewById(R.id.pri_image);


    }



    @Override
    protected void onResume() {
        super.onResume();

         image.setImageURI(imageUri);
    }
}