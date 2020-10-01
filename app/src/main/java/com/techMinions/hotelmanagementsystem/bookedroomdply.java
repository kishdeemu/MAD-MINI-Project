package com.techMinions.hotelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bookedroomdply extends AppCompatActivity {
    Button bordup;
    Button bordcal;
    Button borcom;

    TextView checkIn, checkOut, roomType, numOfRooms, numOfAdults, numOfChildren, fullName, email, phone, total;
    DatabaseReference dbRef, delRef;
    String lastKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookedroomdply);


        checkIn = findViewById(R.id.checkIn);
        checkOut = findViewById(R.id.checkOut);
        roomType = findViewById(R.id.roomType);
        numOfRooms = findViewById(R.id.numOfRooms);
        numOfAdults = findViewById(R.id.numOfAdults);
        numOfChildren = findViewById(R.id.numOfChildren);
        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        total = findViewById(R.id.textView30);


        bordup = findViewById(R.id.brup);
        bordcal = findViewById(R.id.brcanl);
        borcom = findViewById(R.id.brcon);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms").child("lastRoomData");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    Log.d("dbsnp", String.valueOf(dataSnapshot));
                    checkIn.setText(dataSnapshot.child("checkIn").getValue().toString());
                    checkOut.setText(dataSnapshot.child("checkOut").getValue().toString());
                    email.setText(dataSnapshot.child("emin").getValue().toString());
                    fullName.setText(dataSnapshot.child("fullnin").getValue().toString());
                    numOfAdults.setText(dataSnapshot.child("numofAdults").getValue().toString());
                    numOfChildren.setText(dataSnapshot.child("numofChildren").getValue().toString());
                    numOfRooms.setText(dataSnapshot.child("numofRooms").getValue().toString());
                    phone.setText(dataSnapshot.child("phonein").getValue().toString());
                    roomType.setText(dataSnapshot.child("roomlist").getValue().toString());
                    total.setText(dataSnapshot.child("total").getValue().toString());

                } else {
                    Toast.makeText(getApplicationContext(), "No source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        bordcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                delRef = FirebaseDatabase.getInstance().getReference().child("Rooms");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> arrkeys = new ArrayList<>();
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            arrkeys.add(data.getKey());
                        }

                        lastKey = arrkeys.get(arrkeys.size()-2);

                        if(dataSnapshot.hasChild(lastKey)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms").child(lastKey);
                            dbRef.removeValue();
                        }

                        if(dataSnapshot.hasChild("lastRoomData")){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms").child("lastRoomData");
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "Booking Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "No data to delete.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent intent = new Intent(bookedroomdply.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        bordup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookedroomdply.this, updateroom.class);
                startActivity(intent);
            }
        });

       // bordcal.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View view) {
       //         Intent myI4 = new Intent(bookedroomdply.this, MainActivity.class);

                //Toast Message for reacting to button click
       //         Context context = getApplicationContext();
       //         CharSequence message = "booking canceled";
       //         int duration = Toast.LENGTH_SHORT;
       //         Toast toast = Toast.makeText(context, message, duration);
       //         toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
       //         toast.show();
       //         startActivity(myI4);
       //     }
       // });
        borcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(bookedroomdply.this);
                builder.setTitle("CONFIRMED!");
                builder.setMessage("Your booking has been confirmed.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent myI6 = new Intent(bookedroomdply.this, MainActivity.class);
                        startActivity(myI6);
                    }
                });
                dialog = builder.create();
                dialog.show();

            }
        });
    }
}