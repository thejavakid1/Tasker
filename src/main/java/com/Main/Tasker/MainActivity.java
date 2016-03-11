package com.Main.Tasker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final TextView InstallLocation = (TextView)findViewById(R.id.InstallLocation);
        setSupportActionBar(toolbar);
        Button fab = (Button) findViewById(R.id.Add);
        Boolean DoesInstallExist = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Tasker").exists();
        if(DoesInstallExist){
            InstallLocation.setText(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tasker/");
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Tasker").mkdir();
                    InstallLocation.setText(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tasker/");
                }catch(Exception e){
                    InstallLocation.setText("There was a error! This app may not be compatable with this device (sorry).");
                }
            }
        });
    }
    public void Help(View v){
        Intent myIntent = new Intent(this, Help.class);
        this.startActivity(myIntent);
    }

}
