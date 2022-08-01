drop database if exists GuessNumber;

create database GuessNumber;

use GuessNumber;
 create table game(
 id int primary key auto_increment,
 answer char(20) not null,
 answerstatus boolean not null default false);
 
 create table rounds(
 id int primary key auto_increment,
 playerGuess char(20) ,
  `result` CHAR(50) ,
 gameId int not null,
 FOREIGN KEY (gameId) REFERENCES game(id),
 `roundsTime` datetime DEFAULT current_timestamp );
 
 