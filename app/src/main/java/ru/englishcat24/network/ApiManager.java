package ru.englishcat24.network;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Crish on 09.12.2017.
 */

public class ApiManager implements Api {
    private Api api;

    public ApiManager(Api api) {
        this.api = api;
    }

    @Override
    public Observable<String> getHistoricalPlaces(int lang) {
        return api.getHistoricalPlaces(lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getAboutUzbekistan(int lang) {
        return api.getAboutUzbekistan(lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getDiscounts(int lang) {
        return api.getDiscounts(lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getWeather(int lang) {
        return api.getWeather(lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getExchangeRates() {
        return api.getExchangeRates().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> signUp(int lang, String userName, String email, String name, String phone, String address, String password) {
        return api.signUp(lang, userName, email, name, phone, address, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> signIn(int lang, String userName, String password) {
        return api.signIn(lang, userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> restorePassword(int lang, String email) {
        return api.restorePassword(lang, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getAboutService(int lang) {
        return api.getAboutService(lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getPublicOffer(int lang) {
        return api.getPublicOffer(lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getHowToUse(int lang) {
        return api.getHowToUse(lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getInsurance(int lang) {
        return api.getInsurance(lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> activateDiscountCard(int lang, String serialNumber, String activationCode, String deviceId) {
        return api.activateDiscountCard(lang, serialNumber, activationCode, deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getTouristInformationCard(int lang) {
        return api.getTouristInformationCard(lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getCompanies(int lang, String searchText, Long categoryId, Long regionId, Long districtId, int page, double latitude, double longitude) {
        return api.getCompanies(lang, searchText, categoryId, regionId, districtId, page, latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getCompanyDescription(int lang, long saleId) {
        return api.getCompanyDescription(lang, saleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getSearch(int lang) {
        return api.getSearch(lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> getRegionDescription(int lang, long id) {
        return api.getRegionDescription(lang, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getSightDescription(int lang, long id) {
        return api.getSightDescription(lang, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getLanguages() {
        return api.getLanguages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getCategories(int lang, long parent) {
        return api.getCategories(lang, parent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getFavorites(int lang, long userId) {
        return api.getFavorites(lang, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> addFavorite(long userId, long saleId) {
        return api.addFavorite(userId, saleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> removeFavorite(long userId, long saleId) {
        return api.removeFavorite(userId, saleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getDirections(String origin, String destination, String key) {
        return api.getDirections(origin, destination, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
