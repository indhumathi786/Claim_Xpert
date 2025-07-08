package com.java.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HospitalizationDetailsActivity extends AppCompatActivity {

    private EditText editTextHospitalName;
    private TextView textViewDateOfAdmission;
    private TextView textViewTimeOfAdmission;
    private TextView textViewDateOfDischarge;
    private TextView textViewTimeOfDischarge;
    private EditText editTextDiagnosis;
    private EditText editTextReasonForAdmission;
    private Spinner spinnerRoomCategory;
    private RadioGroup radioGroupHospitalizationCause;
    private RadioButton radioButtonIllness;
    private RadioButton radioButtonInjury;
    private RadioButton radioButtonMaternity;
    private Button buttonNext;
    private Button buttonPrevious;
    private Button buttonPickAdmissionDate;
    private Button buttonPickAdmissionTime;
    private Button buttonPickDischargeDate;
    private Button buttonPickDischargeTime;

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

    private Calendar admissionCalendar = Calendar.getInstance();
    private Calendar dischargeCalendar = Calendar.getInstance();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitalization_details);

        // Initialize views
        editTextHospitalName = findViewById(R.id.editTextHospitalName);
        textViewDateOfAdmission = findViewById(R.id.textViewDateOfAdmission);
        textViewTimeOfAdmission = findViewById(R.id.textViewTimeOfAdmission);
        textViewDateOfDischarge = findViewById(R.id.textViewDateOfDischarge);
        textViewTimeOfDischarge = findViewById(R.id.textViewTimeOfDischarge);
        editTextDiagnosis = findViewById(R.id.editTextDiagnosis);
        editTextReasonForAdmission = findViewById(R.id.editTextReasonForAdmission);
        spinnerRoomCategory = findViewById(R.id.spinnerRoomCategory);
        radioGroupHospitalizationCause = findViewById(R.id.radioGroupHospitalizationCause);
        radioButtonIllness = findViewById(R.id.radioButtonIllness);
        radioButtonInjury = findViewById(R.id.radioButtonInjury);
        radioButtonMaternity = findViewById(R.id.radioButtonMaternity);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonPickAdmissionDate = findViewById(R.id.buttonPickAdmissionDate);
        buttonPickAdmissionTime = findViewById(R.id.buttonPickAdmissionTime);
        buttonPickDischargeDate = findViewById(R.id.buttonPickDischargeDate);
        buttonPickDischargeTime = findViewById(R.id.buttonPickDischargeTime);

        // Populate spinner with room categories
        String[] roomCategories = {"General Ward", "Semi-Private Room", "Private Room", "ICU", "Deluxe Room", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomCategory.setAdapter(adapter);

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

        // Set default date and time to current
        textViewDateOfAdmission.setText(dateFormatter.format(admissionCalendar.getTime()));
        textViewTimeOfAdmission.setText(timeFormatter.format(admissionCalendar.getTime()));
        textViewDateOfDischarge.setText(dateFormatter.format(dischargeCalendar.getTime()));
        textViewTimeOfDischarge.setText(timeFormatter.format(dischargeCalendar.getTime()));

        // Set up Date and Time Pickers for Admission
        buttonPickAdmissionDate.setOnClickListener(v -> showDatePickerDialog(textViewDateOfAdmission, admissionCalendar));
        buttonPickAdmissionTime.setOnClickListener(v -> showTimePickerDialog(textViewTimeOfAdmission, admissionCalendar));

        // Set up Date and Time Pickers for Discharge
        buttonPickDischargeDate.setOnClickListener(v -> showDatePickerDialog(textViewDateOfDischarge, dischargeCalendar));
        buttonPickDischargeTime.setOnClickListener(v -> showTimePickerDialog(textViewTimeOfDischarge, dischargeCalendar));

        // Set up click listener for the next button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentHospitalName = editTextHospitalName.getText().toString().trim();
                String currentDateOfAdmission = textViewDateOfAdmission.getText().toString().trim();
                String currentTimeOfAdmission = textViewTimeOfAdmission.getText().toString().trim();
                String currentDateOfDischarge = textViewDateOfDischarge.getText().toString().trim();
                String currentTimeOfDischarge = textViewTimeOfDischarge.getText().toString().trim();
                String currentDiagnosis = editTextDiagnosis.getText().toString().trim();
                String currentReasonForAdmission = editTextReasonForAdmission.getText().toString().trim();
                String currentRoomCategory = spinnerRoomCategory.getSelectedItem().toString();
                String hospitalizationCause = "";
                if (radioButtonIllness.isChecked()) hospitalizationCause = "Illness";
                else if (radioButtonInjury.isChecked()) hospitalizationCause = "Injury";
                else if (radioButtonMaternity.isChecked()) hospitalizationCause = "Maternity";

                Intent intent;
                if (hospitalizationCause.equals("Injury")) {
                    intent = new Intent(HospitalizationDetailsActivity.this, InjuryDetailsActivity.class);
                } else {
                    intent = new Intent(HospitalizationDetailsActivity.this, ClaimDetailsActivity.class);
                }
                // Pass all the existing data and hospitalization details
                intent.putExtra("hospitalName", currentHospitalName);
                intent.putExtra("dateOfAdmission", currentDateOfAdmission);
                intent.putExtra("timeOfAdmission", currentTimeOfAdmission);
                intent.putExtra("dateOfDischarge", currentDateOfDischarge);
                intent.putExtra("timeOfDischarge", currentTimeOfDischarge);
                intent.putExtra("diagnosis", currentDiagnosis);
                intent.putExtra("reasonForAdmission", currentReasonForAdmission);
                intent.putExtra("roomCategory", currentRoomCategory);
                intent.putExtra("hospitalizationCause", hospitalizationCause);
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

    private void showDatePickerDialog(final TextView textView, final Calendar calendar) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    textView.setText(dateFormatter.format(calendar.getTime()));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePickerDialog(final TextView textView, final Calendar calendar) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    textView.setText(timeFormatter.format(calendar.getTime()));
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true); // 24-hour format
        timePickerDialog.show();
    }
}