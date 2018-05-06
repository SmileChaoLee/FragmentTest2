package com.smile.fragmenttest2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
// import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListAdaptorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListAdaptorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListAdaptorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String TAG = "FragmentListAdaptor";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private DetailFragment detailFragment = null;

    public ListAdaptorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListAdaptor.
     */
    // TODO: Rename and change types and number of parameters
    public static ListAdaptorFragment newInstance(String param1, String param2) {
        ListAdaptorFragment fragment = new ListAdaptorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Log.i(TAG, "Created.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_list_adaptor, container, false);
        LinearLayout parentLayout = view.findViewById(R.id.adaptorFragmentLayout);

        String[] dataSet = {"Chao Lee", "David Cheng", "John Oliver"};

        ArrayAdapter<String> nameListAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.layout_item_for_adaptor,
                R.id.textViewForItem,
                dataSet);

        ListView nameListView = new ListView(getActivity());

        nameListView.setAdapter(nameListAdapter);

        nameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int color = Color.GRAY;
                String item = "Hello ";
                item += (String)parent.getItemAtPosition(position);
                switch (position) {
                    case 0:
                        color = Color.RED;
                        break;
                    case 1:
                        color = Color.BLACK;
                        break;
                    case 2:
                        color = Color.BLUE;
                        break;
                    default:
                }
                showDetail(color,item);
            }
        });

        parentLayout.addView(nameListView);

        // LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // parentLayout.setLayoutParams(layoutParams);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detailFragment =  (DetailFragment)getFragmentManager().findFragmentById(R.id.fragment_detail);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            // throw new RuntimeException(context.toString()
            //         + " must implement OnFragmentInteractionListener");
            mListener = new OnFragmentInteractionListener() {
                @Override
                public void onFragmentInteraction(Uri uri) {
                    System.out.println("FragmentListAdaptor --> Uri = " + uri);
                }
            };
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void showDetail(int color ,String item) {
        if (detailFragment != null) {
            if (detailFragment.isInLayout()) {
                detailFragment.setTextColor(color);
                detailFragment.setText(item);
            }
        } else {
            Intent intent = new Intent(getActivity().getApplicationContext(),DetailActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("colorContent",color);
            extras.putString("itemContent",item);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }
}
