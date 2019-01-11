package ru.englishcat24.ui.activities.profile;

import android.net.Uri;

import ru.englishcat24.core.Presenter;

/**
 * Created by crish on 1/18/18.
 */

public interface ProfilePresenter extends Presenter {
    void getUserInfo();
    void updateUserName(String name);
    void updateEmail(String email);
    void updateCity(String city);
    void addSocialNetwork(String url);
    void uploadPhoto(Uri uri);
}
