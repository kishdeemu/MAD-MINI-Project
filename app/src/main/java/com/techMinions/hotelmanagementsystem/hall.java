package com.techMinions.hotelmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class hall extends AppCompatActivity {
    Button hbtnbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);
        hbtnbook = findViewById(R.id.hbbutton);

        hbtnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myI = new Intent(hall.this, hallsbooking.class);
                startActivity(myI);
            }
        });


    }

}
