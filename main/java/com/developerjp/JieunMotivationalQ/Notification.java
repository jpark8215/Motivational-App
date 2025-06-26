package com.developerjp.JieunMotivationalQ;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class Notification extends BroadcastReceiver {

    private static final String[] quotes = {
            "The only way to do great work is to love what you do.",
            "Believe you can and you're halfway there.",
            "Success is not the key to happiness. Happiness is the key to success.",
            "You are never too old to set another goal or to dream a new dream.",
            "Success is liking yourself, liking what you do, and liking how you do it.",
            "The only way to achieve the impossible is to believe it is possible. ",
            "Success is walking from failure to failure with no loss of enthusiasm.",
            "Don't count the days, make the days count.",
            "Your time is limited, so don't waste it living someone else's life.",
            "A person who never made a mistake never tried anything new.",
            "If you can dream it, you can do it.",
            "Our greatest glory is not in never falling, but in rising every time we fall.",
            "The road to success and the road to failure are almost exactly the same.",
            "Don't be afraid to give up the good to go for the great.",
            "The journey of a thousand miles begins with one step.",
            "Hardships often prepare ordinary people for an extraordinary destiny.",
            "You must do the things you think you cannot do.",
            "Keep your face always toward the sunshine—and shadows will fall behind you.",
            "Opportunities don't happen. You create them.",
            "If you want to live a happy life, tie it to a goal, not to people or things.",
            "Logic will get you from A to Z. Imagination will get you everywhere.",
            "Common sense is what tells us the earth is flat.",
            "Success usually comes to those who are too busy to be looking for it.",
            "Don't watch the clock; do what it does. Keep going.",
            "Success is not in what you have, but who you are.",
            "You have to expect things of yourself before you can do them.",
            "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart.",
            "The greatest teacher, failure is.",
            "It's not who I am underneath, but what I do that defines me.",
            "All we have to decide is what to do with the time that is given to us.",
            "Every man dies, not every man really lives.",
            "The best way to predict the future is to create it.",
            "It does not matter how slowly you go as long as you do not stop.",
            "Act as if what you do makes a difference. It does.",
            "You miss 100% of the shots you don't take.",
            "The only limit to our realization of tomorrow is our doubts of today.",
            "You don't have to be great to start, but you have to start to be great.",
            "The way to get started is to quit talking and begin doing.",
            "It always seems impossible until it's done.",
            "You are capable of more than you know.",
            "Hard work beats talent when talent doesn't work hard.",
            "Don't wait for opportunity. Create it.",
            "Success is the sum of small efforts, repeated day in and day out.",
            "The only thing standing between you and your goal is the story you keep telling yourself.",
            "Success is not the absence of failure; it's the persistence through failure.",
            "In the middle of difficulty lies opportunity.",
            "What lies behind us and what lies before us are tiny matters compared to what lies within us.",
            "Everything you've ever wanted is on the other side of fear.",
            "The best time to plant a tree was 20 years ago. The second best time is now.",
            "Don't be afraid to fail. Be afraid not to try.",
            "What you get by achieving your goals is not as important as what you become by achieving your goals.",
            "Progress doesn’t shout — it whispers in every quiet decision to try again.",
            "You are not running out of time; you are learning how to use it.",
            "The strength you seek is often built in silence and revealed in storms.",
            "Dreams grow roots when you take action, not when you wait for perfect conditions.",
            "Your pace doesn’t matter as long as you don’t lose sight of your purpose.",
            "Doubt kills more dreams than failure ever could.",
            "The future is not something you wait for — it's something you build.",
            "Let your passion speak louder than your fear.",
            "Small steps in the right direction can become the biggest journey of your life.",
            "Your story is not defined by the chapter you’re in right now.",
            "You can be both a masterpiece and a work in progress.",
            "Growth feels like breaking until it feels like becoming.",
            "Resilience is not about never falling, but about learning how to rise softer each time.",
            "Nothing changes unless you change something.",
            "Keep planting, even when the harvest feels far away.",
            "Your energy introduces you before your words ever do.",
            "Comparison steals joy; gratitude restores it.",
            "Be bold enough to be bad at something new.",
            "You can’t find your voice if you’re too busy echoing others.",
            "Failure is feedback dressed as a detour.",
            "The only permission you need to begin is your own.",
            "When it’s hard, that’s when it’s working.",
            "Don’t wait for motivation; show up and let it catch up.",
            "Your purpose won’t let you quit — it will only let you pause.",
            "Even the darkest night teaches the stars how to shine.",
            "Say yes to your next step, even if you don’t see the whole staircase.",
            "You don’t need a map — just enough courage to take the first step.",
            "The only time you truly fail is the time you stop trying.",
            "Confidence is built, not born.",
            "The fire in you is meant to light the way, not burn you out.",
            "Peace doesn’t mean perfect — it means present.",
            "Fall in love with becoming, not just arriving.",
            "You are the author — stop handing the pen to fear.",
            "Your setbacks are setups for a stronger comeback.",
            "The mountain only looks tall until you start climbing.",
            "Do it scared — courage often looks like trembling hands.",
            "You were never meant to shrink to fit a smaller version of you.",
            "Discipline is the bridge between intention and outcome.",
            "Don’t rush the process — trust the becoming.",
            "Your value is not measured by your productivity.",
            "Storms show you what you’re made of — and what you no longer need.",
            "Be gentle with yourself — growth isn’t always loud.",
            "Every day you rise is a revolution against the impossible.",
            "Let your setbacks be stepping stones, not stopping signs.",
            "What you’re building takes time because it’s meant to last.",
            "The world needs your voice, not your perfection.",
            "Success is consistency, not speed.",
            "You are not behind — you’re exactly where you grow from.",
            "Even if it’s messy, unfinished, or unsure — begin anyway.",
            "You owe it to yourself to become everything you’ve dreamed of.",
            "The world needs your voice, not your perfection."
    };

    private static final String CHANNEL_ID = "Notification";
    private static final String CHANNEL_NAME = "Daily Spark Channel";
    private static final String CHANNEL_DESCRIPTION = "Channel for Daily Spark";

    private static void showCustomNotification(Context context, String quote) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Your Daily Spark")
                .setContentText(quote)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(quote));

        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notificationManager.notify(1, builder.build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription(CHANNEL_DESCRIPTION);
            channel.enableLights(true);
            channel.enableVibration(true);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Create notification channel first
        createNotificationChannel(context);

        // Pick a random quote
        Random random = new Random();
        String randomQuote = quotes[random.nextInt(quotes.length)];

        // Show custom notification
        showCustomNotification(context, randomQuote);
    }

    public static String getRandomQuote() {
        int idx = new Random().nextInt(quotes.length);
        return quotes[idx];
    }
}
