package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sliit_travel_app.HomePage;
import com.example.sliit_travel_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText Email, Password  ;
    private TextView signupRedirectText;
    private Button loginButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.login_email);
        Password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TRAIN", "login");
                if (Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Calling a method to post the data and passing email and password.
                postDataUsingVolley(Email.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void postDataUsingVolley(String email, String password) {
        String url = "https://app-ticket-ease-api.azurewebsites.net/api/users/login";

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

        // Create a JSON object with login credentials
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", email);
            jsonParams.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create a JsonObjectRequest
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response here
                        try {
                            // Parse the response JSON object
                            boolean success = response.getBoolean("success");
                            if (success) {
                                // Login was successful
                                String message = response.getString("message");
                                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                // Redirect to the home page or perform any other action
                                Intent intent = new Intent(LoginActivity.this, HomePage.class);
                                startActivity(intent);
                            } else {
                                // Login failed
                                String errorMessage = response.getString("message");
                                Toast.makeText(LoginActivity.this, "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Login failed due to a server error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        Toast.makeText(LoginActivity.this, "Failed to login: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the Volley queue
        queue.add(request);
    }
}
