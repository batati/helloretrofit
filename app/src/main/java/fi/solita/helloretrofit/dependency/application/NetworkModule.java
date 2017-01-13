package fi.solita.helloretrofit.dependency.application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fi.solita.helloretrofit.data.DataManager;
import fi.solita.helloretrofit.data.network.BackendRestClient;
import fi.solita.helloretrofit.data.network.BackendRestService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eetupa on 01/09/16.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    BackendRestService provideYellowtabRestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BackendRestService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(BackendRestService.class);
    }

    @Provides
    @Singleton
    BackendRestClient provideNetworkService(BackendRestService backendRestService) {
        return new BackendRestClient(backendRestService);
    }

    @Provides
    @Singleton
    DataManager provideDataManager(BackendRestClient backendRestClient) {
        return new DataManager(backendRestClient);
    }
}
