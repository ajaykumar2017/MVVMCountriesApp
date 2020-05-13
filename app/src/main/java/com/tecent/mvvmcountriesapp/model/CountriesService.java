package com.tecent.mvvmcountriesapp.model;
import com.tecent.mvvmcountriesapp.di.DaggerApiComponent;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

public class CountriesService {

  private static CountriesService instance;

  @Inject
  public CountriesApi api;

  private CountriesService() {
    DaggerApiComponent.create().inject(this);
  }

  public static CountriesService getInstance() {
    if (instance == null) {
      instance = new CountriesService();
    }
    return instance;
  }

  public Single<List<CountryModel>> getCountries() {
    return api.getCountries();
  }
}
