package com.java.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TermsAndConditionsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        TextView textView = findViewById(R.id.textViewTermsContent);
        textView.setText("ClaimXpert Reimbursement Claim Submission - Terms and Conditions\n" +
                "\n" +
                "By submitting a reimbursement claim through the ClaimXpert app, you agree to the following:\n" +
                "\n" +
                "Submission Timeframe: You must submit all required documents within the following timeframes:\n" +
                "\n" +
                "Hospitalization, Day Care, and Pre-Hospitalization expenses: Within 30 days of discharge from the hospital.\n" +
                "Post-Hospitalization expenses: Within 15 days from the completion of post-hospitalization treatment.\n" +
                "Notification of Hospitalization:\n" +
                "\n" +
                "For emergency hospitalization: You must notify ClaimXpert/the Insurance Company within 24 hours of admission or before discharge, whichever is earlier.\n" +
                "For planned hospitalization: You must notify ClaimXpert/the Insurance Company at least 48 hours before admission.\n" +
                "Document Submission: You are responsible for providing all necessary documentation to support your reimbursement claim. The specific documents required will be detailed within the ClaimXpert app during the claim submission process.\n" +
                "\n" +
                "Bill Requirements: All bills, invoices, and medical treatment-related documents must be in the name of the insured person who is submitting the claim.\n" +
                "\n" +
                "Co-payment: Each claim is subject to a co-payment, as per the terms and conditions of your insurance policy. The payable amount will be calculated after deducting the co-payment.\n" +
                "\n" +
                "Claim Settlement: ClaimXpert/the Insurance Company will process and settle or reject your claim within 30 days from the date all necessary documents are received.");
    }
} 