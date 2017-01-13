package fi.solita.helloretrofit.dependency.items;

import dagger.Module;
import dagger.Provides;
import fi.solita.helloretrofit.data.DataManager;
import fi.solita.helloretrofit.presenters.items.ItemsActivityPresenter;

/**
 * Created by eetupa on 30/08/16.
 */
@Module
public class ItemsActivityModule {

    @Provides
    @ItemsActivityScope
    ItemsActivityPresenter provideItemPresenter(DataManager dataManager) {
        return new ItemsActivityPresenter(dataManager);
    }
}
