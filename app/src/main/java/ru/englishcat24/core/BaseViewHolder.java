package ru.englishcat24.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    protected View view;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, itemView);
    }
}
