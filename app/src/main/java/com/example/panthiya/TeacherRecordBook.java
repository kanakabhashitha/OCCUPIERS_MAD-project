package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;

import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherRecordBook extends AppCompatActivity {

    Button tr_add_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_teacher_record_book);

        tr_add_btn = findViewById(R.id.tr_add_btn);

        tr_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherRecordBook.this, teachers_record_book_add.class);
                startActivity(intent);
            }
        });


    }


    public void clickBack(View view) {
        Intent intentback = new Intent(this, main_activity.class);
        startActivity(intentback);
    }
}