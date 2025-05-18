package com.developerjp.JieunMotivationalQ;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapterPeople.ItemClickListener {
    private static final String TAG = "MainActivity";

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    Log.d(TAG, "Notification permission granted");
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    // Schedule notification after permission is granted
                    NotificationScheduler.scheduleNotification(MainActivity.this);
                    // Show immediate notification
                    new Notification().onReceive(MainActivity.this, null);
                } else {
                    Log.d(TAG, "Notification permission denied");
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            });

    private MyRecyclerViewAdapterPeople adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate called");

        // Initialize the notification button
        MaterialButton postNotification = findViewById(R.id.postNotification);
        if (postNotification != null) {
            Log.d(TAG, "Notification button found");
            postNotification.setOnClickListener(view -> {
                Log.d(TAG, "Notification button clicked");
                handleNotificationButtonClick();
            });
        } else {
            Log.e(TAG, "Notification button not found in layout");
        }

        // Data for the RecyclerView
        ArrayList<String> peopleNames = new ArrayList<>();
        peopleNames.add("Success in Action");
        peopleNames.add("Proverbs");
        peopleNames.add("Feel it!");
        peopleNames.add("Woman Power");
        peopleNames.add("Confucius");
        peopleNames.add("Believe it!");
        peopleNames.add("Through Storm");

        ArrayList<String> peoplePictures = new ArrayList<>();
        peoplePictures.add("success");
        peoplePictures.add("proverbs");
        peoplePictures.add("feel_it");
        peoplePictures.add("woman_power");
        peoplePictures.add("confucius");
        peoplePictures.add("albert_einstein");
        peoplePictures.add("storm");

        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapterPeople(this, peopleNames, peoplePictures);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        MobileAds.initialize(this, initializationStatus -> {
            Log.d(TAG, "MobileAds initialization status: " + initializationStatus);
        });
    }

    private void handleNotificationButtonClick() {
        Log.d(TAG, "Handling notification button click");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Requesting notification permission");
                activityResultLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
            } else {
                Log.d(TAG, "Notification permission already granted");
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                // Schedule notification
                NotificationScheduler.scheduleNotification(this);
                // Show immediate notification
                new Notification().onReceive(this, null);
            }
        } else {
            Log.d(TAG, "Android version below Tiramisu, no permission needed");
            // For Android 12 and below, no permission needed
            NotificationScheduler.scheduleNotification(this);
            new Notification().onReceive(this, null);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent myIntent = new Intent(MainActivity.this, ShowQuotes.class);
        myIntent.putExtra("person", adapter.getItem(position));
        MainActivity.this.startActivity(myIntent);
    }
}
