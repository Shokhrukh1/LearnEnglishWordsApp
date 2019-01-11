package ru.englishcat24.ui.activities.withdrawal.di;

import dagger.Binds;
import dagger.Module;
import ru.englishcat24.config.scope.PerActivity;
import ru.englishcat24.core.BaseActivityModule;
import ru.englishcat24.ui.activities.withdrawal.WithdrawalActivity;
import ru.englishcat24.ui.activities.withdrawal.WithdrawalPresenter;
import ru.englishcat24.ui.activities.withdrawal.WithdrawalPresenterImpl;
import ru.englishcat24.ui.activities.withdrawal.WithdrawalView;

/**
 * Created by crish on 2/15/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class WithdrawalActivityModule {
    @Binds
    @PerActivity
    abstract WithdrawalView provideWithdrawalView(WithdrawalActivity activity);

    @Binds
    @PerActivity
    abstract WithdrawalPresenter provideWithdrawalPresenter(WithdrawalPresenterImpl presenter);
}
