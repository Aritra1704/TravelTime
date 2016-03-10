package com.example.arpaul.traveltime.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.arpaul.traveltime.DataObject.TravelDO;
import com.example.arpaul.traveltime.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ARPaul on 10-03-2016.
 */
public class DestinationAdapter extends BaseAdapter {
    private Context context;
    private List<TravelDO> mTravelDOs = new ArrayList<>();

    public DestinationAdapter(Context context,List<TravelDO> listTravelDOs){
        this.context = context;
        mTravelDOs = listTravelDOs;
    }

    public void clear() {
        mTravelDOs.clear();
    }

    public void addItem(TravelDO yourObject) {
        mTravelDOs.add(yourObject);
    }

    public void addItems(List<TravelDO> yourObjectList) {
        mTravelDOs.addAll(yourObjectList);
    }

    @Override
    public int getCount() {
        return mTravelDOs.size();
    }

    @Override
    public Object getItem(int position) {
        return mTravelDOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
            view = LayoutInflater.from(context).inflate(R.layout.toolbar_spinner_item_dropdown, parent, false);
            view.setTag("DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));

        return view;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
            view = LayoutInflater.from(context).inflate(R.layout.toolbar_spinner_item_actionbar, parent, false);
            view.setTag("NON_DROPDOWN");
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));
        return view;
    }

    private String getTitle(int position) {
        return position >= 0 && position < mTravelDOs.size() ? mTravelDOs.get(position).name : "";
    }
}
