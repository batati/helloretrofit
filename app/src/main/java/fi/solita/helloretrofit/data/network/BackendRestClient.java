package fi.solita.helloretrofit.data.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fi.solita.helloretrofit.models.items.ItemHolder;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by eetupa on 25/08/16.
 */
public class BackendRestClient {

    private BackendRestService backendRestService;

    private static final String TAG = "RETRORX";
    private static final long TIMEOUT_SECONDS = 5;

    public BackendRestClient(BackendRestService backendRestService) {
        this.backendRestService = backendRestService;
    }

    public Observable<ItemHolder> getItems(String serial) {
        return backendRestService.getItems(serial)
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                //Returning Observable.just(null) causes retrying until timeout
                                //while returning the error stops retrying
                                if (throwable instanceof IOException)
                                    return Observable.just(null);
                                return Observable.error(throwable);
                            }
                        });
                    }
                })
                //If there is no network connection, the request is timeouted.
                .timeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                ;
    }

}

//For cache. If not used we can just call getItems(String serial) straight
    /*
    private LruCache<Class<?>, Observable<?>> apiObservables;

    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable, Class<?> type, boolean cacheObservable, boolean useCache) {
        Observable<?> preparedObservable = null;
        if (useCache)//this way we don't reset anything in the cache if this is the only instance of us not wanting to use it.
            preparedObservable = apiObservables.get(type);
        if (preparedObservable == null) {
            //we are here because we have never created this observable before or we didn't want to use the cache.
            preparedObservable = unPreparedObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
            if (cacheObservable) {
                preparedObservable = preparedObservable.cache();
                apiObservables.put(type, preparedObservable);
            }
        }
        return preparedObservable;
    }

    public void clearCache() {
        apiObservables.evictAll();
    }
*/