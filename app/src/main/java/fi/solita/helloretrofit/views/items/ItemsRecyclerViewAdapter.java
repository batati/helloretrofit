package fi.solita.helloretrofit.views.items;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fi.solita.helloretrofit.R;
import fi.solita.helloretrofit.models.items.Item;

/**
 * Created by eetupa on 06/09/16.
 */
public class ItemsRecyclerViewAdapter extends RecyclerView.Adapter<ItemsRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "ItemsRecyclerViewAdap";

    private ArrayList<Item> items;
    private RecyclerViewItemClickListener itemClickListener;

    // Provide a reference to the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
            mTextView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Log.d(TAG, "Item clicked: "+mTextView.getText());
            itemClickListener.onItemSelected(getAdapterPosition());
        }
    }

    public ItemsRecyclerViewAdapter(List<Item> items, RecyclerViewItemClickListener itemClickListener) {
        this.items = (ArrayList<Item>) items;
        this.itemClickListener = itemClickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        // create a new view
        TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new ViewHolder(tv);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(items.get(position).getTitle());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }
}



