package ru.englishcat24.model;

import android.support.annotation.Keep;

import java.util.List;

import lombok.Data;

/**
 * Created by crish on 1/18/18.
 */

@Keep
@Data
public class Word {
    private String word;
    private String answer;
    private List<String> answers;
}
