package com.parndt.zehntausend.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.History;

public class HistoryReader {

    public History read(Context context) {
        Gson gson = new Gson();
        SharedPreferences preferences = context.getSharedPreferences(Constants.HISTORY, Context.MODE_PRIVATE);

        String json = preferences.getString(Constants.HISTORY, "");

        if (!json.equals("")) {
            return gson.fromJson(json, History.class);
        } else {
            return new History();
        }
    }
}
