package fi.solita.helloretrofit.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import fi.solita.helloretrofit.data.DataManager;
import fi.solita.helloretrofit.data.network.BackendRestClient;
import fi.solita.helloretrofit.models.items.ItemHolder;
import fi.solita.helloretrofit.utils.ExceptionUtils;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.TestUtils.ITEM1;
import static utils.TestUtils.ITEM2;
import static utils.TestUtils.ITEM3;
import static utils.TestUtils.getHolderMixedOrder;

/**
 * Created by eetupa on 06/09/16.
 */
public class DataManagerTest {

    @Mock
    BackendRestClient backendRestClient;

    DataManager dataManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dataManager = new DataManager(backendRestClient);
    }

    @Test
    public void getItems_returnsItemHolder_itemsUppercaseAndInRightOrder() {
        when(backendRestClient.getItems(anyString())).thenReturn(Observable.just(getHolderMixedOrder()));

        TestSubscriber<ItemHolder> testSubscriber = new TestSubscriber<>();
        dataManager.getItems(anyString()).subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();

        List<ItemHolder> itemHolderList = testSubscriber.getOnNextEvents();
        ItemHolder itemHolder = itemHolderList.get(0);
        assertThat(itemHolder.getData(), contains(ITEM1, ITEM2, ITEM3));
        verify(backendRestClient).getItems(anyString());
    }

    @Test
    public void getItems_returnsHttpException_error() {
        when(backendRestClient.getItems(anyString())).thenReturn(ExceptionUtils.get400BadRequestError());

        TestSubscriber<ItemHolder> testSubscriber = new TestSubscriber<>();
        dataManager.getItems(anyString()).subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(HttpException.class);

        List<ItemHolder> itemHolderList = testSubscriber.getOnNextEvents();
        assertThat(itemHolderList, hasSize(0));
        verify(backendRestClient).getItems(anyString());
    }

    @Test
    public void getItems_returnsIOException_error() {
        when(backendRestClient.getItems(anyString())).thenReturn(ExceptionUtils.getIOExceptionError());

        TestSubscriber<ItemHolder> testSubscriber = new TestSubscriber<>();
        dataManager.getItems(anyString()).subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(IOException.class);

        List<ItemHolder> itemHolderList = testSubscriber.getOnNextEvents();
        assertThat(itemHolderList, hasSize(0));
        verify(backendRestClient).getItems(anyString());
    }

}