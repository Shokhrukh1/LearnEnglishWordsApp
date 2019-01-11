package ru.englishcat24.ui.activities.signIn;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import ru.englishcat24.core.BasePresenterImpl;

/**
 * Created by crish on 1/18/18.
 */

public class SignInPresenterImpl extends BasePresenterImpl<SignInView> implements SignInPresenter {
    @Inject
    public SignInPresenterImpl(SignInView view) {
        super(view);
    }

    @Override
    public void signIn(String email, String password) {
        view.showLoadingDialog();

        RxFirebaseAuth.signInWithEmailAndPassword(FirebaseAuth.getInstance(), email, password)
                .subscribe(authResult -> {
                    view.signInSuccessful();
                }, throwable -> {
                    view.clearFocus();
                    view.signInFailed();
                    view.hideLoadingDialog();
                });
    }

    @Override
    public void signInWithFacebook(AccessToken accessToken) {
        RxFirebaseAuth.signInWithCredential(FirebaseAuth.getInstance(),
                FacebookAuthProvider.getCredential(accessToken.getToken()))
                .subscribe(authResult -> {
                    view.signInSuccessful();
                }, throwable -> {
                    view.clearFocus();
                    view.signInFailed(throwable.getLocalizedMessage());
                });
    }

    @Override
    public void signInWithGoogle(GoogleSignInAccount account) {
        RxFirebaseAuth.signInWithCredential(FirebaseAuth.getInstance(),
                GoogleAuthProvider.getCredential(account.getIdToken(), null))
                .subscribe(authResult -> {
                    view.signInSuccessful();
                }, throwable -> {
                    view.clearFocus();
                    view.signInFailed(throwable.getLocalizedMessage());
                });
    }
}
