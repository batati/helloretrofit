package fi.solita.helloretrofit;


import android.content.Context;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import fi.solita.helloretrofit.dependency.application.AppComponent;
import fi.solita.helloretrofit.data.DataManager;
import fi.solita.helloretrofit.dependency.TestAppModule;
import fi.solita.helloretrofit.dependency.DaggerTestAppComponent;
import fi.solita.helloretrofit.dependency.TestNetworkModule;

/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 * <p/>
 * https://github.com/hitherejoe/Bourbon/blob/73cfa3b076e559e695eeae7c0609507cab5908d6/CoreCommon/src/main/java/com/hitherejoe/bourboncorecommon/injection/component/TestComponentRule.java
 */
public class DaggerRule implements TestRule {

    private final Context context;
    private AppComponent applicationComponent = null;

    public DaggerRule(Context context) {
        this.context = context;
        applicationComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(MVPApplication.get(context)))
                .testNetworkModule(new TestNetworkModule())
                .build();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                MVPApplication mvpApplication = ((MVPApplication)
                        context.getApplicationContext());

                mvpApplication.setAppComponent(applicationComponent);
                base.evaluate();
                mvpApplication.setAppComponent(null);
            }
        };
    }

    public DataManager mockDataManager() {
        return applicationComponent.dataManager();
    }

}
