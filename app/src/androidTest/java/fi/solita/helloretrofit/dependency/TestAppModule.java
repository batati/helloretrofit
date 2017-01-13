package fi.solita.helloretrofit.dependency;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eetupa on 13/09/16.
 */
@Module
public class TestAppModule {
    private Application application;

    public TestAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }
}
