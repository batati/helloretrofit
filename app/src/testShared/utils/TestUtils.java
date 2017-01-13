package utils;

import java.util.ArrayList;

import fi.solita.helloretrofit.models.items.Item;
import fi.solita.helloretrofit.models.items.ItemHolder;

public class TestUtils {
    public static final String ITEM_TITLE1 = "TestItem1";
    public static final String ITEM_TITLE2 = "TestItem2";
    public static final String ITEM_TITLE3 = "TestItem3";
    public static final String ITEM_CATEGORY1 = "TestCategory1";
    public static final String ITEM_CATEGORY2 = "TestCategory2";
    public static final String ITEM_CATEGORY3 = "TestCategory3";
    public static final Item ITEM1 = new Item(ITEM_TITLE1, ITEM_CATEGORY1);
    public static final Item ITEM2 = new Item(ITEM_TITLE2, ITEM_CATEGORY2);
    public static final Item ITEM3 = new Item(ITEM_TITLE3, ITEM_CATEGORY3);
    public static final int size = 3;

    public static ItemHolder getHolder() {
        ArrayList<Item> list = new ArrayList<>();
        list.add(ITEM1);
        list.add(ITEM2);
        list.add(ITEM3);
        ItemHolder itemHolder = new ItemHolder();
        itemHolder.setData(list);
        return itemHolder;
    }

    public static ItemHolder getHolderMixedOrder() {
        ArrayList<Item> list = new ArrayList<>();
        list.add(ITEM2);
        list.add(ITEM3);
        list.add(ITEM1);
        ItemHolder itemHolder = new ItemHolder();
        itemHolder.setData(list);
        return itemHolder;
    }
}
