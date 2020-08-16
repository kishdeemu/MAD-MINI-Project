package com.techMinions.hotelmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class tableBookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_bookings);

        Button tblConBtn = findViewById(R.id.tblConBtn);
        Button tblDltBtn = findViewById(R.id.tblDltBtn);
        Button updtBookBtn = findViewById(R.id.updtBookBtn);

        tblConBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tableBookings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tblDltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tableBookings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        updtBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tableBookings.this, tableUpdate.class);
                startActivity(intent);
            }
        });
    }
}