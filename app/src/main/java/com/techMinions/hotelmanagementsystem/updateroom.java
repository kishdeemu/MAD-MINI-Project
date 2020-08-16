package com.techMinions.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class updateroom extends AppCompatActivity {

    Spinner rlistup;
    Button uproombtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateroom);

        uproombtn = findViewById(R.id.rupbtn);

        rlistup = findViewById(R.id.roomlistup);
        ArrayAdapter<String> radp = new ArrayAdapter<String>(updateroom.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.rolist));
        radp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rlistup.setAdapter(radp);
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
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                toast.show();

                startActivity(myI5);

            }
        });
    }
}
