package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


public class StudentReportView extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DatabaseHelperMKASG databaseHelper;
    TextView total,average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_marks_student);

        setContentView(R.layout.activity_student_report_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.studentvir);
        total = (TextView) findViewById(R.id.totals);
        average = (TextView) findViewById(R.id.avgs);
        databaseHelper = new DatabaseHelperMKASG(StudentReportView.this);

        studentReportAdapter adapter = new studentReportAdapter(StudentReportView.this, databaseHelper.getAllData_table_5(ConstantsMKASG.ID + " DESC"));
        mRecyclerView.setAdapter(adapter);

        databaseHelper.countTotalMarks();

        if(databaseHelper.countTotalMarks().isNull(0)){
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, databaseHelper.countTotalMarks().getString(0), Toast.LENGTH_SHORT).show();
            total.setText(databaseHelper.countTotalMarks().getString(0));
        }

        databaseHelper.countTotalMarksAVG();

        if(databaseHelper.countTotalMarksAVG().isNull(0)){
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, databaseHelper.countTotalMarksAVG().getString(0), Toast.LENGTH_SHORT).show();
            average.setText(databaseHelper.countTotalMarksAVG().getString(0));
        }
    }


    public void clickBack(View view) {
        Intent intentback = new Intent(this, view_marks_student.class);
        startActivity(intentback);
    }
}