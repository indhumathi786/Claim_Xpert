package com.java.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ClaimDetailsActivity extends AppCompatActivity {

    private RadioGroup radioGroupClaimType;
    private RadioButton radioButtonInPatient;
    private RadioButton radioButtonPrePostHospitalization;
    private CheckBox checkBoxDomiciliaryHospitalization;
    private EditText editTextLumpsumDetails;
    private LinearLayout treatmentExpensesLayout;
    private EditText editTextHospitalizationExpenses;
    private EditText editTextPreHospitalizationExpenses;
    private EditText editTextPostHospitalizationExpenses;
    private EditText editTextAmbulanceCharges;
    private EditText editTextPharmacyBills;
    private EditText editTextDoctorConsultationBills;
    private EditText editTextInvestigationReports;
    private EditText editTextOthersExpenses;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_details);

        // Initialize views
        radioGroupClaimType = findViewById(R.id.radioGroupClaimType);
        radioButtonInPatient = findViewById(R.id.radioButtonInPatient);
        radioButtonPrePostHospitalization = findViewById(R.id.radioButtonPrePostHospitalization);
        checkBoxDomiciliaryHospitalization = findViewById(R.id.checkBoxDomiciliaryHospitalization);
        editTextLumpsumDetails = findViewById(R.id.editTextLumpsumDetails);
        treatmentExpensesLayout = findViewById(R.id.treatmentExpensesLayout);
        editTextHospitalizationExpenses = findViewById(R.id.editTextHospitalizationExpenses);
        editTextPreHospitalizationExpenses = findViewById(R.id.editTextPreHospitalizationExpenses);
        editTextPostHospitalizationExpenses = findViewById(R.id.editTextPostHospitalizationExpenses);
        editTextAmbulanceCharges = findViewById(R.id.editTextAmbulanceCharges);
        editTextPharmacyBills = findViewById(R.id.editTextPharmacyBills);
        editTextDoctorConsultationBills = findViewById(R.id.editTextDoctorConsultationBills);
        editTextInvestigationReports = findViewById(R.id.editTextInvestigationReports);
        editTextOthersExpenses = findViewById(R.id.editTextOthersExpenses);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrevious = findViewById(R.id.buttonPrevious);

        // Initially hide lumpsum details
        editTextLumpsumDetails.setVisibility(View.GONE);

        // Set listener for claim type to potentially show/hide lumpsum details
        radioGroupClaimType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != R.id.radioButtonInPatient) {
                editTextLumpsumDetails.setVisibility(View.VISIBLE);
            } else {
                editTextLumpsumDetails.setVisibility(View.GONE);
            }
        });

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

        // Set up click listener for the next button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String claimType = "";
                if (radioButtonInPatient.isChecked()) claimType = "In-Patient";
                else if (radioButtonPrePostHospitalization.isChecked()) claimType = "Pre/Post-Hospitalization";
                boolean domiciliaryHospitalization = checkBoxDomiciliaryHospitalization.isChecked();
                String lumpsumDetails = editTextLumpsumDetails.getText().toString().trim();
                String hospitalizationExpenses = editTextHospitalizationExpenses.getText().toString().trim();
                String preHospitalizationExpenses = editTextPreHospitalizationExpenses.getText().toString().trim();
                String postHospitalizationExpenses = editTextPostHospitalizationExpenses.getText().toString().trim();
                String ambulanceCharges = editTextAmbulanceCharges.getText().toString().trim();
                String pharmacyBills = editTextPharmacyBills.getText().toString().trim();
                String doctorConsultationBills = editTextDoctorConsultationBills.getText().toString().trim();
                String investigationReports = editTextInvestigationReports.getText().toString().trim();
                String othersExpenses = editTextOthersExpenses.getText().toString().trim();

                Intent intent = new Intent(ClaimDetailsActivity.this, BillDetailsActivity.class);
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
                startActivity(intent);
            }
        });

        // Set up click listener for the previous button
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });
    }
}