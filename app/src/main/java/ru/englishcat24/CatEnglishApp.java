package ru.englishcat24;

import android.app.Activity;
import android.app.Application;
import com.vk.sdk.VKSdk;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import ru.englishcat24.config.common.DaggerAppComponent;

/**
 * Created by crish on 1/16/18.
 */

public class CatEnglishApp extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

        VKSdk.initialize(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
