package ru.englishcat24.network;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Crish on 09.12.2017.
 */

public interface Api {
    @GET("service/get-regions")
    Observable<String> getHistoricalPlaces(@Query("lang") int lang);

    @GET("service/get-country-goodness")
    Observable<String> getAboutUzbekistan(@Query("lang") int lang);

    @GET("service/get-categories")
    Observable<String> getCategories(@Query("lang") int lang, @Query("parent") long parent);

    @GET("service/get-currency")
    Observable<String> getExchangeRates();

    @GET("service/get-economy")
    Observable<String> getDiscounts(@Query("lang") int lang);

    @GET("service/get-weather")
    Observable<String> getWeather(@Query("lang") int lang);

    @GET("service/user-register")
    Observable<String> signUp(@Query("lang") int lang, @Query("username") String userName, @Query("email") String email, @Query("name") String name, @Query("phone") String phone, @Query("address") String address, @Query("password") String password);

    @GET("service/user-login")
    Observable<String> signIn(@Query("lang") int lang, @Query("username") String userName, @Query("password") String password);

    @GET("service/user-recovery")
    Observable<String> restorePassword(@Query("lang") int lang, @Query("email") String email);

    @GET("service/page-detail?id=241")
    Observable<String> getAboutService(@Query("lang") int lang);

    @GET("service/page-detail?id=8")
    Observable<String> getPublicOffer(@Query("lang") int lang);

    @GET("service/page-detail?id=114")
    Observable<String> getHowToUse(@Query("lang") int lang);

    @GET("service/page-detail?id=244")
    Observable<String> getInsurance(@Query("lang") int lang);

    @POST("service/activate-card")
    @FormUrlEncoded
    Observable<String> activateDiscountCard(@Query("lang") int lang, @Field("serial") String serialNumber, @Field("secret") String activationCode, @Field("device_id") String deviceId);

    @GET("service/page-detail?id=246")
    Observable<String> getTouristInformationCard(@Query("lang") int lang);

    @GET("service/sales-list")
    Observable<String> getCompanies(@Query("lang") int lang, @Query("search_text") String searchText, @Query("category_id") Long categoryId, @Query("region_id") Long regionId, @Query("district_id") Long districtId, @Query("page") int page, @Query("lat") double latitude, @Query("lon") double longitude);

    @GET("service/sale-detail")
    Observable<String> getCompanyDescription(@Query("lang") int lang, @Query("sale_id") long saleId);

    @GET("service/search-data")
    Observable<String> getSearch(@Query("lang") int lang);

    @GET("service/region-detail")
    Observable<String> getRegionDescription(@Query("lang") int lang, @Query("id") long id);

    @GET("service/sight-detail")
    Observable<String> getSightDescription(@Query("lang") int lang, @Query("id") long id);

    @GET("service/languages")
    Observable<String> getLanguages();

    @GET("service/user-favourite-list")
    Observable<String> getFavorites(@Query("lang") int lang, @Query("user_id") long userId);

    @POST("service/user-favourite-add")
    @FormUrlEncoded
    Observable<String> addFavorite(@Field("user_id") long userId, @Field("sale_id") long saleId);

    @POST("service/user-favourite-delete")
    @FormUrlEncoded
    Observable<String> removeFavorite(@Field("user_id") long userId, @Field("sale_id") long saleId);

    @GET("https://maps.googleapis.com/maps/api/directions/json")
    Observable<String> getDirections(@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key);
}
