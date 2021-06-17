package com.example.finalproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void signUp(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    public void signIn(View view) {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if(TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Vui long nhap email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Vui long mat khau", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Tai khoan hoac mat khau sai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}