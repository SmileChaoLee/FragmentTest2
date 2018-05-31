package com.smile.fragmenttest2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
// import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by lee on 2/20/2016.
 */
public class ListButtonsFragment extends Fragment {

    private static final String TAG = "FragmentListButtons";

    private Button buttonOne = null, buttonTwo = null, buttonThree = null;
    private DetailFragment detailFragment = null;

    public ListButtonsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "ListButtonsFragment's onCreated() called and fragment is created.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_for_ListButtonsFragment, container, false);
        buttonOne = (Button) view.findViewById(R.id.buttonOne);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = Color.RED;
                String item = new String("Welcome to Library one and enjoy your reading and searching");
                showDetail(color,item);
            }
        });
        buttonTwo = (Button) view.findViewById(R.id.buttonTwo);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = Color.BLACK;
                String item = new String("Welcome to Library two and enjoy your reading and searching");
                showDetail(color,item);
            }
        });
        buttonThree = (Button) view.findViewById(R.id.buttonThree);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = Color.BLUE;
                String item = new String("Welcome to Library three and enjoy your reading and searching");
                showDetail(color,item);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // if portrait then no R.id.fragment.derail -> fragmentDetail will be null
            // else there is R.id.fragment.detail -> fragmentDetail will not be null
            detailFragment = (DetailFragment)getFragmentManager().findFragmentById(R.id.fragment_detail);

            Log.d("ListButtonsFragment", "onActivityCreated");
    }

    private void showDetail(int color ,String item) {

        if (detailFragment != null) {
            // for Landscape
            Log.d("showDetail", "not null");
            if (detailFragment.isInLayout()) {
                detailFragment.setTextColor(color);
                detailFragment.setText(item);
            }
        } else {
            // for portrait
            Log.d("showDetail", "null");
            Intent intent = new Intent(getActivity().getApplicationContext(),DetailActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("colorContent",color);
            extras.putString("itemContent",item);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }
}
