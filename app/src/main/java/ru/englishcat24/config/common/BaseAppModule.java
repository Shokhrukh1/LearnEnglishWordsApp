package ru.englishcat24.config.common;

import android.app.Application;

import ru.englishcat24.CatEnglishApp;

import dagger.Module;

/**
 * Created by Crish on 09.12.2017.
 */

@Module
public abstract class BaseAppModule {
    abstract Application BaseAppModule(CatEnglishApp app);
}
