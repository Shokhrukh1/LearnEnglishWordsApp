package ru.englishcat24.model;

import android.support.annotation.Keep;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;

/**
 * Created by crish on 1/25/18.
 */
@Keep
@Data
public class User {
    private String name;
    private int rating;
    private String city;
    private List<String> socialNetworks;
    private String photoURL;
    private String promoCode;

    public User() {
        name = new String();
        rating = 0;
        city = new String();
        socialNetworks = new ArrayList<>();
        photoURL = new String();
    }
}
