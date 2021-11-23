package com.example.mysebo.role.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button btnUploadLetter;
    private Button btnUploadID;
    private boolean isUploadedLetter = false;
    private boolean isUploadedID = false;

    private String letterPath;
    private String idPath;

    private TextInputEditText tietEventClub;
    private TextInputEditText tietEventName;
    private TextInputEditText tietStaffID;
    private TextInputEditText tietPhone;

    private FirebaseUserHelper firebaseUserHelper = new FirebaseUserHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_detail);

        activityReservationDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_reservation_detail);

        tvNextPage = activityReservationDetailBinding.tvNextPage;
        tvBack = activityReservationDetailBinding.tvBack;
        btnUploadLetter = activityReservationDetailBinding.btnUploadLetter;
        btnUploadID = activityReservationDetailBinding.btnUploadId;

        tietEventClub = activityReservationDetailBinding.tietEventClub;
        tietEventName = activityReservationDetailBinding.tietEventName;
        tietStaffID = activityReservationDetailBinding.tietStaffId;
        tietPhone = activityReservationDetailBinding.tietPhone;

        tvNextPage.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        btnUploadLetter.setOnClickListener(this);
        btnUploadID.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tvNextPage) {

            String sEventClub = tietEventClub.getText().toString();
            String sEventName = tietEventName.getText().toString();
            String sStaffID = tietStaffID.getText().toString();
            String sPhone = tietPhone.getText().toString();

            Log.d(TAG, "onClick: " + sEventClub);
            Log.d(TAG, "onClick: " + sEventName);
            Log.d(TAG, "onClick: " + sStaffID);
            Log.d(TAG, "onClick: " + sPhone);


            if (TextUtils.isEmpty(sEventClub)) {
                Toast.makeText(ReservationDetailActivity.this, "Please fill in", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(sEventName)) {
                Toast.makeText(ReservationDetailActivity.this, "Please fill in", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(sStaffID)) {
                Toast.makeText(ReservationDetailActivity.this, "Please fill in", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(sPhone)) {
                Toast.makeText(ReservationDetailActivity.this, "Please fill in", Toast.LENGTH_LONG).show();
            } else if (!isUploadedLetter) {
                Toast.makeText(ReservationDetailActivity.this, "Please upload PDF Letter", Toast.LENGTH_LONG).show();
            } else if (!isUploadedID) {
                Toast.makeText(ReservationDetailActivity.this, "Please upload PDF ID Staff/Student", Toast.LENGTH_LONG).show();
            } else {

                Intent intent = new Intent(this, EquipmentDetailsActivity.class);
                intent.putExtra(Constant.EVENT_ORGANIZATION, sEventClub);
                intent.putExtra(Constant.EVENT_NAME, sEventName);
                intent.putExtra(Constant.STAFF_ID, sStaffID);
                intent.putExtra(Constant.PHONE_NUMBER, sPhone);
                intent.putExtra(Constant.LETTER_PATH, letterPath);
                intent.putExtra(Constant.ID_PATH, idPath);

                startActivity(intent);
            }

        } else if (v == tvBack) {
            finish();
        } else if (v == btnUploadLetter) {
            Intent pdfIntent = new Intent();
            pdfIntent.setAction(Intent.ACTION_GET_CONTENT);
            pdfIntent.setType("application/pdf");
            startActivityForResult(pdfIntent, 1);
        } else if (v == btnUploadID) {
            Intent pdfIntent = new Intent();
            pdfIntent.setAction(Intent.ACTION_GET_CONTENT);
            pdfIntent.setType("application/pdf");
            startActivityForResult(pdfIntent, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Uri pdfUri = data.getData();
            isUploadedLetter = true;
            letterPath = firebaseUserHelper.uploadPDFLetter(pdfUri);
            Toast.makeText(ReservationDetailActivity.this, "Upload PDF Successfully.", Toast.LENGTH_SHORT).show();
        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            Uri pdfUri = data.getData();
            isUploadedID = true;
            idPath = firebaseUserHelper.uploadPDFID(pdfUri);
            Toast.makeText(ReservationDetailActivity.this, "Upload PDF Successfully.", Toast.LENGTH_SHORT).show();
        }
    }
}
