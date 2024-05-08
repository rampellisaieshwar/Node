package com.design1.App;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private static final String SHARED_PREFS_KEY = "UserPrefs";
    private static final String USERNAME_KEY = "username";

    private TextView tvUsername;
    private TextView tvEmail;
    private EditText etNewEmail;
    private EditText etNewPassword;
    private EditText etConfirmPassword;
    private Button btnUpdateEmail;
    private Button btnUpdatePassword;
    private Button btnSaveEmail;
    private Button btnSavePassword;
    private Button btnLogout;
    private LinearLayout llUpdateEmail;
    private LinearLayout llUpdatePassword;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = findViewById(R.id.tv_username);
        tvEmail = findViewById(R.id.tv_email);
        etNewEmail = findViewById(R.id.et_new_email);
        etNewPassword = findViewById(R.id.et_new_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnUpdateEmail = findViewById(R.id.btn_update_email);
        btnUpdatePassword = findViewById(R.id.btn_update_password);
        btnSaveEmail = findViewById(R.id.btn_save_email);
        btnSavePassword = findViewById(R.id.btn_save_password);
        btnLogout = findViewById(R.id.btn_logout);
        llUpdateEmail = findViewById(R.id.ll_update_email);
        llUpdatePassword = findViewById(R.id.ll_update_password);

        requestQueue = Volley.newRequestQueue(this);

        fetchUserProfile();

        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateEmailFields();
            }
        });

        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdatePasswordFields();
            }
        });

        btnSaveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail();
            }
        });

        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void fetchUserProfile() {
        String username = getUsernameFromSharedPrefs();
        Log.d(TAG, "Fetching profile for username: " + username);

        String url = "http://172.20.10.3:3000/api/profile?username=" + username;
        Log.d(TAG, "URL: " + url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Server response: " + response.toString());
                        try {
                            String username = response.getString("username");
                            String email = response.getString("email");

                            tvUsername.setText("Username: " + username);
                            tvEmail.setText("Email: " + email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "Error parsing profile response", e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error fetching profile: " + error.getMessage());
                        Log.e(TAG, "Error details: " + error.networkResponse);
                    }
                });

        requestQueue.add(request);
    }

    private void showUpdateEmailFields() {
        llUpdateEmail.setVisibility(View.VISIBLE);
        etNewEmail.setText(tvEmail.getText().toString().replace("Email: ", ""));
    }

    private void showUpdatePasswordFields() {
        llUpdatePassword.setVisibility(View.VISIBLE);
    }

    private void updateEmail() {
        String username = getUsernameFromSharedPrefs();
        String newEmail = etNewEmail.getText().toString().trim();

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("username", username);
            requestBody.put("email", newEmail);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://172.20.10.3:3000/api/update-email";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");
                            String message = response.getString("message");
                            if (success) {
                                Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_SHORT).show();
                                hideUpdateEmailFields();
                                fetchUserProfile();
                            } else {
                                Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ProfileActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this, "Error updating email", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }

    private void updatePassword() {
        String username = getUsernameFromSharedPrefs();
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("username", username);
            requestBody.put("password", newPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://172.20.10.3:3000/api/update-password";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");
                            String message = response.getString("message");
                            if (success) {
                                Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_SHORT).show();
                                hideUpdatePasswordFields();
                            } else {
                                Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ProfileActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this, "Error updating password", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }

    private void hideUpdateEmailFields() {
        llUpdateEmail.setVisibility(View.GONE);
        etNewEmail.setText("");
    }

    private void hideUpdatePasswordFields() {
        llUpdatePassword.setVisibility(View.GONE);
        etNewPassword.setText("");
        etConfirmPassword.setText("");
    }

    private String getUsernameFromSharedPrefs() {
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        return sharedPrefs.getString(USERNAME_KEY, null);
    }

    private void logout() {
        SharedPreferences sharedPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.remove("username"); // Remove the stored username
        editor.apply();

        // Start the LoginActivity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Finish the ProfileActivity
    }
}