package ru.englishcat24.config.common;

import android.app.Application;

import ru.englishcat24.CatEnglishApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Portable-Acer on 05.12.2017.
 */
@Singleton
@Component(modules = {AppModule.class, BaseAppModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(CatEnglishApp app);
}
