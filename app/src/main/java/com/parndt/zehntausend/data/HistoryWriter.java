package com.parndt.zehntausend.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.History;

public class HistoryWriter {

    private History history;

    public HistoryWriter(History history) {
        this.history = history;
    }

    public void write(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.HISTORY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        String json = gson.toJson(history);
        editor.putString(Constants.HISTORY, json);
        editor.apply();
    }
}
