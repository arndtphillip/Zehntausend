package com.parndt.zehntausend.model;

import android.content.Context;

import com.parndt.zehntausend.data.ScoreSaver;

import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {

    private List<PlayerScore> players;
    private int playerTurns = 0;

    /** win conditions */
    private boolean tenThousandReached = false;
    private int turnsLeft;

    public List<PlayerScore> getPlayers() {
        return players;
    }

    public GameState(List<PlayerScore> players) {
        this.players = players;

        turnsLeft = players.size();
    }

    public void addScore(int points) {
        if (!gameOver()) {
            int player = playerTurns % players.size();

            players.get(player).addPoints(points);

            playerTurns++;
            if (tenThousandReached) turnsLeft--;

            if (players.get(player).hasWon()) {
                tenThousandReached = true;
                turnsLeft--;
            }
        }
    }

    public void undo() {
        if (playerTurns > 0 && !gameOver()) {
            // find last turn
            int player = --playerTurns % players.size();
            players.get(player).undo();

            if (tenThousandReached && turnsLeft < players.size()) {
                turnsLeft++;
            }
        }
    }

    public Player getCurrentPlayer() {
        int player = playerTurns % players.size();
        return players.get(player).getPlayer();
    }

    public void save(Context context) {
        new ScoreSaver(this).save(context);
    }

    public boolean gameOver() {
        return tenThousandReached && turnsLeft < 1;
    }
}
