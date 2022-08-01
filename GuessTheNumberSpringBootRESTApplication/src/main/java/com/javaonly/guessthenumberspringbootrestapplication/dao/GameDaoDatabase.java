/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.dao;

import com.javaonly.guessthenumberspringbootrestapplication.model.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prathima
 */

@Repository
@Profile("database")
public class GameDaoDatabase implements GameDao{
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GameDaoDatabase(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO game(answer) VALUES(?);" ;
     GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
     
jdbcTemplate.update((Connection conn)-> {
    PreparedStatement statement = conn.prepareStatement(
    sql,
    Statement.RETURN_GENERATED_KEYS );
    
    statement.setString(1, game.getAnswer());
    
    return statement;
   
    
},keyHolder);

game.setId(keyHolder.getKey().intValue());
return game;
        
       
    }
//get all games
    @Override
    public List<Game> getAll() {
        
        final String sql = "SELECT * FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
        
    }

    
    
    //gives all rows of that game id when status is true i.e user
    //guessed answer and game not in progress
    @Override
    public Game findById(int id) {
        try{
        final String sql = "SELECT id, answer,answerstatus "
                + "FROM game WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(),id);
        }catch(DataAccessException e){
            return null;
        } 
    }
    
   
//    
    
//to update a game
    @Override
    public boolean update(Game game) {
        
         final String sql = "UPDATE game SET "
                + "answer = ?, "
                + "answerstatus = ? "
               
                + "WHERE id = ?;";

        return jdbcTemplate.update(sql,
                game.getAnswer(),
                game.isStatus(),
                
                game.getId()) > 0;
         
    }
    
//delete a game,rounds has a fk so first delete 
    //game id in rounds and then delete game
    @Override
    public boolean deleteById(int id) {
        final String DELETE_ROUNDS_GAMEID = "DELETE  FROM rounds WHERE gameId = ?;";
        jdbcTemplate.update(DELETE_ROUNDS_GAMEID , id);
         final String sql = "DELETE FROM game WHERE id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
         //To change body of generated methods, choose Tools | Templates.
    }
    
     private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game actualGame = new Game();
            actualGame.setId(rs.getInt("id"));
            actualGame.setAnswer(rs.getString("answer"));
           actualGame.setStatus(rs.getBoolean("answerstatus"));
            
            return actualGame;
        }
     }
     
}
