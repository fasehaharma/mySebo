package com.example.mysebo.role.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysebo.Constant;
import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityReservationDetailBinding;
import com.google.android.material.textfield.TextInputEditText;

public class ReservationDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityReservationDetailBinding activityReservationDetailBinding;

    private static final String TAG = "Reservation";

    private TextView tvNextPage;
    private TextView tvBack;

    private TextInputEditText tietEventClub;
    private TextInputEditText tietEventName;
    private TextInputEditText tietStaffID;
    private TextInputEditText tietPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_detail);

        activityReservationDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_reservation_detail);

        tvNextPage = activityReservationDetailBinding.tvNextPage;
        tvBack = activityReservationDetailBinding.tvBack;

        tietEventClub = activityReservationDetailBinding.tietEventClub;
        tietEventName = activityReservationDetailBinding.tietEventName;
        tietStaffID = activityReservationDetailBinding.tietStaffId;
        tietPhone = activityReservationDetailBinding.tietPhone;

        tvNextPage.setOnClickListener(this);
        tvBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tvNextPage) {

            String sEventClub = tietEventClub.getText().toString();
            String sEventName = tietEventName.getText().toString();
            String sStaffID = tietStaffID.getText().toString();
            String sPhone = tietPhone.getText().toString();

            Log.d(TAG, "onClick: "+ sEventClub);
            Log.d(TAG, "onClick: "+ sEventName);
            Log.d(TAG, "onClick: "+ sStaffID);
            Log.d(TAG, "onClick: "+ sPhone);


            if(TextUtils.isEmpty(sEventClub)){
                Toast.makeText(ReservationDetailActivity.this, "Please fill in", Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(sEventName)){
                Toast.makeText(ReservationDetailActivity.this, "Please fill in", Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(sStaffID)){
                Toast.makeText(ReservationDetailActivity.this, "Please fill in", Toast.LENGTH_LONG).show();
            }else if (TextUtils.isEmpty(sPhone)){
                Toast.makeText(ReservationDetailActivity.this, "Please fill in", Toast.LENGTH_LONG).show();
            }else{

                Intent intent = new Intent(this, EquipmentDetailsActivity.class);
                intent.putExtra(Constant.EVENT_ORGANIZATION, sEventClub);
                intent.putExtra(Constant.EVENT_NAME, sEventName);
                intent.putExtra(Constant.STAFF_ID, sStaffID);
                intent.putExtra(Constant.PHONE_NUMBER, sPhone);
                startActivity(intent);
            }
        } else if (v == tvBack) {
            finish();
        }
    }
}