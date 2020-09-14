# Game Plan
## Alex Chao


### Breakout Variation Ideas

### Interesting Existing Game Variations

 * Vortex, it was interesting to think about how to put the paddle somewhere else relationally to the blocks instead of 
 just at the bottom 

 * Game 2


#### Block Ideas

 * Normal Block, breaks on one hit 

 * Buff block, takes multiple hits to break

 * Bomb block, breaks blocks near it 
 
 * 


#### Power Up Ideas

 * Speed up paddle 

 * Makes ball do more damage 

 * Wider paddle 
 
 * Extra life/immunity 


#### Cheat Key Ideas

 * "R" - resets ball and paddle 
 
 * " " - pause/unpause game 

 * "Tab" -go to next level

 * "$" - no death (ball bounces up from bottom)

 * "+/-" speed up or slow down ball 


#### Level Descriptions
 * Common across levels 
   * score displays based off of blocks broken 
   
   * level of lives displayed 
   
   * win and loss statements show up on screen 
   
 * Level 1
   * Grid of blocks 
   
   * all blocks take one hit to break, normal speed of paddle, normal speed of ball, no powerups,
   paddle is at the bottom, 5 lives  

 * Level 2
   * shell, middle is open, powerups are protected by other blocks,

   * paddle is at the top, introduce powerups, some rise up randomly and some come from blocks, time 
   limit, 3 lives

 * Level 3
   * Blocks surround the paddle from the top and bottom 

   * power downs (slow down paddle, speed up ball), two balls, paddle in middle, 1 life


### Possible Classes

 * Block
   * represents a block 

   * handleHit method that determines what happens when the ball hits the block

 * Paddle
   * represents the player controlled paddle 

   * movePaddle method moves the paddle 

 * LevelConfig
   * sets up the scene for the current level that the player is on 

   * setUpBlocks that given a textfile and a root, adds all the blocks specified in the text file to the root

 * Ball 
   * represents the ball 

   * handleBounce method that changes the direction of the ball when it hits something 

 * Powerup 
   * represents the different types of powerups in the game 

   * handlePowerup method that makes changes when the powerup hits the paddle 
   
 * Styles
