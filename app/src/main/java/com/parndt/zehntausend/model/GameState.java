package com.parndt.zehntausend.model;

import android.content.Context;

import com.parndt.zehntausend.data.HistoryReader;
import com.parndt.zehntausend.data.HistoryWriter;
import com.parndt.zehntausend.data.ScoreSaver;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GameState implements Serializable {

    private List<PlayerScore> players = new ArrayList<>();
    private int playerTurns = 0;

    private boolean isStarted = false;
    private Date dateStart;
    private Date dateEnd;

    /** win conditions */
    private boolean tenThousandReached = false;
    private int turnsLeft;

    public List<PlayerScore> getPlayers() {
        return players;
    }

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("LLL d yyyy, kk:mm", Locale.UK);
        String test = formatter.format(dateStart);

        return test;
    }

    public String getDuration() {
        long diffInMillies = dateEnd.getTime() - dateStart.getTime();
        return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS) + " min";
    }

    public boolean isStarted() {
        return isStarted;
    }

    public List<PlayerScore> getWinners() {
        if (!gameOver()) return new ArrayList<>();

        PlayerScore first = new PlayerScore(new Player("")),
                second = new PlayerScore(new Player("")),
                third = new PlayerScore(new Player(""));

        int firstScore = -10000, secondScore = -10000, thirdScore = -10000;

        for (PlayerScore player : players) {
            int points = player.getPointsSum();
            if (points > firstScore) {
                third = second;
                thirdScore = secondScore;

                second = first;
                secondScore = firstScore;

                first = player;
                firstScore = points;
            } else if (points > secondScore) {
                third = second;
                thirdScore = secondScore;

                second = player;
                secondScore = points;
            } else if (points > thirdScore) {
                third = player;
                thirdScore = points;
            }
        }

        List<PlayerScore> result = new ArrayList<>();

        result.add(first);
        result.add(second);

        if (!third.getPlayer().getName().isEmpty()) {
            result.add(third);
        }

        return result;
    }

    public GameState() {

    }

    public void start(List<Player> players) {
        this.players = new ArrayList<>();

        for (Player player : players) {
            this.players.add(new PlayerScore(player));
        }

        dateStart = new Date();

        turnsLeft = players.size();

        isStarted = true;
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
