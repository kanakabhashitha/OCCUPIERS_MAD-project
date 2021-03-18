package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class makeAssingment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_assingment);
    }

    public void clickBack(View view) {
        Intent intentback = new Intent(this, main_activity.class);
        startActivity(intentback);
    }
}