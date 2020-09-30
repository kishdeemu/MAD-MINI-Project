package com.techMinions.hotelmanagementsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class tables extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Spinner noOfPeople;
    EditText date, time, comments, fname, lname, email, phone;
    DatabaseReference dbRef;
    tables_model tables_model;
    Button bookBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        noOfPeople = findViewById(R.id.noOfPeople);
        date = findViewById(R.id.date_picker);
        time = findViewById(R.id.time_picker);
        comments = findViewById(R.id.comment);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        bookBtn = findViewById(R.id.bookBtn);

        tables_model = new tables_model();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Tables");
                try {
                    if (TextUtils.isEmpty(noOfPeople.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(), "Please select no. of people attending", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(date.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please select the date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(time.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please select the time", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(fname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter first name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(lname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter last name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(phone.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter phone", Toast.LENGTH_SHORT).show();
                    else {
                        tables_model.setNoOfPeople(noOfPeople.getSelectedItem().toString());
                        tables_model.setDate(date.getText().toString());
                        tables_model.setTime(time.getText().toString());
                        tables_model.setComments(comments.getText().toString());
                        tables_model.setFname(fname.getText().toString());
                        tables_model.setLname(lname.getText().toString());
                        tables_model.setEmail(email.getText().toString());
                        tables_model.setPhone(Integer.parseInt(phone.getText().toString()));

                        dbRef.push().setValue(tables_model);
                        dbRef.child("tableData").setValue(tables_model);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                        Intent intent = new Intent(tables.this, tableBookings.class);
                        startActivity(intent);


                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Contact Number is invalid", Toast.LENGTH_SHORT).show();
                }




            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numberOfPeople, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noOfPeople.setAdapter(adapter);



    }

    public void clearControls() {
        date.setText("");
        time.setText("");
        comments.setText("");
        fname.setText("");
        lname.setText("");
        email.setText("");
        phone.setText("");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDate = DateFormat.getDateInstance().format(c.getTime());

        EditText date_picker = findViewById(R.id.date_picker);
        date_picker.setText(currentDate);
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        EditText time_picker = findViewById(R.id.time_picker);
        time_picker.setText(hourOfDay + ":" + minute);
    }


}