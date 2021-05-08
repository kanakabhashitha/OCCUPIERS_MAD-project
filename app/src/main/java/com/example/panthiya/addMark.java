package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;


import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addMark extends AppCompatActivity {

//MAIN EKA MEKA


    private Button addmarks_btn, reportBtn;
    RecyclerView recyclerView;
    DatabaseHelperMKASG databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();



        databaseHelper = new DatabaseHelperMKASG(addMark.this);

        setContentView(R.layout.activity_add_mark);

        addmarks_btn = findViewById(R.id.addmarksBtn);
        reportBtn = findViewById(R.id.treportBtn);
        recyclerView = findViewById(R.id.recylceViewTM);


        addmarks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addMark.this, addMarksForm.class);
                startActivity(intent);
            }
        });


        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addMark.this, TeacherReport.class);
                startActivity(intent);
            }
        });






    }


    public void showRercord() {

        Adapter_TM adapter = new Adapter_TM(addMark.this, databaseHelper.getAllData_table_5(ConstantsMKASG.ID + " DESC"));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        showRercord();
    }





    public void clickBack(View view) {
        Intent intentback = new Intent(this, main_activity.class);
        startActivity(intentback);
    }


}