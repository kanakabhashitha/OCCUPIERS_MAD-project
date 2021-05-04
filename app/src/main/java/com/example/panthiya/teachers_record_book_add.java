package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class teachers_record_book_add extends AppCompatActivity {

    private Button tr_save_btn;
    private EditText tr_date, tr_subject, tr_titel, tr_outcomes, tr_donePoints, tr_exceptedPoint;

    private String  date, subject, titel, outComes, donePoints, exceptionPoints, addTimes, updateTimes, timeStamp;
    private DatabaseHelperTRBnSRB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_record_book_add);

        //initiate database object in main funtion
        dbHelper = new DatabaseHelperTRBnSRB(this);

        tr_date = findViewById(R.id.tr_date);
        tr_subject = findViewById(R.id.tr_subject);
        tr_titel = findViewById(R.id.tr_titel);
        tr_outcomes = findViewById(R.id.tr_outcome);
        tr_donePoints = findViewById(R.id.tr_done_points);
        tr_exceptedPoint = findViewById(R.id.tr_expected_pont);

        tr_save_btn = findViewById(R.id.tr_save_btn);

        tr_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                //startActivity(new Intent(teachers_record_book_add.this, TeacherRecordBook.class));
                Toast.makeText(teachers_record_book_add.this, "Add Successfull", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getData() {
        date = "" + tr_date.getText().toString().trim();
        subject = "" + tr_subject.getText().toString().trim();
        titel = "" + tr_titel.getText().toString().trim();
        outComes = "" + tr_outcomes.getText().toString().trim();
        donePoints = "" + tr_donePoints.getText().toString().trim();
        exceptionPoints = "" + tr_exceptedPoint.getText().toString().trim();
        timeStamp = "" + getDateTime();

        dbHelper.insertInfo_TRB(
                "" + date,
                "" + subject,
                "" + titel,
                "" + outComes,
                "" + donePoints,
                "" + exceptionPoints,
                "" + timeStamp,
                "" + timeStamp
        );

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}