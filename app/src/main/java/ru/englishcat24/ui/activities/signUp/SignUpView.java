package ru.englishcat24.ui.activities.signUp;

import ru.englishcat24.core.BaseView;

/**
 * Created by crish on 1/18/18.
 */

public interface SignUpView extends BaseView {
    void signUpSuccessful();
    void signUpFailed(String error);
    void clearFocus();
    void showLoadingDialog();
    void hideLoadingDialog();
    void showUserFullName(String fullName);
}
