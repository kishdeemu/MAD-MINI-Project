package com.techMinions.hotelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class updateroom extends AppCompatActivity {

    Spinner rlistup;
    Button uproombtn;
    EditText chiinput, choinput, mumofroomsup, adlinput, chilinput, fullnin, emin, phonein;
    DatabaseReference dbRef;
    room_model roomModel;
    int total;

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
        ArrayAdapter<String> radp = new ArrayAdapter<String>(updateroom.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.rolist));
        radp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rlistup.setAdapter(radp);


        dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms").child("lastRoomData");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
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
                    total = (int) dataSnapshot.child("total").getValue();

                    total = dataSnapshot.



                } else {
                    Toast.makeText(getApplicationContext(), "No source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        uproombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myI5 = new Intent(updateroom.this, bookedroomdply.class);

                //Toast Message for reacting to button click
                Context context = getApplicationContext();
                CharSequence message = "booking updated";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, message, duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                toast.show();

                startActivity(myI5);

            }
        });
    }
}
