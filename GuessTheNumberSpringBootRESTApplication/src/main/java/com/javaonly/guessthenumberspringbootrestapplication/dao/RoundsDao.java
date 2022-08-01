/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.dao;

import com.javaonly.guessthenumberspringbootrestapplication.model.Rounds;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface RoundsDao {
    Rounds add(Rounds round);
    List<Rounds> getAll();
       
    Rounds findRoundById(int id);
    
    boolean update(Rounds round);
List<Rounds> findRoundsForGivenGameId(int id);
    
    boolean deleteById(int id);
  
}
