package com.parndt.zehntausend.model;

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
        // find last turn
        int player = --playerTurns % scores.size();
        scores.get(player).undo();
    }
}
