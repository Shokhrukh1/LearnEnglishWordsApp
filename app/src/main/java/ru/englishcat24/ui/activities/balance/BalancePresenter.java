package ru.englishcat24.ui.activities.balance;

import ru.englishcat24.core.Presenter;

/**
 * Created by crish on 2/11/18.
 */

public interface BalancePresenter extends Presenter {
    void getBalance();
    int getBalanceFromCache();
}
