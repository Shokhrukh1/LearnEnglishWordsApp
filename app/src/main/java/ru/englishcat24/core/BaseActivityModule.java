package ru.englishcat24.core;

import android.support.v7.app.AppCompatActivity;

import ru.englishcat24.config.scope.PerActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

@Module
public class BaseActivityModule {
    @Provides
    @PerActivity
    static RxPermissions provideRxPermissions(AppCompatActivity activity) {
        return new RxPermissions(activity);
    }
}
