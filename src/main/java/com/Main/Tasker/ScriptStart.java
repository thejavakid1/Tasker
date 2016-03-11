package com.Main.Tasker;

import android.app.AlertDialog;
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
import android.widget.Toast;

import java.io.DataOutputStream;
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
             File Dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Tasker");
             String text = "";
             if(Dir.exists()){
                 try{
                     Process p = Runtime.getRuntime().exec("su");
                     p.waitFor();
                     DataOutputStream os = new DataOutputStream(p.getOutputStream());
                     os.writeBytes("su -c \"for file in " + Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tasker/*; do chmod 777 .$file; done\"");
                     text += "made files executable.";
                     p.waitFor();
                     os.writeBytes("su -c \"for file in "+Environment.getExternalStorageDirectory().getAbsolutePath()+"/Tasker/*; do .$file; done\"");
                     p.waitFor();
                     Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                 }catch(IOException e){
                     text = e.getMessage();
                     Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                 } catch (InterruptedException e) {
                     text = e.getMessage();
                     Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                 }
             }else{
                 try {
                     Dir.mkdir();
                 }catch(Exception e){}
             }
         } else {

         }
     }
}
