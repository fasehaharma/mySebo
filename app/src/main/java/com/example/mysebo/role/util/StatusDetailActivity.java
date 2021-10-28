package com.example.mysebo.role.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityStatusBinding;
import com.example.mysebo.databinding.ActivityStatusDetailBinding;
import com.example.mysebo.role.user.FirebaseUserHelper;
import com.example.mysebo.role.user.adapter.ItemStatusDetailEquiptmentAdapter;
import com.example.mysebo.role.user.model.Equipment;
import com.example.mysebo.role.user.model.Reservation;
import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StatusDetailActivity extends AppCompatActivity {

    private FirebaseUserHelper firebaseUserHelper;

    ItemStatusDetailEquiptmentAdapter itemStatusDetailEquiptmentAdapter = new ItemStatusDetailEquiptmentAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseUserHelper = new FirebaseUserHelper();

        ActivityStatusDetailBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_status_detail);

        RecyclerView rvStatusDetail = binding.rvStatusDetail;

        rvStatusDetail.setLayoutManager(new LinearLayoutManager(this));
        rvStatusDetail.setAdapter(itemStatusDetailEquiptmentAdapter);


        String reservationId = getIntent().getStringExtra("reservationId");

        firebaseUserHelper.readReservation(reservationId);
        firebaseUserHelper.getReservationMutableLiveData().observe(this, new Observer<Reservation>() {
            @Override
            public void onChanged(Reservation reservation) {
                binding.tvEvent.setText(reservation.getEventName());
                binding.tvOrganization.setText(reservation.getEventOrganization());
                binding.tvPhone.setText(reservation.getPhoneNumber());

                final Timestamp reserveDate = reservation.getDateStart();
                final Date date = reserveDate.toDate();
                String dateString = new SimpleDateFormat("dd/MM/yy").format(date);

                final Timestamp returnDate = reservation.getDateEnd();
                final Date rdate = returnDate.toDate();
                String rdateString = new SimpleDateFormat("dd/MM/yy").format(rdate);


                binding.tvReturnDate.setText(rdateString);
                binding.tvBorrowingDate.setText(dateString);


                if (reservation.getStatus() == 1){
                    binding.tvStatus.setText("REJECT");
                } else if (reservation.getStatus() == 2){
                    binding.tvStatus.setText("ACCEPT");
                } else {
                    binding.tvStatus.setText("PENDING");
                }


                itemStatusDetailEquiptmentAdapter.setEquipmentList((ArrayList<Equipment>) reservation.getEquipment());
                itemStatusDetailEquiptmentAdapter.notifyDataSetChanged();
            }
        });

    }
}