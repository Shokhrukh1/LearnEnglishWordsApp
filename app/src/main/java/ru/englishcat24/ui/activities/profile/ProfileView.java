package ru.englishcat24.ui.activities.profile;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import ru.englishcat24.core.BaseView;

/**
 * Created by crish on 1/18/18.
 */

public interface ProfileView extends BaseView {
    void showUserInfo(FirebaseUser user);
    void startSignInActivity();
    void showCity(String city);
    void showRating(int rating);
    void showSocialNetworks(List<String> socialNetworks);
    void userNameUpdated(String userName);
    void emailUpdated(String email);
    void cityUpdated(String city);
    void socialNetworkAdded();
    void showEmailUpdateFailed(String text);
    void hideTvEditSocialNetwork();
    void showTvEditSocialNetwork();
    void hideProgressBar();
    void showProgressBar();
    void showLoadingDialog();
    void hideLoadingDialog();
    void showUserPhoto(Uri uri);
    void showUserPhotoUploadFailed();
}
