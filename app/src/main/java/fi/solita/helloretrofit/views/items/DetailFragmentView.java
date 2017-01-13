package fi.solita.helloretrofit.views.items;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fi.solita.helloretrofit.R;
import fi.solita.helloretrofit.models.items.Item;


public class DetailFragmentView extends Fragment {

    private static final String KEY_POSITION = "position";
    private static final String KEY_ITEM = "item";

    //Position for receiving item from presenter
    private int position;

    //Item received straight from the adapter
    private Item item;
    private Unbinder unbinder;

    @BindView(R.id.detailview_title)
    TextView titleView;

    private OnFragmentInteractionListener mListener;

    public DetailFragmentView() {
        // Required empty public constructor
    }

    public static DetailFragmentView newInstance(int position, Item item) {
        DetailFragmentView fragment = new DetailFragmentView();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putParcelable(KEY_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            position = getArguments().getInt(KEY_POSITION);
            item = getArguments().getParcelable(KEY_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail_fragment_view, container, false);
        unbinder = ButterKnife.bind(this, v);
        titleView.setText(item.getTitle());
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
