package com.parndt.zehntausend.model;

public class Player {

    private static int idGenerator = 1;

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Player(String name) {
        this.id = idGenerator;
        idGenerator++;

        this.name = name;
    }
}
