package com.tecent.mvvmcountriesapp.model;

import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;

public interface CountriesApi {
  @GET("DevTides/countries/master/countriesV2.json")
  Single<List<CountryModel>> getCountries();
}
