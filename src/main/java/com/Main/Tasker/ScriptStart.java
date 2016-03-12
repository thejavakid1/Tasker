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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
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
             if(Dir.exists()){
                 try{
                     String CR = "\n";
                     DataOutputStream os;
                     Process p = Runtime.getRuntime().exec("su");
                     os = new DataOutputStream(p.getOutputStream());
                     os.writeBytes("cd " + Dir.getAbsolutePath()+CR);
                     os.writeBytes("for file in $(pwd)/*; do sh $file; done"+CR);
                     os.writeBytes("exit" +CR);
                     os.flush();
                     os.close();
                     showNotification("attempted to execute scripts with no errors.", "Scripts launched",notificationID());
                 }catch(IOException e) {
                     showNotification("There was a error!", "Script launching failed", notificationID());
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
    public void showNotification(String text, String title, int notificationID) {
        Resources r = getResources();
        Intent targetIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this).setSmallIcon(android.R.drawable.ic_dialog_info).setContentTitle(title).setContentText(text).setContentIntent(contentIntent).build();
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
    public String readfile() {
        File file = new File(this.getFilesDir(), "Settings");
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
            return text.toString();
        } catch (IOException e) {
            //You'll need to add proper error handling here
        }
        return null;
    }
}
