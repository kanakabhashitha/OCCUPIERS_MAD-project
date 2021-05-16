package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class check_student_answer_view extends AppCompatActivity {

    private ImageView aImageView;
    private TextView aNumberEt, aSubjectEt, aDeadLinEd, aDescriptionEt, aAddTimeEt, aID;
    Button saveInfo;

    private static String id, number, subject, deadLine, description, addTimeStamp, updateTimeStamp, image, timeStamp, teacherEmail;
    private DatabaseHelperMKASG dbHelper;
    ImageButton pri_image;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_check_student_answer_view);
        //initiate database object in main funtion
        dbHelper = new DatabaseHelperMKASG(this);

        teacherEmail = getIntent().getStringExtra("emailT");

        aImageView = findViewById(R.id.assignmentImage);
        aNumberEt = findViewById(R.id.assignemtNo_tv);
        aSubjectEt = findViewById(R.id.assignmentSubject_tv);
        aDeadLinEd = findViewById(R.id.assignemtDeadLine_tv);
        aDescriptionEt = findViewById(R.id.assignemtDescriptions_tv);
        aAddTimeEt = findViewById(R.id.add_date_time_tv);
        aID = findViewById(R.id.std_tv);

        pri_image = findViewById(R.id.pre_T_btn);
        saveInfo = findViewById(R.id.save_btn);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        subject = intent.getStringExtra("SUBJECT");
        number = intent.getStringExtra("NUMBER");
        deadLine = intent.getStringExtra("DEADLINE");
        description = intent.getStringExtra("DESCRIPTION");
        imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
        addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

        aNumberEt.setText(number);
        aSubjectEt.setText(subject);
        aDeadLinEd.setText(deadLine);
        aDescriptionEt.setText(description);
        aAddTimeEt.setText(addTimeStamp);
        aID.setText(id);
        aImageView.setImageURI(imageUri);


        pri_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent priImage = new Intent(check_student_answer_view.this, Pri_image.class);
                priImage.putExtra("IMAGE_pri", imageUri.toString());
                startActivity(priImage);
            }
        });

    }

    public void clickBack(View view) {
        Intent intentback = new Intent(this, check_student_answer_view.class);
        intentback.putExtra("emailT", teacherEmail);
        startActivity(intentback);
    }
}