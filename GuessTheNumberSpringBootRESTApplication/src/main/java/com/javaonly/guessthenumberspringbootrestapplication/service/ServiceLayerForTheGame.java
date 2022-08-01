/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.service;


import com.javaonly.guessthenumberspringbootrestapplication.model.Game;
import com.javaonly.guessthenumberspringbootrestapplication.model.Rounds;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface ServiceLayerForTheGame {
    
    int addGameWithAnswer(Game game);
    List<Game> getAll();
    
    Game findById(int id)throws NoSuchGameIdException;
  
    //************Rounds****************

    String Result(String guess, String answer);
    Rounds createUserResult(Rounds round)throws RoundsValidationException,NoSuchGameIdException;;
   
    List<Rounds> findRoundsByGameId(int id);
    
}
