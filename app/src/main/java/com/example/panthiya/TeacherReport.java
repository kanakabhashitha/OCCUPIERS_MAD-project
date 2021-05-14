package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class TeacherReport extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DatabaseHelperMKASG databaseHelper;
    TextView tot, avg;
    SearchView searchView;
    ArrayList<Model_TM> marksOriginal;
    ArrayList<Model_TM> marksFiltered;
    teacherReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_teacher_report);

        searchView = (SearchView) findViewById(R.id.searchView);
        mRecyclerView = (RecyclerView) findViewById(R.id.teachervir);
        tot = (TextView) findViewById(R.id.total);
        avg = (TextView) findViewById(R.id.avg);
        databaseHelper = new DatabaseHelperMKASG(TeacherReport.this);


        marksOriginal = databaseHelper.getAllData_table_5(ConstantsMKASG.STUDENTID);
        adapter = new teacherReportAdapter(TeacherReport.this, marksOriginal);
        mRecyclerView.setAdapter(adapter);

        databaseHelper.countTotalMarks();

        if (databaseHelper.countTotalMarks().isNull(0)) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, databaseHelper.countTotalMarks().getString(0), Toast.LENGTH_SHORT).show();
            tot.setText(databaseHelper.countTotalMarks().getString(0));
        }

        databaseHelper.countTotalMarksAVG();

        if (databaseHelper.countTotalMarksAVG().isNull(0)) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, databaseHelper.countTotalMarksAVG().getString(0), Toast.LENGTH_SHORT).show();
            avg.setText(databaseHelper.countTotalMarksAVG().getString(0));
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                marksFiltered = new ArrayList<>();
                for (Model_TM mark : marksOriginal) {

                    if (mark.studentid.toLowerCase().contains(newText.toLowerCase())) {
                        marksFiltered.add(mark);
                    }
                }
                adapter.filterList(marksFiltered);

                return false;
            }
        });

    }

    public void clickBack(View view) {
        Intent intentback = new Intent(this, addMark.class);
        startActivity(intentback);
    }


}