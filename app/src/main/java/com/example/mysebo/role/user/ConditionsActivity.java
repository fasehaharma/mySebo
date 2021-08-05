package com.example.mysebo.role.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mysebo.Constant;
import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityConditionsBinding;
import com.example.mysebo.role.user.model.Equipment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConditionsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityConditionsBinding activityConditionsBinding;

    private Button btnSubmit;
    private TextView tvBackPages2;

    private String sEventClub;
    private String sEventName;
    private String sStaffID;
    private String sPhone;

    private final String TAG = ConditionsActivity.class.getSimpleName();

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityConditionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_conditions);

        btnSubmit = activityConditionsBinding.btnSubmit;
        tvBackPages2 = activityConditionsBinding.tvBackPages2;

        firebaseFirestore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        Bundle extras = getIntent().getExtras();

        sEventClub = extras.getString(Constant.EVENT_CLUB);
        sEventName = extras.getString(Constant.EVENT_NAME);
        sStaffID = extras.getString(Constant.STAFF_ID);
        sPhone = extras.getString(Constant.PHONE_NUMBER);
        List<Equipment> equipmentList = this.getIntent().getExtras().getParcelableArrayList(Constant.EQUIPMENT_LIST);


        Log.d(TAG, "onCreate: " + sEventClub);
        Log.d(TAG, "onCreate: " + sEventName);
        Log.d(TAG, "onCreate: " + sStaffID);
        Log.d(TAG, "onCreate: " + sPhone);

        btnSubmit.setOnClickListener(this);
        tvBackPages2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSubmit){
            String uid = fAuth.getCurrentUser().getUid();
            CollectionReference equipmentReservation = firebaseFirestore.collection("EquipmentReservation");
            equipmentReservation.document().set();

            Map<String, Object> newData = new HashMap<>();
            newData.put("id",fAuth.getCurrentUser().getUid());
            newData.put("user",user);
            newData.put("dateEnd",12);
            newData.put("dateStart",August);

        }

    }
}