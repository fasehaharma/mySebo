package com.example.mysebo.role.user.adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysebo.databinding.ItemReservationBinding;
import com.example.mysebo.role.user.ReservationDetailActivity;
import com.example.mysebo.role.user.model.Reservation;
import com.example.mysebo.role.util.StatusDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder>  {
    private List<Reservation> reservations = new ArrayList<>();
    @NonNull
    @Override
    public ReservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemReservationBinding itemReservationBinding = ItemReservationBinding.inflate(layoutInflater, parent, false);
        return new ReservationAdapter.ViewHolder(itemReservationBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.ViewHolder holder, int position) {
        holder.bind(reservations.get(holder.getAdapterPosition()));
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public void setReservationList(List<Reservation> reservations) {
        this.reservations = reservations;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemReservationBinding binding;

        public ViewHolder(@NonNull ItemReservationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), StatusDetailActivity.class);
                    intent.putExtra("reservationId",reservations.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void bind(Reservation reservation) {
            binding.setReservation(reservation);

            if (reservation.getStatus() == 1){
                binding.tvId.setTextColor(Color.RED);
                binding.tvEmail.setTextColor(Color.RED);
            } else if (reservation.getStatus() == 2){
                binding.tvId.setTextColor(Color.GREEN);
                binding.tvEmail.setTextColor(Color.GREEN);
            } else {
                binding.tvId.setTextColor(Color.BLACK);
                binding.tvEmail.setTextColor(Color.BLACK);
            }
        }
    }
}
