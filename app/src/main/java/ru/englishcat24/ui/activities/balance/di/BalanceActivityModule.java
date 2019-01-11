package ru.englishcat24.ui.activities.balance.di;

import dagger.Binds;
import dagger.Module;
import ru.englishcat24.config.scope.PerActivity;
import ru.englishcat24.core.BaseActivityModule;
import ru.englishcat24.ui.activities.balance.BalanceActivity;
import ru.englishcat24.ui.activities.balance.BalancePresenter;
import ru.englishcat24.ui.activities.balance.BalancePresenterImpl;
import ru.englishcat24.ui.activities.balance.BalanceView;

/**
 * Created by crish on 2/11/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class BalanceActivityModule {
    @Binds
    @PerActivity
    abstract BalanceView provideBalanceView(BalanceActivity activity);

    @Binds
    @PerActivity
    abstract BalancePresenter provideBalancePresenter(BalancePresenterImpl presenter);
}
