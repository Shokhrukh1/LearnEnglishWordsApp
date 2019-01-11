package ru.englishcat24.ui.listeners;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by crish on 1/6/18.
 */

public class GlideRequestListener implements RequestListener<Drawable> {
    private ImageView ivImage;
    private ProgressBar pbLoading;

    public GlideRequestListener(ImageView ivImage, ProgressBar pbLoading) {
        this.ivImage = ivImage;
        this.pbLoading = pbLoading;
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
        return false;
    }

    @Override
    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
        ivImage.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);

        return false;
    }
}
