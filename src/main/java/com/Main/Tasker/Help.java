package com.Main.Tasker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView help = (TextView)findViewById(R.id.Help);
        help.setText("Using Tasker:\n" +
                "Add Scripts to the /(Sd card)/Tasker to be launched automatic on boot.\n" +
                "Scripts are executed with su. Hence, this app requires root. Scripts are given no arguments,\n" +
                "however are not restricted in any way in comparison with other startup scripts on normal\n" +
                "linux systems.");
    }


}
