package com.app.ocean;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.Request;
import okhttp3.RequestBody;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, phoneNumber, password;
    Button regButton;
    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.inputName);
        phoneNumber = findViewById(R.id.inputPhoneNumberR);
        password = findViewById(R.id.inputPasswordR);

        regButton = findViewById(R.id.buttonReg);

        //Operation of the icon hiding the password:
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >=password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = password.getSelectionEnd();
                        if (passwordVisible) {
                            //set drawable image here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_security, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            //for hide password:
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else {
                            //set drawable image here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_security, 0, R.drawable.ic_baseline_visibility_24, 0);
                            //for show password:
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });


        //Action for reg button
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameVal = name.getText().toString();
                String phoneNumberVal = phoneNumber.getText().toString();
                String passwordVal = password.getText().toString();

                


                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //move to login page:
    public void moveToLogin(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}