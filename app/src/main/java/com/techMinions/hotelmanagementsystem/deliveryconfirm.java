package com.techMinions.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class deliveryconfirm extends AppCompatActivity {
    Button updateBtn;
    Button deleteBtn;
    Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryconfirm);

        updateBtn = (Button) findViewById(R.id.deliodrupdtBtn);
        deleteBtn = (Button) findViewById(R.id.deliodrdelBtn);
        confirmBtn = (Button) findViewById(R.id.deliodrcfBtn);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(deliveryconfirm.this,deliveryupdate.class);
                startActivity(update);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(deliveryconfirm.this,MainActivity.class);
                startActivity(delete);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent conf = new Intent(deliveryconfirm.this,MainActivity.class);
                startActivity(conf);
            }
        });
    }
}