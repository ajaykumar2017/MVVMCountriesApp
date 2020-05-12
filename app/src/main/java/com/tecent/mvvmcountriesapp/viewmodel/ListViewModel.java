package com.tecent.mvvmcountriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.tecent.mvvmcountriesapp.model.CountryModel;
import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {
  public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<List<CountryModel>>();
  public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
  public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

  public void refresh() {
    fetchCountries();
  }

  private void fetchCountries() {
    CountryModel country1 = new CountryModel("India", "Delhi", "");
    CountryModel country2 = new CountryModel("India2", "Delhi2", "");
    CountryModel country3 = new CountryModel("India3", "Delhi3", "");

    List<CountryModel> list = new ArrayList<>();
    list.add(country1);
    list.add(country2);
    list.add(country3);

    countries.setValue(list);
    countryLoadError.setValue(false);
    loading.setValue(false);

  }
}
