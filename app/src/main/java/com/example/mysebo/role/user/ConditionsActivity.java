package com.example.mysebo.role.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityConditionsBinding;

public class ConditionsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityConditionsBinding activityConditionsBinding;

    private Button btnSubmit;
    private TextView tvBackPages2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityConditionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_conditions);

        btnSubmit = activityConditionsBinding.btnSubmit;
        tvBackPages2 = activityConditionsBinding.tvBackPages2;

        btnSubmit.setOnClickListener(this);
        tvBackPages2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}