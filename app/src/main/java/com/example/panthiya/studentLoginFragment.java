package com.example.panthiya;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class studentLoginFragment extends Fragment {

    private EditText userIdEt, passwordET;
    Button logBtn;
    private DatabaseHelperMKASG dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_student_login, container, false);
        inisialView(rootView);
        return rootView;

    }


    public void inisialView(View rootView){

        dbHelper = new DatabaseHelperMKASG(getActivity());

        userIdEt = (EditText) rootView.findViewById(R.id.email_login);
        passwordET = (EditText)rootView.findViewById(R.id.password_login);


        logBtn = (Button) rootView.findViewById(R.id.login_btn);


        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uid = userIdEt.getText().toString();
                String password = passwordET.getText().toString();

                Boolean checkStudentUserName = dbHelper.checkSudentemailpassword(uid,password);
                System.out.println("pw__"+checkStudentUserName);

                if (checkStudentUserName == true){

                    Intent intent = new Intent(getActivity(),student_home.class);
                    System.out.println("sE___"+password);
                    intent.putExtra("studentSid", uid);
                    startActivity(intent);

                } else {

                    Toast.makeText(getContext(), "Wrong Email Password", Toast.LENGTH_SHORT).show();

                    userIdEt.setText("");
                    passwordET.setText("");
                }

            }


        });

    }

}