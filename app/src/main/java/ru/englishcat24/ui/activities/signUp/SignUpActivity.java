package ru.englishcat24.ui.activities.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.jakewharton.rxbinding2.view.RxView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import durdinapps.rxfirebase2.RxFirebaseAuth;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import io.reactivex.functions.Consumer;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseActivity;
import ru.englishcat24.ui.activities.homePage.HomeActivity;
import ru.englishcat24.ui.dialogs.LoadingDialog;
import ru.englishcat24.utils.UIUtils;
import ru.englishcat24.utils.validator.MultipleCallback;

import static ru.englishcat24.ui.Constants.REG_EXP_EMAIL;
import static ru.englishcat24.ui.activities.signUp.Constants.RC_SIGN_IN;

public class SignUpActivity extends BaseActivity implements SignUpView {
    @Inject
    SignUpPresenter presenter;
    @BindView(R.id.llContainer)
    LinearLayout llContainer;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 1)
    @MinLength(value = 2, messageId = R.string.minimum_number_of_characters_2, order = 2)
    @BindView(R.id.etName)
    EditText etName;
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
    @BindView(R.id.ivGoogle)
    ImageView ivGoogle;
    @BindView(R.id.ivFacebook)
    ImageView ivFacebook;
    @BindView(R.id.ivVK)
    ImageView ivVK;
    @BindView(R.id.etPromoCode)
    EditText etPromoCode;
    private CallbackManager fbCallbackManager;
    private LoadingDialog loadingDialog;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setToolbarTitle(R.string.sign_up);
        showHomeArrow();

        RxView.clicks(btnSignUp).subscribe(o -> {
            signUp();
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
                    presenter.signUpWithFacebook(loginResult.getAccessToken());
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

        RxView.clicks(ivVK).subscribe(o -> {
            VKSdk.login(this, VKScope.EMAIL);
        });

        etPromoCode.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signUp();

                return true;
            }

            return false;
        });
    }

    private void signUp() {
        if (FormValidator.validate(this, new MultipleCallback())) {
            UIUtils.closeKeyboard(this, btnSignUp);
            presenter.signUp(etName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString(), etPromoCode.getText().toString());
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void signUpSuccessful() {
        Toast.makeText(this, R.string.registration_successful, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }

    @Override
    public void signUpFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
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
    public void showUserFullName(String fullName) {
        etName.setText(fullName);
    }

    @Override
    public void clearFocus() {
        llContainer.clearFocus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (fbCallbackManager != null) {
            if (fbCallbackManager.onActivityResult(requestCode, resultCode, data)) {
                return;
            }
        }

        if (VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                etEmail.setText(res.email);

                presenter.getVKUserInfo();
            }

            @Override
            public void onError(VKError error) {

            }
        })) {
            return;
        }

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                presenter.signUpWithGoogle(account);
            } catch (ApiException e) {
                Log.d("myLogs", e.getLocalizedMessage());
                Toast.makeText(this, R.string.google_sign_in_error, Toast.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
