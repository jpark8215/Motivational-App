package com.example.JieunMotivationalQ;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapterPeople.ItemClickListener {

    private MyRecyclerViewAdapterPeople adapter;
    private String dailyQuoteMessage;
    private String dailyQuotePerson;
    private Integer cacheExpiration;

    private TextView txtTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.txtTitle);


        // data to populate the RecyclerView with
        ArrayList<String> peopleNames = new ArrayList<>();
        peopleNames.add("Feel it!");
        peopleNames.add("Woman Power");
        peopleNames.add("Gary Vaynerchuk");
        peopleNames.add("Arnold Schwarzenegger");
        peopleNames.add("Confucius");
        peopleNames.add("Albert Einstein");

        // data to populate the RecyclerView with
        ArrayList<String> peoplePictures = new ArrayList<>();
        peoplePictures.add("feel_it");
        peoplePictures.add("woman_power");
        peoplePictures.add("gary_vaynerchuk");
        peoplePictures.add("arnold_schwarzenegger");
        peoplePictures.add("confucius");
        peoplePictures.add("albert_einstein");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapterPeople(this, peopleNames, peoplePictures);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        // Creates a button that mimics a crash when pressed
        Button crashButton = new Button(this);
        crashButton.setText("Test Crash");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));


    }


    @Override
    public void onItemClick(View view, int position) {
        Intent myIntent = new Intent(MainActivity.this, ShowQuotes.class);
        //Passes through the value of which item was clicked
        myIntent.putExtra("person", adapter.getItem(position));
        MainActivity.this.startActivity(myIntent);

    }

}
