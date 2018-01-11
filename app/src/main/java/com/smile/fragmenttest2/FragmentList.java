package com.smile.fragmenttest2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
// import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by lee on 2/20/2016.
 */
public class FragmentList extends Fragment {

    private Button buttonOne=null, buttonTwo=null, buttonThree=null;
    private FragmentDetail fragmentDetail=null;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_fragment, container, false);
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
            fragmentDetail =  (FragmentDetail)getFragmentManager().findFragmentById(R.id.detail_Fragment);
    }

    private void showDetail(int color ,String item) {
        if (fragmentDetail != null) {
            if (fragmentDetail.isInLayout()) {
                fragmentDetail.setTextColor(color);
                fragmentDetail.setText(item);
                return;
            }
        }
        Intent intent = new Intent(getActivity().getApplicationContext(),DetailActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("colorContent",color);
        extras.putString("itemContent",item);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
