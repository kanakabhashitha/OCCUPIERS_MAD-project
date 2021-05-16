package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class check_assignment extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DatabaseHelperMKASG databaseHelper;
    private static String teacherEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_check_assignment);

        mRecyclerView = findViewById(R.id.recylceView_t2);
        databaseHelper = new DatabaseHelperMKASG(this);

        teacherEmail = getIntent().getStringExtra("emailT");

        showRercord();

    }


    private void showRercord() {

        Adapter_mkasg_t2 adapter = new Adapter_mkasg_t2(check_assignment.this, databaseHelper.getAllDataTable2(ConstantsMKASG.S_ADD_DATEnTIME + " DESC"));
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
        intentback.putExtra("emailT", teacherEmail);
        startActivity(intentback);
    }
}