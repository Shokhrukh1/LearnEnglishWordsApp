package ru.englishcat24.ui.activities.withdrawal;

import ru.englishcat24.core.Presenter;

/**
 * Created by crish on 2/15/18.
 */

public interface WithdrawalPresenter extends Presenter {
    void getData();
    String getPromoCode();
    int getBalance();
}
