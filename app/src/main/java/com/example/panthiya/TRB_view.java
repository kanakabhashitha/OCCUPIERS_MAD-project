package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TRB_view extends AppCompatActivity {


    private TextView tr_date, tr_subject, tr_titel, tr_outcomes, tr_donePoints, tr_exceptedPoint;
    private String id, date, subject, titel, outComes, donePoints, exceptionPoints, addTimes, updateTimes, timeStamp;
    private DatabaseHelperTRBnSRB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_t_r_b_view);

        //initiate database object in main funtion
        dbHelper = new DatabaseHelperTRBnSRB(this);


        tr_date = findViewById(R.id.tr_date);
        tr_subject = findViewById(R.id.tr_subject);
        tr_titel = findViewById(R.id.tr_titel);
        tr_outcomes = findViewById(R.id.tr_outcome);
        tr_donePoints = findViewById(R.id.tr_done_points);
        tr_exceptedPoint = findViewById(R.id.tr_expected_pont);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        subject = intent.getStringExtra("SUBJECT");
        date = intent.getStringExtra("DATE");
        titel = intent.getStringExtra("TITEL");
        outComes = intent.getStringExtra("OUTCOMES");
        donePoints = intent.getStringExtra("DONE_POINT");
        exceptionPoints = intent.getStringExtra("EXCEPTION_POINT");
        addTimes = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimes = intent.getStringExtra("UPDATE_TIMESTAMP");

        tr_date.setText(date);
        tr_subject.setText(subject);
        tr_titel.setText(titel);
        tr_outcomes.setText(outComes);
        tr_donePoints.setText(donePoints);
        tr_exceptedPoint.setText(exceptionPoints);

    }





    public void clickBack(View view) {
        Intent intentback = new Intent(this, TeacherRecordBook.class);
        startActivity(intentback);
    }
}