package com.java.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.*;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etLastname, etEmail, etDob, etPassword, etConfirmPassword;
    RadioGroup rgGender;
    Button btnRegister;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etLastname = findViewById(R.id.etLastname);
        etEmail = findViewById(R.id.etEmail);
        etDob = findViewById(R.id.etDob);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        rgGender = findViewById(R.id.rgGender);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etDob.setFocusable(false);
        etDob.setOnClickListener(v -> showDatePickerDialog());

        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    etDob.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String lastname = etLastname.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String dob = etDob.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        int genderId = rgGender.getCheckedRadioButtonId();
        RadioButton rb = findViewById(genderId);
        String gender = (rb != null) ? rb.getText().toString() : "";

        // Validate all fields
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(lastname) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(dob) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) ||
                TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isStrongPassword(password)) {
            Toast.makeText(this, "Password must be at least 6 characters with uppercase, lowercase, number & special character", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Registration successful
        Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show();

        //Redirect to login
        startActivity(new Intent(this, LoginActivity.class));
        finish();

        // Firebase Registration
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            // Send email verification
                            firebaseUser.sendEmailVerification()
                                    .addOnSuccessListener(aVoid ->
                                            Toast.makeText(this, "Verification email sent. Please check your inbox.", Toast.LENGTH_LONG).show())
                                    .addOnFailureListener(e ->
                                            Toast.makeText(this, "Failed to send verification email.", Toast.LENGTH_SHORT).show());

                            // Save additional user details
                            Map<String, Object> userMap = new HashMap<>();
                            userMap.put("firstName", username);
                            userMap.put("lastName", lastname);
                            userMap.put("email", email);
                            userMap.put("dob", dob);
                            userMap.put("gender", gender);

                            db.collection("users")
                                    .document(firebaseUser.getUid())
                                    .set(userMap)
                                    .addOnSuccessListener(unused -> {
                                        Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        finish();
                                    })
                                    .addOnFailureListener(e ->
                                            Toast.makeText(this, "Error saving user data", Toast.LENGTH_SHORT).show());
                        }
                    } else {
                        Toast.makeText(this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean isStrongPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$");
        return pattern.matcher(password).matches();
    }
}






/*
package com.java.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etLastname, etEmail, etDob, etPassword, etConfirmPassword;
    RadioGroup rgGender;
    Button btnRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etLastname = findViewById(R.id.etLastname);
        etEmail = findViewById(R.id.etEmail);
        etDob = findViewById(R.id.etDob);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        rgGender = findViewById(R.id.rgGender);
        btnRegister = findViewById(R.id.btnRegister);

        etDob.setFocusable(false);
        etDob.setOnClickListener(v -> showDatePickerDialog());

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String lastname = etLastname.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String dob = etDob.getText().toString().trim();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            int genderId = rgGender.getCheckedRadioButtonId();
            RadioButton rb = findViewById(genderId);
            String gender = (rb != null) ? rb.getText().toString() : "";

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(lastname) ||
                    TextUtils.isEmpty(email) || TextUtils.isEmpty(dob) ||
                    TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) ||
                    TextUtils.isEmpty(gender)) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Registration successful
            Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show();

            //Redirect to login
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    etDob.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
*/