package com.techMinions.hotelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class roombooking extends AppCompatActivity{

    Button rbbtn;
    EditText chiinput, choinput, numofRooms, adlinput, chilinput, fullnin, emin, phonein;
    Spinner roomlist;
    DatabaseReference dbRef;
    room_model roomModel;
    int total;

//    int in_year, in_month, in_day, out_year, out_month, out_day;
//    DatePickerDialog.OnDateSetListener in_dateListner, out_dateListner;
//    int DATE_PICKER_IN = 0;
//    int DATE_PICKER_OUT= 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roombooking);
        chiinput = findViewById(R.id.chiinput);
        choinput = findViewById(R.id.choinput);
        roomlist = findViewById(R.id.roomlist);
        numofRooms = findViewById(R.id.numofRooms);
        adlinput = findViewById(R.id.adlinput);
        chilinput = findViewById(R.id.chilinput);
        fullnin = findViewById(R.id.fullnin);
        emin = findViewById(R.id.emin);
        phonein = findViewById(R.id.phonein);

        roomModel = new room_model();

        rbbtn = findViewById(R.id.rbbtn);

        rbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms");
                try {
                    if (TextUtils.isEmpty(chiinput.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter check-in date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(choinput.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter check-out date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(roomlist.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(), "Please select a room type", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(numofRooms.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter no. of rooms", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(adlinput.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter no.of adults", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(chilinput.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter no. of children", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(fullnin.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter full name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(emin.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(phonein.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter phone number", Toast.LENGTH_SHORT).show();
                    else {
                        if (roomlist.getSelectedItem().toString().equals("Single Room")) {
                            total = 10500 * Integer.parseInt(numofRooms.getText().toString());
                        } else if (roomlist.getSelectedItem().toString().equals("Double Room")) {
                            total = 14500 * Integer.parseInt(numofRooms.getText().toString());
                        } else if (roomlist.getSelectedItem().toString().equals("Triple Room")) {
                            total = 16500 * Integer.parseInt(numofRooms.getText().toString());
                        } else if (roomlist.getSelectedItem().toString().equals("Quadruple Room")) {
                            total = 18000 * Integer.parseInt(numofRooms.getText().toString());
                        }

                        roomModel.setCheckIn(chiinput.getText().toString().trim());
                        roomModel.setCheckOut(choinput.getText().toString().trim());
                        roomModel.setRoomlist(roomlist.getSelectedItem().toString().trim());
                        roomModel.setNumofRooms(numofRooms.getText().toString().trim());
                        roomModel.setNumofAdults(adlinput.getText().toString().trim());
                        roomModel.setNumofChildren(chilinput.getText().toString().trim());
                        roomModel.setFullnin(fullnin.getText().toString().trim());
                        roomModel.setEmin(emin.getText().toString().trim());
                        roomModel.setPhonein(phonein.getText().toString());
                        roomModel.setTotal(total);

                        dbRef.push().setValue(roomModel);

                        dbRef.child("lastRoomData").setValue(roomModel);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                        Intent intent = new Intent(roombooking.this, bookedroomdply.class);
                        startActivity(intent);


                        //Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();

                }

                //Intent myI2 = new Intent(roombooking.this, bookedroomdply.class);

                //Toast Message for reacting to button clicked
                //Context context = getApplicationContext();
                //CharSequence message = "Syncing with Database...";
                //int duration = Toast.LENGTH_SHORT;
                //Toast toast = Toast.makeText(context, message, duration);
                //toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                //toast.show();
                //startActivity(myI2);
            }
        });

//        chiinput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment datePicker = new DatePickerFragment();
//                datePicker.show(getSupportFragmentManager(), "date picker");
//            }
//        });
//
//        choinput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment datePicker2 = new DatePickerFragment();
//                datePicker2.show(getSupportFragmentManager(), "date picker");
//            }
//        });


        ArrayAdapter<String> radp = new ArrayAdapter<String>(roombooking.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.rolist));
        radp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomlist.setAdapter(radp);

//        in_dateListner = new DatePickerDialog.OnDateSetListener() {
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                Calendar c = Calendar.getInstance();
//                c.set(Calendar.YEAR, year);
//                c.set(Calendar.MONTH, month);
//                c.set(Calendar.DAY_OF_MONTH, day);
//                String currentDate = DateFormat.getDateInstance().format(c.getTime());
//                chiinput.setText(currentDate);
//
//            }
//
//
//        };
//
//        out_dateListner = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                Calendar c = Calendar.getInstance();
//                c.set(Calendar.YEAR, year);
//                c.set(Calendar.MONTH, month);
//                c.set(Calendar.DAY_OF_MONTH, day);
//                String currentDate = DateFormat.getDateInstance().format(c.getTime());
//                choinput.setText(currentDate);
//            }
//        };

    }
    public void clearControls() {
        chiinput.setText("");
        choinput.setText("");
        numofRooms.setText("");
        adlinput.setText("");
        chilinput.setText("");
        fullnin.setText("");
        emin.setText("");
        phonein.setText("");
    }
}