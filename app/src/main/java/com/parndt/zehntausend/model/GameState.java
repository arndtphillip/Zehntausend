package com.parndt.zehntausend.model;

import android.content.Context;

import com.parndt.zehntausend.data.ScoreSaver;

import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {

    private List<PlayerScore> players;
    private int playerTurns = 0;

    public List<PlayerScore> getPlayers() {
        return players;
    }

    public GameState(List<PlayerScore> players) {
        this.players = players;
    }

    public void addScore(int points) {
        int player = playerTurns % players.size();

        players.get(player).addPoints(points);

        playerTurns++;
    }

    public void undo() {
        if (playerTurns > 0) {
            // find last turn
            int player = --playerTurns % players.size();
            players.get(player).undo();
        }
    }

    public Player getCurrentPlayer() {
        int player = playerTurns % players.size();
        return players.get(player).getPlayer();
    }

    public void save(Context context) {
        new ScoreSaver(this).save(context);
    }
}
