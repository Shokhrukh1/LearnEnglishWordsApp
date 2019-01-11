package ru.englishcat24.ui.activities.ratingPage.di;

import dagger.Binds;
import dagger.Module;
import ru.englishcat24.config.scope.PerActivity;
import ru.englishcat24.core.BaseActivityModule;
import ru.englishcat24.ui.activities.ratingPage.RatingActivity;
import ru.englishcat24.ui.activities.ratingPage.RatingPresenter;
import ru.englishcat24.ui.activities.ratingPage.RatingPresenterImpl;
import ru.englishcat24.ui.activities.ratingPage.RatingView;

/**
 * Created by crish on 1/31/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class RatingActivityModule {
    @Binds
    @PerActivity
    abstract RatingView provideRatingView(RatingActivity activity);

    @Binds
    @PerActivity
    abstract RatingPresenter provideRatingPresenter(RatingPresenterImpl presenter);
}
