/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.service;

import com.javaonly.guessthenumberspringbootrestapplication.TestApplicationConfiguration;
import com.javaonly.guessthenumberspringbootrestapplication.dao.GameDao;
import com.javaonly.guessthenumberspringbootrestapplication.dao.RoundsDao;
import com.javaonly.guessthenumberspringbootrestapplication.model.Game;
import com.javaonly.guessthenumberspringbootrestapplication.model.Rounds;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Prathima
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ServiceLayerForTheGameImplTest {
    
     @Autowired
    RoundsDao roundsDao;
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    ServiceLayerForTheGame serviceLayerForGame;
    
    public ServiceLayerForTheGameImplTest() {
    }
    
    @Before
   public void setUp() {
     List<Rounds> rounds = roundsDao.getAll();
        for(Rounds round : rounds) {
            roundsDao.deleteById(round.getId());
        }
        List<Game> games = gameDao.getAll();
        for(Game game : games) {
            gameDao.deleteById(game.getId());
        }
    }

    /**
     * Test of addGameWithAnswer method, of class ServiceLayerForTheGameImpl.
     */
    @Test
    public void testAddAndGetGame() {
        Game game = new Game();
        
        game.setAnswer("1234");
        game.setStatus(false);
       Game addOneGame =  gameDao.add(game);
        Game oneGame = gameDao.findById(game.getId());
        //here its equal coz u are adding but not changing any answer in db just
        // displaying some other answer as xyz
        assertEquals(addOneGame, oneGame);
        
    }

    /**
     * Test of createUserResult method, of class ServiceLayerForTheGameImpl.
     */
    @Test
    public void testCreateUserResult()throws RoundsValidationException , NoSuchGameIdException
    {
         Game game = new Game();
       
        game.setAnswer("1234");
        game.setStatus(false);
        
        String palyerGuess = "3456";
      
       String result = serviceLayerForGame.Result(palyerGuess, game.getAnswer());
        String actualResult = "e:0:p:2";
        assertEquals(result,actualResult);
       //for invalid user guess and checks roundsvalidationexception
        String playerGuess1 = "1111";
        String result1 = serviceLayerForGame.Result(playerGuess1, game.getAnswer());
        assertNotEquals(result1, actualResult,"User GUess Not Valid");
        }
    
    @Test
    public void testUserGuessForAlreadyGuessedGame()throws NoSuchGameIdException{
        //check for already played true status game
         Game game = new Game();
       game.setId(1);
        game.setAnswer("1234");
        game.setStatus(true);
        String gameAnswer = game.getAnswer();
        String palyerGuess = "1234";
        int gameId1 = 1;
        
        String result = serviceLayerForGame.Result(palyerGuess, game.getAnswer());
        assertNotEquals(palyerGuess,gameAnswer,"Already played game here");
    }

    /**
     * Test of Result method, of class ServiceLayerForTheGameImpl.
     */
    @Test
    public void testResult() {
        String answer ="2345";
        String guess = "1234";
        String result = "e:0:p:3";
        String actualResult = serviceLayerForGame.Result(guess, answer);
        assertEquals(result,actualResult);
    }

    /**
     * Test of findRoundsByGameId method, of class ServiceLayerForTheGameImpl.
     */
    @Test
    public void testFindRoundsByGameId() {
        
        Game game = new Game();
        
        game.setAnswer("1234");
        game.setStatus(false);
         gameDao.add(game);
        
        Rounds round = new Rounds();
        round.setPlayerGuess("2345");
        round.setGameId(game.getId());
      round.setResult("e:0:p:3");
       round.setRoundsTime(LocalDateTime.now().withNano(0));
       round = roundsDao.add(round);
       
       List<Rounds> rounds = roundsDao.findRoundsForGivenGameId(round.getGameId());
        assertTrue(rounds.contains(round));
        
    }
    
}
