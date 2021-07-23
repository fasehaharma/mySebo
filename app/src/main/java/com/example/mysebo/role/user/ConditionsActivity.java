package com.example.mysebo.role.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityConditionsBinding;

public class ConditionsActivity extends AppCompatActivity {

    private ActivityConditionsBinding activityConditionsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);
    }
}