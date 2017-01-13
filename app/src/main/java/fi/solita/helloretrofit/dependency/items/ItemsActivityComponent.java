package fi.solita.helloretrofit.dependency.items;

import dagger.Subcomponent;
import fi.solita.helloretrofit.views.items.ItemsActivityView;

/**
 * Created by eetupa on 30/08/16.
 */
@ItemsActivityScope
@Subcomponent(modules = {ItemsActivityModule.class})
public interface ItemsActivityComponent {
    void inject(ItemsActivityView itemsActivityView);
}
