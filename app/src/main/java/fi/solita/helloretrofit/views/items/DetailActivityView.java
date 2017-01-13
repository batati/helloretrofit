package fi.solita.helloretrofit.views.items;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import fi.solita.helloretrofit.R;
import fi.solita.helloretrofit.models.items.ItemHolder;
import fi.solita.helloretrofit.utils.IntentUtils;

public class DetailActivityView extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity_view);
        ButterKnife.bind(this);
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true);
        }
        ItemHolder itemHolder = getIntent().getParcelableExtra(IntentUtils.EXTRA_ITEMHOLDER);
        int position = getIntent().getIntExtra(IntentUtils.EXTRA_POSITION, 0);
        setViewPager(itemHolder, position);
    }

    private void setViewPager(ItemHolder itemHolder, int position) {
        DetailViewPagerAdapter adapter = new DetailViewPagerAdapter(getSupportFragmentManager(), itemHolder.getData());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

}
