package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class studentRegisterPage extends AppCompatActivity {

    private DatabaseHelperMKASG dbHelper;
    private static String teacherEmail;
    FloatingActionButton fabBtn;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_student_register_page);

        //db inisialize
        dbHelper = new DatabaseHelperMKASG(this);

        teacherEmail = getIntent().getStringExtra("emailT");

        fabBtn = findViewById(R.id.addFabButton);

        mRecyclerView = findViewById(R.id.recylceView_teachr);
        showRercord();

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(studentRegisterPage.this, add_student_form.class);
                intent.putExtra("emailT", teacherEmail);
                intent.putExtra("EditMode", false);
                startActivity(intent);
            }
        });

    }


    public void showRercord() {

        Adapter_teacher adapter = new Adapter_teacher(studentRegisterPage.this, dbHelper.getAllDataTable4(teacherEmail));
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