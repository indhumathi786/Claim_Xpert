package com.java.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DocumentUploadActivity extends AppCompatActivity {

    private LinearLayout documentUploadLayout;
    private Button buttonUploadDischargeSummary;
    private Button buttonUploadHospitalBill;
    private Button buttonUploadDoctorsPrescription;
    private Button buttonUploadInvestigationReports;
    private Button buttonUploadPharmacyBills;
    private Button buttonUploadOthers;
    private TextView textViewDischargeSummaryName;
    private TextView textViewHospitalBillName;
    private TextView textViewDoctorsPrescriptionName;
    private TextView textViewInvestigationReportsName;
    private TextView textViewPharmacyBillsName;
    private TextView textViewOthersName;
    private Button buttonNext;
    private Button buttonPrevious;

    private final Map<String, Uri> uploadedDocuments = new HashMap<>();
    private ActivityResultLauncher<String> filePickerLauncher; // Declare here
    private String currentDocumentTypeHolder; // To hold the current document type

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
    private String investigationReportsExpense;
    private String othersExpenses;
    private ArrayList<BillAdapter.Bill> billList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);

        // Initialize filePickerLauncher inside onCreate
        filePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                // The documentType is now captured from the launchFilePicker method
                // and available in the lambda's scope.
                // No need to access the contract for input.
                updateFileNameTextView(currentDocumentTypeHolder, uri);
                uploadedDocuments.put(currentDocumentTypeHolder, uri);
            } else {
                Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize views
        documentUploadLayout = findViewById(R.id.documentUploadLayout);
        buttonUploadDischargeSummary = findViewById(R.id.buttonUploadDischargeSummary);
        buttonUploadHospitalBill = findViewById(R.id.buttonUploadHospitalBill);
        buttonUploadDoctorsPrescription = findViewById(R.id.buttonUploadDoctorsPrescription);
        buttonUploadInvestigationReports = findViewById(R.id.buttonUploadInvestigationReports);
        buttonUploadPharmacyBills = findViewById(R.id.buttonUploadPharmacyBills);
        buttonUploadOthers = findViewById(R.id.buttonUploadOthers);
        textViewDischargeSummaryName = findViewById(R.id.textViewDischargeSummaryName);
        textViewHospitalBillName = findViewById(R.id.textViewHospitalBillName);
        textViewDoctorsPrescriptionName = findViewById(R.id.textViewDoctorsPrescriptionName);
        textViewInvestigationReportsName = findViewById(R.id.textViewInvestigationReportsName);
        textViewPharmacyBillsName = findViewById(R.id.textViewPharmacyBillsName);
        textViewOthersName = findViewById(R.id.textViewOthersName);
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
        investigationReportsExpense = getIntent().getStringExtra("investigationReports");
        othersExpenses = getIntent().getStringExtra("othersExpenses");
        billList = getIntent().getParcelableArrayListExtra("billList");

        // Set up click listeners for upload buttons
        buttonUploadDischargeSummary.setOnClickListener(v -> launchFilePicker("Discharge Summary"));
        buttonUploadHospitalBill.setOnClickListener(v -> launchFilePicker("Hospital Bill"));
        buttonUploadDoctorsPrescription.setOnClickListener(v -> launchFilePicker("Doctor's Prescription"));
        buttonUploadInvestigationReports.setOnClickListener(v -> launchFilePicker("Investigation Reports"));
        buttonUploadPharmacyBills.setOnClickListener(v -> launchFilePicker("Pharmacy Bills"));
        buttonUploadOthers.setOnClickListener(v -> launchFilePicker("Others"));

        buttonNext.setOnClickListener(v -> {
            // In a real scenario, you would upload the files here using the URIs in uploadedDocuments.
            // For this example, we'll just pass the URIs to the next activity.
            Toast.makeText(DocumentUploadActivity.this, "Document upload simulated (URIs passed)", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(DocumentUploadActivity.this, BankAccountDetailsActivity.class);
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
            intent.putExtra("investigationReports", investigationReportsExpense);
            intent.putExtra("othersExpenses", othersExpenses);
            intent.putParcelableArrayListExtra("billList", billList);
            intent.putExtra("uploadedDischargeSummary", uploadedDocuments.get("Discharge Summary") != null ? uploadedDocuments.get("Discharge Summary").toString() : null);
            intent.putExtra("uploadedHospitalBill", uploadedDocuments.get("Hospital Bill") != null ? uploadedDocuments.get("Hospital Bill").toString() : null);
            intent.putExtra("uploadedDoctorsPrescription", uploadedDocuments.get("Doctor's Prescription") != null ? uploadedDocuments.get("Doctor's Prescription").toString() : null);
            intent.putExtra("uploadedInvestigationReports", uploadedDocuments.get("Investigation Reports") != null ? uploadedDocuments.get("Investigation Reports").toString() : null);
            intent.putExtra("uploadedPharmacyBills", uploadedDocuments.get("Pharmacy Bills") != null ? uploadedDocuments.get("Pharmacy Bills").toString() : null);
            intent.putExtra("uploadedOthers", uploadedDocuments.get("Others") != null ? uploadedDocuments.get("Others").toString() : null);

            startActivity(intent);
        });

        buttonPrevious.setOnClickListener(v -> finish());
    }

    private void launchFilePicker(String documentType) {
        currentDocumentTypeHolder = documentType; // Store the document type
        filePickerLauncher.launch("*/*");
    }

    private void updateFileNameTextView(String documentType, Uri uri) {
        String fileName = getFileNameFromUri(uri);
        if (documentType.equals("Discharge Summary")) {
            textViewDischargeSummaryName.setText(fileName);
        } else if (documentType.equals("Hospital Bill")) {
            textViewHospitalBillName.setText(fileName);
        } else if (documentType.equals("Doctor's Prescription")) {
            textViewDoctorsPrescriptionName.setText(fileName);
        } else if (documentType.equals("Investigation Reports")) {
            textViewInvestigationReportsName.setText(fileName);
        } else if (documentType.equals("Pharmacy Bills")) {
            textViewPharmacyBillsName.setText(fileName);
        } else if (documentType.equals("Others")) {
            textViewOthersName.setText(fileName);
        }
    }

    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        if (uri.getScheme().equals("content")) {
            try (android.database.Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int displayNameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME);
                    if (displayNameIndex != -1) {
                        fileName = cursor.getString(displayNameIndex);
                    }
                }
            }
        }
        if (fileName == null) {
            fileName = uri.getLastPathSegment();
        }
        return fileName;
    }
}