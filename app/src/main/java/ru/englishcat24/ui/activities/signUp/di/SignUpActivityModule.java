package ru.englishcat24.ui.activities.signUp.di;

import ru.englishcat24.config.scope.PerActivity;
import ru.englishcat24.core.BaseActivityModule;
import ru.englishcat24.ui.activities.signUp.SignUpActivity;
import ru.englishcat24.ui.activities.signUp.SignUpPresenter;
import ru.englishcat24.ui.activities.signUp.SignUpPresenterImpl;
import ru.englishcat24.ui.activities.signUp.SignUpView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by crish on 1/18/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class SignUpActivityModule {
    @Binds
    @PerActivity
    abstract SignUpView provideSignUpView(SignUpActivity activity);

    @Binds
    @PerActivity
    abstract SignUpPresenter provideSignUpPresenter(SignUpPresenterImpl presenter);
}
