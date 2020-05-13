package com.tecent.mvvmcountriesapp.di;

import com.tecent.mvvmcountriesapp.model.CountriesService;
import com.tecent.mvvmcountriesapp.viewmodel.ListViewModel;
import dagger.Component;

@Component(modules = ApiModule.class)
public interface ApiComponent {

  void inject(CountriesService service);

  void inject(ListViewModel viewModel);

}
