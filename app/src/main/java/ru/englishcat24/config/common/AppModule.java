package ru.englishcat24.config.common;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.englishcat24.config.scope.PerActivity;
import ru.englishcat24.ui.activities.about.AboutActivity;
import ru.englishcat24.ui.activities.about.di.AboutActivityModule;
import ru.englishcat24.ui.activities.balance.BalanceActivity;
import ru.englishcat24.ui.activities.balance.di.BalanceActivityModule;
import ru.englishcat24.ui.activities.homePage.HomeActivity;
import ru.englishcat24.ui.activities.homePage.di.HomeActivityModule;
import ru.englishcat24.ui.activities.profile.ProfileActivity;
import ru.englishcat24.ui.activities.profile.di.ProfileActivityModule;
import ru.englishcat24.ui.activities.questionPage.QuestionActivity;
import ru.englishcat24.ui.activities.questionPage.di.QuestionActivityModule;
import ru.englishcat24.ui.activities.ratingPage.RatingActivity;
import ru.englishcat24.ui.activities.ratingPage.di.RatingActivityModule;
import ru.englishcat24.ui.activities.signIn.SignInActivity;
import ru.englishcat24.ui.activities.signIn.di.SignInActivityModule;
import ru.englishcat24.ui.activities.signUp.SignUpActivity;
import ru.englishcat24.ui.activities.signUp.di.SignUpActivityModule;
import ru.englishcat24.ui.activities.withdrawal.WithdrawalActivity;
import ru.englishcat24.ui.activities.withdrawal.di.WithdrawalActivityModule;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class AppModule {
    @PerActivity
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity provideHomeActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = QuestionActivityModule.class)
    abstract QuestionActivity provideQuestionActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = AboutActivityModule.class)
    abstract AboutActivity provideAboutActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = ProfileActivityModule.class)
    abstract ProfileActivity provideProfileActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = SignUpActivityModule.class)
    abstract SignUpActivity provideSignUpActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = SignInActivityModule.class)
    abstract SignInActivity provideSignInActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = RatingActivityModule.class)
    abstract RatingActivity provideRatingActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = BalanceActivityModule.class)
    abstract BalanceActivity provideBalanceActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = WithdrawalActivityModule.class)
    abstract WithdrawalActivity provideWithdrawalActivity();
}
