package com.example.an.hotel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText usrName = (EditText) findViewById(R.id.usrName);
        final EditText usrID = (EditText) findViewById(R.id.usrID);
        final EditText usrPass = (EditText) findViewById(R.id.usrPass);
        final Button bRegister = (Button) findViewById(R.id.btnRegister);
    }
}
