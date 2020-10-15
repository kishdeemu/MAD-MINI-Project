package com.techMinions.hotelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminTables extends AppCompatActivity {
    RecyclerView recyclerView;
    TableRecyclerViewAdapter tableRecyclerViewAdapter;
    List<tables_model> tableBookingDataList;
    DatabaseReference tbldbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tables);

        recyclerView = findViewById(R.id.tablerecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tableBookingDataList = new ArrayList<>();

        tbldbRef = FirebaseDatabase.getInstance().getReference("Tables");
        tbldbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    if(!data.hasChild("tableData")){
                        tables_model tm = data.getValue(tables_model.class);
                        tableBookingDataList.add(tm);
                    }
                    tableRecyclerViewAdapter = new TableRecyclerViewAdapter(tableBookingDataList);
                    recyclerView.setAdapter(tableRecyclerViewAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}