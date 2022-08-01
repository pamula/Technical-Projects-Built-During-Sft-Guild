/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.service;


import com.javaonly.guessthenumberspringbootrestapplication.controller.GuessTheNumberControllerExceptionHandler;
import com.javaonly.guessthenumberspringbootrestapplication.dao.GameDao;
import com.javaonly.guessthenumberspringbootrestapplication.dao.RoundsDao;
import com.javaonly.guessthenumberspringbootrestapplication.model.Game;
import com.javaonly.guessthenumberspringbootrestapplication.model.Rounds;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prathima
 */
@Repository
public class ServiceLayerForTheGameImpl implements ServiceLayerForTheGame{
    
    
    GameDao gameDao;
    RoundsDao roundDao;
    
    @Autowired
    public ServiceLayerForTheGameImpl(GameDao gameDao,RoundsDao roundDao){
        this.gameDao=gameDao;
        this.roundDao=roundDao;
    }
    
    //********Game*************
     @Override
    public int addGameWithAnswer(Game game){
      
        Random rd = new Random();
        Set<Integer> value = new HashSet<>();
       
        while(value.size()<4){
             int num1 = rd.nextInt(9-0+1)+0;
           value.add(new Integer(num1));
        }
        StringBuilder sb = new StringBuilder();
        for(int x: value){
            sb.append(Integer.toString(x));
            sb.append("");
        }
        
        String gameAnswer = sb.toString();
        gameAnswer = gameAnswer.trim();
    game.setAnswer(gameAnswer);
   game= gameDao.add(game);
   //this will show the game id created
   return game.getId();
    }
    
     @Override
    public List<Game> getAll() {
        List<Game> allGames = gameDao.getAll();
        
        
        for(Game games : allGames){
            if(games.isStatus()== false){
                games.setAnswer("**********");
              
            }
        }
        return allGames;
         
    }
      
    // gives a game depending on id but displays/not displays answer depending on status
    @Override
    public Game findById(int id) throws NoSuchGameIdException {
      
      Game   oneGame = gameDao.findById(id); //return oneGame;
      checkGame(oneGame);

        if( oneGame.isStatus()){
            
            return oneGame;
        }else{
            oneGame.setAnswer("******");
            return oneGame;
        }
      
    }

    //****Rounds*****
 

 @Override
    public Rounds createUserResult(Rounds round) throws RoundsValidationException,NoSuchGameIdException{
     //validateRound(round);
      Game game = gameDao.findById(round.getGameId());
      checkGame(game);
        String answerFromGame = game.getAnswer();
        
 validateRound(round);
    String guess = round.getPlayerGuess();
        String updatedResult = Result(guess,answerFromGame);
        round.setResult(updatedResult);
        
        if(guess.equalsIgnoreCase(answerFromGame)){
           
            game.setStatus(true);
            gameDao.update(game);
        }
       
round=roundDao.add(round);
       return round;
    }
    
    @Override
     public String Result(String guess, String answerFromGame) {
        char[] guessChars = guess.toCharArray();
        char[] answerChars = answerFromGame.toCharArray();
        int exact = 0;
        int partial = 0;
        
        for (int i = 0; i < guessChars.length; i++) {
            //only when string /characters are present
            if (answerFromGame.indexOf(guessChars[i]) > -1) {
                if (guessChars[i] == answerChars[i]) {
                    exact++;
                } else {
                    partial++;
                }
            }
        }
        
        String actualResult = "e:" + exact + ":p:" + partial;
        
        return actualResult;
    }
     
     
     //list all rounds for a given game id
     @Override
     public List<Rounds> findRoundsByGameId(int gameId){
         
             return roundDao.findRoundsForGivenGameId(gameId);
     }
     
    private void validateRound(Rounds round) throws RoundsValidationException{
        //here regex matches for numbers 
        if(  round.getPlayerGuess() == null || round.getPlayerGuess().matches("(\\d)(?!\\1+$)\\d{3}")== false
                ){
            throw new RoundsValidationException("NOT VALID GUESS");
        }
    }
    private void checkGame(Game game) throws NoSuchGameIdException{
        if(game == null || game.isStatus() == true){
            throw new NoSuchGameIdException("Game Already Played or No such Game");
        }
    }
     
}
