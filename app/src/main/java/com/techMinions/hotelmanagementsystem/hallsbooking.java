package com.techMinions.hotelmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hallsbooking extends AppCompatActivity {
    DatabaseReference dbRef;
    EditText booking, timed, emailAddress, fullName, phone, noOfPeople;
    Button booknb;
    Spinner halllist;
    hall_model hall_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hallsbooking);

        booking = findViewById(R.id.booking);
        timed = findViewById(R.id.timed);
        emailAddress = findViewById(R.id.emailAddress);
        fullName = findViewById(R.id.fullName);
        phone = findViewById(R.id.phone);
        noOfPeople = findViewById(R.id.noOfPeople);
        booknb = findViewById(R.id.bookBtn);
        halllist = findViewById(R.id.halllist);

        hall_model = new hall_model();

        booknb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Halls");
                try {
                    if (TextUtils.isEmpty(noOfPeople.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter No. of People", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(booking.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Booking", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(timed.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Time", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(emailAddress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter EmailAddress", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(fullName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter FullName", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(phone.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter PhoneNumber", Toast.LENGTH_SHORT).show();
                    else {
                        hall_model.setNumpeople(noOfPeople.getText().toString().trim());
                        hall_model.setDate(booking.getText().toString().trim());
                        hall_model.setTime(timed.getText().toString().trim());
                        hall_model.setHallType(halllist.getSelectedItem().toString().trim());
                        hall_model.setEmail(emailAddress.getText().toString().trim());
                        hall_model.setFullName(fullName.getText().toString().trim());
                        hall_model.setPhone(phone.getText().toString().trim());

                        //Insert into the data base
                        dbRef.push().setValue(hall_model);
                        dbRef.child("lastHallBooking").setValue(hall_model);
                        //feedback to the user via a Toast
                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();



                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();

                }

                Intent intent = new Intent(hallsbooking.this, bookedhallsdply.class);
                startActivity(intent);

            }
        });

        ArrayAdapter<String> radp = new ArrayAdapter<String>(hallsbooking.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.halllist));
        radp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        halllist.setAdapter(radp);
    }
}