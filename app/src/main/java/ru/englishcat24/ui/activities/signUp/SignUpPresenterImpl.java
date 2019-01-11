package ru.englishcat24.ui.activities.signUp;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import durdinapps.rxfirebase2.RxFirebaseUser;
import durdinapps.rxfirebase2.RxFirestore;
import ru.englishcat24.core.BasePresenterImpl;
import ru.englishcat24.ui.*;
import ru.englishcat24.ui.Constants;

import static ru.englishcat24.ui.Constants.USERS_BALANCE_COLLECTION_NAME;
import static ru.englishcat24.ui.Constants.USERS_COLLECTION_NAME;

/**
 * Created by crish on 1/18/18.
 */

public class SignUpPresenterImpl extends BasePresenterImpl<SignUpView> implements SignUpPresenter {
    @Inject
    public SignUpPresenterImpl(SignUpView view) {
        super(view);
    }

    @Override
    public void signUp(String name, String email, String password, String promoCode) {
        view.showLoadingDialog();

        RxFirebaseAuth.createUserWithEmailAndPassword(FirebaseAuth.getInstance(), email, password)
                .flatMap(authResult -> RxFirebaseUser.updateProfile(authResult.getUser(), new UserProfileChangeRequest.Builder().setDisplayName(name).build()).toMaybe())
                .subscribe(o -> {
                        }
                        , throwable -> {
                            view.clearFocus();
                            view.signUpFailed(throwable.getLocalizedMessage());
                            view.hideLoadingDialog();
                        },
                        () -> {
                            view.signUpSuccessful();
                            saveUserName();

                            savePromoCode(promoCode);
                        });
    }

    @Override
    public void signUpWithFacebook(AccessToken accessToken) {
        RxFirebaseAuth.signInWithCredential(FirebaseAuth.getInstance(),
                FacebookAuthProvider.getCredential(accessToken.getToken()))
                .subscribe(authResult -> {
                    view.signUpSuccessful();
                }, throwable -> {
                    view.clearFocus();
                    view.signUpFailed(throwable.getLocalizedMessage());
                });
    }

    @Override
    public void signUpWithGoogle(GoogleSignInAccount account) {
        RxFirebaseAuth.signInWithCredential(FirebaseAuth.getInstance(),
                GoogleAuthProvider.getCredential(account.getIdToken(), null))
                .subscribe(authResult -> {
                    view.signUpSuccessful();
                }, throwable -> {
                    view.clearFocus();
                    view.signUpFailed(throwable.getLocalizedMessage());
                });
    }

    @Override
    public void getVKUserInfo() {
        view.showLoadingDialog();
        VKApi.users().get().executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKApiUserFull user = ((VKList<VKApiUserFull>) response.parsedModel).get(0);

                view.showUserFullName(user.first_name + " " + user.last_name);
                view.hideLoadingDialog();
            }
        });
    }

    private void savePromoCode(String promoCode) {
        /*Map<String, Object> data = new HashMap<>();
        data.put(FirebaseAuth.getInstance().getUid(), "");

        RxFirestore.setDocument(FirebaseFirestore.getInstance()
                .collection(Constants.USED_PROMO_CODES_COLLECTION_NAME)
                .document(promoCode), data)
                .subscribe();

        FirebaseFirestore.getInstance()
                .collection(USERS_COLLECTION_NAME)
                .whereEqualTo("promoCode", promoCode)
                .limit(1)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        String userId = documentSnapshots.getDocuments().get(0).getId();

                        RxFirestore.addDocument(Fire)
                    }
                });*/

        if (!promoCode.trim().isEmpty()) {
            Map<String, Object> data = new HashMap<>();
            data.put(UUID.randomUUID().toString(), 20);

            RxFirestore.setDocument(FirebaseFirestore.getInstance()
                    .collection(USERS_BALANCE_COLLECTION_NAME)
                    .document(FirebaseAuth.getInstance().getUid()), data)
                    .subscribe();

            FirebaseFirestore.getInstance()
                    .collection(USERS_COLLECTION_NAME)
                    .whereEqualTo("promoCode", promoCode)
                    .limit(1)
                    .get()
                    .addOnSuccessListener(documentSnapshots -> {
                        String userId = documentSnapshots.getDocuments().get(0).getId();

                        Map<String, Object> data1 = new HashMap<>();
                        data1.put(UUID.randomUUID().toString(), 40);

                        RxFirestore.setDocument(FirebaseFirestore.getInstance()
                                .collection(USERS_BALANCE_COLLECTION_NAME)
                                .document(userId), data1)
                                .subscribe();
                    });
        } else {
            RxFirestore.setDocument(FirebaseFirestore.getInstance()
                    .collection(USERS_BALANCE_COLLECTION_NAME)
                    .document(FirebaseAuth.getInstance().getUid()), new HashMap<>())
                    .subscribe();
        }
    }
}
