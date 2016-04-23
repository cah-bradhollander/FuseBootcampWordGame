package com.fuse.bootcamp.rockpaperscissorsbootcamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    private int id;
    private List<Player> players;
    private Map<String, String> messages;

    public Game() {
        players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public String getOpponentUsername() {
        String playerUsername = GameSession.player.getUsername();
        String opponentUsername = "Opponent";
        for (Player p : players) {
            if (!playerUsername.equals(p.getUsername())) {
                opponentUsername = p.getUsername();
                break;
            }
        }

        return opponentUsername;
    }
}