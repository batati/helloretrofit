package fi.solita.helloretrofit;

import android.app.Application;
import android.content.Context;

import fi.solita.helloretrofit.dependency.application.AppComponent;
import fi.solita.helloretrofit.dependency.application.DaggerAppComponent;


/**
 * Created by eetupa on 25/08/16.
 */
public class MVPApplication extends Application {

    private AppComponent appComponent;

    public static MVPApplication get(Context context) {
        return (MVPApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .build();
        }
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
