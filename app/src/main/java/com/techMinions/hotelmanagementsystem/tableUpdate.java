package com.techMinions.hotelmanagementsystem;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class tableUpdate extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    Spinner noOfPeople;
    EditText date, time, comment, fname, lname, email, phone;
    DatabaseReference dbRef;
    tables_model tableModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_update);
        Button updtBookBtn = findViewById(R.id.updtBookBtn);

        noOfPeople = findViewById(R.id.noOfPeople);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        comment = findViewById(R.id.comment);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        tableModel = new tables_model();

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

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numberOfPeople, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noOfPeople.setAdapter(adapter);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Tables").child("tableData");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    noOfPeople.setSelection(adapter.getPosition(dataSnapshot.child("noOfPeople").getValue().toString()));
                    date.setText(dataSnapshot.child("date").getValue().toString());
                    time.setText(dataSnapshot.child("time").getValue().toString());
                    comment.setText(dataSnapshot.child("comments").getValue().toString());
                    fname.setText(dataSnapshot.child("fname").getValue().toString());
                    lname.setText(dataSnapshot.child("lname").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    phone.setText(dataSnapshot.child("phone").getValue().toString());

                } else {
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        updtBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Tables");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<tables_model> arr = new ArrayList();
                        ArrayList<String> arrKeys = new ArrayList();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Log.d("yyy", data.getKey());
                            arr.add(data.getValue(tables_model.class));
                            arrKeys.add(data.getKey());
//                            tables_model tb = data.getValue(tables_model.class);
                        }
                        String lastKey = arrKeys.get(arrKeys.size() - 2);
                        Log.d("zzz", lastKey);
                        Log.d("xxx", arr.get(arr.size() - 1).getComments());
                        tables_model lastRecord = arr.get(arr.size() - 2);
//
//                        if(dataSnapshot.hasChild(lastRecord.)){

                        if (dataSnapshot.hasChild(lastKey)) {
                            try {
                                tableModel.setNoOfPeople(noOfPeople.getSelectedItem().toString().trim());
                                tableModel.setDate(date.getText().toString().trim());
                                tableModel.setTime(time.getText().toString().trim());
                                tableModel.setEmail(email.getText().toString().trim());
                                tableModel.setPhone(Integer.parseInt(phone.getText().toString().trim()));
                                tableModel.setFname(fname.getText().toString().trim());
                                tableModel.setLname(lname.getText().toString().trim());
                                tableModel.setComments(comment.getText().toString().trim());

                                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Tables").child(lastKey);
                                dbRef.setValue(tableModel);


                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }

                        if (dataSnapshot.hasChild("tableData")) {
                            try {
                                tableModel.setNoOfPeople(noOfPeople.getSelectedItem().toString().trim());
                                tableModel.setDate(date.getText().toString().trim());
                                tableModel.setTime(time.getText().toString().trim());
                                tableModel.setEmail(email.getText().toString().trim());
                                tableModel.setPhone(Integer.parseInt(phone.getText().toString().trim()));
                                tableModel.setFname(fname.getText().toString().trim());
                                tableModel.setLname(lname.getText().toString().trim());
                                tableModel.setComments(comment.getText().toString().trim());

                                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Tables").child("tableData");
                                dbRef.setValue(tableModel);


                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();


                                Intent intent = new Intent(tableUpdate.this, tableBookings.class);
                                startActivity(intent);

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


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


    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        EditText time_picker = findViewById(R.id.time_picker);
        time_picker.setText(hourOfDay + ":" + minute);
    }
}