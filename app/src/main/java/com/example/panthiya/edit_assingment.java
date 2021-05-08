package com.example.panthiya;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.panthiya.Add_assignment_form_teacher.setListener;

public class edit_assingment extends AppCompatActivity {


    private ImageView aImageView;
    private EditText aNumberEt, aSubjectEt, aDeadLinEd, aDescriptionEt;
    Button saveInfo;



    private String id, number, subject, deadLine, description, addTimeStamp, updateTimeStamp, image, timeStamp;
    private DatabaseHelperMKASG dbHelper;


    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PICK_CAMER_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;

    private boolean editMode = false;

    //calender variable
    Calendar calendar = Calendar.getInstance();
    final  int year = calendar.get(Calendar.YEAR);
    final  int month = calendar.get(Calendar.MONTH);
    final  int day = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_edit_assingment);

        aImageView = findViewById(R.id.assignmentImage);
        aNumberEt = findViewById(R.id.assignemtNo);
        aSubjectEt = findViewById(R.id.assignmentSubject);
        aDeadLinEd = findViewById(R.id.assignemtDeadLine);
        aDescriptionEt = findViewById(R.id.assignemtDescription);

        saveInfo = findViewById(R.id.save_btn);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("EditMode", editMode);
        id = intent.getStringExtra("ID");
        subject = intent.getStringExtra("SUBJECT");
        number = intent.getStringExtra("NUMBER");
        deadLine = intent.getStringExtra("DEADLINE");
        description = intent.getStringExtra("DESCRIPTION");
        imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
        addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");


        if(editMode){
            editMode = intent.getBooleanExtra("EditMode", editMode);
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

            if (imageUri.toString().equals("null")){
                aImageView.setImageResource(R.drawable.upload_assingment_btnicon);
            }else{
                aImageView.setImageURI(imageUri);
            }
        }

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        //calender dialog view

        aDeadLinEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        edit_assingment.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day+"/"+month+"/"+year;
                aDeadLinEd.setText(date);
            }
        };

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day+"/"+month+"/"+year;
                aDeadLinEd.setText(date);
            }
        };

        aDeadLinEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        edit_assingment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        aDeadLinEd.setText(date);
                    }
                }, year,month,day);
                datePickerDialog.show();
            }
        });


        //initiate database object in main funtion
        dbHelper = new DatabaseHelperMKASG(this);

        aImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();

            }
        });

        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //click the save button insert data to db
                getData();
            }
        });
    }
    private void getData() {

        number = "" + aNumberEt.getText().toString().trim();
        subject = "" + aSubjectEt.getText().toString().trim();
        deadLine = "" + aDeadLinEd.getText().toString().trim();
        description = "" + aDescriptionEt.getText().toString().trim();
        boolean dateVlid = validateDate();
        boolean numValidate = vlidateAsiingmentNo();


        if(editMode){
            String newUpdateTime = ""+System.currentTimeMillis();

            try {
                System.out.println("date__" + dateVlid);
                if (numValidate !=true) {
                    Toast.makeText(getApplicationContext(), "Please enter the assignment Number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(subject)) {
                    aSubjectEt.setError("Subject field is required");
                    Toast.makeText(getApplicationContext(), "Please enter the assignment Subject", Toast.LENGTH_SHORT).show();
                } else if (((dateVlid != true))) {
                    Toast.makeText(getApplicationContext(), "Please enter the valid assignment Dead Line", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(description)) {
                    aDescriptionEt.setError("Description field is required");
                    Toast.makeText(getApplicationContext(), "Please enter the assignment Description", Toast.LENGTH_SHORT).show();
                } else if (imageUri == null) {
                    Toast.makeText(getApplicationContext(), "Please enter the assignment Image", Toast.LENGTH_SHORT).show();
                } else {

                    dbHelper.updateInfo(
                            "" + id,
                            "" + number,
                            "" + subject,
                            "" + deadLine,
                            "" + description,
                            "" + imageUri,
                            "" + getDateTime(),
                            "" + getDateTime()

                            );

                    startActivity(new Intent(edit_assingment.this, makeAssingment.class));
                    Toast.makeText(edit_assingment.this, "Add Successfull", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e) {
                Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
            }

        }else {

            String timeStamp = "" + System.currentTimeMillis();

            dbHelper.insertInfo(
                    "" + number,
                    "" + subject,
                    "" + deadLine,
                    "" + description,
                    "" + imageUri,
                    "" + timeStamp,
                    "" + timeStamp
            );
        }

        //Toast.makeText(this, "Record added to id: "+id, Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(edit_assingment.this, makeAssingment.class));
    }


    //get system date
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private boolean validateDate(){
        if(deadLine.length() == 0){
            aDeadLinEd.setError("This field is required");
            return false;
        }
        if(deadLine.length() != 0 ){
            String date = "" + aDeadLinEd.getText().toString().trim();

            String day = date.split("/")[0];
            String month = date.split("/")[1];
            int year = Integer.parseInt(date.split("/")[2]);

            if((0 < Integer.parseInt(day) && Integer.parseInt(day) < 32) && (0 < Integer.parseInt(month) && Integer.parseInt(month) < 13) && (Calendar.getInstance().get(Calendar.YEAR) <= year ) ) {
                if (day.equals("31") &&
                        (month.equals("4") || month.equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month.equals("06") ||
                                month.equals("09"))) {
                    aDeadLinEd.setError("Choose a month that has 31 days");
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                } else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if (year % 4 == 0) {
                        if (day.equals("30") || day.equals("31")) {
                            aDeadLinEd.setError("Date format is Invalid");
                            return false;
                        } else {
                            aDeadLinEd.setError("Date format is Invalid");
                            return true;
                        }
                    } else {
                        if (day.equals("29") || day.equals("30") || day.equals("31")) {
                            aDeadLinEd.setError("Date format is Invalid");
                            return false;
                        } else {
                            aDeadLinEd.setError("Date format is Invalid");
                            return true;
                        }
                    }
                }
            }else {
                aDeadLinEd.setError("Date format is Invalid");
                return false;
            }
        }

        if(deadLine.length() == 0){
            aDeadLinEd.setError("This field is required");
            return false;
        }

        if(deadLine.length() == 0){
            aDeadLinEd.setError("This field is required");
            return false;
        }

        if(deadLine.length() == 0){
            aDeadLinEd.setError("This field is required");
            return false;
        }

        return true;
    }

    private boolean vlidateAsiingmentNo(){
        if(number.length() == 0){
            aNumberEt.setError("Assignment field is required");
            return false;
        }
        String number = "" + aNumberEt.getText().toString().trim();
        if(number.length() != 0){
            if(!(0 < Integer.parseInt(number) && Integer.parseInt(number) < 100)){
                aNumberEt.setError("Number should be between 0 and 10");
                return false;
            }
        }
        return true;
    }

    private void imagePickDialog() {

        String[] options = {"Camera", "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select for Image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    //if 0 then open camera and check the permision
                    if (!checkCmaraPermission()) {
                        //if permission is not granted, request for camera permission
                        requestCameraPermission();
                    } else {
                        pickFromCamera();
                    }

                } else if (which == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        pickFromStorage();
                    }

                }

            }
        });

        builder.create().show();
    }


    private void pickFromStorage() {
        //get image from gallary
        Intent gallaryIntent = new Intent(Intent.ACTION_PICK);
        gallaryIntent.setType("image/*");
        startActivityForResult(gallaryIntent, IMAGE_PICK_GALLERY_CODE);

        System.out.println("image__" + imageUri);

    }

    private void pickFromCamera() {

        //get image from camera
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image Title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image Description");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMER_CODE);
        System.out.println("image__" + imageUri);
    }


    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return result;

    }

    private void requestStoragePermission() {

        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);

    }

    private boolean checkCmaraPermission() {

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);

        boolean resulti = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result && resulti;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {

                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted) {
                        pickFromCamera();
                    } else {
                        Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            break;

            case STORAGE_REQUEST_CODE: {

                if (grantResults.length > 0) {
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (storageAccepted) {
                        pickFromStorage();
                    } else {
                        Toast.makeText(this, "storage permission requried", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(3, 4)
                        .start(this);

            } else if (requestCode == IMAGE_PICK_CAMER_CODE) {
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(3, 4)
                        .start(this);
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(data);

                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    aImageView.setImageURI(resultUri);
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }




    public void clickBack(View view) {
        Intent intentback = new Intent(this, makeAssingment.class);
        startActivity(intentback);
    }
}