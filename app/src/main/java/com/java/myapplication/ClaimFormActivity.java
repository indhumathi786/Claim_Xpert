package com.java.myapplication;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ClaimFormActivity extends AppCompatActivity {

    private TextView textViewPolicyNumber;
    private TextView textViewPolicyHolderName;
    private EditText editTextHospitalName;
    private EditText editTextDateOfAdmission;
    private EditText editTextReasonForAdmission;
    private Button buttonUploadHospitalBill;
    private TextView textViewHospitalBillStatus;
    private Button buttonUploadDischargeSummary;
    private TextView textViewDischargeSummaryStatus;
    private Button buttonSubmitClaim;

    private Uri hospitalBillUri;
    private Uri dischargeSummaryUri;

    private static final int PICK_HOSPITAL_BILL_REQUEST = 1;
    private static final int PICK_DISCHARGE_SUMMARY_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_form);

        // Initialize views
        textViewPolicyNumber = findViewById(R.id.textViewPolicyNumber);
        textViewPolicyHolderName = findViewById(R.id.textViewPolicyHolderName);
        editTextHospitalName = findViewById(R.id.editTextHospitalName);
        editTextDateOfAdmission = findViewById(R.id.editTextDateOfAdmission);
        editTextReasonForAdmission = findViewById(R.id.editTextReasonForAdmission);
        buttonUploadHospitalBill = findViewById(R.id.buttonUploadHospitalBill);
        textViewHospitalBillStatus = findViewById(R.id.textViewHospitalBillStatus);
        buttonUploadDischargeSummary = findViewById(R.id.buttonUploadDischargeSummary);
        textViewDischargeSummaryStatus = findViewById(R.id.textViewDischargeSummaryStatus);
        buttonSubmitClaim = findViewById(R.id.buttonSubmitClaim);

        // Get pre-filled details from the intent
        String policyNumber = getIntent().getStringExtra("policyNumber");
        String policyHolderName = getIntent().getStringExtra("policyHolderName");

        // Set the pre-filled details
        textViewPolicyNumber.setText("Policy Number: " + policyNumber);
        textViewPolicyHolderName.setText("Policy Holder Name: " + policyHolderName);

        // Set up click listeners for document upload buttons
        buttonUploadHospitalBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser(PICK_HOSPITAL_BILL_REQUEST);
            }
        });

        buttonUploadDischargeSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser(PICK_DISCHARGE_SUMMARY_REQUEST);
            }
        });

        // Set up click listener for the submit claim button
        buttonSubmitClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitClaim();
            }
        });
    }

    private void openFileChooser(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Allow all file types, adjust as needed
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(intent, requestCode);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No file chooser found.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedFileUri = data.getData();
            if (requestCode == PICK_HOSPITAL_BILL_REQUEST) {
                hospitalBillUri = selectedFileUri;
                textViewHospitalBillStatus.setText("Hospital Bill Selected");
            } else if (requestCode == PICK_DISCHARGE_SUMMARY_REQUEST) {
                dischargeSummaryUri = selectedFileUri;
                textViewDischargeSummaryStatus.setText("Discharge Summary Selected");
            }
        }
    }

    private void submitClaim() {
        String hospitalName = editTextHospitalName.getText().toString().trim();
        String dateOfAdmission = editTextDateOfAdmission.getText().toString().trim();
        String reasonForAdmission = editTextReasonForAdmission.getText().toString().trim();

        if (hospitalName.isEmpty() || dateOfAdmission.isEmpty() || reasonForAdmission.isEmpty() || hospitalBillUri == null || dischargeSummaryUri == null) {
            Toast.makeText(this, "Please fill all the details and upload required documents", Toast.LENGTH_SHORT).show();
            return;
        }

        // In a real application, you would:
        // 1. Get the policy number and other details.
        // 2. Upload the selected files (hospitalBillUri, dischargeSummaryUri) to your backend.
        // 3. Send the claim data (hospitalName, dateOfAdmission, reasonForAdmission, policy details, and the URLs/references of the uploaded documents) to your backend for processing.
        // 4. Potentially navigate to a claim submitted/tracking screen.

        Toast.makeText(this, "Claim submitted successfully (mock)", Toast.LENGTH_SHORT).show();
        // Optionally navigate to a tracking activity
        // Intent intent = new Intent(ClaimFormActivity.this, ClaimTrackingActivity.class);
        // startActivity(intent);
    }
}