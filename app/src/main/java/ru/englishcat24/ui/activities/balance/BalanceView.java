package ru.englishcat24.ui.activities.balance;

import ru.englishcat24.core.BaseView;

/**
 * Created by crish on 2/11/18.
 */

public interface BalanceView extends BaseView {
    void showBalance(int balance);
    void showProgressBar();
    void hideProgressBar();
}
