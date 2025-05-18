package com.developerjp.JieunMotivationalQ;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

public class NotificationScheduler {
    private static final String TAG = "NotificationScheduler";

    public static void scheduleNotification(Context context) {
        try {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager == null) {
                Log.e(TAG, "AlarmManager is null");
                return;
            }

            // Set the alarm to start at 11:00 AM
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 11);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            // If the scheduled time has already passed today, move to the next day
            if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

            // Create a new PendingIntent
            Intent intent = new Intent(context, Notification.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    getPendingIntentFlags()
            );

            // Cancel any existing alarms
            alarmManager.cancel(pendingIntent);

            // Check if we can schedule exact alarms
            boolean canScheduleExactAlarms = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                canScheduleExactAlarms = alarmManager.canScheduleExactAlarms();
            }

            // Set the alarm to trigger once per day at the specified time
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    if (canScheduleExactAlarms) {
                        alarmManager.setRepeating(
                                AlarmManager.RTC_WAKEUP,
                                calendar.getTimeInMillis(),
                                AlarmManager.INTERVAL_DAY,
                                pendingIntent
                        );
                        Log.d(TAG, "Exact repeating alarm scheduled for: " + calendar.getTime());
                    } else {
                        // Fallback to inexact alarm if exact alarms are not allowed
                        alarmManager.setInexactRepeating(
                                AlarmManager.RTC_WAKEUP,
                                calendar.getTimeInMillis(),
                                AlarmManager.INTERVAL_DAY,
                                pendingIntent
                        );
                        Log.d(TAG, "Inexact repeating alarm scheduled for: " + calendar.getTime());
                    }
                } catch (SecurityException e) {
                    Log.e(TAG, "SecurityException while scheduling alarm: " + e.getMessage(), e);
                    // Fallback to inexact alarm
                    alarmManager.setInexactRepeating(
                            AlarmManager.RTC_WAKEUP,
                            calendar.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY,
                            pendingIntent
                    );
                    Log.d(TAG, "Fallback to inexact repeating alarm scheduled for: " + calendar.getTime());
                }
            } else {
                try {
                    alarmManager.setRepeating(
                            AlarmManager.RTC_WAKEUP,
                            calendar.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY,
                            pendingIntent
                    );
                    Log.d(TAG, "Repeating alarm scheduled for: " + calendar.getTime());
                } catch (SecurityException e) {
                    Log.e(TAG, "SecurityException while scheduling alarm: " + e.getMessage(), e);
                    // Fallback to inexact alarm
                    alarmManager.setInexactRepeating(
                            AlarmManager.RTC_WAKEUP,
                            calendar.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY,
                            pendingIntent
                    );
                    Log.d(TAG, "Fallback to inexact repeating alarm scheduled for: " + calendar.getTime());
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error scheduling notification: " + e.getMessage(), e);
        }
    }

    private static int getPendingIntentFlags() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE;
        } else {
            return PendingIntent.FLAG_UPDATE_CURRENT;
        }
    }
}
