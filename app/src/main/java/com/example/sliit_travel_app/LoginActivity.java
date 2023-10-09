package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.sliit_travel_app.databinding.ActivityLoginBinding;
import com.example.sliit_travel_app.databinding.ActivitySignupBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText Email , Password;
    private Button loginButton;

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Email = findViewById(R.id.login_email);
        Password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Email.getText().toString().isEmpty() && Password.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Calling a method to post the data and passing our email and password.
                postDataUsingVolley(Email.getText().toString(), Password.getText().toString());

            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void postDataUsingVolley(String email, String password) {
        String url = "https://app-ticket-ease-api.azurewebsites.net/api/users/login";

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Inside onResponse method, we are
                // handling the response from the server.
                try {
                    // Parsing the response to a JSON object.
                    JSONObject respObj = new JSONObject(response);

                    // Check if the response contains the expected keys.
                    if (respObj.has("statusCode") && respObj.has("message")) {
                        int statusCode = respObj.getInt("statusCode");
                        String message = respObj.getString("message");

                        // Handle the data based on the actual keys in the response.
                        if (statusCode == 200) {
                            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                            startActivity(intent);
                        } else {
                            // Handle other status codes or error messages.
                            Toast.makeText(LoginActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Handle the case where the expected keys are missing.
                        Toast.makeText(LoginActivity.this, "Invalid response format", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    // Handle JSON parsing errors.
                    Toast.makeText(LoginActivity.this, "JSON Parsing Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Method to handle errors.
                Toast.makeText(LoginActivity.this, "Failed to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    // Creating a JSON object with email and password.
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("email", email);
                    jsonBody.put("password", password);
                    try {
                        return jsonBody.toString().getBytes("utf-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        // Adding the request to the queue.
        queue.add(request);
    }

}
