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
import com.example.sliit_travel_app.databinding.ActivitySignupBinding;
import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    private EditText firstName, lastName, Email, Password, nicNumber;
    private Button signupButton;
    ActivitySignupBinding binding;

    private int responseCode; // Variable to store the HTTP response code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        nicNumber = findViewById(R.id.nic_number);
        signupButton = findViewById(R.id.signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() ||
                        Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty() ||
                        nicNumber.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please enter all values", Toast.LENGTH_SHORT).show();
                } else {
                    postDataUsingVolley(firstName.getText().toString(), lastName.getText().toString(),
                            Email.getText().toString(), Password.getText().toString(), nicNumber.getText().toString());
                }
            }
        });

        // Set the click listener for the loginRedirectText here (outside the onResponse)
        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void postDataUsingVolley(String fname, String lname, String email, String password, String nicNumber) {
        String url = "https://app-ticket-ease-api.azurewebsites.net/api/users/register/traveller";

        RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);

        // Create a JSON object to hold the data
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("firstName", fname);
            jsonParams.put("lastName", lname);
            jsonParams.put("email", email);
            jsonParams.put("password", password);
            jsonParams.put("nicNumber", nicNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String requestBody = jsonParams.toString();

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respObj = new JSONObject(response);
                            boolean success = respObj.getBoolean("success");
                            if (success) {
                                // Registration was successful
                                String message = respObj.getString("message");
                                Toast.makeText(SignupActivity.this, message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                                // Now you can choose to navigate to the login activity
                                // or perform any other action after a successful registration
                            } else {
                                // Registration failed
                                String errorMessage = respObj.getString("message");
                                Toast.makeText(SignupActivity.this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignupActivity.this, "Registration failed due to a server error", Toast.LENGTH_SHORT).show();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error here
                        Toast.makeText(SignupActivity.this, "Registration failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return requestBody.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        // Add the request to the Volley queue
        queue.add(request);
    }
}
