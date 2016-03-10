package com.Main.Tasker;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

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
         String dataString = workIntent.getDataString();
     }
}
