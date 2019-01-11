package ru.englishcat24.ui.activities.profile.di;

import ru.englishcat24.config.scope.PerActivity;
import ru.englishcat24.core.BaseActivityModule;
import ru.englishcat24.ui.activities.profile.ProfileActivity;
import ru.englishcat24.ui.activities.profile.ProfilePresenter;
import ru.englishcat24.ui.activities.profile.ProfilePresenterImpl;
import ru.englishcat24.ui.activities.profile.ProfileView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by crish on 1/18/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class ProfileActivityModule {
    @Binds
    @PerActivity
    abstract ProfileView provideProfileView(ProfileActivity activity);

    @Binds
    @PerActivity
    abstract ProfilePresenter provideProfilePresenter(ProfilePresenterImpl presenter);
}
