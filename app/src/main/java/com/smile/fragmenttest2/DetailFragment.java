package com.smile.fragmenttest2;

import android.os.Bundle;
// import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lee on 2/20/2016.
 */
public class DetailFragment extends Fragment {

    TextView detailText = null;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.layout_for_detail_fragment,container,false);
        detailText = (TextView) view.findViewById(R.id.textDetail);

        System.out.println("DetailFragment is created.");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("DetailFragment onDestroyView() is called");
    }

    public void setText(String item) {
        detailText.setText(item);
    }

    public void setTextColor(int color) {
        detailText.setTextColor(color);
    }
}