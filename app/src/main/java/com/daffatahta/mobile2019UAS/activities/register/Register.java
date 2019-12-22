package com.daffatahta.mobile2019UAS.activities.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.daffatahta.mobile2019UAS.R;
import com.daffatahta.mobile2019UAS.activities.login.Login;

public class Register extends AppCompatActivity {

    Button registerButton;
    EditText EmailUser, PasswordUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.regsiter_btn);
        EmailUser = findViewById(R.id.email_user_regis);
        PasswordUser = findViewById(R.id.password_user_regis);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
