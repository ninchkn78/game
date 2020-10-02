# Game Design Final
### Christian Welch Alex Chao

## Team Roles and Responsibilities

 * Alex Chao

 Created overarching structure of project design, implemented functionality within design goals

 * Christian Welch

 Created information display, implementated functionality within design goals



## Design goals

Our goal was to create a fully functional game of breakout, complete with multiple types of block, powerups and levels.

#### What Features are Easy to Add
New blocks, new levels, new powerups
## High-level Design
Our game is split into multiple classes, each handling specific responsibilities within the implementation of the game. 
#### Core Classes

Our core classes are Game, GameLogic, Level, Paddle, Block, and Ball. Paddle, Ball, and Block are classes that encapsulate the functionality the paddle, the ball, and the block. Block is an abstract superclass, with multiple subclasses that implement different types of blocks. GameLogic  handles mechanics such as win/loss conditions and pausing the game. Level implements the majority of the in-game workings such as adding powerups and removing blocks. Finally, game runs the JavaFX environment and utilizes the step function that actually runs the game. 

## Assumptions that Affect the Design

Level files are in the format level + number + .txt

Level text files contain one line of information to set up the level, in order: paddle x position, 
paddle y position, number of balls above the paddle, and number of balls below the paddle. Under
this are the configuration of the types of blocks separated by commas: 
* 0 - basic block
* D - durable block 
* L - drops an extra life powerup
* B - drops a slow down ball powerup
* P - drops a widen paddle powerup
* S - drops a speed up paddle powerup
* X - no block is placed at this position 

#### Features Affected by Assumptions

Our implementation as it stands can only utilize level files containing the above types of blocks. 

## New Features HowTo

To add a new level, simply make a level file of the format "levelXX.txt" within the level folder. The integer XX is the level number that this file will be assigned to within the game. 

To add a new powerup or block, define a new subclass of Powerup or Block and implement their functions to the specifications of your desired addition.

#### Easy to Add Features

Levels, Blocks, Powerups
#### Other Features not yet Done

High score tracking