package ru.englishcat24.ui.activities.questionPage;

import ru.englishcat24.core.Presenter;

/**
 * Created by crish on 1/17/18.
 */

public interface QuestionPresenter extends Presenter {
    void getWords(String collectionName);
    void checkAnswer(int checkPosition);
    void getNextWord();
    void playAgain();
    int getCorrectCount();
}
