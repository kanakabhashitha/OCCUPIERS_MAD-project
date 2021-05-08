package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addMarksForm extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    Button button1;
    Button button2;
    private DatabaseHelperMKASG dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_add_marks_form);

        editText1=(EditText)findViewById(R.id.assignmentNo);
        editText2=(EditText)findViewById(R.id.studentID);
        editText3=(EditText)findViewById(R.id.Fname);
        editText4=(EditText)findViewById(R.id.subject);
        editText5=(EditText)findViewById(R.id.marks);
        editText6=(EditText)findViewById(R.id.comment);
        button1=(Button)findViewById(R.id.submit);
        button2=(Button)findViewById(R.id.cancel);
        dbHelper = new DatabaseHelperMKASG(addMarksForm.this);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Assignment = editText1.getText().toString();
                String studentID = editText2.getText().toString();
                String Name = editText3.getText().toString();
                String Subject = editText4.getText().toString();
                String MArks = editText5.getText().toString();
                String Comment = editText6.getText().toString();

                if(Assignment.isEmpty()){
                    editText1.setError("Assignment ID is required");
                }else if(studentID.isEmpty()){
                    editText2.setError("Student ID is required");
                }else if(Name.isEmpty()){
                    editText3.setError("Name ID is required");
                }else if(Subject.isEmpty()){
                    editText4.setError("Subject is required");
                }else if(MArks.isEmpty()){
                    editText5.setError("Marks is required");
                }else if(Comment.isEmpty()){
                    editText6.setError("Comment is required");
                }else{
                    dbHelper.insertInfo_tabel_5(
                            "" + Assignment,
                            "" + studentID,
                            "" + Name,
                            "" + Subject,
                            "" + MArks,
                            "" + Comment);

                    Toast.makeText(addMarksForm.this, "Entered details successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), addMark.class);
                    startActivity(intent);

                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addMark.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), addMark.class);
        startActivity(intent);
    }
}