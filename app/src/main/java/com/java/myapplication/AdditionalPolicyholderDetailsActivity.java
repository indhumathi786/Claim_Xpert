package com.java.myapplication;

import static com.java.myapplication.R.*;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AdditionalPolicyholderDetailsActivity extends AppCompatActivity {

    private EditText editTextPanCard;
    private TextView textViewDateOfBirth;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private RadioButton radioButtonOther;
    private EditText editTextRelationship;
    private EditText editTextOccupation;
    private Button buttonNext;
    private Button buttonPrevious;
    private Button buttonPickDob;

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

    private Calendar dobCalendar = Calendar.getInstance();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_policyholder_details);

        // Initialize views
        editTextPanCard = findViewById(R.id.editTextPanCard);
        textViewDateOfBirth = findViewById(R.id.textViewDateOfBirth);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonOther = findViewById(R.id.radioButtonOther);
        editTextRelationship = findViewById(R.id.editTextRelationship);
        editTextOccupation = findViewById(R.id.editTextOccupation);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonPickDob = findViewById(R.id.buttonPickDob);

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
        String panCard = getIntent().getStringExtra("panCard");
        String dateOfBirth = getIntent().getStringExtra("dateOfBirth");
        String gender = getIntent().getStringExtra("gender");
        String relationship = getIntent().getStringExtra("relationship");
        String occupation = getIntent().getStringExtra("occupation");

        if (panCard != null) editTextPanCard.setText(panCard);
        if (dateOfBirth != null) textViewDateOfBirth.setText(dateOfBirth);
        else textViewDateOfBirth.setText(dateFormatter.format(dobCalendar.getTime())); // Set current date as default
        if (gender != null) {
            if (gender.equalsIgnoreCase("male")) radioButtonMale.setChecked(true);
            else if (gender.equalsIgnoreCase("female")) radioButtonFemale.setChecked(true);
            else if (gender.equalsIgnoreCase("other")) radioButtonOther.setChecked(true);
        }
        if (relationship != null) editTextRelationship.setText(relationship);
        if (occupation != null) editTextOccupation.setText(occupation);

        // Set up Date Picker for Date of Birth
        buttonPickDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Set up click listener for the next button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPanCard = editTextPanCard.getText().toString().trim();
                String currentDateOfBirth = textViewDateOfBirth.getText().toString().trim();
                String currentGender = "";
                if (radioButtonMale.isChecked()) currentGender = "Male";
                else if (radioButtonFemale.isChecked()) currentGender = "Female";
                else if (radioButtonOther.isChecked()) currentGender = "Other";
                String currentRelationship = editTextRelationship.getText().toString().trim();
                String currentOccupation = editTextOccupation.getText().toString().trim();

                Intent intent = new Intent(AdditionalPolicyholderDetailsActivity.this, HospitalizationDetailsActivity.class);
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
                intent.putExtra("panCard", currentPanCard);
                intent.putExtra("dateOfBirth", currentDateOfBirth);
                intent.putExtra("gender", currentGender);
                intent.putExtra("relationship", currentRelationship);
                intent.putExtra("occupation", currentOccupation);
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

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dobCalendar.set(Calendar.YEAR, year);
                        dobCalendar.set(Calendar.MONTH, monthOfYear);
                        dobCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        textViewDateOfBirth.setText(dateFormatter.format(dobCalendar.getTime()));
                    }
                },
                dobCalendar.get(Calendar.YEAR),
                dobCalendar.get(Calendar.MONTH),
                dobCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}