package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;



public class TeacherReport extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DatabaseHelperMKASG databaseHelper;
    TextView tot,avg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_teacher_report);

        mRecyclerView = (RecyclerView) findViewById(R.id.teachervir);
        tot = (TextView) findViewById(R.id.total);
        avg = (TextView) findViewById(R.id.avg);
        databaseHelper = new DatabaseHelperMKASG(TeacherReport.this);

        teacherReportAdapter adapter = new teacherReportAdapter(TeacherReport.this, databaseHelper.getAllData_table_5(ConstantsMKASG.ID + " DESC"));
        mRecyclerView.setAdapter(adapter);

        databaseHelper.countTotalMarks();

        if(databaseHelper.countTotalMarks().isNull(0)){
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, databaseHelper.countTotalMarks().getString(0), Toast.LENGTH_SHORT).show();
            tot.setText(databaseHelper.countTotalMarks().getString(0));
        }

        databaseHelper.countTotalMarksAVG();

        if(databaseHelper.countTotalMarksAVG().isNull(0)){
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, databaseHelper.countTotalMarksAVG().getString(0), Toast.LENGTH_SHORT).show();
            avg.setText(databaseHelper.countTotalMarksAVG().getString(0));
        }

    }

    public void clickBack(View view) {
        Intent intentback = new Intent(this, addMark.class);
        startActivity(intentback);
    }


}