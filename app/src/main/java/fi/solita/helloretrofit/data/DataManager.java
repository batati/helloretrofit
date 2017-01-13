package fi.solita.helloretrofit.data;

import java.util.List;

import javax.inject.Singleton;

import fi.solita.helloretrofit.data.network.BackendRestClient;
import fi.solita.helloretrofit.models.items.Item;
import fi.solita.helloretrofit.models.items.ItemHolder;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by eetupa on 29/08/16.
 */

@Singleton
public class DataManager {

    private BackendRestClient backendRestClient;

    public DataManager(BackendRestClient backendRestClient) {
        this.backendRestClient = backendRestClient;
    }

    public Observable<ItemHolder> getItems(String serial) {
        return backendRestClient.getItems(serial)
                .flatMap(new Func1<ItemHolder, Observable<List<Item>>>() {
                    @Override
                    public Observable<List<Item>> call(ItemHolder itemHolder) {
                        return Observable.just(itemHolder.getData());
                    }
                }).flatMap(new Func1<List<Item>, Observable<Item>>() {
                    @Override
                    public Observable<Item> call(List<Item> items) {
                        return Observable.from(items);
                    }
                }).filter(new Func1<Item, Boolean>() {
                    @Override
                    public Boolean call(Item item) {
                        return !item.getContentCategory().equals("magazines");
                    }
                }).map(new Func1<Item, Item>() {
                    @Override
                    public Item call(Item item) {
                        String s = item.getTitle().toUpperCase();
                        item.setTitle(s);
                        return item;
                    }
                })
                .toSortedList(new Func2<Item, Item, Integer>() {
                    @Override
                    public Integer call(Item item, Item item2) {
                        return item.getTitle().compareTo(item2.getTitle());
                    }
                })
                .flatMap(new Func1<List<Item>, Observable<ItemHolder>>() {
                    @Override
                    public Observable<ItemHolder> call(List<Item> items) {
                        ItemHolder itemHolder = new ItemHolder();
                        itemHolder.setData(items);
                        return Observable.just(itemHolder);
                    }
                });
    }
}
