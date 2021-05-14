package com.example.panthiya;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class teachersLoginFragment extends Fragment {

    private EditText emailEt, passwordET;
    Button logBtn;
    private DatabaseHelperMKASG dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_teachers_login, container, false);

        inisialView(rootView);
        return rootView;

    }




    public void inisialView(View rootView){

        dbHelper = new DatabaseHelperMKASG(getActivity());

        emailEt = (EditText) rootView.findViewById(R.id.email_login);
        passwordET = (EditText)rootView.findViewById(R.id.password_login);


        logBtn = (Button) rootView.findViewById(R.id.login_btn);


        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = emailEt.getText().toString();
                String password = passwordET.getText().toString();

                Boolean checkemailpassword = dbHelper.checkemailpassword(email,password);
                System.out.println("pw__"+checkemailpassword);

//                Intent intent = new Intent(getActivity(),main_activity.class);
//                intent.putExtra("emailT", email);
//                startActivity(intent);
//
                if (checkemailpassword == true){

                    Intent intent = new Intent(getActivity(),main_activity.class);
                    intent.putExtra("emailT", email);
                    startActivity(intent);

                } else {

                    Toast.makeText(getContext(), "Wrong Email Password", Toast.LENGTH_SHORT).show();

                    emailEt.setText("");
                    passwordET.setText("");
                }

            }


        });

    }

}