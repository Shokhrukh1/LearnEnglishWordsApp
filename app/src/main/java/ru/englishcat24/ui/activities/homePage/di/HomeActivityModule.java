package ru.englishcat24.ui.activities.homePage.di;

import ru.englishcat24.config.scope.PerActivity;
import ru.englishcat24.core.BaseActivityModule;
import ru.englishcat24.ui.activities.homePage.HomeActivity;
import ru.englishcat24.ui.activities.homePage.HomePresenter;
import ru.englishcat24.ui.activities.homePage.HomePresenterImpl;
import ru.englishcat24.ui.activities.homePage.HomeView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by crish on 1/16/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class HomeActivityModule {
    @Binds
    @PerActivity
    abstract HomeView provideHomeView(HomeActivity activity);

    @Binds
    @PerActivity
    abstract HomePresenter provideHomePresenter(HomePresenterImpl presenter);
}
