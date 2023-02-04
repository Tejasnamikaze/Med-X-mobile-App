package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookLabActivity extends AppCompatActivity {

    EditText ed1, ed2;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnBook, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_lab);


        ed1 = findViewById(R.id.editTextLabFullName);
        ed2 = findViewById(R.id.editTextLabfees);
        dateButton = findViewById(R.id.buttonLabDate);
        timeButton = findViewById(R.id.buttonLabTime);
        btnBook = findViewById(R.id.buttonBookLabTest);
        btnBack = findViewById(R.id.buttonBack);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);

        Intent it = getIntent();
        String fullname = it.getStringExtra("text1");
        String fees = it.getStringExtra("text2");

        ed1.setText(fullname);
        ed2.setText("Fees : " + fees + "/-");


        //datePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        //timePicker
        initTimePicker();
        timeButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        }));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookLabActivity.this, LabTestActivity.class));
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = ed1.getText().toString();
                String fees = ed2.getText().toString();
                String date = dateButton.getText().toString();
                String time = timeButton.getText().toString();


                Database3 db = new Database3(getApplicationContext(),"booklab",null,1);
                //SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                // String phone = sharedPreferences.getString("phone","").toString();

                if(db.checkBookExists(fullname,fees,date,time)==0 ){
                    db.addBook(fullname,fees,date,time);
                    Toast.makeText(getApplicationContext(),"Your lab test is booked successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent (BookLabActivity.this,HomeActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"Your lab test is already booked",Toast.LENGTH_LONG).show();

                }


            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                dateButton.setText(i2 + "/" + i1 + i);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);

    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                timeButton.setText(i + ":" + i1);
            }
        };

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);


        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }
}