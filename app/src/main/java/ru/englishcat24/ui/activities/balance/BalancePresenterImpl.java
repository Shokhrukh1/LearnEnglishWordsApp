package ru.englishcat24.ui.activities.balance;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirestore;
import ru.englishcat24.core.BasePresenterImpl;

import static ru.englishcat24.ui.Constants.USERS_BALANCE_COLLECTION_NAME;

/**
 * Created by crish on 2/11/18.
 */

public class BalancePresenterImpl extends BasePresenterImpl<BalanceView> implements BalancePresenter {
    private int balance = 0;

    @Inject
    public BalancePresenterImpl(BalanceView view) {
        super(view);
    }

    @Override
    public void getBalance() {
        view.showProgressBar();

        RxFirestore.getDocument(FirebaseFirestore.getInstance()
                .collection(USERS_BALANCE_COLLECTION_NAME)
                .document(FirebaseAuth.getInstance().getUid()))
                .subscribe(documentSnapshot -> {
                    for (String key : documentSnapshot.getData().keySet()) {
                        balance += (int) (long) documentSnapshot.getData().get(key);
                    }

                    view.showBalance(balance);
                    view.hideProgressBar();
                }, throwable -> {
                });
    }

    @Override
    public int getBalanceFromCache() {
        return balance;
    }
}
