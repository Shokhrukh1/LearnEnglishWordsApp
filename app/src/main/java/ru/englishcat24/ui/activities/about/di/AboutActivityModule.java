package ru.englishcat24.ui.activities.about.di;

import ru.englishcat24.config.scope.PerActivity;
import ru.englishcat24.core.BaseActivityModule;
import ru.englishcat24.ui.activities.about.AboutActivity;
import ru.englishcat24.ui.activities.about.AboutPresenter;
import ru.englishcat24.ui.activities.about.AboutPresenterImpl;
import ru.englishcat24.ui.activities.about.AboutView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by crish on 1/18/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class AboutActivityModule {
    @Binds
    @PerActivity
    abstract AboutView provideAboutView(AboutActivity activity);

    @Binds
    @PerActivity
    abstract AboutPresenter provideAboutPresenter(AboutPresenterImpl presenter);
}
