package fi.solita.helloretrofit.presenters;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Random;

import fi.solita.helloretrofit.data.DataManager;
import fi.solita.helloretrofit.models.items.Item;
import fi.solita.helloretrofit.models.items.ItemHolder;
import fi.solita.helloretrofit.presenters.items.ItemsActivityPresenter;
import fi.solita.helloretrofit.utils.ExceptionUtils;
import fi.solita.helloretrofit.utils.RxSchedulersOverrideRule;
import fi.solita.helloretrofit.views.items.ItemsMVPView;
import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.TestUtils.getHolder;

/**
 * Created by eetupa on 05/09/16.
 */
public class ItemsActivityPresenterTest {

    private static final String HTTP_400_MESSAGE = "HTTP 400 null";

    @Mock
    DataManager dataManager;
    @Mock
    ItemsMVPView view;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    ItemsActivityPresenter presenter;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        presenter = new ItemsActivityPresenter(dataManager);
        presenter.attachView(view);
    }

    @Test
    public void loadItems_returnsItemHolder_returnResults() {
        ItemHolder itemHolder = getHolder();
        when(dataManager.getItems(anyString())).thenReturn(Observable.just(itemHolder));

        presenter.loadItems();

        verify(view).showProgress(true);
        verify(view).itemDownloadSucceeded(itemHolder);
        verify(view).showProgress(false);
        verify(view, never()).itemDownloadFailed(anyString());
    }

    @Test
    public void loadItems_returnsNull_callItemDownloadFailed() {
        when(dataManager.getItems(anyString())).thenReturn(Observable.<ItemHolder>just(null));

        presenter.loadItems();

        verify(view).showProgress(true);
        verify(view).itemDownloadFailed(null);
        verify(view).showProgress(false);
        verify(view, never()).itemDownloadSucceeded(getHolder());
    }

    @Test
    public void loadItems_returnsHttpError400_callItemDownloadFailed() {
        when(dataManager.getItems(anyString())).thenReturn(ExceptionUtils.get400BadRequestError());

        presenter.loadItems();

        verify(view).showProgress(true);
        verify(view).itemDownloadFailed(HTTP_400_MESSAGE);
        verify(view).showProgress(false);
        verify(view, never()).itemDownloadSucceeded(getHolder());
    }

    @Test
    public void itemSelected_showDetailsCalled() {
        ItemHolder itemHolder = getHolder();

        when(dataManager.getItems(anyString())).thenReturn(Observable.just(itemHolder));

        presenter.loadItems();

        int i = new Random().nextInt(50 - 1) + 1;

        presenter.itemSelected(i);
        verify(view).showDetails(i, itemHolder);
    }


}