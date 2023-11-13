package com.example.JieunMotivationalQ;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapterPeople.ItemClickListener {

    private MyRecyclerViewAdapterPeople adapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Initialize Firebase and Crashlytics
//        FirebaseApp.initializeApp(this);
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
//
//        // Add a button for testing crashes
//        Button crashButton = new Button(this);
//        crashButton.setText("Test Crash");
//        crashButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                throw new RuntimeException("Test Crash"); // Force a crash
//            }
//        });
//        addContentView(crashButton, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));

        // Initialize AdMob
        MobileAds.initialize(this, initializationStatus -> {
            // AdMob initialization is complete.
        });


        // Data for the RecyclerView
        ArrayList<String> peopleNames = new ArrayList<>();
        peopleNames.add("Feel it!");
        peopleNames.add("Woman Power");
        peopleNames.add("Confucius");
        peopleNames.add("Believe it!");
        //peopleNames.add("Gary Vaynerchuk");
        //peopleNames.add("Arnold Schwarzenegger");

        ArrayList<String> peoplePictures = new ArrayList<>();
        peoplePictures.add("feel_it");
        peoplePictures.add("woman_power");
        peoplePictures.add("confucius");
        peoplePictures.add("albert_einstein");
        //peoplePictures.add("gary_vaynerchuk");
        //peoplePictures.add("arnold_schwarzenegger");

        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapterPeople(this, peopleNames, peoplePictures);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent myIntent = new Intent(MainActivity.this, ShowQuotes.class);
        myIntent.putExtra("person", adapter.getItem(position));
        MainActivity.this.startActivity(myIntent);
    }
}
