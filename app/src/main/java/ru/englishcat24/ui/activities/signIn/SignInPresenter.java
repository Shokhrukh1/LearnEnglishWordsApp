package ru.englishcat24.ui.activities.signIn;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import ru.englishcat24.core.Presenter;

/**
 * Created by crish on 1/18/18.
 */

public interface SignInPresenter extends Presenter {
    void signIn(String email, String password);
    void signInWithFacebook(AccessToken accessToken);
    void signInWithGoogle(GoogleSignInAccount account);
}
