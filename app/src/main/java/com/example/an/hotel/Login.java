package com.example.an.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.an.hotel.Adapter.AdminRoomList;
import com.example.an.hotel.Adapter.MainActivity;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText loginID = (EditText) findViewById(R.id.loginID);
        final EditText loginPass = (EditText) findViewById(R.id.loginPass);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Switch swAdmin = (Switch) findViewById(R.id.switchAdmin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this,Register.class);
                Login.this.startActivity(registerIntent);

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!swAdmin.isChecked()) {
                   Intent loginIntent = new Intent(Login.this, MainActivity.class);
                   Login.this.startActivity(loginIntent);
               }else
               {
                   Intent loginIntentAdmin = new Intent(Login.this, AdminRoomList.class);
                   Login.this.startActivity(loginIntentAdmin);
               }

            }
        });
    }
}
