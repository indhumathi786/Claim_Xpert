package com.java.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private Button buttonAddNewClaim;
    private Button buttonTrackClaim;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        buttonAddNewClaim = findViewById(R.id.buttonAddNewClaim);
        buttonTrackClaim = findViewById(R.id.buttonTrackClaim);
        buttonLogout = findViewById(R.id.buttonLogout);

        buttonAddNewClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the PolicyFinder activity when "Add New Claim" is clicked
                startActivity(new Intent(DashboardActivity.this, PolicyFinder.class));
            }
        });

        buttonTrackClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity to track an existing claim
                startActivity(new Intent(DashboardActivity.this, TrackClaimActivity.class));
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear persistent login flag
                SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                prefs.edit().putBoolean("isLoggedIn", false).apply();
                // Go to LoginActivity
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}