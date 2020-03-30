package com.parndt.zehntausend.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.parndt.zehntausend.model.PlayerScore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScoreSaver {

    static final String SCORE_STORE_KEY = "SCORE_STORE_KEY";

    private List<PlayerScore> scores;

    public ScoreSaver(List<PlayerScore> scores) {
        this.scores = scores;
    }

    public void save(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SCORE_STORE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        Set<String> jsonScores = new HashSet<>();
        for (PlayerScore score : scores) {
            jsonScores.add(gson.toJson(score));
        }
        editor.putStringSet(SCORE_STORE_KEY, jsonScores);
        editor.apply();
    }
}
