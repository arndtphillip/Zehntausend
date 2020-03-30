package com.parndt.zehntausend.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.parndt.zehntausend.model.PlayerScore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScoreLoader {
    public List<PlayerScore> load(Context context) {
        Gson gson = new Gson();

        SharedPreferences preferences = context.getSharedPreferences(ScoreSaver.SCORE_STORE_KEY, Context.MODE_PRIVATE);
        Set<String> jsonSet = preferences.getStringSet(ScoreSaver.SCORE_STORE_KEY, new HashSet<String>());

        List<PlayerScore> result = new ArrayList<>();
        for (String json : jsonSet) {
            result.add(gson.fromJson(json, PlayerScore.class));
        }

        return result;
    }
}
