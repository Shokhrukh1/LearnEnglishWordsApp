package ru.englishcat24.ui.activities.withdrawal;

import android.util.Pair;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirestore;
import ru.englishcat24.core.BasePresenterImpl;
import ru.englishcat24.model.User;

import static ru.englishcat24.ui.Constants.USERS_BALANCE_COLLECTION_NAME;
import static ru.englishcat24.ui.Constants.USERS_COLLECTION_NAME;

/**
 * Created by crish on 2/15/18.
 */

public class WithdrawalPresenterImpl extends BasePresenterImpl<WithdrawalView> implements WithdrawalPresenter {
    private int balance;
    private String promoCode;

    @Inject
    public WithdrawalPresenterImpl(WithdrawalView view) {
        super(view);
    }

    @Override
    public String getPromoCode() {
        return promoCode;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void getData() {
        view.showProgressBar();

        RxFirestore.getDocument(FirebaseFirestore.getInstance()
                .collection(USERS_COLLECTION_NAME)
                .document(FirebaseAuth.getInstance().getUid()), User.class)
                .flatMap(user ->
                                RxFirestore.getDocument(FirebaseFirestore.getInstance()
                                        .collection(USERS_BALANCE_COLLECTION_NAME)
                                        .document(FirebaseAuth.getInstance().getUid())),
                        (user, documentSnapshot) -> new Pair<DocumentSnapshot, User>(documentSnapshot, user))
                .subscribe(pair -> {
                    promoCode = pair.second.getPromoCode();

                    for (String key : pair.first.getData().keySet()) {
                        balance += (int) (long) pair.first.getData().get(key);
                    }

                    view.showBalance(balance);
                    view.hideProgressBar();
                }, throwable -> {
                });
    }
}
