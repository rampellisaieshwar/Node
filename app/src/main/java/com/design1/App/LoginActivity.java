package com.design1.App;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_KEY = "UserPrefs";
    private static final String USERNAME_KEY = "username";
    private static final String IS_LOGGED_IN_KEY = "isLoggedIn";

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        boolean isLoggedIn = sharedPrefs.getBoolean(IS_LOGGED_IN_KEY, false);

        if (isLoggedIn) {
            // User is already logged in, start the ProfileActivity
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            finish(); // Optional: Finish the LoginActivity to prevent going back to it
        } else {
            // User is not logged in, show the login screen
            initializeViews();
        }
    }

    private void initializeViews() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        requestQueue = Volley.newRequestQueue(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("username", username);
            requestBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://172.20.10.3:3000/api/login",
                requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        storeLoginState(username); // Store the login state
                        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }

    private void storeLoginState(String username) {
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_LOGGED_IN_KEY, true);
        editor.putString(USERNAME_KEY, username);
        editor.apply();
    }
}