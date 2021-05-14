package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class StudentReportView extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DatabaseHelperMKASG databaseHelper;
    TextView total,average;
    String studentId;
    ArrayList<Model_TM> marksOriginal;
    int totalMarks;
    double averageMarks;
    int totalSubjs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_marks_student);

        setContentView(R.layout.activity_student_report_view);

        Intent from = getIntent();
        if (from != null) {
            studentId = from.getStringExtra("studentId");

        }
        mRecyclerView = (RecyclerView) findViewById(R.id.studentvir);
        total = (TextView) findViewById(R.id.totals);
        average = (TextView) findViewById(R.id.avgs);
        databaseHelper = new DatabaseHelperMKASG(StudentReportView.this);
        marksOriginal = new ArrayList<>();

        marksOriginal = databaseHelper.getAStudentRecord(studentId);
        studentReportAdapter adapter = new studentReportAdapter(StudentReportView.this, marksOriginal);
        mRecyclerView.setAdapter(adapter);

        databaseHelper.countTotalMarks();

        for (Model_TM mark : marksOriginal) {
            totalMarks += Integer.parseInt(mark.marks);
            totalSubjs ++;
        }

        System.out.println("totalMarks"+totalMarks);
        System.out.println("totalSubjs"+totalSubjs);

        total.setText(String.valueOf(totalMarks));

        //find average
        averageMarks = totalMarks * totalSubjs  / 100.0;
        average.setText(String.valueOf(averageMarks).concat("%"));

//        if(databaseHelper.countTotalMarks().isNull(0)){
//            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, databaseHelper.countTotalMarks().getString(0), Toast.LENGTH_SHORT).show();
//            total.setText(databaseHelper.countTotalMarks().getString(0));
//        }
//
//        databaseHelper.countTotalMarksAVG();
//
//        if(databaseHelper.countTotalMarksAVG().isNull(0)){
//            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, databaseHelper.countTotalMarksAVG().getString(0), Toast.LENGTH_SHORT).show();
//            average.setText(databaseHelper.countTotalMarksAVG().getString(0));
//        }
    }


    public void clickBack(View view) {
        Intent intentback = new Intent(this, view_marks_student.class);
        startActivity(intentback);
    }
}