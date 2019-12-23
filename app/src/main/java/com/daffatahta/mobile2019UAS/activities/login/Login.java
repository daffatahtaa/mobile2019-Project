package com.daffatahta.mobile2019UAS.activities.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daffatahta.mobile2019UAS.MainActivity;
import com.daffatahta.mobile2019UAS.R;
import com.daffatahta.mobile2019UAS.activities.register.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private TextView register;
    private EditText email, password;
    private Button login;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.registertext);
        email = findViewById(R.id.emailUser);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                Login.this.startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(Login.this, MainActivity.class);
                //Login.this.startActivity(intent);
                pass();
                signIn();
            }
        });
    }

    private void signIn() {
        String uEmail = email.getText().toString();
        String uPass = password.getText().toString();


        mAuth.signInWithEmailAndPassword(uEmail, uPass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Sign in failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void pass() {
        String uEmail = email.getText().toString();
        String uPass = password.getText().toString();
        if (TextUtils.isEmpty(uEmail)) {
            email.setError("Cannot be empty!");
            return;
        }
        if (TextUtils.isEmpty(uPass)) {
            password.setError("Cannot be empty!");
            return;
        }

    }
}
