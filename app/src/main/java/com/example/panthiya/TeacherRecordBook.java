package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
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

    RecyclerView mRecyclerView;
    DatabaseHelperTRBnSRB databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_teacher_record_book);

        tr_add_btn = findViewById(R.id.tr_add_btn);

        mRecyclerView = findViewById(R.id.recylceView_trb);
        databaseHelper = new DatabaseHelperTRBnSRB(this);

        showRercord();

        tr_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherRecordBook.this, teachers_record_book_add.class);
                intent.putExtra("EditMode", false);
                startActivity(intent);
            }
        });


    }



    private void showRercord() {

        Adapter_trb adapter = new Adapter_trb(TeacherRecordBook.this, databaseHelper.getAllData(ConstantsTRBnSRB.T_ADD_TIMESTAMP + " DESC"));
        //because last add record is show on top
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRercord();
    }



    public void clickBack(View view) {
        Intent intentback = new Intent(this, main_activity.class);
        startActivity(intentback);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK) {
            moveTaskToBack(true);
        }

        return super.onKeyDown(keyCode, event);
    }
}