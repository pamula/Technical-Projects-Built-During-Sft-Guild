/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Prathima
 */
public class Rounds {
    private int id;
    private String playerGuess;
    private String result;
    private int gameId;
    LocalDateTime roundsTime;


   
    public int getGameId() {
        return gameId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.playerGuess);
        hash = 79 * hash + Objects.hashCode(this.result);
        hash = 79 * hash + this.gameId;
        hash = 79 * hash + Objects.hashCode(this.roundsTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rounds other = (Rounds) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.playerGuess, other.playerGuess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.roundsTime, other.roundsTime)) {
            return false;
        }
        return true;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerGuess() {
        return playerGuess;
    }

    public void setPlayerGuess(String playerGuess) {
        this.playerGuess = playerGuess;
    }
    

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getRoundsTime() {
        return roundsTime;
    }

    public void setRoundsTime(LocalDateTime roundsTime) {
        this.roundsTime = roundsTime;
    }


}
