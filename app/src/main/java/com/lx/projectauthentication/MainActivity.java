package com.lx.projectauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lx.auth.update.UpdateUtils;

//import com.lx.auth.update.UpdateUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpdateUtils.init("d4525b1c14af4a44bba3eb7d3d6518aa", "0632c4d88ab0554e5dea230f2ccad396", MainActivity.class);
    }
}
