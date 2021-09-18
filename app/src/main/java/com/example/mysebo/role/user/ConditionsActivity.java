package com.example.mysebo.role.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mysebo.Constant;
import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityConditionsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConditionsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityConditionsBinding activityConditionsBinding;

    private Button btnSubmit;
    private TextView tvBackPages2;

    private String sEventOrganization;
    private String sEventName;
    private String sStaffID;
    private String sPhone;
    private String sDateStart;
    private String sDateEnd;

    private Date dDateStart;
    private Date dDateEnd;


    private final String TAG = ConditionsActivity.class.getSimpleName();

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth fAuth;
    private ArrayList<Parcelable> equipmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityConditionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_conditions);

        btnSubmit = activityConditionsBinding.btnSubmit;
        tvBackPages2 = activityConditionsBinding.tvBackPages2;

        firebaseFirestore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        Bundle extras = getIntent().getExtras();

        sEventOrganization = extras.getString(Constant.EVENT_ORGANIZATION);
        sEventName = extras.getString(Constant.EVENT_NAME);
        sStaffID = extras.getString(Constant.STAFF_ID);
        sPhone = extras.getString(Constant.PHONE_NUMBER);
        sDateStart = extras.getString(Constant.DATE_START);
        sDateEnd = extras.getString(Constant.DATE_END);
        equipmentList = this.getIntent().getExtras().getParcelableArrayList(Constant.EQUIPMENT_LIST);


        Log.d(TAG, "onCreate: " + sEventOrganization);
        Log.d(TAG, "onCreate: " + sEventName);
        Log.d(TAG, "onCreate: " + sStaffID);
        Log.d(TAG, "onCreate: " + sPhone);

        Log.d(TAG, "onCreate:" + sDateStart);
        Log.d(TAG, "onCreate:" + sDateEnd);
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            dDateStart = format.parse(sDateStart);
            dDateEnd = format.parse(sDateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        btnSubmit.setOnClickListener(this);
        tvBackPages2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSubmit){
            String uid = fAuth.getCurrentUser().getUid();
            CollectionReference equipmentReservation = firebaseFirestore.collection("EquipmentReservation");

            Map<String, Object> newData = new HashMap<>();

            newData.put("id",uid);
            newData.put("dateEnd",dDateEnd);
            newData.put("dateStart",dDateStart);
            newData.put("equipment",equipmentList);
            newData.put("eventName",sEventName);
            newData.put("eventOrganization",sEventOrganization);
            newData.put("phoneNumber", sPhone);

            equipmentReservation.document().set(newData);
        }
    }
}