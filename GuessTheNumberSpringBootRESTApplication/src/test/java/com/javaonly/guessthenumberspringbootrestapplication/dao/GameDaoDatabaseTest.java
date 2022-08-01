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
public class GameDaoDatabaseTest {
    
    @Autowired
    RoundsDao roundsDao;
    
    @Autowired
    GameDao gameDao;
    
    public GameDaoDatabaseTest() {
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
     * Test of getAll method, of class GameDaoDatabase.
     * we are creating GAme object , adding to db and getting all 
     * games int this method down
     */
    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        gameDao.add(game);
        
        Game game2 = new Game();
        game2.setAnswer("2345");
        game2.setStatus(false);
        gameDao.add(game2);
        
        List<Game> allGamesFromTestDB = gameDao.getAll();
        assertEquals(2, allGamesFromTestDB.size());
        assertTrue(allGamesFromTestDB.contains(game));
        assertTrue(allGamesFromTestDB.contains(game2));
        
        
    }
    @Test
    public void testAddAndGetOneGame() {
        
       Game game = new Game();
       
        game.setAnswer("1234");
        game.setStatus(false);
       Game addOneGame =  gameDao.add(game);
        Game oneGame = gameDao.findById(game.getId());
        //here its equal coz u are adding but not changing any answer in db just
        // displaying some other answer as xyz
        assertEquals(addOneGame, oneGame);
        
    }
    
     @Test
    public void testAddAndGetOneGameWithOutAnswer() {
        Game game = new Game();
        //gameDao.add(game);
        game.setAnswer("1234");
        game.setStatus(false);
       Game addOneGame =  gameDao.add(game);
        Game oneGame = gameDao.findById(game.getId());
        //here values are same equals i.e just adding and finding by id
        assertEquals(addOneGame, oneGame);
        
    }
    @Test
    public void testUpdateGame() {
        
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        
       Game addOneGame =  gameDao.add(game);
       Game updateGameStatus = gameDao.findById(addOneGame.getId());
       assertEquals(addOneGame, updateGameStatus);
      
      
        game.setStatus(true);
        gameDao.update(game);
        
        assertNotEquals(addOneGame, updateGameStatus);
       updateGameStatus = gameDao.findById(game.getId());
//       
      assertEquals(addOneGame, updateGameStatus);
        
    }
    

    /**
     * Test of deleteById method, of class GameDaoDatabase.
     */
    @Test
    public void testDeleteById() {
         Game game = new Game();
         game.setId(1);
        game.setAnswer("1234");
        game.setStatus(false);
        game = gameDao.add(game);
//        
        Rounds round = new Rounds();
        round.setPlayerGuess("2345");
        round.setGameId(game.getId());
      round.setResult("e:0:p:3");
       round.setRoundsTime(LocalDateTime.now().withNano(0));
    round =  roundsDao.add(round);
    
    gameDao.deleteById(game.getId());
        Game gameFromDao = gameDao.findById(game.getId());
        
       assertNull(gameFromDao);
        
    }
    
}
