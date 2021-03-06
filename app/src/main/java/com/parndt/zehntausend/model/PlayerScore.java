package com.parndt.zehntausend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerScore implements Serializable {

    private Player player;
    private List<Integer> points = new ArrayList<>();

    public Player getPlayer() {
        return player;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public int getPointsSum() {
        int sum = 0;

        for (int p : points) {
            sum += p;
        }

        return sum;
    }

    public PlayerScore(Player player) {
        this.player = player;
    }

    public void addPoints(int points) {
        int rounds = this.points.size();

        if (points == 0 && rounds >= 2 && this.points.get(rounds - 1) == 0 && this.points.get(rounds - 2) == 0) {
            this.points.add(-500);
        } else {
            this.points.add(points);
        }
    }

    public void undo() {
        if (points.size() > 0) {
            points.remove(points.size() - 1);
        }
    }

    public boolean hasWon() {
        return getPointsSum() >= 10000;
    }
}
