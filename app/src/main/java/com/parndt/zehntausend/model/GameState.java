package com.parndt.zehntausend.model;

import android.content.Context;

import com.parndt.zehntausend.data.ScoreSaver;

import java.util.List;

public class GameState {

    private List<PlayerScore> scores;
    private int playerTurns = 0;

    public GameState(List<PlayerScore> scores) {
        this.scores = scores;
    }

    public void addScore(int points) {
        int player = playerTurns % scores.size();

        scores.get(player).addPoints(points);

        playerTurns++;
    }

    public void undo() {
        if (playerTurns > 0) {
            // find last turn
            int player = --playerTurns % scores.size();
            scores.get(player).undo();
        }
    }

    public Player getCurrentPlayer() {
        int player = playerTurns % scores.size();
        return scores.get(player).getPlayer();
    }

    public void save(Context context) {
        new ScoreSaver(scores).save(context);
    }
}
