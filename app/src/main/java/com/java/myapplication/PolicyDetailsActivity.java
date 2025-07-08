package com.java.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import android.util.Patterns;

public class PolicyDetailsActivity extends AppCompatActivity {

    private TextView textViewPolicyNumber;
    private TextView textViewPolicyHolderName;
    private TextView textViewInsuranceCompany;
    private TextView textViewSumInsured;
    private TextView textViewRoomRentLimit;
    private Button buttonProceedClaim;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_details);

        // Initialize Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("Policies");

        textViewPolicyNumber = findViewById(R.id.textViewPolicyNumber);
        textViewPolicyHolderName = findViewById(R.id.textViewPolicyHolderName);
        textViewInsuranceCompany = findViewById(R.id.textViewInsuranceCompany);
        textViewSumInsured = findViewById(R.id.textViewSumInsured);
        textViewRoomRentLimit = findViewById(R.id.textViewRoomRentLimit);
        buttonProceedClaim = findViewById(R.id.buttonProceedClaim);

        // Get the policy number from the intent
        String policyNumber = getIntent().getStringExtra("policyNumber");
        if (policyNumber != null) {
            // Fetch and display policy details from Realtime Database
            fetchPolicyDetailsFromDatabase(policyNumber);
        } else {
            Toast.makeText(this, "Policy number not found", Toast.LENGTH_SHORT).show();
        }

        buttonProceedClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate email if present in intent
                String email = getIntent().getStringExtra("email");
                if (email != null && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(PolicyDetailsActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Navigate to the PolicyHoldersDetailsActivity (Claim Processing Form)
                Intent intent = new Intent(PolicyDetailsActivity.this, PolicyholderDetailsActivity.class);
                // Pass all policy details
                intent.putExtra("policyNumber", getIntent().getStringExtra("policyNumber"));
                intent.putExtra("policyHolderName", getIntent().getStringExtra("policyHolderName"));
                intent.putExtra("insuranceCompany", getIntent().getStringExtra("insuranceCompany"));
                intent.putExtra("sumInsured", getIntent().getStringExtra("sumInsured"));
                intent.putExtra("roomRentLimit", getIntent().getStringExtra("roomRentLimit"));
                if (email != null) intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }

    private void fetchPolicyDetailsFromDatabase(String policyNumber) {
        databaseReference.orderByChild("PolicyNumber").equalTo(policyNumber)
          .addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  if (dataSnapshot.exists()) {
                      for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                          Map<String, Object> policyDetails = (Map<String, Object>) snapshot.getValue();
                          if (policyDetails != null) {
                              updateUIWithPolicyDetails(policyDetails);
                              return;
                          }
                      }
                  }
                  Toast.makeText(PolicyDetailsActivity.this, 
                      "Policy details not found", 
                      Toast.LENGTH_SHORT).show();
              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {
                  Toast.makeText(PolicyDetailsActivity.this, 
                      "Failed to fetch policy details: " + databaseError.getMessage(), 
                      Toast.LENGTH_SHORT).show();
              }
          });
    }

    private void updateUIWithPolicyDetails(Map<String, Object> policyDetails) {
        textViewPolicyNumber.setText("Policy Number: " + getIntent().getStringExtra("policyNumber"));
        textViewPolicyHolderName.setText("Policy Holder Name: " + policyDetails.get("PolicyHolderName"));
        textViewInsuranceCompany.setText("Insurance Company: " + policyDetails.get("InsuranceCompany"));
        textViewSumInsured.setText("Sum Insured: ₹" + policyDetails.get("SumInsured"));
        
        if (policyDetails.containsKey("RoomRentLimit")) {
            textViewRoomRentLimit.setText("Room Rent Limit: ₹" + policyDetails.get("RoomRentLimit"));
            textViewRoomRentLimit.setVisibility(View.VISIBLE);
        } else {
            textViewRoomRentLimit.setVisibility(View.GONE);
        }
    }
}