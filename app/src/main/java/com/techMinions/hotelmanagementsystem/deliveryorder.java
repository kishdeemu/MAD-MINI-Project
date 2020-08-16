package com.techMinions.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class deliveryorder extends AppCompatActivity {
    Spinner foditm;
    Spinner ncity;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryorder);

        foditm = findViewById(R.id.fooditemlist);
        ArrayAdapter<String> fditm = new ArrayAdapter<>(deliveryorder.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.fooditem));
        fditm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foditm.setAdapter(fditm);

        ncity = findViewById(R.id.nearcitylist);
        ArrayAdapter<String> ncty = new ArrayAdapter<>(deliveryorder.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nearcity));
        ncty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ncity.setAdapter(ncty);

        nextBtn=(Button) findViewById(R.id.plcodrBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextintent = new Intent(deliveryorder.this,deliveryconfirm.class);
                startActivity(nextintent);
            }
        });
    }
}