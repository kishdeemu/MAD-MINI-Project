package com.techMinions.hotelmanagementsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bookedhallsdply extends AppCompatActivity {
    Button uphbook;
    Button delhbook;
    Button cofhbook;

    EditText Numpeople, Halltype, Date, Time, Fullname, Email, Phone;
    DatabaseReference dbref;
    hall_model hallModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookedhallsdply);

        Halltype = findViewById(R.id.sht);
        Date = findViewById(R.id.cdate);
        Numpeople = findViewById(R.id.ctimed2);
        Time = findViewById(R.id.ctimed);
        Fullname = findViewById(R.id.difullname);
        Email = findViewById(R.id.discfemail);
        Phone = findViewById(R.id.disphone);

        uphbook = findViewById(R.id.update);
        delhbook = findViewById(R.id.delete);
        cofhbook = findViewById(R.id.confirmbooking);

        hallModel = new hall_model();

        dbref = FirebaseDatabase.getInstance().getReference().child("Halls").child("lastHallBooking");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {

                    Fullname.setText(dataSnapshot.child("fullName").getValue().toString());
                    Email.setText(dataSnapshot.child("email").getValue().toString());
                    Phone.setText(dataSnapshot.child("phone").getValue().toString());
                    Time.setText(dataSnapshot.child("time").getValue().toString());
                    Numpeople.setText(dataSnapshot.child("numpeople").getValue().toString());
                    Date.setText(dataSnapshot.child("date").getValue().toString());


                }else{
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}