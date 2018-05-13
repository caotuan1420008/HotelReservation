package com.example.an.hotel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.an.hotel.Adapter.RoomListActivity;

import java.util.Calendar;

public class FinderActivity extends AppCompatActivity {
    private EditText txtDateFrom;
    private EditText txtDateTo;
    private Calendar myCalendar;
    private int year;
    private int month;
    private int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder);
        txtDateFrom = (EditText) findViewById(R.id.dateFrom);
        txtDateTo = (EditText) findViewById(R.id.dateTO);

        txtDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v,txtDateFrom);
            }
        });
        txtDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v,txtDateTo);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent roomIntent = new Intent(FinderActivity.this,RoomListActivity.class);
                FinderActivity.this.startActivity(roomIntent);
            }
        });
    }
    public void setDate(View v,final EditText ed) {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthofyear, int dayofmonth) {
                ed.setText(dayofmonth + "-" + monthofyear + "-" + year);
            }
        }, year, month, day);
        dpd.show();
    }
}

