package com.parndt.zehntausend.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.GameState;

public class ScoreLoader {
    public GameState load(Context context) throws NoDataException {
        Gson gson = new Gson();
        SharedPreferences preferences = context.getSharedPreferences(Constants.GAME_STATE, Context.MODE_PRIVATE);

        String json = preferences.getString(Constants.GAME_STATE, "");

        if (!json.equals("")) {
            return gson.fromJson(json, GameState.class);
        } else {
            throw new NoDataException("No data saved! ");
        }
    }
}
