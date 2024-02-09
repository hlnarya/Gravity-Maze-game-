# Gravity-Maze-game-
 
The aim of the project is to develop a maze game, 
in which treasures are collected while walking in it.


General Information

The game is played in a 25*55 game field including walls. There are two competitors: Player (P) and Computer (X). There are some treasures/numbers in the game, which the player collects to increase his/her score. The aim of the game is gaining the highest score.
![image](https://github.com/hlnarya/Gravity-Maze-game-/assets/142156676/924b0b68-768c-4071-8223-03a8ae94ec58)

Game Elements
 
P : Player
•	Player uses cursor keys to control P.
•	Player uses left or right cursor keys to push a boulder.
o	Player can push a boulder if there is an empty square behind it. 
•	Player uses space key to teleport himself/herself to a random earth square.
•	Player has a backpack (size: 8 elements).

X : Computer robots
•	Computer controls all X robots.
•	Robots move randomly in empty squares in 4 directions.   

Numbers (1-3): Treasure elements

O : Boulder
•	They are the only element affected by the gravity. 

:  : Earth
•	Boulders can stay over them. Player can move over them, converting them into empty squares.

#  : Wall
•	Boulders can stay over them. Game elements cannot harm the walls.




Game Initialization

1.	Walls are placed.
2.	All empty squares are converted into earth squares.
3.	Random 180 earth squares are converted into boulders.
4.	Random 30 earth squares are converted into treasures (Random 1, 2 or 3 with equal probability).
5.	Random 200 earth squares are converted into empty squares.
6.	Random 7 earth squares are converted into robots.
7.	Player P is placed on a random earth square.

At the beginning; player's backpack is empty and he/she has 3 teleport rights. After the game begins, an element is inserted into the maze area from the input queue in every 3 seconds.


Input Queue
 
Game elements are inserted into the maze area from an input queue. The input queue (size: 15 elements) is always full of elements, and shows the next element which will be inserted into the maze. The first element in the queue is inserted into the maze, at a random place in every 3 seconds. 

When an element is inserted into the maze, some game elements are converted into other game elements. In boulder insertion, one boulder is inserted and another boulder disappears. So, the number of boulders are constant during the game.


![image](https://github.com/hlnarya/Gravity-Maze-game-/assets/142156676/abfbede6-0885-4bdd-8fa6-bc7154ef1733)



Game Playing Information
 
The player can move over empty or earth squares, and can collect a treasure item when he/she reaches to the treasure’s square. Robots can move randomly in empty squares, and cannot take treasures. Movements are in 4 directions (no diagonal movement). There cannot be more than one game element on the same square. Game elements which cannot take or harm each other, behave like walls when they encounter.

Game speed is 10 frames per second for all movements (player moves, robot moves, boulder falls for one square). When a falling boulder or computer robot reaches the player, game is over. If a boulder falls on a robot; robot disappears, player gains 900 score points.
 
When the player reaches a treasure's square, he/she places the number (1, 2, or 3) into the backpack. If the backpack is full, the element on the top is removed and the new taken element is inserted. At the top of the backpack, if two elements are identical numbers, they turn into score and teleport rights. 


![image](https://github.com/hlnarya/Gravity-Maze-game-/assets/142156676/65ef9a13-1ecf-4a94-aab9-65a4c274423a)


Boulder Fall

•	For a boulder; 
o	If there is an empty square under it, it falls (a).
o	If a boulder is on top of another boulder, and side squares are empty; the upper boulder does a side fall (b).
•	Player can go to the square under a static boulder without any harm (c). But if the player goes down from there, he/she cannot escape from a falling boulder just over himself/herself (d).
![image](https://github.com/hlnarya/Gravity-Maze-game-/assets/142156676/f47d1491-e6de-490d-94ee-bec4ffc1ffb8)

