package ru.englishcat24.core;

import android.os.Bundle;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

public interface Presenter {
    void onStart();
    void onResume();
    void onPause();
    void onSaveInstanceState(Bundle bundle);
    void onDestroy();
    void onCreateView(Bundle bundle);
}
