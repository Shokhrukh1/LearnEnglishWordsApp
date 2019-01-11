package ru.englishcat24.ui.activities.questionPage;

import ru.englishcat24.core.BaseView;
import ru.englishcat24.model.Word;

/**
 * Created by crish on 1/17/18.
 */

public interface QuestionView extends BaseView {
    void clearViews();
    void showWord(Word word);
    void showProgressBar();
    void hideProgressBar();
    void showPositiveCat();
    void hidePositiveCat();
    void showNegativeCat(String correctAnswer);
    void hideNegativeCat();
    void showResult(int correctCount);
    void hideResult();
    void clearLevelProgress();
    void updateLevelProgress(int position, boolean isAnswerCorrect);
}
