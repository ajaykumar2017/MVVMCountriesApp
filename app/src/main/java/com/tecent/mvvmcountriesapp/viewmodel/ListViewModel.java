package com.tecent.mvvmcountriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.tecent.mvvmcountriesapp.di.DaggerApiComponent;
import com.tecent.mvvmcountriesapp.model.CountriesService;
import com.tecent.mvvmcountriesapp.model.CountryModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class ListViewModel extends ViewModel {
  public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<List<CountryModel>>();
  public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
  public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

  @Inject
  public CountriesService countriesService;
  private CompositeDisposable disposable = new CompositeDisposable();

  public ListViewModel() {
    super();
    DaggerApiComponent.create().inject(this);
  }

  public void refresh() {
    fetchCountries();
  }

  private void fetchCountries() {
    loading.setValue(true);
    disposable.add(
        countriesService.getCountries()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {

          @Override public void onSuccess(List<CountryModel> countryModels) {
            countries.setValue(countryModels);
            countryLoadError.setValue(false);
            loading.setValue(false);
          }

          @Override public void onError(Throwable e) {
            countryLoadError.setValue(true);
            loading.setValue(false);
            e.printStackTrace();
          }
        })
    );
  }

  @Override protected void onCleared() {
    super.onCleared();
    disposable.clear();
  }
}
