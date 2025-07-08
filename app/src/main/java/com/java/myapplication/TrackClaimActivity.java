package com.java.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TrackClaimActivity extends AppCompatActivity {

    private EditText editTextPolicyNumber;
    private Button buttonCheckStatus;
    private TextView textViewStatus;
    private FirebaseDatabase database;
    private DatabaseReference claimsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_claim);

        editTextPolicyNumber = findViewById(R.id.editTextPolicyNumber);
        buttonCheckStatus = findViewById(R.id.buttonCheckStatus);
        textViewStatus = findViewById(R.id.textViewStatus);

        database = FirebaseDatabase.getInstance();
        claimsRef = database.getReference("claims");

        buttonCheckStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String policyNumber = editTextPolicyNumber.getText().toString().trim();
                if (!policyNumber.isEmpty()) {
                    trackClaimStatus(policyNumber);
                } else {
                    Toast.makeText(TrackClaimActivity.this, "Please enter your policy number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void trackClaimStatus(String policyNumber) {
        // Query Firebase to find the claim with the matching policy number
        claimsRef.orderByChild("policyNumber").equalTo(policyNumber)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.hasChild("status")) {
                                    String status = snapshot.child("status").getValue(String.class);
                                    textViewStatus.setText("Claim Status: " + status);
                                    textViewStatus.setVisibility(View.VISIBLE);
                                    return; // Display the status of the first matching claim
                                }
                            }
                            textViewStatus.setText("Claim Status: Status not available for this policy number.");
                            textViewStatus.setVisibility(View.VISIBLE);
                        } else {
                            textViewStatus.setText("Claim not found for policy number: " + policyNumber);
                            textViewStatus.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        textViewStatus.setText("Failed to fetch claim status: " + databaseError.getMessage());
                        textViewStatus.setVisibility(View.VISIBLE);
                    }
                });
    }
}