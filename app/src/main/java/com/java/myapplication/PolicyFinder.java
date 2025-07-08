package com.java.myapplication;

import android.content.Intent;
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

import java.util.Map;

public class PolicyFinder extends AppCompatActivity {

    private EditText editTextPolicyNumber;
    private Button buttonFetchPolicy;
    private TextView textViewVerificationStatus;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_finder);

        // Initialize Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("Policies");

        editTextPolicyNumber = findViewById(R.id.editTextPolicyNumber);
        buttonFetchPolicy = findViewById(R.id.buttonFetchPolicy);
        textViewVerificationStatus = findViewById(R.id.textViewVerificationStatus);

        buttonFetchPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String policyNumber = editTextPolicyNumber.getText().toString().trim();
                if (!policyNumber.isEmpty()) {
                    verifyPolicy(policyNumber);
                } else {
                    Toast.makeText(PolicyFinder.this, "Please enter your policy number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verifyPolicy(String policyNumber) {
        textViewVerificationStatus.setVisibility(View.VISIBLE);
        textViewVerificationStatus.setText("Verifying policy...");

        databaseReference.orderByChild("PolicyNumber").equalTo(policyNumber)
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Policy found, get the details
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> policyDetails = (Map<String, Object>) snapshot.getValue();
                            if (policyDetails != null) {
                                navigateToPolicyDetails(policyNumber, policyDetails);
                                return;
                            }
                        }
                    }
                    textViewVerificationStatus.setVisibility(View.GONE);
                    Toast.makeText(PolicyFinder.this, "Invalid Policy Number", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    textViewVerificationStatus.setVisibility(View.GONE);
                    Toast.makeText(PolicyFinder.this, 
                        "Failed to verify policy: " + databaseError.getMessage(), 
                        Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void navigateToPolicyDetails(String policyNumber, Map<String, Object> policyDetails) {
        textViewVerificationStatus.setVisibility(View.GONE);
        
        Intent intent = new Intent(PolicyFinder.this, PolicyDetailsActivity.class);
        intent.putExtra("policyNumber", policyNumber);
        intent.putExtra("policyHolderName", policyDetails.get("PolicyHolderName").toString());
        intent.putExtra("insuranceCompany", policyDetails.get("InsuranceCompany").toString());
        intent.putExtra("sumInsured", policyDetails.get("SumInsured").toString());
        
        if (policyDetails.containsKey("RoomRentLimit")) {
            intent.putExtra("roomRentLimit", policyDetails.get("RoomRentLimit").toString());
        }
        
        startActivity(intent);
    }
}