package com.example.arpaul.traveltime.Pinch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arpaul.traveltime.R;

/**
 * Created by ARPaul on 12-03-2016.
 */
public class MainScreenFragment extends Fragment {

    private int myInt;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        myInt = bundle.getInt("fragment_number", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView tvFragment = (TextView) rootView.findViewById(R.id.tvFragment);

        tvFragment.setText("This is Fragment "+(myInt+1));
        tvFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This is Fragment "+(myInt+1),Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}

