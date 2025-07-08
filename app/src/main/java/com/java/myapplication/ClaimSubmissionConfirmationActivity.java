package com.java.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClaimSubmissionConfirmationActivity extends AppCompatActivity {

    private TextView textViewPolicyDetails;
    private TextView textViewPolicyholderDetails;
    private TextView textViewHospitalizationDetails;
    private TextView textViewClaimDetails;
    private TextView textViewBankDetails;
    private Button buttonConfirmSubmit;
    private Button buttonPrevious;
    private CheckBox checkBoxTerms;
    private TextView textViewTermsLink;

    // All the data variables we've been passing
    // ... (keep your variables here as before) ...

    private FirebaseDatabase database;
    private DatabaseReference claimsRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_submission_confirmation);

        // Initialize Firebase Realtime Database and Auth
        database = FirebaseDatabase.getInstance();
        claimsRef = database.getReference("claims");
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        textViewPolicyDetails = findViewById(R.id.textViewPolicyDetails);
        textViewPolicyholderDetails = findViewById(R.id.textViewPolicyholderDetails);
        textViewHospitalizationDetails = findViewById(R.id.textViewHospitalizationDetails);
        textViewClaimDetails = findViewById(R.id.textViewClaimDetails);
        textViewBankDetails = findViewById(R.id.textViewBankDetails);
        buttonConfirmSubmit = findViewById(R.id.buttonConfirmSubmit);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        textViewTermsLink = findViewById(R.id.textViewTermsLink);

        // Get all the data passed from the previous activity
        Intent intent = getIntent();
        String policyNumber = intent.getStringExtra("policyNumber");
        String insuranceCompany = getIntent().getStringExtra("insuranceCompany");
        String customerId = intent.getStringExtra("customerId");
        String proposerName = intent.getStringExtra("proposerName");
        String panCard = intent.getStringExtra("panCard");
        String dateOfBirth = intent.getStringExtra("dateOfBirth");
        String gender = intent.getStringExtra("gender");
        String relationship = intent.getStringExtra("relationship");
        String occupation = intent.getStringExtra("occupation");
        String address = intent.getStringExtra("address");
        String city = intent.getStringExtra("city");
        String state = intent.getStringExtra("state");
        String pinCode = intent.getStringExtra("pinCode");
        String email = intent.getStringExtra("email");
        String mobile = intent.getStringExtra("mobile");
        String hospitalName = intent.getStringExtra("hospitalName");
        String dateOfAdmission = intent.getStringExtra("dateOfAdmission");
        String timeOfAdmission = intent.getStringExtra("timeOfAdmission");
        String dateOfDischarge = intent.getStringExtra("dateOfDischarge");
        String timeOfDischarge = intent.getStringExtra("timeOfDischarge");
        String diagnosis = intent.getStringExtra("diagnosis");
        String reasonForAdmission = intent.getStringExtra("reasonForAdmission");
        String roomCategory = intent.getStringExtra("roomCategory");
        String hospitalizationCause = intent.getStringExtra("hospitalizationCause");
        String causeOfInjury = intent.getStringExtra("causeOfInjury");
        String dateOfInjury = intent.getStringExtra("dateOfInjury");
        String timeOfInjury = intent.getStringExtra("timeOfInjury");
        String placeOfAccident = intent.getStringExtra("placeOfAccident");
        String reportedToPolice = intent.getStringExtra("reportedToPolice");
        String firNumber = intent.getStringExtra("firNumber");
        String medicoLegalCase = intent.getStringExtra("medicoLegalCase");
        String claimType = intent.getStringExtra("claimType");
        boolean domiciliaryHospitalization = intent.getBooleanExtra("domiciliaryHospitalization", false);
        String lumpsumDetails = intent.getStringExtra("lumpsumDetails");
        String hospitalizationExpenses = intent.getStringExtra("hospitalizationExpenses");
        String preHospitalizationExpenses = intent.getStringExtra("preHospitalizationExpenses");
        String postHospitalizationExpenses = intent.getStringExtra("postHospitalizationExpenses");
        String ambulanceCharges = intent.getStringExtra("ambulanceCharges");
        String pharmacyBills = intent.getStringExtra("pharmacyBills");
        String doctorConsultationBills = intent.getStringExtra("doctorConsultationBills");
        String investigationReports = intent.getStringExtra("investigationReports");
        String othersExpenses = intent.getStringExtra("othersExpenses");
        String accountNumber = intent.getStringExtra("accountNumber");
        String accountHolderName = intent.getStringExtra("accountHolderName");
        String bankName = intent.getStringExtra("bankName");
        String ifscCode = intent.getStringExtra("ifscCode");

        // Build and set each section
        textViewPolicyDetails.setText(
                "Policy Number: " + policyNumber + "\n" +
                        "Insurance company: " + insuranceCompany
        );

        textViewPolicyholderDetails.setText(
                "Name: " + proposerName + "\n" +
                "PAN: " + panCard + "\n" +
                "DOB: " + dateOfBirth + "\n" +
                "Gender: " + gender + "\n" +
                "Relationship: " + relationship + "\n" +
                "Occupation: " + occupation + "\n" +
                "Address: " + address + ", " + city + ", " + state + " - " + pinCode + "\n" +
                "Email: " + email + "\n" +
                "Mobile: " + mobile
        );

        textViewHospitalizationDetails.setText(
                "Hospital Name: " + hospitalName + "\n" +
                "Admission: " + dateOfAdmission + " " + timeOfAdmission + "\n" +
                "Discharge: " + dateOfDischarge + " " + timeOfDischarge + "\n" +
                "Diagnosis: " + diagnosis + "\n" +
                "Reason: " + reasonForAdmission + "\n" +
                "Room Category: " + roomCategory + "\n" +
                "Cause: " + hospitalizationCause
        );

        textViewClaimDetails.setText(
                "Claim Type: " + claimType + "\n" +
                "Domiciliary Hospitalization: " + (domiciliaryHospitalization ? "Yes" : "No") + "\n" +
                "Lumpsum Details: " + lumpsumDetails + "\n" +
                "Hospitalization Expenses: " + hospitalizationExpenses + "\n" +
                "Pre-Hospitalization Expenses: " + preHospitalizationExpenses + "\n" +
                "Post-Hospitalization Expenses: " + postHospitalizationExpenses + "\n" +
                "Ambulance Charges: " + ambulanceCharges + "\n" +
                "Pharmacy Bills: " + pharmacyBills + "\n" +
                "Doctor Consultation Bills: " + doctorConsultationBills + "\n" +
                "Investigation Reports: " + investigationReports + "\n" +
                "Others: " + othersExpenses + "\n" +
                (causeOfInjury != null ? ("Cause of Injury: " + causeOfInjury + "\n") : "") +
                (dateOfInjury != null ? ("Date of Injury: " + dateOfInjury + "\n") : "") +
                (timeOfInjury != null ? ("Time of Injury: " + timeOfInjury + "\n") : "") +
                (placeOfAccident != null ? ("Place of Accident: " + placeOfAccident + "\n") : "") +
                (reportedToPolice != null ? ("Reported to Police: " + reportedToPolice + "\n") : "") +
                (firNumber != null ? ("FIR Number: " + firNumber + "\n") : "") +
                (medicoLegalCase != null ? ("Medico Legal Case: " + medicoLegalCase + "\n") : "")
        );

        textViewBankDetails.setText(
                "Account Number: " + accountNumber + "\n" +
                "Account Holder: " + accountHolderName + "\n" +
                "Bank Name: " + bankName + "\n" +
                "IFSC: " + ifscCode
        );

        // Terms and Conditions link
        textViewTermsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClaimSubmissionConfirmationActivity.this, TermsAndConditionsActivity.class);
                startActivity(intent);
            }
        });

        // Confirm button logic
        buttonConfirmSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBoxTerms.isChecked()) {
                    Toast.makeText(ClaimSubmissionConfirmationActivity.this, "Please agree to the Terms and Conditions", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, Object> claimData = new HashMap<>();
                claimData.put("policyNumber", policyNumber);
                claimData.put("insuranceCompany",insuranceCompany);
                claimData.put("customerId", customerId);
                claimData.put("proposerName", proposerName);
                claimData.put("panCard", panCard);
                claimData.put("dateOfBirth", dateOfBirth);
                claimData.put("gender", gender);
                claimData.put("relationship", relationship);
                claimData.put("occupation", occupation);
                claimData.put("address", address);
                claimData.put("city", city);
                claimData.put("state", state);
                claimData.put("pinCode", pinCode);
                claimData.put("email", email);
                claimData.put("mobile", mobile);
                claimData.put("hospitalName", hospitalName);
                claimData.put("dateOfAdmission", dateOfAdmission);
                claimData.put("timeOfAdmission", timeOfAdmission);
                claimData.put("dateOfDischarge", dateOfDischarge);
                claimData.put("timeOfDischarge", timeOfDischarge);
                claimData.put("diagnosis", diagnosis);
                claimData.put("reasonForAdmission", reasonForAdmission);
                claimData.put("roomCategory", roomCategory);
                claimData.put("hospitalizationCause", hospitalizationCause);
                claimData.put("causeOfInjury", causeOfInjury);
                claimData.put("dateOfInjury", dateOfInjury);
                claimData.put("timeOfInjury", timeOfInjury);
                claimData.put("placeOfAccident", placeOfAccident);
                claimData.put("reportedToPolice", reportedToPolice);
                claimData.put("firNumber", firNumber);
                claimData.put("medicoLegalCase", medicoLegalCase);
                claimData.put("claimType", claimType);
                claimData.put("domiciliaryHospitalization", domiciliaryHospitalization);
                claimData.put("lumpsumDetails", lumpsumDetails);
                claimData.put("hospitalizationExpenses", hospitalizationExpenses);
                claimData.put("preHospitalizationExpenses", preHospitalizationExpenses);
                claimData.put("postHospitalizationExpenses", postHospitalizationExpenses);
                claimData.put("ambulanceCharges", ambulanceCharges);
                claimData.put("pharmacyBills", pharmacyBills);
                claimData.put("doctorConsultationBills", doctorConsultationBills);
                claimData.put("investigationReports", investigationReports);
                claimData.put("othersExpenses", othersExpenses);
                claimData.put("accountNumber", accountNumber);
                claimData.put("accountHolderName", accountHolderName);
                claimData.put("bankName", bankName);
                claimData.put("ifscCode", ifscCode);

                claimsRef.push().setValue(claimData)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(ClaimSubmissionConfirmationActivity.this, "Claim submitted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ClaimSubmissionConfirmationActivity.this, DashboardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(ClaimSubmissionConfirmationActivity.this, "Submission failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}