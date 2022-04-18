package com.example.burger.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.burger.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView forgetPassword, signUp, login;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.forget_password_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        initViews();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allInfoExist()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

    }

    private boolean allInfoExist() {
        if(email.getText().toString().isEmpty()){
            email.setError(getString(R.string.enter_your_email));
            return false;
        }else{
            email.setError(null);
        }
        if(password.getText().toString().isEmpty()){
            password.setError(getString(R.string.enter_your_password));
            return false;
        }else{
            password.setError(null);
        }
        return true;
    }

    private void initViews() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forgetPassword = findViewById(R.id.btn_forget_password);
        signUp = findViewById(R.id.btn_sign_up);
        login = findViewById(R.id.btn_login);
    }
}