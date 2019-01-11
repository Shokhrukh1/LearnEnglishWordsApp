package ru.englishcat24.model;

import android.support.annotation.Keep;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by crish on 2/1/18.
 */
@Keep
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rating {
    private String userName;
    private long point;
}
