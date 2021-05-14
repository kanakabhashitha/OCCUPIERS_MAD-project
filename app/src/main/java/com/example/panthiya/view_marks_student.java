package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class view_marks_student extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DatabaseHelperMKASG databaseHelper;
    Button button;
    String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_marks_student);

        mRecyclerView = (RecyclerView) findViewById(R.id.recylceViews);
        button=(Button)findViewById(R.id.sreport);
        databaseHelper = new DatabaseHelperMKASG(view_marks_student.this);


        Intent from = getIntent();
        if (from != null) {
            studentId = from.getStringExtra("studentId");
            System.out.println("myid------------"+studentId);

        }
        studentAdapter studentAdapter = new studentAdapter(view_marks_student.this, databaseHelper.getAStudentRecord(studentId));
        //because last add record is show on top
        mRecyclerView.setAdapter(studentAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentReportView.class);
                intent.putExtra("studentId",studentId);
                startActivity(intent);
            }
        });

    }


    public void clickBack(View view) {
        Intent intentback = new Intent(this, student_home.class);
        startActivity(intentback);
    }

}