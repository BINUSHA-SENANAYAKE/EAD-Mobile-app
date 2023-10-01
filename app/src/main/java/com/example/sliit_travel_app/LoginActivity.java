package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sliit_travel_app.databinding.ActivityLoginBinding;
import com.example.sliit_travel_app.databinding.ActivitySignupBinding;

public class LoginActivity extends AppCompatActivity {


    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(LoginActivity.this, HomePage.class);
                      startActivity(intent);
            }
//                String nationalId = binding.loginNationalID.getText().toString();
//                String password = binding.loginPassword.getText().toString();
//                if(nationalId.equals("")||password.equals(""))
//                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
//                else{
//                    Boolean checkCredentials = databaseHelper.checkNationalIdPassword(nationalId, password);
//                    if(checkCredentials == true){
//                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
//                        Intent intent  = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    }else{
//                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
//                        Intent intent  = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    }
//                }
//            }
        });
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}