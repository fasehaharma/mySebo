package com.example.mysebo.role.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityReservationDetailBinding;

public class ReservationDetailActivity extends AppCompatActivity {

    private ActivityReservationDetailBinding activityReservationDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_detail);
    }
}