package ru.englishcat24.ui.activities.homePage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import ru.englishcat24.R;
import ru.englishcat24.core.BaseActivity;
import ru.englishcat24.ui.activities.about.AboutActivity;
import ru.englishcat24.ui.activities.profile.ProfileActivity;
import ru.englishcat24.ui.activities.questionPage.QuestionActivity;
import ru.englishcat24.ui.activities.ratingPage.RatingActivity;
import ru.englishcat24.ui.activities.signIn.SignInActivity;

import static ru.englishcat24.ui.Constants.CARD_BACKGROUND;
import static ru.englishcat24.ui.Constants.ENGLISH_WORDS;
import static ru.englishcat24.ui.Constants.RUSSIAN_WORDS;
import static ru.englishcat24.ui.Constants.WORDS_LANGUAGE;

public class HomeActivity extends BaseActivity implements HomeView, NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.cvEnglishWords)
    CardView cvEnglishWords;
    @BindView(R.id.cvRussianWords)
    CardView cvRussianWords;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle("");
        tvTitle.setText(R.string.cat_english);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);

        RxView.clicks(cvEnglishWords).subscribe(o -> {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra(WORDS_LANGUAGE, ENGLISH_WORDS);
            intent.putExtra(CARD_BACKGROUND, R.color.colorBlue);

            startActivity(intent);
        });

        RxView.clicks(cvRussianWords).subscribe(o -> {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra(WORDS_LANGUAGE, RUSSIAN_WORDS);
            intent.putExtra(CARD_BACKGROUND, R.color.colorLightBlue);

            startActivity(intent);
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.person) {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else {
                startActivity(new Intent(this, SignInActivity.class));
            }
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.feedback:
                openGooglePlay();
                break;
            case R.id.invite:
                inviteFriend();
                break;
            case R.id.best_players:
                startActivity(new Intent(this, RatingActivity.class));
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openGooglePlay() {
        String url = "https://play.google.com/store/apps/details?id=" + getPackageName();
        String googlePlayUrl = "market://details?id=" + getPackageName();

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(googlePlayUrl)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    private void inviteFriend() {
        String url = "https://play.google.com/store/apps/details?id=" + getPackageName();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.invite_text, url));
        intent.setType("text/plain");

        startActivity(intent);
    }
}