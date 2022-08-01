/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.dao;

import com.javaonly.guessthenumberspringbootrestapplication.TestApplicationConfiguration;
import com.javaonly.guessthenumberspringbootrestapplication.model.Game;
import com.javaonly.guessthenumberspringbootrestapplication.model.Rounds;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class RoundsDaoDatabaseTest {
    
    @Autowired
    RoundsDao roundsDao;
    
    @Autowired
    GameDao gameDao;
    
    public RoundsDaoDatabaseTest() {
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
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class RoundsDaoDatabase.
     */
    @Test
    public void testAddAndGetAllRounds() {
        
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        gameDao.add(game);
        
        Rounds round = new Rounds();
        round.setPlayerGuess("2345");
        round.setGameId(game.getId());
        round.setResult("e:0:p:3");
        round.setRoundsTime(LocalDateTime.now().withNano(0));
        roundsDao.add(round);
        
        Rounds round2 = new Rounds();
        round2.setPlayerGuess("3452");
        round2.setGameId(game.getId());
        round2.setResult("e:0:p:3");
        round2.setRoundsTime(LocalDateTime.now().withNano(0));
        roundsDao.add(round2);
        
        
        
         List<Rounds> allRoundsFromTestDB = roundsDao.getAll();
        assertEquals(2, allRoundsFromTestDB.size());
        
        
    }

    

    /**
     * Test of findRoundById method, of class RoundsDaoDatabase.
     */
    @Test
    public void testFindRoundById() {
         Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(true);
        gameDao.add(game);
        Rounds round = new Rounds();
        round.setPlayerGuess("1234");
        round.setGameId(game.getId());
        round.setResult("e:4:p:0");
        round.setRoundsTime(LocalDateTime.now().withNano(0));
        
       Rounds oneRound = roundsDao.add(round);
         Rounds secondRound = roundsDao.findRoundById(round.getId());
        assertEquals(oneRound , secondRound);
    }

    /**
     * Test of findRoundsForGivenGameId method, of class RoundsDaoDatabase.
     */
    @Test
    public void testFindRoundsForGivenGameId() {
        
         Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(true);
        gameDao.add(game);
        
        Rounds round = new Rounds();
        round.setPlayerGuess("1234");
        round.setGameId(game.getId());
        round.setResult("e:4:p:0");
        round.setRoundsTime(LocalDateTime.now().withNano(0));
        
       round = roundsDao.add(round);
        
       List<Rounds> round2 =  roundsDao.findRoundsForGivenGameId(round.getGameId());
       //here this checks if list contains round or not
       assertTrue(round2.contains(round));
    }


    /**
     * Test of deleteById method, of class RoundsDaoDatabase.
     */
    @Test
    public void testDeleteById() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(true);
        gameDao.add(game);
        
       Rounds round = new Rounds();
        round.setPlayerGuess("1234");
        round.setGameId(game.getId());
        round.setResult("e:4:p:0");
        round.setRoundsTime(LocalDateTime.now().withNano(0));
        
        roundsDao.add(round); 
        roundsDao.deleteById(round.getId());
         Rounds roundFromDao = roundsDao.findRoundById(round.getId());
        assertNull(roundFromDao);
        
    }
    
}
