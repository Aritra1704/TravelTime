package com.example.arpaul.traveltime.WebServices;

import android.content.Context;

import com.example.arpaul.traveltime.DataObject.TravelDO;
import com.example.arpaul.traveltime.DataObject.TravelList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by ARPaul on 11-03-2016.
 */
public class TravelWebServices {

    private String BASE_URL = "http://express-it.optusnet.com.au/sample.json";

    private final static int STATUS_SUCCESS             = 200;
    private final static int STATUS_FAILED              = 500;
    public static int get_STATUS_SUCCESS() {
        return STATUS_SUCCESS;
    }

    public static int get_STATUS_FAILED() {
        return STATUS_FAILED;
    }

    private Context context;
    private DataListener dataListener = null;
    public TravelWebServices(Context context,DataListener dataListener) {
        this.context = context;
        this.dataListener = dataListener;
    }

    OkHttpClient okHttpClient;
    Call call;
    public void getTravelData() {
        okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(BASE_URL).build();
        call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                dataListener.DataRetrieved(new TravelList(), STATUS_FAILED);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful() && dataListener != null) {

                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonArray jArray = parser.parse(response.body().string()).getAsJsonArray();

                    TravelList objTravelList = new TravelList();

                    for(JsonElement obj : jArray )
                    {
                        TravelDO objTravelDO = gson.fromJson(obj , TravelDO.class);
                        objTravelList.travels.add(objTravelDO);
                    }
                    dataListener.DataRetrieved(objTravelList, STATUS_SUCCESS);
                }
            }
        });
    }
}
