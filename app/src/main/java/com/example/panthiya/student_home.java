package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class student_home extends AppCompatActivity {

    //initialize variables
    private DrawerLayout drawerLayout;
    TextView studentReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_student_home);

        //assing variable
        drawerLayout = findViewById(R.id.student_drawer_layout);

        //image slider
        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel>slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.welcom, "Welcome To Panthiya"));
        slideModels.add(new SlideModel(R.drawable.welcomtow, "The Class Management System"));
        slideModels.add(new SlideModel(R.drawable.welcometree, "Knowledge Is Power"));
        slideModels.add(new SlideModel(R.drawable.welcomfore, "Make Assignment Easily"));
        slideModels.add(new SlideModel(R.drawable.welcomesix, "Check You Score"));

        imageSlider.setImageList(slideModels, true);

    }




    public void student_clickMenu(View view){
        //open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void clickLogo(View view){
        //close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open
            //close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void clickHome(View view){
        //recreate activitry
        recreate();
    }

    public void clickStudentProfile(View view){
        //rederect to activity to profile
        redirectActivity(this, student_profile.class);
    }



    public void clickAboutus(View view){
        //redirect actiity to about us
        redirectActivity(this, student_aboutUs.class);
    }

    public void clickLogout(View view){
        //close app
        logout(this);
    }

    public static void logout(Activity activity) {
        //initializ alaert dialog
        AlertDialog.Builder builder =  new AlertDialog.Builder(activity);
        //set title
        builder.setTitle("Logout");
        //set massage
        builder.setMessage("Are you sure, you want to logout ?");
        //positive yes button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish activity
                activity.finishAffinity();
                //exit app
                System.exit(0);
            }
        });

        //Negetive no button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dismiss dialog
                dialog.dismiss();
            }
        });

        //show dialog
        builder.show();

    }



    public static void redirectActivity(Activity activity, Class aClass) {
        //initilize intent
        Intent intent =  new Intent(activity,aClass);
        //setfalg
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //satart activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }




    //add page

    public void clickViewStudentAssignment(View view) {
        Intent intentview_assignment_student_view = new Intent(this,view_assignment_student_view.class);
        startActivity(intentview_assignment_student_view);
    }

    public void clickStudentAddAssignment(View view) {
        Intent intentadd_assingment_student = new Intent(this,add_assingment_student.class);
        startActivity(intentadd_assingment_student);
    }

    public void clickViewMarkAssignment(View view) {
    }

    public void clickMyNote(View view) {
    }

    public void clickMyprofile(View view) {
        Intent intentStudent_profile = new Intent(this,student_profile.class);
        startActivity(intentStudent_profile);
    }




}