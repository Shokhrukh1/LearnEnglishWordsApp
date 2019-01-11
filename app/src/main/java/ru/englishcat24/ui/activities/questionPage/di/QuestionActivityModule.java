package ru.englishcat24.ui.activities.questionPage.di;

import ru.englishcat24.config.scope.PerActivity;
import ru.englishcat24.core.BaseActivityModule;
import ru.englishcat24.ui.activities.questionPage.QuestionActivity;
import ru.englishcat24.ui.activities.questionPage.QuestionPresenter;
import ru.englishcat24.ui.activities.questionPage.QuestionPresenterImpl;
import ru.englishcat24.ui.activities.questionPage.QuestionView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by crish on 1/17/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class QuestionActivityModule {
    @Binds
    @PerActivity
    abstract QuestionView provideQuestionView(QuestionActivity activity);

    @Binds
    @PerActivity
    abstract QuestionPresenter provideQuestionPresenter(QuestionPresenterImpl presenter);
}
