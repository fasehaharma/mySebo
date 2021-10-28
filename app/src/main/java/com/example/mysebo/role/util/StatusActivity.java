package com.example.mysebo.role.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mysebo.LoginActivity;
import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityStatusBinding;
import com.example.mysebo.role.user.FirebaseUserHelper;
import com.example.mysebo.role.user.MainActivity;
import com.example.mysebo.role.user.ReservationDetailActivity;
import com.example.mysebo.role.user.UserProfileActivity;
import com.example.mysebo.role.user.adapter.ReservationAdapter;
import com.example.mysebo.role.user.model.Reservation;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class StatusActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityStatusBinding activityStatusBinding;

    private Button btnHome;
    private Button btnLogout;

    private RecyclerView rvStatus;
    private FirebaseUserHelper firebaseUserHelper = new FirebaseUserHelper();
    private ReservationAdapter reservationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityStatusBinding = DataBindingUtil.setContentView(this, R.layout.activity_status);

        btnHome = activityStatusBinding.btnHomePages;
        btnLogout = activityStatusBinding.btnLogout2;

        rvStatus = activityStatusBinding.rvStatus;

        reservationAdapter = new ReservationAdapter();

        rvStatus.setAdapter(reservationAdapter);
        rvStatus.setLayoutManager(new LinearLayoutManager(this));

        firebaseUserHelper.readReservationList();
        firebaseUserHelper.getReservationListMutableLiveData().observe(this, new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                Log.d("asset", "onChanged: " + reservations.size());



                reservationAdapter.setReservationList(reservations);
                reservationAdapter.notifyDataSetChanged();
            }
        });


        btnHome.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut(); //logout

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClick(View v) {
        if (v == btnLogout) {
            logout();
        } else if (v == btnHome) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}