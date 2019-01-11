package ru.englishcat24.ui.activities.signIn;

import ru.englishcat24.core.BaseView;

/**
 * Created by crish on 1/18/18.
 */

public interface SignInView extends BaseView {
    void signInSuccessful();
    void signInFailed();
    void signInFailed(String error);
    void clearFocus();
    void showLoadingDialog();
    void hideLoadingDialog();
}
