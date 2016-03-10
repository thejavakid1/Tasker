package com.Main.Tasker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by rwr_inc on 3/9/16.
 */
public class Flagger extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent serviceIntent = new Intent(context, ScriptStart.class);
            context.startService(serviceIntent);
        }
    }
}
