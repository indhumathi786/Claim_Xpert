package com.java.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BankAccountDetailsActivity extends AppCompatActivity {

    private EditText editTextAccountNumber;
    private EditText editTextAccountHolderName;
    private EditText editTextBankName;
    private EditText editTextIFSCCode;
    private Button buttonNext;
    private Button buttonPrevious;

    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String email;
    private String mobile;
    private String policyNumber;
    private String insuranceCompany;
    private String customerId;
    private String proposerName;
    private String panCard;
    private String dateOfBirth;
    private String gender;
    private String relationship;
    private String occupation;
    private String hospitalName;
    private String dateOfAdmission;
    private String timeOfAdmission;
    private String dateOfDischarge;
    private String timeOfDischarge;
    private String diagnosis;
    private String reasonForAdmission;
    private String roomCategory;
    private String hospitalizationCause;
    private String causeOfInjury;
    private String dateOfInjury;
    private String timeOfInjury;
    private String placeOfAccident;
    private String reportedToPolice;
    private String firNumber;
    private String medicoLegalCase;
    private String claimType;
    private boolean domiciliaryHospitalization;
    private String lumpsumDetails;
    private String hospitalizationExpenses;
    private String preHospitalizationExpenses;
    private String postHospitalizationExpenses;
    private String ambulanceCharges;
    private String pharmacyBills;
    private String doctorConsultationBills;
    private String investigationReports;
    private String othersExpenses;
    private ArrayList<BillAdapter.Bill> billList;
    private boolean dischargeSummaryUploaded;
    private boolean hospitalBillUploaded;
    private boolean doctorsPrescriptionUploaded;
    private boolean investigationReportsUploaded;
    private boolean pharmacyBillsUploaded;
    private boolean othersUploaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account_details);

        // Initialize views
        editTextAccountNumber = findViewById(R.id.editTextAccountNumber);
        editTextAccountHolderName = findViewById(R.id.editTextAccountHolderName);
        editTextBankName = findViewById(R.id.editTextBankName);
        editTextIFSCCode = findViewById(R.id.editTextIFSCCode);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrevious = findViewById(R.id.buttonPrevious);

        // Get data passed from the previous activity
        address = getIntent().getStringExtra("address");
        city = getIntent().getStringExtra("city");
        state = getIntent().getStringExtra("state");
        pinCode = getIntent().getStringExtra("pinCode");
        email = getIntent().getStringExtra("email");
        mobile = getIntent().getStringExtra("mobile");
        policyNumber = getIntent().getStringExtra("policyNumber");
        insuranceCompany = getIntent().getStringExtra("insuranceCompany");
        customerId = getIntent().getStringExtra("customerId");
        proposerName = getIntent().getStringExtra("proposerName");
        panCard = getIntent().getStringExtra("panCard");
        dateOfBirth = getIntent().getStringExtra("dateOfBirth");
        gender = getIntent().getStringExtra("gender");
        relationship = getIntent().getStringExtra("relationship");
        occupation = getIntent().getStringExtra("occupation");
        hospitalName = getIntent().getStringExtra("hospitalName");
        dateOfAdmission = getIntent().getStringExtra("dateOfAdmission");
        timeOfAdmission = getIntent().getStringExtra("timeOfAdmission");
        dateOfDischarge = getIntent().getStringExtra("dateOfDischarge");
        timeOfDischarge = getIntent().getStringExtra("timeOfDischarge");
        diagnosis = getIntent().getStringExtra("diagnosis");
        reasonForAdmission = getIntent().getStringExtra("reasonForAdmission");
        roomCategory = getIntent().getStringExtra("roomCategory");
        hospitalizationCause = getIntent().getStringExtra("hospitalizationCause");
        causeOfInjury = getIntent().getStringExtra("causeOfInjury");
        dateOfInjury = getIntent().getStringExtra("dateOfInjury");
        timeOfInjury = getIntent().getStringExtra("timeOfInjury");
        placeOfAccident = getIntent().getStringExtra("placeOfAccident");
        reportedToPolice = getIntent().getStringExtra("reportedToPolice");
        firNumber = getIntent().getStringExtra("firNumber");
        medicoLegalCase = getIntent().getStringExtra("medicoLegalCase");
        claimType = getIntent().getStringExtra("claimType");
        domiciliaryHospitalization = getIntent().getBooleanExtra("domiciliaryHospitalization", false);
        lumpsumDetails = getIntent().getStringExtra("lumpsumDetails");
        hospitalizationExpenses = getIntent().getStringExtra("hospitalizationExpenses");
        preHospitalizationExpenses = getIntent().getStringExtra("preHospitalizationExpenses");
        postHospitalizationExpenses = getIntent().getStringExtra("postHospitalizationExpenses");
        ambulanceCharges = getIntent().getStringExtra("ambulanceCharges");
        pharmacyBills = getIntent().getStringExtra("pharmacyBills");
        doctorConsultationBills = getIntent().getStringExtra("doctorConsultationBills");
        investigationReports = getIntent().getStringExtra("investigationReports");
        othersExpenses = getIntent().getStringExtra("othersExpenses");
        billList = getIntent().getParcelableArrayListExtra("billList");
        dischargeSummaryUploaded = getIntent().getBooleanExtra("dischargeSummaryUploaded", false);
        hospitalBillUploaded = getIntent().getBooleanExtra("hospitalBillUploaded", false);
        doctorsPrescriptionUploaded = getIntent().getBooleanExtra("doctorsPrescriptionUploaded", false);
        investigationReportsUploaded = getIntent().getBooleanExtra("investigationReportsUploaded", false);
        pharmacyBillsUploaded = getIntent().getBooleanExtra("pharmacyBillsUploaded", false);
        othersUploaded = getIntent().getBooleanExtra("othersUploaded", false);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountNumber = editTextAccountNumber.getText().toString().trim();
                String accountHolderName = editTextAccountHolderName.getText().toString().trim();
                String bankName = editTextBankName.getText().toString().trim();
                String ifscCode = editTextIFSCCode.getText().toString().trim();

                if (accountNumber.isEmpty() || accountHolderName.isEmpty() || bankName.isEmpty() || ifscCode.isEmpty()) {
                    Toast.makeText(BankAccountDetailsActivity.this, "Please fill in all bank account details", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add the account number validation here
                if (accountNumber.length() != 16) {
                    Toast.makeText(BankAccountDetailsActivity.this, "Account number must be 16 digits", Toast.LENGTH_SHORT).show();
                    editTextAccountNumber.setError("Account number must be 16 digits"); // Optional: Set an error on the EditText
                    return;
                }

                Intent intent = new Intent(BankAccountDetailsActivity.this, ClaimSubmissionConfirmationActivity.class);
                // Pass all the collected data
                intent.putExtra("address", address);
                intent.putExtra("city", city);
                intent.putExtra("state", state);
                intent.putExtra("pinCode", pinCode);
                intent.putExtra("email", email);
                intent.putExtra("mobile", mobile);
                intent.putExtra("policyNumber", policyNumber);
                intent.putExtra("insuranceCompany", insuranceCompany);
                intent.putExtra("customerId", customerId);
                intent.putExtra("proposerName", proposerName);
                intent.putExtra("panCard", panCard);
                intent.putExtra("dateOfBirth", dateOfBirth);
                intent.putExtra("gender", gender);
                intent.putExtra("relationship", relationship);
                intent.putExtra("occupation", occupation);
                intent.putExtra("hospitalName", hospitalName);
                intent.putExtra("dateOfAdmission", dateOfAdmission);
                intent.putExtra("timeOfAdmission", timeOfAdmission);
                intent.putExtra("dateOfDischarge", dateOfDischarge);
                intent.putExtra("timeOfDischarge", timeOfDischarge);
                intent.putExtra("diagnosis", diagnosis);
                intent.putExtra("reasonForAdmission", reasonForAdmission);
                intent.putExtra("roomCategory", roomCategory);
                intent.putExtra("hospitalizationCause", hospitalizationCause);
                intent.putExtra("causeOfInjury", causeOfInjury);
                intent.putExtra("dateOfInjury", dateOfInjury);
                intent.putExtra("timeOfInjury", timeOfInjury);
                intent.putExtra("placeOfAccident", placeOfAccident);
                intent.putExtra("reportedToPolice", reportedToPolice);
                intent.putExtra("firNumber", firNumber);
                intent.putExtra("medicoLegalCase", medicoLegalCase);
                intent.putExtra("claimType", claimType);
                intent.putExtra("domiciliaryHospitalization", domiciliaryHospitalization);
                intent.putExtra("lumpsumDetails", lumpsumDetails);
                intent.putExtra("hospitalizationExpenses", hospitalizationExpenses);
                intent.putExtra("preHospitalizationExpenses", preHospitalizationExpenses);
                intent.putExtra("postHospitalizationExpenses", postHospitalizationExpenses);
                intent.putExtra("ambulanceCharges", ambulanceCharges);
                intent.putExtra("pharmacyBills", pharmacyBills);
                intent.putExtra("doctorConsultationBills", doctorConsultationBills);
                intent.putExtra("investigationReports", investigationReports);
                intent.putExtra("othersExpenses", othersExpenses);
                intent.putParcelableArrayListExtra("billList", billList);
                intent.putExtra("dischargeSummaryUploaded", dischargeSummaryUploaded);
                intent.putExtra("hospitalBillUploaded", hospitalBillUploaded);
                intent.putExtra("doctorsPrescriptionUploaded", doctorsPrescriptionUploaded);
                intent.putExtra("investigationReportsUploaded", investigationReportsUploaded);
                intent.putExtra("pharmacyBillsUploaded", pharmacyBillsUploaded);
                intent.putExtra("othersUploaded", othersUploaded);
                intent.putExtra("accountNumber", accountNumber);
                intent.putExtra("accountHolderName", accountHolderName);
                intent.putExtra("bankName", bankName);
                intent.putExtra("ifscCode", ifscCode);
                startActivity(intent);
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