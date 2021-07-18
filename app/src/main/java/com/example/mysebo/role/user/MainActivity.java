package com.example.mysebo.role.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mysebo.LoginActivity;
import com.example.mysebo.R;
import com.example.mysebo.RegisterActivity;
import com.example.mysebo.databinding.ActivityMainBinding;
import com.example.mysebo.role.util.StatusActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding activityMainBinding;
    private Button btnLogout;
    private Button btnUpdateUser;
    private Button btnReservation;
    private Button btnStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        btnLogout = activityMainBinding.btnLogout;
        btnUpdateUser = activityMainBinding.btnUpdateUser;
        btnReservation = activityMainBinding.btnReservation;
        btnStatus = activityMainBinding.btnStatus;


        btnLogout.setOnClickListener(this);
        btnUpdateUser.setOnClickListener(this);
        btnReservation.setOnClickListener(this);
        btnStatus.setOnClickListener(this);

    }

    public void logout() {
        FirebaseAuth.getInstance().signOut(); //logout

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogout) {
            logout();
        } else if (v == btnUpdateUser) {
            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
            startActivity(intent);
        } else if (v == btnStatus) {
            Intent intent = new Intent(getApplicationContext(), StatusActivity.class);
            startActivity(intent);
        } else if (v == btnReservation) {
            Intent intent = new Intent(getApplicationContext(), ReservationDetailActivity.class);
            startActivity(intent);
        }
    }
}