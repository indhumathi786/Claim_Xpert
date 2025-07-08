package com.java.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class InjuryDetailsActivity extends AppCompatActivity {

    private EditText editTextCauseOfInjury;
    private TextView textViewDateOfInjury;
    private TextView textViewTimeOfInjury;
    private EditText editTextPlaceOfAccident;
    private RadioGroup radioGroupReportedToPolice;
    private RadioButton radioButtonYesPolice;
    private RadioButton radioButtonNoPolice;
    private EditText editTextFirNumber;
    private RadioGroup radioGroupMedicoLegalCase;
    private RadioButton radioButtonYesMedicoLegal;
    private RadioButton radioButtonNoMedicoLegal;
    private Button buttonNext;
    private Button buttonPrevious;
    private Button buttonPickDateOfInjury;
    private Button buttonPickTimeOfInjury;
    private Calendar injuryDateCalendar = Calendar.getInstance();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injury_details);

        // Initialize views
        editTextCauseOfInjury = findViewById(R.id.editTextCauseOfInjury);
        textViewDateOfInjury = findViewById(R.id.textViewDateOfInjury);
        textViewTimeOfInjury = findViewById(R.id.textViewTimeOfInjury);
        editTextPlaceOfAccident = findViewById(R.id.editTextPlaceOfAccident);
        radioGroupReportedToPolice = findViewById(R.id.radioGroupReportedToPolice);
        radioButtonYesPolice = findViewById(R.id.radioButtonYesPolice);
        radioButtonNoPolice = findViewById(R.id.radioButtonNoPolice);
        editTextFirNumber = findViewById(R.id.editTextFirNumber);
        radioGroupMedicoLegalCase = findViewById(R.id.radioGroupMedicoLegalCase);
        radioButtonYesMedicoLegal = findViewById(R.id.radioButtonYesMedicoLegal);
        radioButtonNoMedicoLegal = findViewById(R.id.radioButtonNoMedicoLegal);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonPickDateOfInjury = findViewById(R.id.buttonPickDateOfInjury);
        buttonPickTimeOfInjury = findViewById(R.id.buttonPickTimeOfInjury);

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

        // Initially hide FIR Number field
        editTextFirNumber.setVisibility(View.GONE);

        radioGroupReportedToPolice.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButtonYesPolice) {
                editTextFirNumber.setVisibility(View.VISIBLE);
            } else {
                editTextFirNumber.setVisibility(View.GONE);
            }
        });

        // Set default date and time
        textViewDateOfInjury.setText(dateFormatter.format(injuryDateCalendar.getTime()));
        textViewTimeOfInjury.setText(timeFormatter.format(injuryDateCalendar.getTime()));
        buttonPickDateOfInjury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(textViewDateOfInjury, injuryDateCalendar);
            }
        });
        buttonPickTimeOfInjury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(textViewTimeOfInjury, injuryDateCalendar);
            }
        });

        // Set up click listener for the next button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentCauseOfInjury = editTextCauseOfInjury.getText().toString().trim();
                String currentDateOfInjury = textViewDateOfInjury.getText().toString().trim();
                String currentTimeOfInjury = textViewTimeOfInjury.getText().toString().trim();
                String currentPlaceOfAccident = editTextPlaceOfAccident.getText().toString().trim();
                String reportedToPolice = radioButtonYesPolice.isChecked() ? "Yes" : "No";
                String currentFirNumber = editTextFirNumber.getText().toString().trim();
                String medicoLegalCase = radioButtonYesMedicoLegal.isChecked() ? "Yes" : "No";

                Intent intent = new Intent(InjuryDetailsActivity.this, ClaimDetailsActivity.class);
                // Pass all the collected data
                intent.putExtra("address", address);
                intent.putExtra("city", city);
                intent.putExtra("state", state);
                intent.putExtra("pinCode", pinCode);
                intent.putExtra("email", email);
                intent.putExtra("mobile", mobile);
                intent.putExtra("policyNumber", policyNumber);
                intent.putExtra("insuranceCompany",insuranceCompany);
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
                intent.putExtra("causeOfInjury", currentCauseOfInjury);
                intent.putExtra("dateOfInjury", currentDateOfInjury);
                intent.putExtra("timeOfInjury", currentTimeOfInjury);
                intent.putExtra("placeOfAccident", currentPlaceOfAccident);
                intent.putExtra("reportedToPolice", reportedToPolice);
                intent.putExtra("firNumber", currentFirNumber);
                intent.putExtra("medicoLegalCase", medicoLegalCase);
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
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        textView.setText(dateFormatter.format(calendar.getTime()));
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePickerDialog(final TextView textView, final Calendar calendar) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        textView.setText(timeFormatter.format(calendar.getTime()));
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true); // 24-hour format
        timePickerDialog.show();
    }
}