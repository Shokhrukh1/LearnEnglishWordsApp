package ru.englishcat24.ui.activities.profile;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jakewharton.rxbinding2.view.RxView;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseActivity;
import ru.englishcat24.ui.activities.profile.adapters.SocialNetworkAdapter;
import ru.englishcat24.ui.activities.signIn.SignInActivity;
import ru.englishcat24.ui.dialogs.LoadingDialog;
import ru.englishcat24.utils.UIUtils;

import static ru.englishcat24.ui.Constants.REG_EXP_EMAIL;
import static ru.englishcat24.ui.activities.profile.Constants.PICK_IMAGE;

public class ProfileActivity extends BaseActivity implements ProfileView {
    @Inject
    ProfilePresenter presenter;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvEditName)
    TextView tvEditName;
    @BindView(R.id.tvEditEmail)
    TextView tvEditEmail;
    @BindView(R.id.llNameEditContainer)
    LinearLayout llNameEditContainer;
    @BindView(R.id.llNameContainer)
    LinearLayout llNameContainer;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 1)
    @MinLength(value = 2, messageId = R.string.minimum_number_of_characters_2, order = 2)
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvSaveName)
    TextView tvSaveName;
    @BindView(R.id.llEmailContainer)
    LinearLayout llEmailContainer;
    @BindView(R.id.llEmailEditContainer)
    LinearLayout llEmailEditContainer;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 3)
    @RegExp(value = REG_EXP_EMAIL, messageId = R.string.type_correct_email_address, order = 4)
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.tvSaveEmail)
    TextView tvSaveEmail;
    @BindView(R.id.llCityContainer)
    LinearLayout llCityContainer;
    @BindView(R.id.llCityEditContainer)
    LinearLayout llCityEditContainer;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvEditCity)
    TextView tvEditCity;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 5)
    @MinLength(value = 2, messageId = R.string.minimum_number_of_characters_2, order = 6)
    @BindView(R.id.etCity)
    EditText etCity;
    @BindView(R.id.tvSaveCity)
    TextView tvSaveCity;
    @BindView(R.id.tvRating)
    TextView tvRating;
    @BindView(R.id.llAddSocialNetwork)
    LinearLayout llAddSocialNetwork;
    @BindView(R.id.llSocialNetworkEditContainer)
    LinearLayout llSocialNetworkEditContainer;
    @BindView(R.id.etSocialNetwork)
    EditText etSocialNetwork;
    @BindView(R.id.tvSaveSocialNetwork)
    TextView tvSaveSocialNetwork;
    @BindView(R.id.rvSocialNetworks)
    RecyclerView rvSocialNetworks;
    @BindView(R.id.btnLogOut)
    Button btnLogOut;
    @BindView(R.id.tvEditSocialNetwork)
    TextView tvEditSocialNetwork;
    @BindView(R.id.llEditPhoto)
    LinearLayout llEditPhoto;
    @BindView(R.id.civProfileImage)
    CircleImageView civProfileImage;
    private DialogFragment loadingDialog;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setToolbarTitle(R.string.my_profile);
        showHomeArrow();
        presenter.getUserInfo();

        RxView.clicks(tvEditName).subscribe(o -> {
            llNameContainer.setVisibility(View.GONE);
            llNameEditContainer.setVisibility(View.VISIBLE);
            etName.setText(tvName.getText());
            etName.requestFocus();
            UIUtils.openKeyboard(this, etName);
        });

        RxView.clicks(tvEditEmail).subscribe(o -> {
            llEmailContainer.setVisibility(View.GONE);
            llEmailEditContainer.setVisibility(View.VISIBLE);
            etEmail.setText(tvEmail.getText());
            etEmail.requestFocus();
            UIUtils.openKeyboard(this, etEmail);
        });

        RxView.clicks(tvEditCity).subscribe(o -> {
            llCityContainer.setVisibility(View.GONE);
            llCityEditContainer.setVisibility(View.VISIBLE);
            etCity.setText(tvCity.getText());
            etCity.requestFocus();
            UIUtils.openKeyboard(this, etCity);
        });

        RxView.clicks(tvSaveName).subscribe(o -> {
            if (FormValidator.validateSingleView(this, etName, new SimpleErrorPopupCallback(this))) {
                UIUtils.closeKeyboard(this, tvSaveName);
                presenter.updateUserName(etName.getText().toString());
            }
        });

        RxView.clicks(tvSaveEmail).subscribe(o -> {
            if (FormValidator.validateSingleView(this, etEmail, new SimpleErrorPopupCallback(this))) {
                UIUtils.closeKeyboard(this, tvSaveEmail);

                loadingDialog.show(getSupportFragmentManager(), "LoadingDialog");
                presenter.updateEmail(etEmail.getText().toString());
            }
        });

        RxView.clicks(tvSaveCity).subscribe(o -> {
            if (FormValidator.validateSingleView(this, etCity, new SimpleErrorPopupCallback(this))) {
                UIUtils.closeKeyboard(this, tvSaveCity);
                presenter.updateCity(etCity.getText().toString());
            }
        });

        RxView.clicks(llAddSocialNetwork).subscribe(o -> {
            llSocialNetworkEditContainer.setVisibility(View.VISIBLE);
            etSocialNetwork.requestFocus();
            UIUtils.openKeyboard(this, etSocialNetwork);
        });

        RxView.clicks(tvSaveSocialNetwork).subscribe(o -> {
            UIUtils.closeKeyboard(this, tvSaveSocialNetwork);
            presenter.addSocialNetwork(etSocialNetwork.getText().toString());
        });

        RxView.clicks(btnLogOut).subscribe(o -> {
            FirebaseAuth.getInstance().signOut();
            finish();
        });

        RxView.clicks(llEditPhoto).subscribe(o -> {
            openPhotoPicker();
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_profile;
    }

    @Override
    public void showUserInfo(FirebaseUser user) {
        tvName.setText(user.getDisplayName());
        tvEmail.setText(user.getEmail());
    }

    @Override
    public void startSignInActivity() {
        startActivity(new Intent(this, SignInActivity.class));
    }

    @Override
    public void showCity(String city) {
        tvCity.setText(city);
        llCityEditContainer.setVisibility(View.GONE);
        llCityContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRating(int rating) {
        tvRating.setText(rating + "");
    }

    @Override
    public void showSocialNetworks(List<String> socialNetworks) {
        rvSocialNetworks.setLayoutManager(new LinearLayoutManager(this));
        rvSocialNetworks.setAdapter(new SocialNetworkAdapter(socialNetworks));
    }

    @Override
    public void userNameUpdated(String userName) {
        tvName.setText(userName);
        llNameEditContainer.setVisibility(View.GONE);
        llNameContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void emailUpdated(String email) {
        tvEmail.setText(email);
        llEmailEditContainer.setVisibility(View.GONE);
        llEmailContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void cityUpdated(String city) {
        tvCity.setText(city);
        llCityEditContainer.setVisibility(View.GONE);
        llCityContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void socialNetworkAdded() {
        rvSocialNetworks.getAdapter().notifyDataSetChanged();
        llSocialNetworkEditContainer.setVisibility(View.GONE);
        etSocialNetwork.getText().clear();
    }

    @Override
    public void showEmailUpdateFailed(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideTvEditSocialNetwork() {
        tvEditSocialNetwork.setVisibility(View.GONE);
    }

    @Override
    public void showTvEditSocialNetwork() {
        tvEditSocialNetwork.setVisibility(View.VISIBLE);
    }

    private void openPhotoPicker() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, getString(R.string.select_app));
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                presenter.uploadPhoto(data.getData());
            }
        }
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
    public void showUserPhoto(Uri uri) {
        Glide.with(this)
                .load(uri)
                .into(civProfileImage);
    }

    @Override
    public void showUserPhotoUploadFailed() {
        Toast.makeText(this, getString(R.string.upload_photo_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
