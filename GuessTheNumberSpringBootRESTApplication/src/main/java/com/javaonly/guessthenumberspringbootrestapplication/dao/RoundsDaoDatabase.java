/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.dao;


import com.javaonly.guessthenumberspringbootrestapplication.model.Rounds;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Prathima
 */

@Repository
@Profile("database")
public class RoundsDaoDatabase implements RoundsDao{
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public RoundsDaoDatabase(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
   
    public Rounds add(Rounds round) {
        final String sql = "INSERT INTO rounds(playerGuess,result,gameId) VALUES(?,?,?);" ;
     GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
     
jdbcTemplate.update((Connection conn)-> {
    PreparedStatement statement = conn.prepareStatement(
    sql,
    Statement.RETURN_GENERATED_KEYS );
    
    statement.setString(1,round.getPlayerGuess());
    
    statement.setString(2,round.getResult());
    statement.setInt(3,round.getGameId());
   
    return statement;
   
    
},keyHolder);

round.setId(keyHolder.getKey().intValue());
//this is where u will insert time without null value
int roundId = round.getId();
 final String sql2 = "SELECT * FROM rounds WHERE id = ?";
        return jdbcTemplate.queryForObject(sql2, new RoundsMapper(), roundId);
    }
    //
    @Override
    public List<Rounds> getAll() {
        
         final String sql = "SELECT* FROM rounds;";
        return jdbcTemplate.query(sql, new RoundsMapper());
       
    }

    
    //find round for given id
    @Override
    public Rounds findRoundById(int id){
       try{
        final String sql = "SELECT * "
                + "FROM rounds WHERE id=? ;";

        return jdbcTemplate.queryForObject(sql, new RoundsMapper(), id);
        }catch(DataAccessException e){
            return null;
        } 
    }
    // Returns a list of rounds for the specified game i.e by gameId sorted by time.
    @Override
    public List<Rounds> findRoundsForGivenGameId(int id) {
        try{
        final String sql = "SELECT * FROM rounds WHERE gameId = ? order by rounds.roundsTime";
         
        return jdbcTemplate.query(sql, new RoundsMapper(),id);
        }catch(DataAccessException e){
            
            return null;
        }
        
    }

    @Override
    public boolean update(Rounds round) {
         final String sql = "UPDATE rounds SET "
                + "playerGuess = ?, "
                + "roundsTime= ? "
                 +"result =?"
               +"gameId=?"
                + "WHERE id = ?;";

        return jdbcTemplate.update(sql,
                round.getPlayerGuess(),
                round.getRoundsTime(),
                round.getResult(),
                round.getGameId(),
                round.getId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        final String sql = "DELETE FROM rounds WHERE id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    private static final class RoundsMapper implements RowMapper<Rounds> {

        @Override
        public Rounds mapRow(ResultSet rs, int index) throws SQLException {
            Rounds actualRound = new Rounds();
            actualRound.setId(rs.getInt("id"));
            actualRound.setPlayerGuess(rs.getString("playerGuess"));
             
           actualRound.setResult(rs.getString("result"));
            actualRound.setGameId(rs.getInt("gameId"));
           
            actualRound.setRoundsTime(rs.getTimestamp("roundsTime").toLocalDateTime());
           
            return actualRound;
        }
     }
}
