package com.Main.Tasker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final TextView InstallLocation = (TextView)findViewById(R.id.InstallLocation);
        setSupportActionBar(toolbar);
        Button create = (Button) findViewById(R.id.Add);
        final Boolean DoesInstallExist = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Tasker").exists();
        if(DoesInstallExist){
            InstallLocation.setText(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tasker/");
        }
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!DoesInstallExist) {
                        new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tasker").mkdir();
                        InstallLocation.setText(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tasker");
                        Snackbar.make(view, "(sdcard)/Tasker folder created! Restart the app please.", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(view, "sdcard/Tasker folder already created!", Snackbar.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    InstallLocation.setText("There was a error! This app may not be compatable with this device (sorry).");
                }
            }
        });
    }
    public void Help(View v){
        Intent Help = new Intent(this, Help.class);
        this.startActivity(Help);
    }

    public void Settings(View v) {
        Intent Settings = new Intent(this, Settings.class);
        this.startActivity(Settings);
    }
}
