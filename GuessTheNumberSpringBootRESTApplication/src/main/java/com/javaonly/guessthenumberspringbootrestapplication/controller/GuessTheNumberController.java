/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.controller;

import com.javaonly.guessthenumberspringbootrestapplication.dao.GameDao;
import com.javaonly.guessthenumberspringbootrestapplication.service.NoSuchGameIdException;
import com.javaonly.guessthenumberspringbootrestapplication.dao.RoundsDao;
import com.javaonly.guessthenumberspringbootrestapplication.model.Game;
import com.javaonly.guessthenumberspringbootrestapplication.model.Rounds;
import com.javaonly.guessthenumberspringbootrestapplication.service.RoundsValidationException;

import com.javaonly.guessthenumberspringbootrestapplication.service.ServiceLayerForTheGame;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Prathima
 */

@RestController
@RequestMapping("/api/game")
public class GuessTheNumberController {
    
   @Autowired
    private final ServiceLayerForTheGame serviceLayer;
    

     @Autowired
   public GuessTheNumberController(ServiceLayerForTheGame serviceLayer){
       this.serviceLayer = serviceLayer;
   }
   
   //Returns a list of all games(just for quick check)
   @GetMapping
   public List<Game> all(){
       return serviceLayer.getAll();
       
   }
   
   //Starts a game, generates an answer, and sets the correct status
   @PostMapping("/begin")
@ResponseStatus(HttpStatus.CREATED)
   public int create(@RequestBody Game game){
      return serviceLayer.addGameWithAnswer(game);
      
   }
   
   @GetMapping("/{id}")
   public  ResponseEntity<Game> findById(@PathVariable int id) throws NoSuchGameIdException{
       
          Game result =  serviceLayer.findById(id);
       
       if(result.getId()!=id){
         return new ResponseEntity(null, HttpStatus.NOT_FOUND);
       }
       
      return ResponseEntity.ok(result);
   }

//Makes a guess by passing the guess and gameId in as JSON and creates round object.
@PostMapping("/guess")
@ResponseStatus(HttpStatus.CREATED)
   public Rounds createUserResultAnswer(@RequestBody Rounds round) throws RoundsValidationException, NoSuchGameIdException{
       
           return serviceLayer.createUserResult(round);
       
   }
   
   //Returns a list of rounds for the specified game sorted by time.
    @GetMapping("/rounds/{gameId}")
   public List<Rounds> roundsForGivenGameId(@PathVariable int gameId){
       List<Rounds> allRounds = serviceLayer.findRoundsByGameId( gameId);
       if(allRounds==null){
           return (List<Rounds>) new ResponseEntity(null, HttpStatus.NOT_FOUND);
       }
      return allRounds;
       
   }
    
}
