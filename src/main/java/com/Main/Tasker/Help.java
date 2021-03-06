package com.Main.Tasker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView help = (TextView)findViewById(R.id.Help);
        help.setText("Using Tasker:\n" +
                "Add Scripts to the /(Sd card)/Tasker to be launched automatic on boot. Scripts are executed with su. Hence, this app requires root. Scripts can not be given arguments," +
                " however are not restricted in any other way. To allow execution of scripts, make sure" +
                "that you allow root on boot, or it will not allow the scripts to execute. For SuperSu that is in Settings --> Enable su during boot. This may differ"
                +" for other root permission apps. Scripts are executed with the sh command! Make sure that your file/script support this."
        );
    }


}
