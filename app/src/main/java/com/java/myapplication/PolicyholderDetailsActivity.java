package com.java.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PolicyholderDetailsActivity extends AppCompatActivity {

    private TextView textViewPolicyNumber;
    private TextView textViewProposerName;
    private TextView textViewInsuranceCompany;
    private TextView textViewSumInsured;
    private TextView textViewRoomRentLimit;
    private EditText editTextAddress;
    private EditText editTextCity;
    private EditText editTextState;
    private EditText editTextPinCode;
    private EditText editTextEmail;
    private EditText editTextMobile;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policyholder_details);

        // Initialize views
        textViewPolicyNumber = findViewById(R.id.textViewPolicyNumber);
        textViewProposerName = findViewById(R.id.textViewProposerName);
        textViewInsuranceCompany = findViewById(R.id.textViewInsuranceCompany);
        textViewSumInsured = findViewById(R.id.textViewSumInsured);
        textViewRoomRentLimit = findViewById(R.id.textViewRoomRentLimit);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextCity = findViewById(R.id.editTextCity);
        editTextState = findViewById(R.id.editTextState);
        editTextPinCode = findViewById(R.id.editTextPinCode);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMobile = findViewById(R.id.editTextMobile);
        buttonNext = findViewById(R.id.buttonNext);

        String policyNumber = getIntent().getStringExtra("policyNumber");
        String policyHolderName = getIntent().getStringExtra("policyHolderName");
        String insuranceCompany = getIntent().getStringExtra("insuranceCompany");
        String sumInsured = getIntent().getStringExtra("sumInsured");
        String roomRentLimit = getIntent().getStringExtra("roomRentLimit");

        if (policyNumber != null) textViewPolicyNumber.setText("Policy Number: " + policyNumber);
        if (policyHolderName != null) textViewProposerName.setText("Insured Name: " + policyHolderName);
        if (insuranceCompany != null) textViewInsuranceCompany.setText("Insurance Company: " + insuranceCompany);
        if (sumInsured != null) textViewSumInsured.setText("Sum Insured: ₹" + sumInsured);
        if (roomRentLimit != null) {
            textViewRoomRentLimit.setText("Room Rent Limit: ₹" + roomRentLimit);
            textViewRoomRentLimit.setVisibility(View.VISIBLE);
        } else {
            textViewRoomRentLimit.setVisibility(View.GONE);
        }

        // Set up the click listener for the next button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect data from this screen
                String currentAddress = editTextAddress.getText().toString().trim();
                String currentCity = editTextCity.getText().toString().trim();
                String currentState = editTextState.getText().toString().trim();
                String currentPinCode = editTextPinCode.getText().toString().trim();
                String currentEmail = editTextEmail.getText().toString().trim().toLowerCase();
                String currentMobile = editTextMobile.getText().toString().trim();

                // Validate email
                if (TextUtils.isEmpty(currentEmail) || !Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches()) {
                    Toast.makeText(PolicyholderDetailsActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }


                Intent intent = new Intent(PolicyholderDetailsActivity.this, AdditionalPolicyholderDetailsActivity.class);


                intent.putExtra("address", currentAddress);
                intent.putExtra("city", currentCity);
                intent.putExtra("state", currentState);
                intent.putExtra("pinCode", currentPinCode);
                intent.putExtra("email", currentEmail);
                intent.putExtra("mobile", currentMobile);
                intent.putExtra("policyNumber", policyNumber);
                intent.putExtra("proposerName", policyHolderName);
                intent.putExtra("insuranceCompany", insuranceCompany);
                intent.putExtra("sumInsured", sumInsured);
                intent.putExtra("roomRentLimit", roomRentLimit);

                startActivity(intent);
            }
        });
    }
}