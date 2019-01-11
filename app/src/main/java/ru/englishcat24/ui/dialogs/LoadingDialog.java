package ru.englishcat24.ui.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.englishcat24.R;

/**
 * Created by crish on 1/8/18.
 */

public class LoadingDialog extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        getDialog().getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        setCancelable(false);

        return LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, container, false);
    }
}