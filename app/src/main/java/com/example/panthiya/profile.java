package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class profile extends AppCompatActivity {
    //initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //assing variable
        drawerLayout =  findViewById(R.id.drawer_layout);
    }

    public void clickMenu(View view){
        //open drawer
        home_main_activity.openDrawer(drawerLayout);
    }

    public void clickLogo(View view){
        //close drawer
        home_main_activity.closeDrawer(drawerLayout);
    }

    public void clickHome(View view){
        //redirect activity to home
        home_main_activity.redirectActivity(this, home_main_activity.class);
    }

    public void clickRegister(View view){
        //redirect activity to profile
        home_main_activity.redirectActivity(this,register.class);
    }

    public void clickProfile(View view){
        //recreate activity
        recreate();
    }

    public void clickAboutus(View view){
        //redirect activity to about us
        home_main_activity.redirectActivity(this,aboutus.class);
    }

    public void clickLogout(View view){
        //close app
        home_main_activity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        home_main_activity.closeDrawer(drawerLayout);
    }
}