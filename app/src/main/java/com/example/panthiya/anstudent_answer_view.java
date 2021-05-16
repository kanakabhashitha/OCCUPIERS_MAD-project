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

public class anstudent_answer_view extends AppCompatActivity {
    Button answer_btn;

    private ImageView aImageView;
    private TextView aNumberEt, aSubjectEt, aDeadLinEd, aDescriptionEt, aAddTimeEt, aUpdateTimeEt;
    Button saveInfo;
    ImageButton pri_image;

    private String id, number, subject, deadLine, description, addTimeStamp, updateTimeStamp, image, timeStamp;
    private DatabaseHelperMKASG dbHelper;


    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_anstudent_answer_view);

        aImageView = findViewById(R.id.assignmentImage);
        aNumberEt = findViewById(R.id.assignemtNo_st);
        aSubjectEt = findViewById(R.id.assignmentSubject_st);
        aDeadLinEd = findViewById(R.id.assignemtDeadLine_st);
        aDescriptionEt = findViewById(R.id.assignemtDescriptions_st);
        aAddTimeEt = findViewById(R.id.add_date_time_st);
        aUpdateTimeEt = findViewById(R.id.update_date_st);

        saveInfo = findViewById(R.id.save_btn);
        pri_image = findViewById(R.id.pre_T_btn);

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
        aImageView.setImageURI(imageUri);
        aAddTimeEt.setText(addTimeStamp);
        aUpdateTimeEt.setText(updateTimeStamp);

        pri_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent priImage = new Intent(anstudent_answer_view.this, Pri_image.class);
                priImage.putExtra("IMAGE_pri", imageUri.toString());
                startActivity(priImage);
            }
        });

    }

    public void clickBack(View view) {
        Intent intentback = new Intent(this, my_assignment_log.class);
        startActivity(intentback);
    }
}