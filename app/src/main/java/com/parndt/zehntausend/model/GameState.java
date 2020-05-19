package com.parndt.zehntausend.model;

import android.content.Context;

import com.parndt.zehntausend.data.HistoryReader;
import com.parndt.zehntausend.data.HistoryWriter;
import com.parndt.zehntausend.data.ScoreSaver;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GameState implements Serializable {

    private List<PlayerScore> players;
    private int playerTurns = 0;

    private Date dateStart;
    private Date dateEnd;

    /** win conditions */
    private boolean tenThousandReached = false;
    private int turnsLeft;

    public List<PlayerScore> getPlayers() {
        return players;
    }

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm \nLLL d yyyy", Locale.UK);
        String test = formatter.format(dateStart);

        return test;
    }

    public String getDuration() {
        long diffInMillies = dateEnd.getTime() - dateStart.getTime();
        return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS) + " min";
    }

    public Player getWinner() {
        Player winner = null;
        int highscore = 10000;

        if (gameOver()) {
            for (PlayerScore player : players) {
                if (player.getPointsSum() >= highscore) {
                    winner = player.getPlayer();
                    highscore = player.getPointsSum();
                }
            }
        }

        return winner;
    }

    public GameState(List<PlayerScore> players) {
        this.players = players;

        dateStart = new Date();

        turnsLeft = players.size();
    }

    public void addScore(int points) {
        if (!gameOver()) {
            int player = playerTurns % players.size();

            players.get(player).addPoints(points);

            playerTurns++;
            if (tenThousandReached) turnsLeft--;

            if (players.get(player).hasWon() && !tenThousandReached) {
                tenThousandReached = true;
                turnsLeft--;
            }

            if (turnsLeft == 0) {
                dateEnd = new Date();
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

        if (gameOver()) {
            History history = new HistoryReader().read(context);
            history.add(this);
            new HistoryWriter(history).write(context);
        }
    }

    public boolean gameOver() {
        return tenThousandReached && turnsLeft < 1;
    }
}
