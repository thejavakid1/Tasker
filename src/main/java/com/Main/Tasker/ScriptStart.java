package com.Main.Tasker;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.renderscript.Allocation;
import android.support.design.widget.Snackbar;
import android.support.v7.app.NotificationCompat;

import java.io.IOException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ScriptStart extends IntentService{

    public ScriptStart() {
        super("ScriptStart");
    }
     @Override
     protected void onHandleIntent(Intent workIntent) {
         Notification n;
         try {
             Runtime.getRuntime().exec("su -c echo");
             n = new Notification.Builder(this)
                     .setContentTitle("Tasker:")
                     .setContentText("Starting Scripts Failed!").build();
             NotificationManager notificationManager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

             notificationManager.notify(1837, n);
         } catch (IOException e) {
             n = new Notification.Builder(this)
                     .setContentTitle("Tasker:")
                     .setContentText("Starting Scripts Failed!").build();
             NotificationManager notificationManager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

             notificationManager.notify(1837, n);
         }
         String dataString = workIntent.getDataString();
     }
}
