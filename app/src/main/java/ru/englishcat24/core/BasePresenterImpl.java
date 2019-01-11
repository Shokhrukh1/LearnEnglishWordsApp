package ru.englishcat24.core;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import durdinapps.rxfirebase2.RxFirestore;

import static ru.englishcat24.ui.Constants.USERS_COLLECTION_NAME;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

public class BasePresenterImpl<T extends BaseView> implements Presenter {
    protected final T view;

    protected BasePresenterImpl(T view) {
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreateView(Bundle bundle) {

    }

    protected void saveUserName() {
        String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        Map<String, Object> data = new HashMap<>();
        DocumentReference docRef = FirebaseFirestore
                .getInstance()
                .collection(USERS_COLLECTION_NAME)
                .document(FirebaseAuth.getInstance().getUid());

        data.put("name", userName);

        RxFirestore.setDocument(docRef, data)
                .subscribe();
    }

    protected void saveUserPhotoURL(String photoURL) {
        Map<String, Object> data = new HashMap<>();
        DocumentReference docRef = FirebaseFirestore
                .getInstance()
                .collection(USERS_COLLECTION_NAME)
                .document(FirebaseAuth.getInstance().getUid());

        data.put("photoURL", photoURL);

        RxFirestore.setDocument(docRef, data)
                .subscribe();
    }
}
