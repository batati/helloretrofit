package fi.solita.helloretrofit.dependency.application;

import javax.inject.Singleton;

import dagger.Component;
import fi.solita.helloretrofit.dependency.items.ItemsActivityComponent;
import fi.solita.helloretrofit.data.DataManager;
import fi.solita.helloretrofit.dependency.items.ItemsActivityModule;

/**
 * Created by eetupa on 01/09/16.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    ItemsActivityComponent plus(ItemsActivityModule itemsActivityModule);
    DataManager dataManager();

}
