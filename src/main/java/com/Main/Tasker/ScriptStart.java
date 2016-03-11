package com.Main.Tasker;

import android.app.AlertDialog;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.renderscript.Allocation;
import android.support.design.widget.Snackbar;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

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
             File Dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Tasker");
             String text = "";

             if(Dir.exists()){
                 try{
                     String CR = "\n";
                     Process p = Runtime.getRuntime().exec("su");
                     text += " Got su.";
                     DataOutputStream os = new DataOutputStream(p.getOutputStream());
                     os.writeBytes("cd " + Dir.getAbsolutePath()+CR);
                     text += " Entering (sdcard)/Tasker.";
                     os.writeBytes("for file in $(pwd)/*; do sh $file; done"+CR);
                     text += " Executing scripts.";
                     os.writeBytes("exit");
                     text += " Done. exiting su...";
                     showNotification(text, notificationID());
                 }catch(IOException e) {
                     showNotification("There was a error!", notificationID());
                 }
             }else{
                 try {
                     Dir.mkdir();
                 }catch(Exception e){}
             }
         } else {
             System.err.println("Broken Enviroment!!");
         }
     }
    public void showNotification(String text, int notificationID) {
        Resources r = getResources();
        Intent targetIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this).setSmallIcon(android.R.drawable.ic_dialog_alert).setContentTitle("Script").setContentText(text).setContentIntent(contentIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, notification);
    }
    public int notificationID(){
        long time = new Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        int notificationID = Integer.valueOf(last4Str);
        return notificationID;
    }
}
