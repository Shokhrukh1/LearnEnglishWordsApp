package ru.englishcat24.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;

import ru.englishcat24.BuildConfig;
import ru.englishcat24.R;

/**
 * Created by Crish on 20.12.2017.
 */

public class UIUtils {
    public static void openKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void closeKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showAlertDialog(Context context, int messageId, int textId, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setMessage(messageId)
                .setPositiveButton(textId, listener)
                .create()
                .show();
    }

    public static void shareImageWithText(Context context, String text, File file) {
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        context.startActivity(Intent.createChooser(intent, context.getString(R.string.select_app)));
    }

    public static void shareText(Context context, String text) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");

        context.startActivity(Intent.createChooser(intent, context.getString(R.string.select_app)));
    }
}