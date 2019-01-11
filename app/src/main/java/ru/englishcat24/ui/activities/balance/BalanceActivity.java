package ru.englishcat24.ui.activities.balance;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseActivity;
import ru.englishcat24.ui.activities.withdrawal.WithdrawalActivity;

import static ru.englishcat24.ui.activities.withdrawal.Constants.BALANCE;

public class BalanceActivity extends BaseActivity implements BalanceView {
    @Inject
    BalancePresenter presenter;
    @BindView(R.id.btnWithdrawal)
    Button btnWithdrawal;
    @BindView(R.id.tvBalance)
    TextView tvBalance;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setToolbarTitle(R.string.your_balance);
        showHomeArrow();

        presenter.getBalance();

        RxView.clicks(btnWithdrawal).subscribe(o -> {
            startActivity(new Intent(this, WithdrawalActivity.class));
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_balance;
    }

    @Override
    public void showBalance(int balance) {
        tvBalance.setText(String.valueOf(balance) + " ₽");
    }
}
