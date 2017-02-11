package com.Main.Tasker;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.io.File;
import java.io.IOException;

public class Settings extends AppCompatActivity {
    public File TaskFolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ToggleButton toggleRoot = (ToggleButton) findViewById(R.id.toggleRoot);
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            TaskFolder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tasker");
            if (TaskFolder.exists()) {
                if (new File(TaskFolder.getPath() + "/.noRoot").exists()) {
                    toggleRoot.setChecked(true);
                } else {
                    toggleRoot.setChecked(false);
                }
            }
        }
        toggleRoot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean toggle) {
                if (toggle) {
                    try {
                        new File(TaskFolder.getPath() + "/.noRoot").createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    new File(TaskFolder.getPath() + "/.noRoot").delete();
                }
            }
        });
    }


}
