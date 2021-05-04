package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import android.os.Bundle;

public class my_note_student extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tab1,tab2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_my_note_student);

        //fragment
        //tabcontroler
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tab1 = (TabItem) findViewById(R.id.tab1);
        tab2 = (TabItem) findViewById(R.id.tab2);
        viewPager = findViewById(R.id.viewpager);




    }

    public void clickBack(View view) {
        Intent intentback = new Intent(this, student_home.class);
        startActivity(intentback);
    }
}