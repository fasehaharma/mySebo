package com.example.mysebo.role.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.mysebo.Constant;
import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityEquipmentDetailsBinding;
import com.example.mysebo.role.user.adapter.ItemEquipmentAdapter;

import java.util.Calendar;

public class EquipmentDetailsActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private final String TAG = EquipmentDetailsActivity.class.getSimpleName();

    private ActivityEquipmentDetailsBinding activityEquipmentDetailsBinding;

    private TextView tvNextPage2;
    private TextView tvBackPages;
    private Button btnBorrowDate;
    private Button btnReturnDate;
    private Button btnAddItem;


    private String sEventClub;
    private String sEventName;
    private String sStaffID;
    private String sPhone;
    private DatePickerDialog datePickerDialog;

    private RecyclerView rvItemEquipment;
    private ItemEquipmentAdapter itemEquipmentAdapter;

    private final int TYPE_BORROW_DATE = 2001;
    private final int TYPE_RETURN_DATE = 2002;
    private int type = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEquipmentDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_equipment_details);


        itemEquipmentAdapter = new ItemEquipmentAdapter();


        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(
                this, this, currentYear, 1, 1);

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
        btnAddItem = activityEquipmentDetailsBinding.btnAddEquipment;

        rvItemEquipment = activityEquipmentDetailsBinding.recyclerView;

        btnBorrowDate = activityEquipmentDetailsBinding.btnBorrowingDate;
        btnReturnDate = activityEquipmentDetailsBinding.btnReturnDate;


        rvItemEquipment.setAdapter(itemEquipmentAdapter);
        rvItemEquipment.setLayoutManager(new LinearLayoutManager(this));

        tvNextPage2.setOnClickListener(this);
        tvBackPages.setOnClickListener(this);

        btnBorrowDate.setOnClickListener(this);
        btnReturnDate.setOnClickListener(this);
        btnAddItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvNextPage2) {
            Bundle bundle = new Bundle();

            Intent intent = new Intent(this, ConditionsActivity.class);
            bundle.putParcelableArrayList(Constant.EQUIPMENT_LIST,itemEquipmentAdapter.getEquipmentList());
            intent.putExtras(bundle);
            bundle.putString(Constant.EVENT_CLUB,sEventClub);
            bundle.putString(Constant.EVENT_NAME,sEventName);
            bundle.putString(Constant.STAFF_ID,sStaffID);
            bundle.putString(Constant.PHONE_NUMBER,sPhone);

            startActivity(intent);

        } else if (v == tvBackPages) {
            finish();
        } else if (v == btnBorrowDate) {
                type = TYPE_BORROW_DATE;
                datePickerDialog.show();
        } else if (v == btnReturnDate) {
            type = TYPE_RETURN_DATE;
            datePickerDialog.show();
        }else if(v == btnAddItem){
            itemEquipmentAdapter.addItem();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d(TAG, "onDateSet: " + year);

        if (type == TYPE_BORROW_DATE) {
            btnBorrowDate.setText(year + "-" + month + "-" + dayOfMonth);
        } else if (type == TYPE_RETURN_DATE) {
            btnReturnDate.setText(year + "-" + month + "-" + dayOfMonth);
        }
    }
}