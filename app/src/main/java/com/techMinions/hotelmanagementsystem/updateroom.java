package com.techMinions.hotelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class updateroom extends AppCompatActivity {

    Spinner rlistup;
    Button uproombtn;
    EditText chiinput, choinput, mumofroomsup, adlinput, chilinput, fullnin, emin, phonein;
    DatabaseReference dbRef;
    DatabaseReference dbRef3;
    room_model roomModel;
    String roomList;
    int total;

    String phoneNo;
    String emailAdd;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateroom);

        chiinput = findViewById(R.id.chiinput);
        choinput = findViewById(R.id.choinput);
        mumofroomsup = findViewById(R.id.mumofroomsup);
        adlinput = findViewById(R.id.adlinput);
        chilinput = findViewById(R.id.chilinput);
        fullnin = findViewById(R.id.fullnin);
        emin = findViewById(R.id.emin);
        phonein = findViewById(R.id.phonein);

        uproombtn = findViewById(R.id.rupbtn);

        roomModel = new room_model();

        rlistup = findViewById(R.id.roomlistup);

        final ArrayAdapter<String> radp = new ArrayAdapter<String>(updateroom.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.rolist));
        radp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rlistup.setAdapter(radp);


        DatabaseReference dbRef2 = FirebaseDatabase.getInstance().getReference().child("Rooms").child("lastRoomData");
        dbRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    rlistup.setSelection(radp.getPosition(dataSnapshot.child("roomlist").getValue().toString()));
                    chiinput.setText(dataSnapshot.child("checkIn").getValue().toString());
                    choinput.setText(dataSnapshot.child("checkOut").getValue().toString());
                    emin.setText(dataSnapshot.child("emin").getValue().toString());
                    fullnin.setText(dataSnapshot.child("fullnin").getValue().toString());
                    adlinput.setText(dataSnapshot.child("numofAdults").getValue().toString());
                    chilinput.setText(dataSnapshot.child("numofChildren").getValue().toString());
                    mumofroomsup.setText(dataSnapshot.child("numofRooms").getValue().toString());
                    phonein.setText(dataSnapshot.child("phonein").getValue().toString());
                    //rlistup.((dataSnapshot.child("roomlist").getValue().toString()));
                    //total.setText(dataSnapshot.child("total").getValue().toString());
                    roomList = dataSnapshot.child("roomlist").getValue().toString();

                    if (roomList.equals("Single Room")) {
                        total = 10500 * Integer.parseInt(mumofroomsup.getText().toString());
                    } else if (roomList.equals("Double Room")) {
                        total = 14500 * Integer.parseInt(mumofroomsup.getText().toString());
                    } else if (roomList.equals("Triple Room")) {
                        total = 16500 * Integer.parseInt(mumofroomsup.getText().toString());
                    } else if (roomList.equals("Quadruple Room")) {
                        total = 18000 * Integer.parseInt(mumofroomsup.getText().toString());
                    }



                } else {
                    Toast.makeText(getApplicationContext(), "No source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        uproombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNo = phonein.getText().toString();
                emailAdd = emin.getText().toString().trim();
                dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> arrkeys = new ArrayList<>();
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            arrkeys.add(data.getKey());
                        }

                        String lastKey = arrkeys.get(arrkeys.size()-2);

                        if(dataSnapshot.hasChild(lastKey)){
                            try{

                                if (TextUtils.isEmpty(chiinput.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter check-in date", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(choinput.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter check-out date", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(rlistup.getSelectedItem().toString()))
                                    Toast.makeText(getApplicationContext(), "Please select a room type", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(mumofroomsup.getText().toString()))
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

                                    if (rlistup.getSelectedItem().toString().equals("Single Room")) {
                                        total = 10500 * Integer.parseInt(mumofroomsup.getText().toString());
                                    } else if (rlistup.getSelectedItem().toString().equals("Double Room")) {
                                        total = 14500 * Integer.parseInt(mumofroomsup.getText().toString());
                                    } else if (rlistup.getSelectedItem().toString().equals("Triple Room")) {
                                        total = 16500 * Integer.parseInt(mumofroomsup.getText().toString());
                                    } else if (rlistup.getSelectedItem().toString().equals("Quadruple Room")) {
                                        total = 18000 * Integer.parseInt(mumofroomsup.getText().toString());
                                    }


                                    roomModel.setCheckIn(chiinput.getText().toString().trim());
                                    roomModel.setCheckOut(choinput.getText().toString().trim());
                                    roomModel.setEmin(emin.getText().toString().trim());
                                    roomModel.setFullnin(fullnin.getText().toString().trim());
                                    roomModel.setNumofAdults(adlinput.getText().toString().trim());
                                    roomModel.setNumofChildren(chilinput.getText().toString().trim());
                                    roomModel.setNumofRooms(mumofroomsup.getText().toString().trim());
                                    roomModel.setPhonein(phonein.getText().toString().trim());
                                    roomModel.setRoomlist(rlistup.getSelectedItem().toString().trim());

                                    roomModel.setTotal(total);

                                    dbRef3 = FirebaseDatabase.getInstance().getReference().child("Rooms").child(lastKey);
                                    dbRef3.setValue(roomModel);

                                    Intent myI5 = new Intent(updateroom.this, bookedroomdply.class);
                                    startActivity(myI5);

                                    Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();

                                }

                            }catch(NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact number", Toast.LENGTH_SHORT).show();
                            }
                        }


                        if(dataSnapshot.hasChild("lastRoomData")) {
                            try{
                                if (TextUtils.isEmpty(chiinput.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter check-in date", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(choinput.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter check-out date", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(rlistup.getSelectedItem().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please select a room type", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(mumofroomsup.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter no. of rooms", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(adlinput.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter no.of adults", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(chilinput.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter no. of children", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(fullnin.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter full name", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(emin.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                                else if (!emailAdd.matches(emailPattern));
                                    //Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(phonein.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter phone number", Toast.LENGTH_SHORT).show();
                                else if (phoneNo.length() != 10);
                                    //Toast.makeText(getApplicationContext(), "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                                else {

                                    if (rlistup.getSelectedItem().toString().equals("Single Room")) {
                                        total = 10500 * Integer.parseInt(mumofroomsup.getText().toString());
                                    } else if (rlistup.getSelectedItem().toString().equals("Double Room")) {
                                        total = 14500 * Integer.parseInt(mumofroomsup.getText().toString());
                                    } else if (rlistup.getSelectedItem().toString().equals("Triple Room")) {
                                        total = 16500 * Integer.parseInt(mumofroomsup.getText().toString());
                                    } else if (rlistup.getSelectedItem().toString().equals("Quadruple Room")) {
                                        total = 18000 * Integer.parseInt(mumofroomsup.getText().toString());
                                    }


                                    roomModel.setCheckIn(chiinput.getText().toString().trim());
                                    roomModel.setCheckOut(choinput.getText().toString().trim());
                                    roomModel.setEmin(emin.getText().toString().trim());
                                    roomModel.setFullnin(fullnin.getText().toString().trim());
                                    roomModel.setNumofAdults(adlinput.getText().toString().trim());
                                    roomModel.setNumofChildren(chilinput.getText().toString().trim());
                                    roomModel.setNumofRooms(mumofroomsup.getText().toString().trim());
                                    roomModel.setPhonein(phonein.getText().toString().trim());
                                    roomModel.setRoomlist(rlistup.getSelectedItem().toString().trim());

                                    roomModel.setTotal(total);

                                    dbRef3 = FirebaseDatabase.getInstance().getReference().child("Rooms").child("lastRoomData");
                                    dbRef3.setValue(roomModel);

                                    Intent intent = new Intent(updateroom.this, bookedroomdply.class);
                                    startActivity(intent);

                                    //Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                                }
                            }catch(NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact number", Toast.LENGTH_SHORT).show();
                            }
                        }else {
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



//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        uproombtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //Toast Message for reacting to button click
//                Context context = getApplicationContext();
//                CharSequence message = "booking updated";
//                int duration = Toast.LENGTH_SHORT;
//                Toast toast = Toast.makeText(context, message, duration);
//                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
//                toast.show();
//
//
//            }
//        });
//    }
}
