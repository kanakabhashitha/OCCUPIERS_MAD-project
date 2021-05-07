package com.example.panthiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class profile extends AppCompatActivity {
    //initialize variable
    DrawerLayout drawerLayout;

    private ImageView pImageView;
    private TextView pfName, pLname, pEmail, pPassword, pRePassword;
    Button updateInfo, deleteBtn;

    private String id, fName, lName, email, password, rePassword;
    private DatabaseHelperMKASG dbHelper;


    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_profile);

        //assing variable
        drawerLayout =  findViewById(R.id.drawer_layout);

        pImageView = findViewById(R.id.personImage);
        pfName = findViewById(R.id.fName);
        pLname = findViewById(R.id.lName);
        pEmail = findViewById(R.id.email);
        pPassword = findViewById(R.id.password);
        pRePassword = findViewById(R.id.rePassword);

        //initiate database object in main funtion
        dbHelper = new DatabaseHelperMKASG(this);

        updateInfo = findViewById(R.id.update_pro);
        deleteBtn = findViewById(R.id.delete_pro);

        String teacherEmail = getIntent().getStringExtra("emailT");
        Cursor cursor = dbHelper.getUserData(teacherEmail);






        if (cursor.moveToFirst()) {
            do {
                Model_TR model = new Model_TR(

                        ""+cursor.getInt(cursor.getColumnIndex(ConstantsMKASG.T_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_F_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_L_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_EMAIL)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_PASSWORD)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_ADD_TIMESTAMP)),
                        ""+cursor.getString(cursor.getColumnIndex(ConstantsMKASG.T_UPDATE_TIMESTAMP))
                );
                // Adding user record to list
                pImageView.setImageURI(Uri.parse(model.t_image));
                pfName.setText(model.t_fname);
                pLname.setText(model.t_lname);
                pEmail.setText(model.t_email);
                pPassword.setText(model.t_password);

            } while (cursor.moveToNext());
        }

                fName = pfName.getText().toString().trim();
                lName = pLname.getText().toString().trim();
                password = pPassword.getText().toString().trim();
                email = pEmail.getText().toString().trim();


        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent = new Intent(profile.this, edit_teacher_pro.class);
                intent.putExtra("emailT", email);
                intent.putExtra("fname", fName);
                intent.putExtra("lname", lName);
                intent.putExtra("password", password);

                System.out.println("test__"+fName);

                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(
                        ""+email
                );
            }
        });


    }


    private void deleteDialog(final String email) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Are you want to delete ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.delete_icon);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dbHelper.deleteInfo_table_3(email);
                Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(profile.this, register.class);
                intent.putExtra("emailT", email);
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
        main_activity.redirectActivity(this, main_activity.class);
        Intent intent = new Intent(profile.this,main_activity.class);
        intent.putExtra("emailT", email);
        startActivity(intent);
    }

    public void clickRegister(View view){
        //redirect activity to profile
        main_activity.redirectActivity(this,register.class);
        Intent intent = new Intent(profile.this,main_activity.class);
        intent.putExtra("emailT", email);
        startActivity(intent);
    }

    public void clickProfile(View view){
        //recreate activity
        Intent intent = new Intent(profile.this,main_activity.class);
        intent.putExtra("emailT", email);
        startActivity(intent);
        recreate();

    }

    public void clickAboutus(View view){
        //redirect activity to about us
        main_activity.redirectActivity(this,aboutus.class);
    }

    public void clickLogout(View view){
        //close app
        main_activity.logout(this);
        Intent intent = new Intent(profile.this,main_activity.class);
        intent.putExtra("emailT", email);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        main_activity.closeDrawer(drawerLayout);
    }
}