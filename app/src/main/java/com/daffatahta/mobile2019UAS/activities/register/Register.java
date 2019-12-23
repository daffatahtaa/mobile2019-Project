package com.daffatahta.mobile2019UAS.activities.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daffatahta.mobile2019UAS.R;
import com.daffatahta.mobile2019UAS.activities.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    Button registerButton;
    EditText EmailUser, PasswordUser;

    private static final String TAG = "";

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.regsiter_btn);
        EmailUser = findViewById(R.id.email_user);
        PasswordUser = findViewById(R.id.password_user_regis);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInput();
            }
        });
    }

    private void Register(){
        String email = EmailUser.getText().toString();
        String password = PasswordUser.getText().toString();

        checkInput();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "create user with Email Success");
                    //authSuccess(task.getResult().getUser());
                    Intent intent = new Intent(Register.this, Login.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else if (!task.isSuccessful()){
                    Log.w(TAG, "Creating user with email = Failed", task.getException());
                    Toast.makeText(Register.this, "Creating New Account Failed!", Toast.LENGTH_SHORT).show();
                }
                else if (task.getException() instanceof FirebaseAuthUserCollisionException){
                    EmailUser.setError("Email suda di pakai");
                    Toast.makeText(Register.this, "User already exist, please login instead", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkInput (){
        String email = EmailUser.getText().toString();
        String password = PasswordUser.getText().toString();
        if (TextUtils.isEmpty(email)){
            EmailUser.setError("Cannot be empty!");
            //Toast.makeText(this, "")
            return;
        }

        else if (TextUtils.isEmpty(password)){
            PasswordUser.setError("Cannot be empty!");
            return;
        }

        else{
            Register();
        }
    }

    //private void authSuccess(FirebaseUser user){
    //    String email = EmailUser.getText().toString();
    //    String password = PasswordUser.getText().toString();
    //}
}
