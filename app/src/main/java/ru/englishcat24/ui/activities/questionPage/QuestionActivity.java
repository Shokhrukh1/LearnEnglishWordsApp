package ru.englishcat24.ui.activities.questionPage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.TypeVariable;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseActivity;
import ru.englishcat24.model.Word;
import ru.englishcat24.ui.activities.signUp.SignUpActivity;
import ru.englishcat24.utils.UIUtils;
import ru.englishcat24.utils.Utils;

import static ru.englishcat24.ui.Constants.CARD_BACKGROUND;
import static ru.englishcat24.ui.Constants.WORDS_LANGUAGE;

public class QuestionActivity extends BaseActivity implements QuestionView {
    @Inject
    QuestionPresenter presenter;
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.rlFirstRadioButton)
    RelativeLayout rlFirstRadioButton;
    @BindView(R.id.rlSecondRadioButton)
    RelativeLayout rlSecondRadioButton;
    @BindView(R.id.rlThirdRadioButton)
    RelativeLayout rlThirdRadioButton;
    @BindView(R.id.rlFourthRadioButton)
    RelativeLayout rlFourthRadioButton;
    @BindView(R.id.rbFirst)
    RadioButton rbFirst;
    @BindView(R.id.rbSecond)
    RadioButton rbSecond;
    @BindView(R.id.rbThird)
    RadioButton rbThird;
    @BindView(R.id.rbFourth)
    RadioButton rbFourth;
    @BindView(R.id.btnCheck)
    Button btnCheck;
    @BindView(R.id.firstResult)
    View firstResult;
    @BindView(R.id.secondResult)
    View secondResult;
    @BindView(R.id.thirdResult)
    View thirdResult;
    @BindView(R.id.fourthResult)
    View fourthResult;
    @BindView(R.id.fifthResult)
    View fifthResult;
    @BindView(R.id.sixthResult)
    View sixthResult;
    @BindView(R.id.seventhResult)
    View seventhResult;
    @BindView(R.id.eighthResult)
    View eighthResult;
    @BindView(R.id.ninth)
    View ninth;
    @BindView(R.id.tenth)
    View tenth;
    @BindView(R.id.tvFirstAnswer)
    TextView tvFirstAnswer;
    @BindView(R.id.tvSecondAnswer)
    TextView tvSecondAnswer;
    @BindView(R.id.tvThirdAnswer)
    TextView tvThirdAnswer;
    @BindView(R.id.tvFourthAnswer)
    TextView tvFourthAnswer;
    @BindView(R.id.tvWord)
    TextView tvWord;
    @BindView(R.id.btnPositiveNext)
    Button btnPositiveNext;
    @BindView(R.id.btnNegativeNext)
    Button btnNegativeNext;
    @BindView(R.id.tvPositiveText)
    TextView tvPositiveText;
    @BindView(R.id.tvNegativeText)
    TextView tvNegativeText;
    @BindView(R.id.tvCorrectAnswer)
    TextView tvCorrectAnswer;
    @BindView(R.id.ivPositiveCat)
    ImageView ivPositiveCat;
    @BindView(R.id.ivNegativeCat)
    ImageView ivNegativeCat;
    @BindView(R.id.rlCorrectAnswer)
    RelativeLayout rlCorrectAnswer;
    @BindView(R.id.rlIncorrectAnswer)
    RelativeLayout rlIncorrectAnswer;
    @BindView(R.id.rgAnswers)
    RadioGroup rgAnswers;
    @BindView(R.id.tvResultText)
    TextView tvResultText;
    @BindView(R.id.tvResultScore)
    TextView tvResultScore;
    @BindView(R.id.ivResultCat)
    ImageView ivResultCat;
    @BindView(R.id.rlFinishPage)
    RelativeLayout rlFinishPage;
    @BindView(R.id.btnPlayAgain)
    Button btnPlayAgain;
    @BindView(R.id.tvFromFinishToMenu)
    TextView tvFromFinishToMenu;
    @BindView(R.id.cvWord)
    CardView cvWord;
    @BindView(R.id.btnShare)
    Button btnShare;
    @BindView(R.id.btnCreateAccount)
    Button btnCreateAccount;
    @BindView(R.id.tvCreateAccountLater)
    TextView tvCreateAccountLater;
    @BindView(R.id.rlCreateAccount)
    RelativeLayout rlCreateAccount;
    private Random random;
    private int[] positiveCats = {R.drawable.happy_cat_1, R.drawable.happy_cat_2, R.drawable.happy_cat_3};
    private int[] negativeCats = {R.drawable.angry_cat_1, R.drawable.angry_cat_2, R.drawable.angry_cat_3};
    private String[] positiveWords;
    private String[] negativeWords;
    private String[] finishWords;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        random = new Random();
        positiveWords = getResources().getStringArray(R.array.positive_cat_words);
        negativeWords = getResources().getStringArray(R.array.negative_cat_words);
        finishWords = getResources().getStringArray(R.array.finish_words);
        tvCorrectAnswer.setPaintFlags(tvCorrectAnswer.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvResultScore.setPaintFlags(tvResultScore.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        cvWord.setCardBackgroundColor(getResources().getColor(getIntent().getIntExtra(CARD_BACKGROUND, 0)));
        presenter.getWords(getIntent().getStringExtra(WORDS_LANGUAGE));

        RxView.clicks(ivClose).subscribe(o -> {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle(R.string.do_you_really_want_to_leave)
                    .setMessage(R.string.progress_of_this_level_will_not_be_saved)
                    .setPositiveButton(R.string.yes, (dialog, which) -> {
                        finish();
                    })
                    .setNegativeButton(R.string.no, (dialog, which) -> {
                        dialog.dismiss();
                    }).show();
        });

        RxView.clicks(rlFirstRadioButton).subscribe(o -> {
            rbFirst.setChecked(true);
            rbSecond.setChecked(false);
            rbThird.setChecked(false);
            rbFourth.setChecked(false);
        });

        RxView.clicks(rlSecondRadioButton).subscribe(o -> {
            rbFirst.setChecked(false);
            rbSecond.setChecked(true);
            rbThird.setChecked(false);
            rbFourth.setChecked(false);
        });

        RxView.clicks(rlThirdRadioButton).subscribe(o -> {
            rbFirst.setChecked(false);
            rbSecond.setChecked(false);
            rbThird.setChecked(true);
            rbFourth.setChecked(false);
        });

        RxView.clicks(rlFourthRadioButton).subscribe(o -> {
            rbFirst.setChecked(false);
            rbSecond.setChecked(false);
            rbThird.setChecked(false);
            rbFourth.setChecked(true);
        });

        RxView.clicks(btnCheck).subscribe(o -> {
            if (rbFirst.isChecked()) {
                presenter.checkAnswer(0);
            } else if (rbSecond.isChecked()) {
                presenter.checkAnswer(1);
            } else if (rbThird.isChecked()) {
                presenter.checkAnswer(2);
            } else if (rbFourth.isChecked()) {
                presenter.checkAnswer(3);
            }
        });

        RxView.clicks(btnPositiveNext).subscribe(o -> {
            presenter.getNextWord();
        });

        RxView.clicks(btnNegativeNext).subscribe(o -> {
            presenter.getNextWord();
        });

        RxView.clicks(btnPlayAgain).subscribe(o -> {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                presenter.playAgain();
            } else {
                rlFinishPage.setVisibility(View.GONE);
                rlCreateAccount.setVisibility(View.VISIBLE);
            }
        });

        RxView.clicks(tvFromFinishToMenu).subscribe(o -> {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                finish();
            } else {
                rlFinishPage.setVisibility(View.GONE);
                rlCreateAccount.setVisibility(View.VISIBLE);
            }
        });

        RxView.clicks(btnCreateAccount).subscribe(o -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        RxView.clicks(tvCreateAccountLater).subscribe(o -> {
            finish();
        });

        RxView.clicks(btnShare).subscribe(o -> {
            String url = "https://play.google.com/store/apps/details?id=" + getPackageName();
            Bitmap bitmap = Utils.getScreenshot(rlFinishPage);
            File file = Utils.saveImage(this, bitmap);

            UIUtils.shareImageWithText(this, getString(R.string.share_text, presenter.getCorrectCount(), url), file);
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_question;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showWord(Word word) {
        tvWord.setText(word.getWord());
        tvFirstAnswer.setText(word.getAnswers().get(0));
        tvSecondAnswer.setText(word.getAnswers().get(1));
        tvThirdAnswer.setText(word.getAnswers().get(2));
        tvFourthAnswer.setText(word.getAnswers().get(3));
    }

    @Override
    public void showPositiveCat() {
        tvPositiveText.setText(positiveWords[random.nextInt(positiveWords.length)]);
        ivPositiveCat.setImageResource(positiveCats[random.nextInt(positiveCats.length)]);
        rlCorrectAnswer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNegativeCat(String correctAnswer) {
        tvCorrectAnswer.setText(correctAnswer);
        tvNegativeText.setText(negativeWords[random.nextInt(negativeWords.length)]);
        ivNegativeCat.setImageResource(negativeCats[random.nextInt(negativeCats.length)]);
        rlIncorrectAnswer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePositiveCat() {
        rlCorrectAnswer.setVisibility(View.GONE);
    }

    @Override
    public void hideNegativeCat() {
        rlIncorrectAnswer.setVisibility(View.GONE);
    }

    @Override
    public void showResult(int correctCount) {
        if (correctCount <= 2) {
            tvResultText.setTextColor(getResources().getColor(R.color.colorRed));
            tvResultText.setText(finishWords[0]);
            ivResultCat.setImageResource(R.drawable.finish_cat_1);
        } else if (correctCount <= 6) {
            tvResultText.setTextColor(getResources().getColor(R.color.colorBlue));
            tvResultText.setText(finishWords[1]);
            ivResultCat.setImageResource(R.drawable.finish_cat_2);
        } else {
            tvResultText.setTextColor(getResources().getColor(R.color.colorLightGreen));
            tvResultText.setText(finishWords[2]);
            ivResultCat.setImageResource(R.drawable.finish_cat_3);
        }

        tvResultScore.setText(correctCount + "/10");
        rlFinishPage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideResult() {
        rlFinishPage.setVisibility(View.GONE);
    }

    @Override
    public void clearViews() {
        rbFirst.setChecked(false);
        rbSecond.setChecked(false);
        rbThird.setChecked(false);
        rbFourth.setChecked(false);
    }

    @Override
    public void updateLevelProgress(int position, boolean isAnswerCorrect) {
        switch (position) {
            case 0:
                firstResult.setBackgroundResource(isAnswerCorrect ? R.color.colorLightGreen : R.color.colorRed);
                break;
            case 1:
                secondResult.setBackgroundResource(isAnswerCorrect ? R.color.colorLightGreen : R.color.colorRed);
                break;
            case 2:
                thirdResult.setBackgroundResource(isAnswerCorrect ? R.color.colorLightGreen : R.color.colorRed);
                break;
            case 3:
                fourthResult.setBackgroundResource(isAnswerCorrect ? R.color.colorLightGreen : R.color.colorRed);
                break;
            case 4:
                fifthResult.setBackgroundResource(isAnswerCorrect ? R.color.colorLightGreen : R.color.colorRed);
                break;
            case 5:
                sixthResult.setBackgroundResource(isAnswerCorrect ? R.color.colorLightGreen : R.color.colorRed);
                break;
            case 6:
                seventhResult.setBackgroundResource(isAnswerCorrect ? R.color.colorLightGreen : R.color.colorRed);
                break;
            case 7:
                eighthResult.setBackgroundResource(isAnswerCorrect ? R.color.colorLightGreen : R.color.colorRed);
                break;
            case 8:
                ninth.setBackgroundResource(isAnswerCorrect ? R.color.colorLightGreen : R.color.colorRed);
                break;
            case 9:
                tenth.setBackgroundResource(isAnswerCorrect ? R.color.colorLightGreen : R.color.colorRed);
                break;
        }
    }

    @Override
    public void clearLevelProgress() {
        firstResult.setBackgroundResource(R.color.colorLightLightGray);
        secondResult.setBackgroundResource(R.color.colorLightLightGray);
        thirdResult.setBackgroundResource(R.color.colorLightLightGray);
        fourthResult.setBackgroundResource(R.color.colorLightLightGray);
        fifthResult.setBackgroundResource(R.color.colorLightLightGray);
        sixthResult.setBackgroundResource(R.color.colorLightLightGray);
        seventhResult.setBackgroundResource(R.color.colorLightLightGray);
        eighthResult.setBackgroundResource(R.color.colorLightLightGray);
        ninth.setBackgroundResource(R.color.colorLightLightGray);
        tenth.setBackgroundResource(R.color.colorLightLightGray);
    }
}
