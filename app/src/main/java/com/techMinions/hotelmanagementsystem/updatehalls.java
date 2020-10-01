package com.techMinions.hotelmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class updatehalls extends AppCompatActivity {

    Spinner spinnerhty;
    EditText updtedate, utimet, updname, upemail, updphone, upnoofPeople;
    Button updabutton;
    DatabaseReference dbRef;
    hall_model hallModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatehalls);

        spinnerhty = findViewById(R.id.spinnerhty);
        updtedate = findViewById(R.id.updtedate);
        utimet = findViewById(R.id.utimet);
        updname = findViewById(R.id.updname);
        upemail = findViewById(R.id.upemail);
        updphone = findViewById(R.id.updphone);
        upnoofPeople = findViewById(R.id.noOfPeople);

        updabutton = findViewById(R.id.updabutton);

        hallModel = new hall_model();


        final ArrayAdapter<String> radp = new ArrayAdapter<String>(updatehalls.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.halllist));
        radp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerhty.setAdapter(radp);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Halls").child("lastHallBooking");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    spinnerhty.setSelection(radp.getPosition(dataSnapshot.child("hallType").getValue().toString()));
                    updtedate.setText(dataSnapshot.child("date").getValue().toString());
                    utimet.setText(dataSnapshot.child("time").getValue().toString());
                    updname.setText(dataSnapshot.child("fullName").getValue().toString());
                    upemail.setText(dataSnapshot.child("email").getValue().toString());
                    updphone.setText(dataSnapshot.child("phone").getValue().toString());
                    upnoofPeople.setText(dataSnapshot.child("numpeople").getValue().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Halls");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> arrKeys = new ArrayList();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            arrKeys.add(data.getKey());
                        }
                        String lastKey = arrKeys.get(arrKeys.size() - 2);

                        if (dataSnapshot.hasChild(lastKey)) {
                            try {
                                hallModel.setHallType(spinnerhty.getSelectedItem().toString().trim());
                                hallModel.setDate(updtedate.getText().toString().trim());
                                hallModel.setTime(utimet.getText().toString().trim());
                                hallModel.setEmail(upemail.getText().toString().trim());
                                hallModel.setPhone(updphone.getText().toString().trim());
                                hallModel.setFullName(updname.getText().toString().trim());
                                hallModel.setEmail(upemail.getText().toString().trim());
                                hallModel.setNumpeople(upnoofPeople.getText().toString().trim());

                                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Halls").child(lastKey);
                                dbRef.setValue(hallModel);


                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }

                        if (dataSnapshot.hasChild("lastHallBooking")) {
                            try {
                                hallModel.setNumpeople(spinnerhty.getSelectedItem().toString().trim());
                                hallModel.setDate(updtedate.getText().toString().trim());
                                hallModel.setTime(utimet.getText().toString().trim());
                                hallModel.setEmail(upemail.getText().toString().trim());
                                hallModel.setPhone(updphone.getText().toString().trim());
                                hallModel.setFullName(updname.getText().toString().trim());
                                hallModel.setEmail(upemail.getText().toString().trim());
                                hallModel.setNumpeople(upnoofPeople.getText().toString().trim());

                                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Halls").child("lastHallBooking");
                                dbRef.setValue(hallModel);


                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();


                                Intent intent = new Intent(updatehalls.this, bookedhallsdply.class);
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
}