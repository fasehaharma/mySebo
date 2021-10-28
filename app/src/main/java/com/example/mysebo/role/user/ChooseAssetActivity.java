package com.example.mysebo.role.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mysebo.R;
import com.example.mysebo.databinding.ActivityChooseAssetBinding;
import com.example.mysebo.role.user.adapter.ItemEquipmentChooseAdapter;
import com.example.mysebo.role.user.model.Equipment;

import java.util.ArrayList;

public class ChooseAssetActivity extends AppCompatActivity {

    private ActivityChooseAssetBinding binding;
    private FirebaseUserHelper firebaseUserHelper = new FirebaseUserHelper();
    private ItemEquipmentChooseAdapter itemEquipmentChooseAdapter;
    private RecyclerView rvAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_choose_asset);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_asset);
        itemEquipmentChooseAdapter = new ItemEquipmentChooseAdapter();

        rvAsset = binding.rvAsset;
        rvAsset.setLayoutManager(new LinearLayoutManager(this));
        rvAsset.setAdapter(itemEquipmentChooseAdapter);

        itemEquipmentChooseAdapter.setOnEquipmentCallBack(new ItemEquipmentChooseAdapter.OnEquipmentCallBack() {
            @Override
            public void onEquipmentCallBack(Equipment equipment) {
                Log.d("TAG", "onEquipmentCallBack: test");

                Intent intent = new Intent();
                intent.putExtra("equipmentName",equipment.getItem());
                intent.putExtra("id",equipment.getId());

                setResult(RESULT_OK,intent);
                finish();
            }
        });

        firebaseUserHelper.getEquipmentListMutableLiveData().observe(this, new Observer<ArrayList<Equipment>>() {
            @Override
            public void onChanged(ArrayList<Equipment> equipment) {
                itemEquipmentChooseAdapter.setEquipmentList(equipment);
                itemEquipmentChooseAdapter.notifyDataSetChanged();
            }
        });

        firebaseUserHelper.readAsset();
    }
}