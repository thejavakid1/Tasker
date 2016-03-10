package com.Main.Tasker;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Environment;
import android.renderscript.Allocation;
import android.support.design.widget.Snackbar;
import android.support.v7.app.NotificationCompat;

import java.io.File;
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
         String state = Environment.getExternalStorageState();
         if (Environment.MEDIA_MOUNTED.equals(state)) {
             File Dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Tasker/Scripts");
             if(Dir.exists()){

             }else{
             }
         } else {

         }
         try {
             Runtime.getRuntime().exec("su -c for file in ./*; do ./$file; done");

         } catch (IOException e) {

         }
         String dataString = workIntent.getDataString();
     }
}
