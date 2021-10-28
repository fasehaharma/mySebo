package com.example.mysebo.role.user;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.mysebo.RegisterActivity;
import com.example.mysebo.role.user.model.Equipment;
import com.example.mysebo.role.user.model.Reservation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseUserHelper {
    private MutableLiveData<ArrayList<Equipment>> equipmentListMutableLiveData = new MutableLiveData<>();

    private final FirebaseFirestore mFirestore;
    private String TAG = FirebaseUserHelper.class.getSimpleName();
    private MutableLiveData<List<Reservation>> reservationListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Reservation> reservationMutableLiveData = new MutableLiveData<>();

    public FirebaseUserHelper() {
        mFirestore = FirebaseFirestore.getInstance();
    }

    public Task<DocumentSnapshot> getSeboUser(String userId){
       return FirebaseFirestore.getInstance().collection("user").document(userId).get();
    }

    public void updateUser(String uid, String fullName, String phoneNumber) {
        Map<String, Object> newData = new HashMap<>();
        newData.put("name",fullName);
        newData.put("phone",phoneNumber);

        FirebaseFirestore.getInstance().collection("user").document(uid).set(newData, SetOptions.merge());

    }

    public MutableLiveData<ArrayList<Equipment>> getEquipmentListMutableLiveData() {
        return equipmentListMutableLiveData;
    }

    public void setEquipmentListMutableLiveData(MutableLiveData<ArrayList<Equipment>> equipmentListMutableLiveData) {
        this.equipmentListMutableLiveData = equipmentListMutableLiveData;
    }

    public void readAsset(){
        ArrayList<Equipment> equipmentList = new ArrayList<>();

               CollectionReference collection = mFirestore
                .collection("itemList");
        collection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Equipment equipment = document.toObject(Equipment.class);
                        equipment.setId(document.getId());


                        equipmentList.add(equipment);
                    }
                    equipmentListMutableLiveData.postValue(equipmentList);
                }
            }
       });

    }

    public void readReservationList() {
        CollectionReference collection = mFirestore
                .collection("EquipmentReservation");
        collection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                for (DocumentSnapshot document : task.getResult()) {
//                    Asset asset = document.toObject(Asset.class);
//                    asset.setId(document.getId());
//                    assetList.add(asset);
//                }
                List<Reservation> reservationList = new ArrayList<>();


                for (QueryDocumentSnapshot doc : value) {
                    Reservation reservation = doc.toObject(Reservation.class);
                    reservation.setId(doc.getId());

                    reservationList.add(reservation);

                    List<Equipment> equipment = reservation.getEquipment();

                    Log.d(TAG, "onEvent: " + reservation.getEventName());

                }



                Log.d(TAG, "onEvent: ");
                reservationListMutableLiveData.postValue(reservationList);
            }
        });
    }

    public MutableLiveData<List<Reservation>> getReservationListMutableLiveData() {
        return reservationListMutableLiveData;
    }

    public MutableLiveData<Reservation> getReservationMutableLiveData() {
        return reservationMutableLiveData;
    }


    public void readReservation(String reservationId) {
        CollectionReference collection = mFirestore
                .collection("EquipmentReservation");
        DocumentReference document = collection.document(reservationId);

        document.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                Reservation reservation = task.getResult().toObject(Reservation.class);
                reservationMutableLiveData.postValue(reservation);
            }
        });
    }
}
