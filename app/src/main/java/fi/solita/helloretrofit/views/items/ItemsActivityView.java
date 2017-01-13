package fi.solita.helloretrofit.views.items;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fi.solita.helloretrofit.utils.IntentUtils;
import fi.solita.helloretrofit.MVPApplication;
import fi.solita.helloretrofit.R;
import fi.solita.helloretrofit.models.items.ItemHolder;
import fi.solita.helloretrofit.dependency.items.ItemsActivityModule;
import fi.solita.helloretrofit.presenters.items.ItemsActivityPresenter;

public class ItemsActivityView extends AppCompatActivity implements ItemsMVPView, RecyclerViewItemClickListener {

    private static final String TAG = "ItemsActivityView";
    private static final String EXTRA_DOWNLOADING = "Downloading";

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.Adapter adapter;
    private boolean itemsShouldBeLoaded = false;

    @Inject
    ItemsActivityPresenter presenter;

    @BindView(R.id.button1)
    Button button;
    //@BindView(R.id.button2)
    //Button buttonFail;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.textview_selection)
    TextView selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MVPApplication.get(this)
                .getAppComponent()
                .plus(new ItemsActivityModule())
                .inject(this);

        presenter.attachView(this);

        if (savedInstanceState != null) {
            itemsShouldBeLoaded = savedInstanceState.getBoolean(EXTRA_DOWNLOADING);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (itemsShouldBeLoaded) presenter.loadItems();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EXTRA_DOWNLOADING, itemsShouldBeLoaded);
    }

    @OnClick(R.id.button1)
    public void loadData() {
        Log.d(TAG, "loadData: button1 clicked");
        itemsShouldBeLoaded = true;
        presenter.loadItems();
    }

    /*
    @OnClick(R.id.button2)
    public void failToLoadData() {
        Log.d(TAG, "failToLoadData: button2 clicked");
        //presenter.failToLoadItems();
    }
    */

    @Override
    public void showProgress(boolean progressing) {
        if (progressing) {
            button.setEnabled(false);
            //buttonFail.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            button.setEnabled(true);
            //buttonFail.setEnabled(true);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void itemDownloadFailed(String e) {
        //tv.setText("Downloading has failed: " + e);
    }

    @Override
    public void itemDownloadSucceeded(ItemHolder itemHolder) {
        initRecyclerView(itemHolder);
    }

    private void initRecyclerView(ItemHolder itemHolder) {
        Log.d(TAG, "initRecyclerView: "+itemHolder.getData().size());
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ItemsRecyclerViewAdapter(itemHolder.getData(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(int position) {
        presenter.itemSelected(position);
    }

    @Override
    public void showDetails(int position, ItemHolder itemHolder) {
        //selection.setText(item.getTitle());
        Intent intent = new Intent(ItemsActivityView.this, DetailActivityView.class);
        intent.putExtra(IntentUtils.EXTRA_POSITION, position);
        intent.putExtra(IntentUtils.EXTRA_ITEMHOLDER, itemHolder);
        startActivity(intent);
    }
}

