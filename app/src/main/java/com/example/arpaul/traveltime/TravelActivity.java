package com.example.arpaul.traveltime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arpaul.traveltime.Adapter.DestinationAdapter;
import com.example.arpaul.traveltime.DataObject.TravelDO;
import com.example.arpaul.traveltime.DataObject.TravelList;
import com.example.arpaul.traveltime.WebServices.DataListener;
import com.example.arpaul.traveltime.WebServices.TravelWebServices;

import java.util.ArrayList;

public class TravelActivity extends AppCompatActivity implements DataListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private DestinationAdapter spinnerAdapter;
    private Spinner spinner;
    private Toolbar toolbar;
    private ProgressDialog ringProgressDialog;
    private TravelWebServices objTravelWebServices;
    private TextView tvModes;
    private ArrayList<TravelDO> listTravelDOs;
    private Button btnNavigate;
    private TravelDO objTravelDO;
    private int spinnerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        initialiseControls();

        objTravelWebServices = new TravelWebServices(TravelActivity.this,TravelActivity.this);
        objTravelWebServices.getTravelData();

        showLoader();

        if (savedInstanceState != null) {
            spinnerPosition = savedInstanceState.getInt("transportModeSpinner", 0);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                objTravelDO = listTravelDOs.get(position);
                updateTransportMode(listTravelDOs.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelActivity.this,MapActivity.class);
                intent.putExtra("latitude",objTravelDO.location.latitude);
                intent.putExtra("longitude",objTravelDO.location.longitude);
                startActivity(intent);
            }
        });
    }

    private void updateTransportMode(TravelDO objTravelDO){
        StringBuilder modes = new StringBuilder();
        if(!TextUtils.isEmpty(objTravelDO.fromcentral.car))
            modes.append("Car - "+objTravelDO.fromcentral.car+"\n");
        if(!TextUtils.isEmpty(objTravelDO.fromcentral.train))
            modes.append("Train - "+objTravelDO.fromcentral.train+"\n");

        tvModes.setText(modes.toString());
    }

    public void showLoader() {
        ringProgressDialog = ProgressDialog.show(TravelActivity.this, "", "Please wait ...", true);
        ringProgressDialog.setCancelable(false);
    }

    private void hideLoader(){
        if(ringProgressDialog != null && ringProgressDialog.isShowing())
            ringProgressDialog.dismiss();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("transportModeSpinner", spinner.getSelectedItemPosition());
    }

    @Override
    public void DataRetrieved(final TravelList data, final int Status) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(Status == TravelWebServices.get_STATUS_SUCCESS()){
                    listTravelDOs = (ArrayList<TravelDO>) data.travels;
                    spinnerAdapter.refresh(listTravelDOs);
                    if(spinnerPosition > 0)
                        spinner.setSelection(spinnerPosition);
                    hideLoader();
                } else
                    hideLoader();
            }
        });

    }

    private void initialiseControls(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerAdapter = new DestinationAdapter(TravelActivity.this, new ArrayList<TravelDO>());

        spinner = (Spinner) findViewById(R.id.toolbar_spinner);
        spinner.setAdapter(spinnerAdapter);

        tvModes = (TextView) findViewById(R.id.tvModes);
        btnNavigate = (Button) findViewById(R.id.btnNavigate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_travel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
