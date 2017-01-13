package fi.solita.helloretrofit.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.TimeoutException;

import fi.solita.helloretrofit.data.network.BackendRestClient;
import fi.solita.helloretrofit.data.network.BackendRestService;
import fi.solita.helloretrofit.models.items.ItemHolder;
import fi.solita.helloretrofit.utils.ExceptionUtils;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasXPath;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.TestUtils.ITEM_TITLE1;
import static utils.TestUtils.ITEM_TITLE2;
import static utils.TestUtils.getHolder;
import static utils.TestUtils.size;

/**
 * Created by eetupa on 01/09/16.
 */
public class BackendRestClientTest {

    private static final String TAG = "BackendRestClientTest";

    @Mock
    BackendRestService backendRestService;

    private BackendRestClient backendRestClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        backendRestClient = new BackendRestClient(backendRestService);
    }

    private static final String SERIAL = "R52H502GEMY";

    @Test
    public void getItems_returnsItemHolder_CorrectApiCallsCalled() {
        //given
        when(backendRestService.getItems(anyString())).thenReturn(Observable.just(getHolder()));

        //when
        TestSubscriber<ItemHolder> testSubscriber = new TestSubscriber<>();
        backendRestClient.getItems(SERIAL).subscribe(testSubscriber);

        //then
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();

        List<ItemHolder> itemHolderList = testSubscriber.getOnNextEvents();
        ItemHolder itemHolder = itemHolderList.get(0);

        assertThat(itemHolder.getData(), hasSize(size));
        assertThat(itemHolder.getData().get(0).getTitle(), is(equalTo(ITEM_TITLE1)));
        assertThat(itemHolder.getData().get(1).getTitle(), is(equalTo(ITEM_TITLE2)));
        verify(backendRestService).getItems(anyString());
    }

    @Test
    public void getItems_returnsIOException_searchTimeouts() {
        //given
        when(backendRestService.getItems(anyString())).thenReturn(ExceptionUtils.getIOExceptionError());

        //when
        TestSubscriber<ItemHolder> testSubscriber = new TestSubscriber<>();
        backendRestClient.getItems(SERIAL).subscribe(testSubscriber);

        //then
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(TimeoutException.class);

        verify(backendRestService).getItems(SERIAL);
    }

    @Test
    public void getItems_returnsHttpError_searchTerminated() {
        //given
        when(backendRestService.getItems(anyString())).thenReturn(ExceptionUtils.get400BadRequestError());

        //when
        TestSubscriber<ItemHolder> testSubscriber = new TestSubscriber<>();
        backendRestClient.getItems(SERIAL).subscribe(testSubscriber);

        //then
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(HttpException.class);

        verify(backendRestService).getItems(SERIAL);
    }


}