package com.smile.fragmenttest2;

import android.content.res.Configuration;
import android.os.Bundle;
// import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
// import android.app.Fragment;
// import android.app.FragmentManager;

/**
 * Created by lee on 2/20/2016.
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int color = extras.getInt("colorContent");
            String item = extras.getString("itemContent");
            FragmentDetail fragmentDetail =  (FragmentDetail) getFragmentManager().findFragmentById(R.id.fragment_detail);
            if (fragmentDetail != null) {
                fragmentDetail.setTextColor(color);
                fragmentDetail.setText(item);
            }
        }
    }
}
