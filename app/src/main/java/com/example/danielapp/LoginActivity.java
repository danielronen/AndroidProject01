package com.example.danielapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

//        if (FirebaseAuth.getInstance().getCurrentUser() != null)G
//            reload(MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        Button btLogin = findViewById(R.id.bt_login);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        TextView tvSignup = findViewById(R.id.tv_signup);
        TextView text_login = findViewById(R.id.text_login);
        ImageView flagLogo = findViewById(R.id.flag);


        flagLogo.setTranslationY(-300);
        btLogin.setTranslationX(300);
        text_login.setTranslationX(300);
        etEmail.setTranslationX(300);
        etPassword.setTranslationX(300);
        tvSignup.setTranslationX(300);


        final float alpha = 0;

        flagLogo.setAlpha(alpha);
        btLogin.setAlpha(alpha);
        text_login.setAlpha(alpha);
        etEmail.setAlpha(alpha);
        tvSignup.setAlpha(alpha);
        etEmail.setAlpha(alpha);
        etPassword.setAlpha(alpha);

        flagLogo.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        btLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        text_login.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        etEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        etPassword.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        tvSignup.animate().translationX(80).alpha(1).setDuration(1000).setStartDelay(700).start();


        tvSignup.setOnClickListener(v -> reload(RegisterActivity.class));
        btLogin.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            signInWithEmail(etEmail.getText().toString(), etPassword.getText().toString());
        });

    }

    public void reload(Class<?> name) {
        Intent intent = new Intent(this, name);
        startActivity(intent);
        finish();
    }

    public void signInWithEmail(String email, String password) {
        mAuth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        reload(MainActivity.class);
                    }
                }).addOnFailureListener(System.out::println);
    }
}