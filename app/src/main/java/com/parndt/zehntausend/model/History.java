package com.parndt.zehntausend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class History implements Serializable {

    private List<GameState> games = new ArrayList<>();

    public List<GameState> getGames() {
        return games;
    }

    public void add(GameState game) {
        games.add(game);
    }
}
