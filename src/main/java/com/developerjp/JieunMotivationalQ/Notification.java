package com.developerjp.JieunMotivationalQ;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.Manifest;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class Notification extends BroadcastReceiver {

    private static final String[] quotes = {
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Believe you can and you're halfway there. - Theodore Roosevelt",
            "Success is not the key to happiness. Happiness is the key to success. - Albert Schweitzer",
            "You are never too old to set another goal or to dream a new dream. - C.S. Lewis",
            "Success is liking yourself, liking what you do, and liking how you do it. - Maya Angelou",
            "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "Success is walking from failure to failure with no loss of enthusiasm. - Winston S. Churchill",
            "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart. - Roy T. Bennett",
            "Don't count the days, make the days count. - Muhammad Ali",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "What lies behind us and what lies before us are tiny matters compared to what lies within us. - Ralph Waldo Emerson",
            "A person who never made a mistake never tried anything new. - Albert Einstein",
            "If you can dream it, you can do it. - Walt Disney",
            "Our greatest glory is not in never falling, but in rising every time we fall. - Confucius",
            "The road to success and the road to failure are almost exactly the same. - Colin R. Davis",
            "Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle. - Christian D. Larson",
            "Don't be afraid to give up the good to go for the great. - John D. Rockefeller",
            "The journey of a thousand miles begins with one step. - Lao Tzu",
            "The only thing standing between you and your dream is the will to try and the belief that it is actually possible. - Joel Brown",
            "Hardships often prepare ordinary people for an extraordinary destiny. - C.S. Lewis",
            "You must do the things you think you cannot do. - Eleanor Roosevelt",
            "Keep your face always toward the sunshine—and shadows will fall behind you. - Walt Whitman",
            "I can't change the direction of the wind, but I can adjust my sails to always reach my destination. - Jimmy Dean",
            "Opportunities don't happen. You create them. - Chris Grosser",
            "What you get by achieving your goals is not as important as what you become by achieving your goals. - Zig Ziglar",
            "If you want to live a happy life, tie it to a goal, not to people or things. - Albert Einstein",
            "Logic will get you from A to Z. Imagination will get you everywhere. - Albert Einstein",
            "Common sense is what tells us the earth is flat. - Stuart Chase",
            "Believe you can and you're halfway there. - Theodore Roosevelt",
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Your time is limited, don't waste it living someone else's life. - Steve Jobs",
            "You are never too old to set another goal or to dream a new dream. - C.S. Lewis",
            "Don't watch the clock; do what it does. Keep going. - Sam Levenson",
            "Success is not in what you have, but who you are. - Bo Bennett",
            "Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful. - Albert Schweitzer",
            "Believe in yourself, take on your challenges, dig deep within yourself to conquer fears. - Chantal Sutherland",
            "You have to expect things of yourself before you can do them. - Michael Jordan",
            "Keep your face always toward the sunshine—and shadows will fall behind you. - Walt Whitman",
            "I can't change the direction of the wind, but I can adjust my sails to always reach my destination. - Jimmy Dean",
            "Opportunities don't happen. You create them. - Chris Grosser",
            "What you get by achieving your goals is not as important as what you become by achieving your goals. - Zig Ziglar",
            "If you want to live a happy life, tie it to a goal, not to people or things. - Albert Einstein",
            "Logic will get you from A to Z. Imagination will get you everywhere. - Albert Einstein",
            "Common sense is what tells us the earth is flat. - Stuart Chase"

    };

    private static final String CHANNEL_ID = "Notification";
    private static boolean isNotificationScheduled = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Pick a random quote
        Random random = new Random();
        String randomQuote = quotes[random.nextInt(quotes.length)];

        // Schedule daily notifications (only if not already scheduled)
        if (!isNotificationScheduled) {
            NotificationScheduler.scheduleNotification(context);
            isNotificationScheduled = true;
        }

        // Show custom notification
        showCustomNotification(context, randomQuote);
    }

    private static void showCustomNotification(Context context, String quote) {
        // Create a notification channel
        createNotificationChannel(context);

        // Build and display the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Daily Quote")
                .setContentText(quote)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Handle the case where permission is not granted.
            return;
        }
        notificationManager.notify(0, builder.build());
    }

    private static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Daily Quote Channel";
            String description = "Channel for Daily Quotes";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
