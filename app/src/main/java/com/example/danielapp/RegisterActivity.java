package com.example.danielapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText userInput;
    private EditText nameInput;
    private EditText lastNameInput;
    private EditText phoneInput;
    private EditText emailInput;
    private EditText passInput;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dialog = new ProgressDialog(this);

        userInput = findViewById(R.id.et_user_name);
        nameInput = findViewById(R.id.et_name);
        lastNameInput = findViewById(R.id.et_last_name);
        phoneInput = findViewById(R.id.et_phone);
        emailInput = findViewById(R.id.et_email);
        passInput = findViewById(R.id.et_password);
        EditText cPassInput = findViewById(R.id.et_c_password);
        TextView tvBack = findViewById(R.id.tv_back);
        tvBack.setOnClickListener(v -> reload(LoginActivity.class));

        Button singUp = findViewById(R.id.bt_signup);

        singUp.setOnClickListener(v -> {
            String pass = passInput.getText().toString();
            String cPass = cPassInput.getText().toString();
            if (cPass.equals(pass)) {
                setDialog();
                createAccountWithEmail(emailInput.getText().toString(), passInput.getText().toString());
            } else
                Toast.makeText(this, "Authentication failed ", Toast.LENGTH_SHORT).show();
        });

    }

    private void setDialog() {
        dialog.setMessage("Please wait while Registration");
        dialog.setTitle("Register");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void reload(Class<?> name) {
        Intent intent = new Intent(this, name);
        startActivity(intent);
        finish();
    }

    public void createAccountWithEmail(String email, String password) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        createUserData();
                    }
                }).addOnFailureListener(System.out::println);
    }

    private void createUserData() {
        Map<String, Object> user = new HashMap<>();
        user.put("userName", userInput.getText().toString());
        user.put("phone", phoneInput.getText().toString());
        user.put("name", nameInput.getText().toString());
        user.put("lastName", lastNameInput.getText().toString());
        System.out.println(user);
        FirebaseFirestore.getInstance().collection("User").document().set(user).addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                System.out.println("DocumentReference::User::success");
                dialog.dismiss();
                reload(LoginActivity.class);
            }
        }).addOnFailureListener(e -> {
            System.out.println("DocumentReference::User::failed");
            dialog.dismiss();
        });

    }
}