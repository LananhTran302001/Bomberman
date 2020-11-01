package controller;

import base.GamesFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting...");

        GamesFactory mi = new GamesFactory();
        mi.createGame(15,20, 30, 48, 3);
    }
}
