package ru.englishcat24.ui.activities.signIn.di;

import ru.englishcat24.config.scope.PerActivity;
import ru.englishcat24.core.BaseActivityModule;
import ru.englishcat24.ui.activities.signIn.SignInActivity;
import ru.englishcat24.ui.activities.signIn.SignInPresenter;
import ru.englishcat24.ui.activities.signIn.SignInPresenterImpl;
import ru.englishcat24.ui.activities.signIn.SignInView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by crish on 1/18/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class SignInActivityModule {
    @Binds
    @PerActivity
    abstract SignInView provideSignInView(SignInActivity activity);

    @Binds
    @PerActivity
    abstract SignInPresenter provideSignInPresenter(SignInPresenterImpl presenter);
}
