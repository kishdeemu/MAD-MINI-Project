package com.techMinions.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class roombooking extends AppCompatActivity{

    Button rbbtn;
    EditText chiinput, choinput, numofRooms, adlinput, chilinput, fullnin, emin, phonein;
    Spinner roomlist;
    DatabaseReference dbRef;
    room_model roomModel;
    int total;
    long diff;
    String phoneNo;
    String emailAdd;
    String date;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


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
                phoneNo = phonein.getText().toString();
                emailAdd = emin.getText().toString().trim();
                date = chiinput.getText().toString().trim();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date firstDate = null;
                try {
                    firstDate = sdf.parse(chiinput.getText().toString());
                    Date secondDate = sdf.parse(choinput.getText().toString());
                    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                    diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    Log.d("xxx", String.valueOf(diff));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


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
                    else if (!emailAdd.matches(emailPattern))
                        Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(phonein.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter phone number", Toast.LENGTH_SHORT).show();
                    else if (phoneNo.length() != 10)
                        Toast.makeText(getApplicationContext(), "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                    else {
                        if (roomlist.getSelectedItem().toString().equals("Single Room")) {
                            total = ((int) diff ) * 10500 * Integer.parseInt(numofRooms.getText().toString());
                        } else if (roomlist.getSelectedItem().toString().equals("Double Room")) {
                            total = ((int) diff ) * 14500 * Integer.parseInt(numofRooms.getText().toString());
                        } else if (roomlist.getSelectedItem().toString().equals("Triple Room")) {
                            total = ((int) diff ) * 16500 * Integer.parseInt(numofRooms.getText().toString());
                        } else if (roomlist.getSelectedItem().toString().equals("Quadruple Room")) {
                            total = ((int) diff ) * 18000 * Integer.parseInt(numofRooms.getText().toString());
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

                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();

                }

            }
        });

        ArrayAdapter<String> radp = new ArrayAdapter<String>(roombooking.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.rolist));
        radp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomlist.setAdapter(radp);


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