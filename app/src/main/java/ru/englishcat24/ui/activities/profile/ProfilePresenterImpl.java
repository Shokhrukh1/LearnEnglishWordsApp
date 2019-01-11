package ru.englishcat24.ui.activities.profile;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseStorage;
import durdinapps.rxfirebase2.RxFirebaseUser;
import durdinapps.rxfirebase2.RxFirestore;
import io.reactivex.disposables.Disposable;
import ru.englishcat24.core.BasePresenterImpl;
import ru.englishcat24.model.User;

/**
 * Created by crish on 1/18/18.
 */

public class ProfilePresenterImpl extends BasePresenterImpl<ProfileView> implements ProfilePresenter {
    private Disposable disposable;
    private User user;

    @Inject
    public ProfilePresenterImpl(ProfileView view) {
        super(view);
    }

    @Override
    public void getUserInfo() {
        view.showProgressBar();

        disposable = RxFirestore.getDocument(FirebaseFirestore.getInstance()
                .collection("users")
                .document(FirebaseAuth.getInstance().getUid()))
                .subscribe(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        user = documentSnapshot.toObject(User.class);
                    } else {
                        user = new User();
                    }


                    view.showUserInfo(FirebaseAuth.getInstance().getCurrentUser());

                    if (FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl() != null) {
                        view.showUserPhoto(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl());
                    }

                    view.showRating(user.getRating());

                    view.showCity(user.getCity());

                    view.showSocialNetworks(user.getSocialNetworks());

                    view.hideProgressBar();

                }, throwable -> {
                });
    }

    @Override
    public void uploadPhoto(Uri uri) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            view.showLoadingDialog();
            disposable = RxFirebaseStorage.putFile(FirebaseStorage.getInstance()
                    .getReference()
                    .child("user photos/" + FirebaseAuth.getInstance().getCurrentUser().getUid()), uri)
                    .subscribe(taskSnapshot -> {
                        RxFirebaseUser.updateProfile(FirebaseAuth.getInstance().getCurrentUser(),
                                new UserProfileChangeRequest.Builder()
                                        .setPhotoUri(taskSnapshot.getDownloadUrl())
                                        .build()).subscribe();

                        view.hideLoadingDialog();
                        view.showUserPhoto(taskSnapshot.getDownloadUrl());
                        saveUserPhotoURL(taskSnapshot.getDownloadUrl().toString());
                    }, throwable -> {
                        view.showUserPhotoUploadFailed();
                    });
        }
    }

    @Override
    public void updateUserName(String name) {
        disposable = RxFirebaseUser.updateProfile(FirebaseAuth.getInstance().getCurrentUser(), new UserProfileChangeRequest.Builder().setDisplayName(name).build())
                .subscribe(() -> {
                    saveUserName();
                }, throwable -> {

                });

        view.userNameUpdated(name);
    }

    @Override
    public void updateEmail(String email) {
        disposable = RxFirebaseUser.updateEmail(FirebaseAuth.getInstance().getCurrentUser(), email)
                .subscribe(() -> {
                    view.hideLoadingDialog();
                    view.emailUpdated(email);
                }, throwable -> {
                    view.hideLoadingDialog();
                    view.showEmailUpdateFailed(throwable.getMessage());
                });
    }

    @Override
    public void updateCity(String city) {
        Map<String, Object> data = new HashMap<>();
        data.put("city", city);

        disposable = RxFirestore.setDocument(FirebaseFirestore.getInstance()
                .collection("users")
                .document(FirebaseAuth.getInstance().getUid()), data)
                .subscribe(() -> {
                }, throwable -> {
                });

        view.cityUpdated(city);
    }

    @Override
    public void addSocialNetwork(String url) {
        Map<String, Object> data = new HashMap<>();

        if (user.getSocialNetworks() != null) {
            user.getSocialNetworks().add(url);
        } else {
            user.setSocialNetworks(Arrays.asList(url));
        }

        data.put("socialNetworks", user.getSocialNetworks());

        disposable = RxFirestore.setDocument(FirebaseFirestore.getInstance()
                .collection("users")
                .document(FirebaseAuth.getInstance().getUid()), data)
                .subscribe(() -> {
                }, throwable -> {
                });

        //view.showTvEditSocialNetwork();
        view.socialNetworkAdded();
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
