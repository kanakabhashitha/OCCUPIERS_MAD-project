package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class view_assignment_student_view extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DatabaseHelperMKASG databaseHelper;
    TextView svBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_view_assignment_student_view);

        mRecyclerView = findViewById(R.id.recylceView_s1);
        databaseHelper = new DatabaseHelperMKASG(this);


        showRercord();



    }


    private void showRercord() {

        Adapter_mkasg_st1 adapter = new Adapter_mkasg_st1(view_assignment_student_view.this, databaseHelper.getAllData(ConstantsMKASG.A_ADD_TIMESTAMP + " DESC"));
        //because last add record is show on top
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        showRercord();
    }



    public void clickBack(View view) {
        Intent intentback = new Intent(this, student_home.class);
        startActivity(intentback);
    }
}