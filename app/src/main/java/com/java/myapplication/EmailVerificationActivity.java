package com.java.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EmailVerificationActivity extends AppCompatActivity {

    EditText etPassword;
    Button btnVerify;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    String username, lastname, email, gender, dob;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        etPassword = findViewById(R.id.etPassword);
        btnVerify = findViewById(R.id.btnVerify);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        username = getIntent().getStringExtra("username");
        lastname = getIntent().getStringExtra("lastname");
        email = getIntent().getStringExtra("email");
        gender = getIntent().getStringExtra("gender");
        dob = getIntent().getStringExtra("dob");

        btnVerify.setOnClickListener(v -> {
            String password = etPassword.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    user.sendEmailVerification().addOnCompleteListener(verifyTask -> {
                        if (verifyTask.isSuccessful()) {
                            Map<String, Object> userMap = new HashMap<>();
                            userMap.put("username", username);
                            userMap.put("lastname", lastname);
                            userMap.put("email", email);
                            userMap.put("gender", gender);
                            userMap.put("dob", dob);

                            db.collection("users").document(user.getUid()).set(userMap).addOnSuccessListener(unused -> {
                                Toast.makeText(this, "Registered Successfully. Please verify your email.", Toast.LENGTH_LONG).show();
                            });
                        }
                    });
                } else {
                    Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
