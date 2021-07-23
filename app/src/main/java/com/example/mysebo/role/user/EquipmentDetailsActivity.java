package com.example.mysebo.role.user;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mysebo.Constant;
import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityEquipmentDetailsBinding;

public class EquipmentDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = EquipmentDetailsActivity.class.getSimpleName();

    private ActivityEquipmentDetailsBinding activityEquipmentDetailsBinding;

    private TextView tvNextPage2;
    private TextView tvBackPages;

    private String sEventClub;
    private String sEventName;
    private String sStaffID;
    private String sPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEquipmentDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_equipment_details);

        Bundle extras = getIntent().getExtras();

         sEventClub = extras.getString(Constant.EVENT_CLUB);
         sEventName = extras.getString(Constant.EVENT_NAME);
         sStaffID = extras.getString(Constant.STAFF_ID);
         sPhone = extras.getString(Constant.PHONE_NUMBER);

        Log.d(TAG, "onCreate: " + sEventClub);
        Log.d(TAG, "onCreate: " + sEventName);
        Log.d(TAG, "onCreate: " + sStaffID);
        Log.d(TAG, "onCreate: " + sPhone);

        tvNextPage2 = activityEquipmentDetailsBinding.tvNextPage2;
        tvBackPages = activityEquipmentDetailsBinding.tvBackPages;

        tvNextPage2.setOnClickListener(this);
        tvBackPages.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tvNextPage2) {
            Intent intent = new Intent(this, ConditionsActivity.class);
            startActivity(intent);

        } else if (v == tvBackPages) {
            finish();
        }
    }
}