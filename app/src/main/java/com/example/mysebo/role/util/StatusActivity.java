package com.example.mysebo.role.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mysebo.LoginActivity;
import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityStatusBinding;
import com.example.mysebo.role.user.MainActivity;
import com.example.mysebo.role.user.ReservationDetailActivity;
import com.example.mysebo.role.user.UserProfileActivity;
import com.google.firebase.auth.FirebaseAuth;

public class StatusActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityStatusBinding activityStatusBinding;

    private Button btnHome;
    private Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityStatusBinding = DataBindingUtil.setContentView(this, R.layout.activity_status);

        btnHome = activityStatusBinding.btnHomePages;
        btnLogout = activityStatusBinding.btnLogout2;

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