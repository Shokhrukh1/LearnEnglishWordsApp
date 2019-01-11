package ru.englishcat24.ui.activities.questionPage;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import durdinapps.rxfirebase2.RxFirestore;
import io.reactivex.disposables.Disposable;
import lombok.Getter;
import ru.englishcat24.core.BasePresenterImpl;
import ru.englishcat24.model.Word;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by crish on 1/17/18.
 */

public class QuestionPresenterImpl extends BasePresenterImpl<QuestionView> implements QuestionPresenter {
    private List<Word> allWords;
    private List<Word> tenWords;
    private int currentPosition = 0;
    @Getter
    private int correctCount = 0;
    private Disposable disposable;

    @Inject
    public QuestionPresenterImpl(QuestionView view) {
        super(view);
    }

    @Override
    public void getWords(String collectionName) {
        view.showProgressBar();

        disposable = RxFirestore.getCollection(FirebaseFirestore.getInstance().collection(collectionName))
                .subscribe(documentSnapshots -> {
                    allWords = documentSnapshots.toObjects(Word.class);

                    setTenRandomWords();
                    view.showWord(tenWords.get(currentPosition));
                    view.hideProgressBar();
                }, throwable -> {
                });
    }

    @Override
    public void checkAnswer(int checkPosition) {
        Word word = tenWords.get(currentPosition);

        if (word.getAnswers().get(checkPosition).equals(word.getAnswer())) {
            correctCount++;
            view.updateLevelProgress(currentPosition, true);

            if (currentPosition == 9) {
                view.showResult(correctCount);
            } else {
                view.showPositiveCat();
            }
        } else {
            view.updateLevelProgress(currentPosition, false);

            if (currentPosition == 9) {
                view.showResult(correctCount);
            } else {
                view.showNegativeCat(word.getAnswer());
            }
        }

        view.clearViews();
    }

    @Override
    public void getNextWord() {
        currentPosition++;
        view.showWord(tenWords.get(currentPosition));
        view.hidePositiveCat();
        view.hideNegativeCat();
    }

    @Override
    public void playAgain() {
        currentPosition = 0;
        correctCount = 0;
        setTenRandomWords();
        view.clearLevelProgress();
        view.showWord(tenWords.get(currentPosition));
        view.hideResult();
    }

    private void setTenRandomWords() {
        Set<Integer> numbers = new HashSet<>();
        Random random = new Random();
        tenWords = new ArrayList<>();

        while (numbers.size() != 10) {
            numbers.add(random.nextInt(allWords.size()));
        }

        for (int i : numbers) {
            Collections.shuffle(allWords.get(i).getAnswers());
            tenWords.add(allWords.get(i));
        }
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
