package com.michalszekalski.bootcamp_zad26.match;

public enum Winner {
    WINA ("Team A"),
    WINB ("Team B"),
    DRAW ("Draw");

    private final String description;

    Winner(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
