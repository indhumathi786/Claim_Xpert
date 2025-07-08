package com.java.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BillDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBills;
    private BillAdapter billAdapter;
    private List<BillAdapter.Bill> billList = new ArrayList<>();
    private Button buttonAddBill;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);

        // Initialize views
        recyclerViewBills = findViewById(R.id.recyclerViewBills);
        buttonAddBill = findViewById(R.id.buttonAddBill);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrevious = findViewById(R.id.buttonPrevious);

        // Set up RecyclerView
        recyclerViewBills.setLayoutManager(new LinearLayoutManager(this));
        billAdapter = new BillAdapter(billList);
        recyclerViewBills.setAdapter(billAdapter);

        // Add an initial bill item
        billAdapter.addBill();

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

        // Set up click listener for the add bill button
        buttonAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billAdapter.addBill();
            }
        });

        // Set up click listener for the next button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BillAdapter.Bill> currentBillList = billAdapter.getBillList();

                Intent intent = new Intent(BillDetailsActivity.this, DocumentUploadActivity.class);
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
                // Pass the list of bills from the RecyclerView
                intent.putParcelableArrayListExtra("billList", (ArrayList<? extends android.os.Parcelable>) currentBillList);
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