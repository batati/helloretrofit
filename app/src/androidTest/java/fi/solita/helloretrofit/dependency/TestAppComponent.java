package fi.solita.helloretrofit.dependency;

import javax.inject.Singleton;

import dagger.Component;
import fi.solita.helloretrofit.dependency.application.AppComponent;

/**
 * Created by eetupa on 13/09/16.
 */

@Singleton
@Component(modules = {TestNetworkModule.class, TestAppModule.class})
public interface TestAppComponent extends AppComponent {
    //void inject(RecordedItemsActivityViewTest itemActivityTest)
}
