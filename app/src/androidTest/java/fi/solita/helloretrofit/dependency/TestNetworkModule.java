package fi.solita.helloretrofit.dependency;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fi.solita.helloretrofit.data.DataManager;

/**
 * Created by eetupa on 01/09/16.
 */
@Module
public class TestNetworkModule {

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return Mockito.mock(DataManager.class);
    }
}
