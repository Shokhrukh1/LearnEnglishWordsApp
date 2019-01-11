package ru.englishcat24.ui.activities.signUp;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import ru.englishcat24.core.Presenter;

/**
 * Created by crish on 1/18/18.
 */

public interface SignUpPresenter extends Presenter {
    void signUp(String name, String email, String password, String promoCode);
    void signUpWithFacebook(AccessToken accessToken);
    void signUpWithGoogle(GoogleSignInAccount account);
    void getVKUserInfo();
}
