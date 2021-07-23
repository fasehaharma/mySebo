package com.example.mysebo.role.util;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityStatusBinding;

public class StatusActivity extends AppCompatActivity {

    private ActivityStatusBinding activityStatusBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
    }
}