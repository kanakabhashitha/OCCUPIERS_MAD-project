package com.example.panthiya;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class student_profile extends AppCompatActivity {

    //initialize variable
    DrawerLayout drawerLayout;

    private DatabaseHelperMKASG dbHelper;

    EditText pfkId, pFname, pLname, pAge, pGrade, pPhone, pEmail, pPassword, pRepass ;
    ImageView pImage;
    TextView pUserNane, pSRid;
    RadioButton pGender, pMale, pFemal;
    Button editBtn, deleteBtn;
    String sid,  fkid,  image,  fname,  lname,  grade,  age,  gender,  phone,  email,  password, rePassoword, addTimeStamp,  updateTimeStamp;
    Boolean editMode = false;

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

        setContentView(R.layout.activity_student_profile);

        //assing variable
        drawerLayout =  findViewById(R.id.drawer_layout);

        //initiate database object in main funtion
        dbHelper = new DatabaseHelperMKASG(this);



        pSRid = findViewById(R.id.sid_s);
        //pfkId = findViewById(R.id.sid_s);
        pImage = findViewById(R.id.studentImage);
        pFname = findViewById(R.id.fname_s);
        pLname = findViewById(R.id.lName_s);
        pGrade = findViewById(R.id.grade_s);
        pAge = findViewById(R.id.age_s);
        pMale = findViewById(R.id.maleBtn);
        pFemal = findViewById(R.id.femailBtn);
        pPhone = findViewById(R.id.phone_s);
        pEmail = findViewById(R.id.email_s);
        pPassword = findViewById(R.id.password_s);
        pRepass = findViewById(R.id.rePassword_s);
        pUserNane = findViewById(R.id.username_s);

        editBtn = findViewById(R.id.editBtn_s);
        deleteBtn = findViewById(R.id.deleteBtn_s);



        String studentID = getIntent().getStringExtra("studentSid");

        Cursor cursor = dbHelper.getStudentData(studentID);


        while (cursor.moveToNext()){
            sid = cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_ID));
            image = cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_IMAGE));
            fname = cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_F_NAME));
            lname = cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_L_NAME));
            grade = cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_GRADE));
            email = cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_EMAIL));
            age = cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_AGE));
            gender = cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_GENDER));
            phone = cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_PONE));
            password = cursor.getString(cursor.getColumnIndex(ConstantsMKASG.SR_PASSWORD));


            pImage.setImageURI(Uri.parse(image));
            pFname.setText(fname);
            pLname.setText(lname);
            pGrade.setText(grade);
            pEmail.setText(email);
            pPassword.setText(password);
            pUserNane.setText(sid);
            pSRid.setText(sid);
            pAge.setText(age);
            pPhone.setText(phone);

            System.out.println("e__"+pEmail);

        }

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};




        pImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();

            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(
                      "" +studentID
                );
            }
        });

    }



    private void getData() {


        email = "" + pEmail.getText().toString().trim();
        sid = "" + pSRid.getText().toString().trim();
        grade = "" + pGrade.getText().toString().trim();
        fname = "" + pFname.getText().toString().trim();
        lname = "" + pLname.getText().toString().trim();
        phone = "" + pPhone.getText().toString().trim();
        password = "" + pPassword.getText().toString().trim();
        age = "" + pAge.getText().toString().trim();
        rePassoword = "" + pRepass.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all the information", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(rePassoword)) {
            Toast.makeText(this, "password mismatch", Toast.LENGTH_SHORT).show();
        }else{

                dbHelper.updateInfoTable_4_student(

                        "" + sid,
                        "" + fkid,
                        "" + imageUri,
                        "" + fname,
                        "" + lname,
                        "" + grade,
                        "" + age,
                        "" + gender,
                        "" + phone,
                        "" + email,
                        "" + password,
                        "" + getDateTime(),
                        "" + getDateTime()

                );

                Intent intent = new Intent(student_profile.this, student_profile.class);
                intent.putExtra("studentSid", sid);
                startActivity(intent);
                Toast.makeText(student_profile.this, "Update Successfull", Toast.LENGTH_SHORT).show();
            }

    }



    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


    private void editDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit");
        builder.setMessage("Are you want to edit ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.editicon);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getData();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        builder.create().show();
    }



    private void deleteDialog(final String sid) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Are you want to delete ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.delete_icon);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dbHelper.deleteInfo_table_4(sid);
                Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(student_profile.this, login_page.class);
                intent.putExtra("studentSid", sid);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        builder.create().show();
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
                        .setAspectRatio(1, 1)
                        .start(this);

            } else if (requestCode == IMAGE_PICK_CAMER_CODE) {
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(data);

                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    pImage.setImageURI(resultUri);
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }












    public void clickMenu(View view){
        //open drawer
        main_activity.openDrawer(drawerLayout);

    }

    public void clickLogo(View view){
        //close drawer
        main_activity.closeDrawer(drawerLayout);
    }

    public void clickHome(View view){
        //redirect activity to home
        main_activity.redirectActivity(this, student_home.class);
        Intent intent = new Intent(student_profile.this, student_home.class);
        intent.putExtra("studentSid", sid);
        startActivity(intent);
    }

    public void clickStudentProfile(View view){
        //redirect activity to profile
        main_activity.redirectActivity(this,student_profile.class);
        Intent intent = new Intent(student_profile.this, student_profile.class);
        intent.putExtra("studentSid", sid);
        startActivity(intent);
    }


    public void clickAboutus(View view){

        //recreate activity
        main_activity.redirectActivity(this,student_aboutUs.class);
        Intent intent = new Intent(student_profile.this, student_aboutUs.class);
        intent.putExtra("studentSid", sid);
        startActivity(intent);
    }

    public void clickLogout(View view){
        //close app
        main_activity.logout(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        main_activity.closeDrawer(drawerLayout);
    }
}

