package com.parndt.zehntausend.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.parndt.zehntausend.model.Constants;
import com.parndt.zehntausend.model.GameState;

public class ScoreSaver {

    private GameState state;

    public ScoreSaver(GameState state) {
        this.state = state;
    }

    public void save(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.GAME_STATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        String json = gson.toJson(state);
        editor.putString(Constants.GAME_STATE, json);
        editor.apply();
    }
}
