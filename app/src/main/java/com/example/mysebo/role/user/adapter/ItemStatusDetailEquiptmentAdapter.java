package com.example.mysebo.role.user.adapter;

import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysebo.databinding.ItemEquipmentBinding;
import com.example.mysebo.databinding.ItemStatusDetailEquiptmentBinding;
import com.example.mysebo.role.user.model.Equipment;

import java.util.ArrayList;

public class ItemStatusDetailEquiptmentAdapter extends RecyclerView.Adapter<ItemStatusDetailEquiptmentAdapter.ViewHolder> {

    private ArrayList<Equipment> equipmentList = new ArrayList<>();


    public ItemStatusDetailEquiptmentAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemStatusDetailEquiptmentBinding itemBinding = ItemStatusDetailEquiptmentBinding.inflate(layoutInflater, parent, false);
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

    public void addItem(String id, String equipmentName) {
        boolean isContainId = false;
        for (int i = 0; i < equipmentList.size(); i++) {

            Equipment equipment = equipmentList.get(i);
            isContainId = equipment.getId().equals(id);

            if(isContainId){
                break;
            }

        }

        if(!isContainId){
            Equipment equipment = new Equipment();
            equipment.setItem(equipmentName);
            equipment.setId(id);

            equipmentList.add(equipment);

            notifyItemInserted(getItemCount() + 1);
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemStatusDetailEquiptmentBinding binding;

        public ViewHolder(ItemStatusDetailEquiptmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


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
