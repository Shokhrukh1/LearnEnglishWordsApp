package ru.englishcat24.ui.activities.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseActivity;
import ru.englishcat24.ui.activities.signUp.SignUpActivity;
import ru.englishcat24.ui.dialogs.LoadingDialog;
import ru.englishcat24.utils.UIUtils;
import ru.englishcat24.utils.validator.MultipleCallback;

import static ru.englishcat24.ui.Constants.REG_EXP_EMAIL;
import static ru.englishcat24.ui.activities.signUp.Constants.RC_SIGN_IN;

public class SignInActivity extends BaseActivity implements SignInView {
    @Inject
    SignInPresenter presenter;
    @BindView(R.id.rlContainer)
    RelativeLayout rlContainer;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 3)
    @RegExp(value = REG_EXP_EMAIL, messageId = R.string.type_correct_email_address, order = 4)
    @BindView(R.id.etEmail)
    EditText etEmail;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 5)
    @MinLength(value = 6, messageId = R.string.minimum_number_of_characters_6, order = 6)
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.ivGoogle)
    ImageView ivGoogle;
    @BindView(R.id.ivFacebook)
    ImageView ivFacebook;
    private CallbackManager fbCallbackManager;
    private LoadingDialog loadingDialog;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setToolbarTitle(R.string.sign_in);
        showHomeArrow();

        RxView.clicks(btnSignIn).subscribe(o -> {
            signIn();
        });

        RxView.clicks(btnSignUp).subscribe(o -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        RxView.clicks(ivGoogle).subscribe(o -> {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.google_web_client_id))
                    .requestEmail()
                    .build();

            Intent signInIntent = GoogleSignIn.getClient(this, gso).getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);

        });

        RxView.clicks(ivFacebook).subscribe(o -> {
            fbCallbackManager = CallbackManager.Factory.create();

            LoginManager.getInstance().registerCallback(fbCallbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    presenter.signInWithFacebook(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {

                }
            });

            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        });

        etPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signIn();

                return true;
            }

            return false;
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sign_in;
    }

    @Override
    public void signInSuccessful() {
        finish();
        Toast.makeText(this, R.string.sign_in_successful, Toast.LENGTH_LONG).show();
    }

    @Override
    public void signInFailed() {
        Toast.makeText(this, getString(R.string.not_valid_login_or_password), Toast.LENGTH_LONG).show();
    }

    @Override
    public void signInFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void signIn() {
        if (FormValidator.validate(this, new MultipleCallback())) {
            UIUtils.closeKeyboard(this, btnSignUp);
            presenter.signIn(etEmail.getText().toString(), etPassword.getText().toString());
        }
    }

    @Override
    public void clearFocus() {
        rlContainer.clearFocus();
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog = new LoadingDialog();
        loadingDialog.show(getSupportFragmentManager(), "LoadingDialog");
    }

    @Override
    public void hideLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (fbCallbackManager != null) {
            fbCallbackManager.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                presenter.signInWithGoogle(account);
            } catch (ApiException e) {
                Log.d("myLogs", e.getLocalizedMessage());
                Toast.makeText(this, R.string.google_sign_in_error, Toast.LENGTH_LONG).show();
            }
        }
    }
}
