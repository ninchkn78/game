game
====

This project implements the game of Breakout.

Name: Alex Chao

### Timeline

Start Date: 9/12/2020

Finish Date: 

Hours Spent: 45 hours (Alex) 40 hours (Christian)


### Resources Used
removing items from list while iterating
https://www.java67.com/2018/12/how-to-remove-objects-or-elements-while-iterating-Arraylist-java.html
getting random numbers
https://stackoverflow.com/questions/27976857/how-do-i-get-a-random-number-with-a-negative-number-in-range


### Running the Program

Main class: Game 

Data files needed:  
level0.txt to test the common logic in the game
level1.txt, level2.txt, level3.txt to run the game 

Key/Mouse inputs:
SHIFT: starts the game 
LEFT/RIGHT: moves paddle 
SPACE: Pause

Cheat keys:
R: resets the game
P: adds a powerup from a random block 
Tab: advances to next level 
B: adds a ball from the paddle
L: adds a life
D: destroys the first block (by order that they were in the text file)
I: ball doesn't go out of bounds
UP: increase ball speed
DOWN: decrease ball speed
1-3: change to this level

Known Bugs:
On level 3, the ball is supposed to rebound off the bottom if it started on the lower half of the screen, and cause a life loss and reset if it
goes through the top. Likewise, if the ball begins in the top half of the screen (above the paddle)
it is supposed to rebound off the top, and cause a life loss and reset
when passing through the bottom. However, both balls rebound off the top and disappear through
the bottom without causing a life loss, only a reset. 

Implementation of High Score Tracking was attempted, but a bug occurs where the myDisplay.getScore method always
returns 0, and thus does not update the high score. 

Level descriptions: 

Level 1 paddle is at bottom, blocks include durable block, extra life block, and basic block 
blocks are in a grid format

Level 2 moves the paddle to the top, so powerups "fall" upwards, and losing a life occurs at the top
extra blocks included are the slow down ball block and the paddle widen block. 

Level 3 moves the paddle to the middle, so powerups fall both ways,
and losing a life occurs at both ends of the screen. Extra blocks included are slown down ball, paddle widen, and paddle speed blocks. 


### Assumptions

Level files are in the format level + number + .txt

Level text files contain one line of information to set up the level, in order: paddle x position, 
paddle y position, number of balls above the paddle, and number of balls below the paddle. Under
this are the configuration of the types of blocks separated by commas: 
* 0 - basic block
* D - durable block 
* L - drops an extra life powerup
* B - drops a slow down ball powerup
* P - drops a widen paddle powerup
* X - no block is placed at this position 

### Notes

The initial size of all objects are determined by constants. Blocks, balls, and powerups do not 
change sizes. 

Each block destroyed by the ball adds one point to the score

Each powerup is color coded by what its effect is, but powerup blocks are randomly colored so that 
whether it drops a powerup or not is a surprise to the player.

Durable blocks are shaded dark purple specifically while basic blocks are randomly colored. 

Cheat keys that enhance gameplay should not work when the ball isn't launched or the game is paused 

### Impressions

