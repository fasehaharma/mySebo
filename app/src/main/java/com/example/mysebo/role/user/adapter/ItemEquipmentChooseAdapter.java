package com.example.mysebo.role.user.adapter;

import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysebo.databinding.ItemEquipmentBinding;
import com.example.mysebo.databinding.ItemEquiptmentChooseBinding;
import com.example.mysebo.role.user.model.Equipment;

import java.util.ArrayList;

public class ItemEquipmentChooseAdapter extends RecyclerView.Adapter<ItemEquipmentChooseAdapter.ViewHolder> {

    private ArrayList<Equipment> equipmentList = new ArrayList<>();
    private  OnEquipmentCallBack onEquipmentCallBack;

    public ItemEquipmentChooseAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemEquiptmentChooseBinding itemBinding = ItemEquiptmentChooseBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(equipmentList.get(holder.getAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    public void setEquipmentList(ArrayList<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public ArrayList<? extends Parcelable> getEquipmentList() {
        return equipmentList;
    }

    public interface OnEquipmentCallBack {
        void onEquipmentCallBack(Equipment equipment);
    }

    public void setOnEquipmentCallBack(OnEquipmentCallBack onEquipmentCallBack) {
        this.onEquipmentCallBack = onEquipmentCallBack;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEquiptmentChooseBinding binding;

        public ViewHolder(ItemEquiptmentChooseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEquipmentCallBack.onEquipmentCallBack(equipmentList.get(getAdapterPosition()));
                }
            });


            binding.etQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(!s.toString().isEmpty()) {
                        Equipment equipment = equipmentList.get(getAdapterPosition());

                        equipment.setQuantity(Integer.valueOf(s.toString().trim()));
                    }
                }
            });
        }


        public void bind(Equipment equipment) {
            binding.setEquipment(equipment);
            binding.etQuantity.setText(String.valueOf(equipment.getQuantity()));


        }
    }
}
