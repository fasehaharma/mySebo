package com.example.mysebo.role.user.adapter;

import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysebo.databinding.ItemEquipmentBinding;
import com.example.mysebo.role.user.model.Equipment;

import java.util.ArrayList;
import java.util.List;

public class ItemEquipmentAdapter extends RecyclerView.Adapter<ItemEquipmentAdapter.ViewHolder> {

    private ArrayList<Equipment> equipmentList = new ArrayList<>();

    public ItemEquipmentAdapter() {
        equipmentList.add(new Equipment());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemEquipmentBinding itemBinding = ItemEquipmentBinding.inflate(layoutInflater, parent, false);
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

    public void addItem() {
        equipmentList.add(new Equipment());
        notifyDataSetChanged();
    }

    public ArrayList<? extends Parcelable> getEquipmentList() {
        return equipmentList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEquipmentBinding binding;

        public ViewHolder(ItemEquipmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.etEquipmentName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Equipment equipment = equipmentList.get(getAdapterPosition());
                    equipment.setName(s.toString());
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



        }
    }
}
