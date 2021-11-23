package com.example.mysebo.role.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysebo.Constant;
import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityEquipmentDetailsBinding;
import com.example.mysebo.role.user.adapter.ItemEquipmentAdapter;
import com.example.mysebo.role.user.model.Equipment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EquipmentDetailsActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private final String TAG = EquipmentDetailsActivity.class.getSimpleName();

    private ActivityEquipmentDetailsBinding activityEquipmentDetailsBinding;
    private FirebaseUserHelper firebaseUserHelper = new FirebaseUserHelper();

    private TextView tvNextPage2;
    private TextView tvBackPages;
    private Button btnBorrowDate;
    private Button btnReturnDate;


    private String sEventClub;
    private String sEventName;
    private String sStaffID;
    private String sPhone;
    private String sLetter;
    private String sID;


    private String sDateStart;
    private String sDateEnd;

    private DatePickerDialog datePickerDialog;

    private RecyclerView rvItemEquipment;
    private ItemEquipmentAdapter itemEquipmentAdapter;

    private final int TYPE_BORROW_DATE = 2001;
    private final int TYPE_RETURN_DATE = 2002;
    private int type = 0;
    private Button btnChooseAsset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEquipmentDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_equipment_details);

        itemEquipmentAdapter = new ItemEquipmentAdapter();



        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(
                this, this, currentYear, currentMonth, currentDay);

        Bundle extras = getIntent().getExtras();

        sEventClub = extras.getString(Constant.EVENT_ORGANIZATION);
        sEventName = extras.getString(Constant.EVENT_NAME);
        sStaffID = extras.getString(Constant.STAFF_ID);
        sPhone = extras.getString(Constant.PHONE_NUMBER);
        sLetter = extras.getString(Constant.LETTER_PATH);
        sID = extras.getString(Constant.ID_PATH);


        Log.d(TAG, "onCreate: " + sEventClub);
        Log.d(TAG, "onCreate: " + sEventName);
        Log.d(TAG, "onCreate: " + sStaffID);
        Log.d(TAG, "onCreate: " + sPhone);
        Log.d(TAG, "onCreate: " + sLetter);
        Log.d(TAG, "onCreate: " + sID);

        tvNextPage2 = activityEquipmentDetailsBinding.tvNextPage2;
        tvBackPages = activityEquipmentDetailsBinding.tvBackPages;

        rvItemEquipment = activityEquipmentDetailsBinding.recyclerView;

        btnBorrowDate = activityEquipmentDetailsBinding.btnBorrowingDate;
        btnReturnDate = activityEquipmentDetailsBinding.btnReturnDate;

        btnChooseAsset = activityEquipmentDetailsBinding.btnChooseAsset;


        rvItemEquipment.setAdapter(itemEquipmentAdapter);
        rvItemEquipment.setLayoutManager(new LinearLayoutManager(this));

        tvNextPage2.setOnClickListener(this);
        tvBackPages.setOnClickListener(this);

        btnBorrowDate.setOnClickListener(this);
        btnReturnDate.setOnClickListener(this);
        btnChooseAsset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tvNextPage2) {

            Bundle bundle = new Bundle();
            if(!TextUtils.isEmpty(sDateStart) && !TextUtils.isEmpty(sDateEnd)) {


                Intent intent = new Intent(this, ConditionsActivity.class);
                bundle.putParcelableArrayList(Constant.EQUIPMENT_LIST, itemEquipmentAdapter.getEquipmentList());
                bundle.putString(Constant.EVENT_ORGANIZATION, sEventClub);
                bundle.putString(Constant.EVENT_NAME, sEventName);
                bundle.putString(Constant.STAFF_ID, sStaffID);
                bundle.putString(Constant.PHONE_NUMBER, sPhone);
                bundle.putString(Constant.LETTER_PATH, sLetter);
                bundle.putString(Constant.ID_PATH, sID);

                bundle.putString(Constant.DATE_START, sDateStart);
                bundle.putString(Constant.DATE_END, sDateEnd);

                intent.putExtras(bundle);

                startActivity(intent);
            }else {
                Toast.makeText(EquipmentDetailsActivity.this, "Please Select Date", Toast.LENGTH_LONG).show();
            }

        } else if (v == tvBackPages) {
            finish();
        } else if (v == btnBorrowDate) {
                type = TYPE_BORROW_DATE;
                datePickerDialog.show();
        } else if (v == btnReturnDate) {
            type = TYPE_RETURN_DATE;
            datePickerDialog.show();
        } else if(v == btnChooseAsset){
            Intent intent = new Intent(this,ChooseAssetActivity.class);
            startActivityForResult(intent,2002);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK &&  requestCode ==2002){
            String equipmentName = data.getStringExtra("equipmentName");
            String id = data.getStringExtra("id");
            itemEquipmentAdapter.addItem(id,equipmentName);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d(TAG, "onDateSet: " + year);

        if (type == TYPE_BORROW_DATE) {
            sDateStart =year + "-" + month + "-" + dayOfMonth;
            btnBorrowDate.setText(sDateStart);
        } else if (type == TYPE_RETURN_DATE) {
            sDateEnd =year + "-" + month + "-" + dayOfMonth;
            btnReturnDate.setText(sDateEnd);
        }
    }
}