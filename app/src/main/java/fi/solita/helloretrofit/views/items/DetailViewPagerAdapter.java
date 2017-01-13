package fi.solita.helloretrofit.views.items;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import fi.solita.helloretrofit.models.items.Item;

/**
 * Created by eetupa on 15/09/16.
 */
public class DetailViewPagerAdapter extends FragmentPagerAdapter {

    List<Item> items;

    public DetailViewPagerAdapter(FragmentManager fm, List<Item> items) {
        super(fm);
        this.items = items;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailFragmentView.newInstance(position, items.get(position));
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
