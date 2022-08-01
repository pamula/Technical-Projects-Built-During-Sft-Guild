/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Prathima
 */
public class Game {
    private int id;
    private String answer;
    private boolean answerstatus;

    //List<Rounds> round = new ArrayList<>();

//    public List<Rounds> getRound() {
//        return round;
//    }
//
//    public void setRound(List<Rounds> round) {
//        this.round = round;
//    }
    
    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.answer);
        hash = 71 * hash + (this.answerstatus ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.answerstatus != other.answerstatus) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isStatus() {
        return answerstatus;
    }

    public void setStatus(boolean answerstatus) {
        this.answerstatus = answerstatus;
    }
    
    
}
