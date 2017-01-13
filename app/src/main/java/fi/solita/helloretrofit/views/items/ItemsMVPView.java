package fi.solita.helloretrofit.views.items;

import fi.solita.helloretrofit.MVPView;
import fi.solita.helloretrofit.models.items.Item;
import fi.solita.helloretrofit.models.items.ItemHolder;

/**
 * Created by eetupa on 30/08/16.
 */
public interface ItemsMVPView extends MVPView{
    void itemDownloadSucceeded(ItemHolder itemHolder);
    void itemDownloadFailed(String e);
    void showProgress(boolean progressing);
    void showDetails(int position, ItemHolder itemHolder);
}
