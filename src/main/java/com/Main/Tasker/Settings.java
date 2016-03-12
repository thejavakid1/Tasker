package com.Main.Tasker;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(! new File(this.getFilesDir()+"/Settings").exists()){
            writeTofile("true");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Switch rootOn = (Switch) findViewById(R.id.switch1);
        rootOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                writeTofile("true");
            }else{
                writeTofile("false");
            }
            }
        });
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
    public void writeTofile(String str){
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("Settings", Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
