package com.example.panthiya;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Add_assignment_form_teacher extends AppCompatActivity {


    private ImageView aImageView;
    private EditText aNumberEt, aSubjectEt, aDeadLinEd, aDescriptionEt;
    Button saveInfo;
    public static DatePickerDialog.OnDateSetListener setListener;

    private String number, subject, deadLine, description, timeStamp;
    private DatabaseHelperMKASG dbHelper;


    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PICK_CAMER_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_add_assignment_form_teacher);


        aImageView = findViewById(R.id.assignmentImage);
        aNumberEt = findViewById(R.id.assignemtNo);
        aSubjectEt = findViewById(R.id.assignmentSubject);
        aDeadLinEd = findViewById(R.id.assignemtDeadLine);
        aDescriptionEt = findViewById(R.id.assignemtDescription);

        saveInfo = findViewById(R.id.save_btn);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);

        aDeadLinEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Add_assignment_form_teacher.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
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
                        Add_assignment_form_teacher.this, new DatePickerDialog.OnDateSetListener() {
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
        timeStamp = "" + getDateTime();
        boolean dateVlid1 = checkDateFormat1(deadLine);
        boolean dateVlid2 = checkDateFormat2(deadLine);


        try{
            System.out.println("date__"+dateVlid2);
            if(TextUtils.isEmpty(number)){
                Toast.makeText(getApplicationContext(), "Please enter the assignment Number", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(subject)){
                Toast.makeText(getApplicationContext(), "Please enter the assignment Subject", Toast.LENGTH_SHORT).show();
            }else if( ( (dateVlid1 != true) || (dateVlid2 != false) ) && ( (dateVlid1 != false) || (dateVlid2 != true) )  ){
                Toast.makeText(getApplicationContext(), "Please enter the assignment Dead Line", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(description)){
                Toast.makeText(getApplicationContext(), "Please enter the assignment Description", Toast.LENGTH_SHORT).show();
            }else if(imageUri == null){
                Toast.makeText(getApplicationContext(), "Please enter the assignment Image", Toast.LENGTH_SHORT).show();
            }else{


                dbHelper.insertInfo(
                        "" + number,
                        "" + subject,
                        "" + deadLine,
                        "" + description,
                        "" + imageUri,
                        "" + timeStamp,
                        "" + timeStamp
                );

                startActivity(new Intent(Add_assignment_form_teacher.this, makeAssingment.class));
                Toast.makeText(Add_assignment_form_teacher.this, "Add Successfull", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(this, "Record added to id: "+id, Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(Add_assignment_form_teacher.this, makeAssingment.class));
    }


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
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


    //date validate
 /* private static boolean dateValidate(String d){
        System.out.println("d__"+d);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = sdf.parse(String.valueOf(date));

            if(!sdf.format(date).equals(d)){
                return false;
            }else{

                return true;
            }
        }catch (ParseException e){
            return false;
        }
    }*/

     //if (date == null || !date.matches("^(0[0-9]||1[0-2])/([0-2][0-9]||3[0-1])/([0-9][0-9])?[0-9][0-9]$"))

    public Boolean checkDateFormat1(String date){
        System.out.println("d__"+date);
        if (date == null || !date.matches("^([0-9]||1[0-2])/([0-9]||3[0-1])/([0-9][0-9])?[0-9][0-9]$"))
            return false;

        SimpleDateFormat format=new SimpleDateFormat("d/M/yyyy");
        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }

    public Boolean checkDateFormat2(String date){
        System.out.println("d__"+date);
        if (date == null || !date.matches("^(0[0-9]||1[0-2])/([0-9]||3[0-1])/([0-9][0-9])?[0-9][0-9]$"))
            return false;

        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
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