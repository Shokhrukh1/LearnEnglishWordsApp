package ru.englishcat24.ui.activities.withdrawal;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.BindView;
import de.cketti.mailto.EmailIntentBuilder;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import io.reactivex.functions.Consumer;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseActivity;
import ru.englishcat24.ui.activities.homePage.HomeActivity;
import ru.englishcat24.utils.UIUtils;
import ru.englishcat24.utils.validator.MultipleCallback;

import static ru.englishcat24.ui.Constants.REG_EXP_EMAIL;
import static ru.englishcat24.ui.activities.withdrawal.Constants.BALANCE;

public class WithdrawalActivity extends BaseActivity implements WithdrawalView {
    @Inject
    WithdrawalPresenter presenter;
    @BindView(R.id.btnSendData)
    Button btnSendData;
    @BindView(R.id.tvToMenu)
    TextView tvToMenu;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 1)
    @BindView(R.id.etFullName)
    EditText etFullName;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 2)
    @RegExp(value = REG_EXP_EMAIL, messageId = R.string.type_correct_email_address, order = 3)
    @BindView(R.id.etEmail)
    EditText etEmail;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 4)
    @BindView(R.id.etCardNumber)
    EditText etCardNumber;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 5)
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.tvAmount)
    TextView tvAmount;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setToolbarTitle(R.string.withdrawal);
        showHomeArrow();

        presenter.getData();

        RxView.clicks(btnSendData).subscribe(o -> {
            sendData();
        });

        RxView.clicks(tvToMenu).subscribe(o -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        });

        etAmount.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                sendData();

                return true;
            }

            return false;
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_withdrawal;
    }

    @Override
    public void showBalance(int balance) {
        tvAmount.setText(balance + " ₽");
    }

    private void sendData() {
        if (FormValidator.validate(this, new MultipleCallback())) {
            if (Double.parseDouble(etAmount.getText().toString()) <= presenter.getBalance()) {
                sendEmail();
            } else {
                etAmount.setError(getString(R.string.amount_not_be_more_than_balance));
            }
        }
    }

    private void sendEmail() {
        EmailIntentBuilder.from(this)
                .to("englishcatenglish@gmail.com")
                .body(getEmailData())
                .start();
    }

    private String getEmailData() {
        StringBuilder result = new StringBuilder();

        result.append("ФИО: " + etFullName.getText() + "\n");
        result.append("Email: " + etEmail.getText() + "\n");
        result.append("Номер карты: " + etCardNumber.getText() + "\n");
        result.append("Промо код: " + presenter.getPromoCode() + "\n");
        result.append("Сумма вывода: " + etAmount.getText() + "\n");

        return result.toString();
    }
}
