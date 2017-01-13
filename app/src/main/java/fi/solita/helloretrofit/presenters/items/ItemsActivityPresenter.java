package fi.solita.helloretrofit.presenters.items;

import android.util.Log;

import fi.solita.helloretrofit.Presenter;
import fi.solita.helloretrofit.data.DataManager;
import fi.solita.helloretrofit.models.items.Item;
import fi.solita.helloretrofit.models.items.ItemHolder;
import fi.solita.helloretrofit.views.items.ItemsMVPView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ItemsActivityPresenter implements Presenter<ItemsMVPView> {

    private static final String SERIAL = "R52H302X4YW";
    private static final String TAG = "ItemsActivityPresenter";
    private ItemsMVPView view;
    private Subscription subscription;
    private DataManager dataManager;
    private ItemHolder itemHolder;

    public ItemsActivityPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(ItemsMVPView mvpView) {
        this.view = mvpView;
    }

    @Override
    public void detachView() {
        if (subscription != null) subscription.unsubscribe();
    }

    public void loadItems() {
        view.showProgress(true);
        subscription = dataManager.getItems(SERIAL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ItemHolder>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                        view.showProgress(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                        view.itemDownloadFailed(e.getMessage());
                        view.showProgress(false);
                    }

                    @Override
                    public void onNext(ItemHolder itemHolder) {
                        ItemsActivityPresenter.this.itemHolder = itemHolder;
                        Log.d(TAG, "onNext: " + itemHolder.getData().size());
                        view.itemDownloadSucceeded(itemHolder);
                    }
                });
    }

    public void itemSelected(int position) {
        view.showDetails(position, itemHolder);
    }

    /*
    public void failToLoadItems() {
        view.showProgress(true);
        subscription = dataManager.getItems("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ItemHolder>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                        view.showProgress(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                        view.itemDownloadFailed(e.getMessage());
                        view.showProgress(false);
                    }

                    @Override
                    public void onNext(ItemHolder itemHolder) {
                        Log.d(TAG, "onNext: " + itemHolder.getData().size());
                        view.itemDownloadSucceeded(itemHolder);
                    }
                });
    }
    */

}
