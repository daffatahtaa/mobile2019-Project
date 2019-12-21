package com.daffatahta.mobile2019UAS.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daffatahta.mobile2019UAS.MainScreen;
import com.daffatahta.mobile2019UAS.R;

public class Login extends AppCompatActivity{

    private TextView register;
    private EditText email,password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.registertext);
        email = findViewById(R.id.emailUser);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this,"Logged in",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this,MainScreen.class);
                Login.this.startActivity(intent);
            }
        });
    }
}
