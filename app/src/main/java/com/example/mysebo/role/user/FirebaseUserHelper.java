package com.example.mysebo.role.user;

import android.content.Intent;
import android.widget.Toast;

import com.example.mysebo.RegisterActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class FirebaseUserHelper {
    public Task<DocumentSnapshot> getSeboUser(String userId){
       return FirebaseFirestore.getInstance().collection("user").document(userId).get();
    }

    public void updateUser(String uid, String fullName, String phoneNumber) {
        Map<String, Object> newData = new HashMap<>();
        newData.put("name",fullName);
        newData.put("phone",phoneNumber);

        FirebaseFirestore.getInstance().collection("user").document(uid).set(newData, SetOptions.merge());


    }
}
