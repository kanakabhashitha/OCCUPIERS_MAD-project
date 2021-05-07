package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class add_student_form extends AppCompatActivity {

    private DatabaseHelperMKASG dbHelper;
    private static String teacherEmail;

    EditText pSRid, pSRemail, pSRfname, pSRlname, pSRgrade;
    private static String sid, email, t_email, fname,lname,grade;
    Button regBtn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_add_student_form);

        //db inisialize
        dbHelper = new DatabaseHelperMKASG(this);

        teacherEmail = getIntent().getStringExtra("emailT");



        pSRemail = findViewById(R.id.sr_email);
        pSRid = findViewById(R.id.sr_id);
        pSRfname = findViewById(R.id.sr_fName);
        pSRlname = findViewById(R.id.sr_lName);
        pSRgrade = findViewById(R.id.sr_grad);
        regBtn = findViewById(R.id.s_regBtn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //click the save button insert data to db
                getData();
                //dbHelper.getAllDataTable4(t_email);

            }
        });


    }




    private void getData() {


        email = "" + pSRemail.getText().toString().trim();
        sid = "" + pSRid.getText().toString().trim();
        t_email = teacherEmail;
        grade = "" + pSRgrade.getText().toString().trim();
        fname = "" + pSRfname.getText().toString().trim();
        lname = "" + pSRlname.getText().toString().trim();

        String test = " ";
        System.out.println("te__"+teacherEmail);

        if (email.isEmpty() || sid.isEmpty()) {
            Toast.makeText(this, "Please fill all the information", Toast.LENGTH_SHORT).show();
        }
        else{

            dbHelper.insertInfoTable_4_from_teacher(

                    "" + sid,
                    "" + t_email,
                    "" + test,
                    "" + fname,
                    "" + lname,
                    "" + grade,
                    "" + test,
                    "" + test,
                    "" + test,
                    "" + email,
                    "" + email,
                    "" + getDateTime(),
                    "" + getDateTime()

            );

            Intent intent = new Intent(add_student_form.this, studentRegisterPage.class);
            intent.putExtra("emailT", t_email);
            startActivity(intent);
            Toast.makeText(add_student_form.this, "Register Successfull", Toast.LENGTH_SHORT).show();
        }


    }





    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }






    public void clickBack(View view) {
        Intent intentback = new Intent(this, main_activity.class);
        intentback.putExtra("emailT", teacherEmail);
        startActivity(intentback);
    }
}