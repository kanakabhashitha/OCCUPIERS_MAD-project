package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class checkAssingment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_assingment);
    }

    public void clickBack(View view) {
        Intent intentback = new Intent(this, main_activity.class);
        startActivity(intentback);
    }
}