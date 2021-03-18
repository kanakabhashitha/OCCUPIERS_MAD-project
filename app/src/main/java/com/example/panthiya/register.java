package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class register extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //assing variable
        drawerLayout =  findViewById(R.id.drawer_layout);
    }



    public void clickMenu(View view){
        //open drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void clickLogo(View view){
        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void clickHome(View view){
        //redirect activity to home
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void clickProfile(View view){
        //redirect activity to profile
        MainActivity.redirectActivity(this,profile.class);
    }

    public void clickAboutus(View view){
        //redirect activity to about us
        MainActivity.redirectActivity(this,aboutus.class);
    }


    public void clickRegister(View view){

        //recreate activity
        recreate();
    }

    public void clickLogout(View view){
        //close app
        MainActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}