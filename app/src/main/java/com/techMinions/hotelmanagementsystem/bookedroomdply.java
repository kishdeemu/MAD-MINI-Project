package com.techMinions.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class bookedroomdply extends AppCompatActivity {
    Button bordup;
    Button bordcal;
    Button borcom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookedroomdply);

        bordup = findViewById(R.id.brup);
        bordcal = findViewById(R.id.brcanl);
        borcom = findViewById(R.id.brcon);
    }

    @Override
    protected void onResume() {
        super.onResume();

        bordup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myI3 = new Intent(bookedroomdply.this, updateroom.class);
                startActivity(myI3);
            }
        });
        bordcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myI4 = new Intent(bookedroomdply.this, MainActivity.class);

                //Toast Message for reacting to button click
                Context context = getApplicationContext();
                CharSequence message = "booking canceled";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, message, duration);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                toast.show();
                startActivity(myI4);
            }
        });
        borcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myI6 = new Intent(bookedroomdply.this, MainActivity.class);
                startActivity(myI6);
            }
        });
    }
}