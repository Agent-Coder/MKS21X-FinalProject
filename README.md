# MKS21X-FinalProject

Instructions to run the program:

Logs:

AC;01-03-19;
I worked on writing the Square class and finished all of the methods we initially decided to write. I also changed some private methods to public methods.

AZ;01-03-19;
I finished creating the board and the Start screen that appears when the file is run

AC;01-04-19
I wrote the toString method for the Square class and started writing the constructor for the board class.

AZ;01-04-19;
I added all the private variables for Board class and fixed the Startgame Method to be compatible with lanterna. Then, I wrote the constructor for Board and tweaked the toString of Square.

AZ;01-06-19;
Big Progress!:) I finished the constructor of the block class and added new instance variable to make it easier to do the toString. I also finished the toString and everytime u rerun the program and make a block its a different one. However-big obstance!-some of the randomly generated blocks are disjointed! So we are going to have to come up with an algorithm to generate different JOINTED blocks.

AC;01-06-19;
Board is now on lantera! After some experimenting, I added the lanterna functionality to the game. However, I believe that the Board class is suppose to be our driver class? We will clarify this after a discussion.

AC;01-07-19;
Changed Board class so it is not the driver; drive is now a new class called game. When running the game, there is now three modes: the start screen, the game screen, and the pause screen. I will design the start and pause screen in the future. The game screen displays the board.

AZ;01-07-19;
We decided to make the block class have three little child block classes that are three types: Full, Long, and L-shaped. I finished creating the three block classes and we are able to print it out in main after constructing them.

AZ;01-08-19;
I finished creating the method of generating random blocks and creating the display of selection blocks and added instance variables in the block classes to make it easier to create the selection block and also adjusted the constructors.

AC;01-08-29;
I wrote the two commands placeable() which checks to see if a block can be placed on the board and placeBlock() which modifies the board to place the block. I tested the methods in a separate driver file to see if they worked. I also changed the constructor for board so it will not have any squares unless a player places a block.

AZ;01-09-19;
I finished writing the ClearCol and ClearRow methods that clear the rows and columns when the blocks fill a row or column of the board and then the spotCount increases and the score increases. Also, I fixed the design and corrected much of the "this" stuff needed for the board thing to compile. Also added a couple of instance variables such as Layout a boolean[][] for easier printing for the board.

AC;01-09-19;
I was able to print the block selection onto lantera. I also took out the pause screen and merged the features onto the game screen. Something went wrong while branching and my commits for today were lost.

AZ;01-10-19;
I worked on doing the shifts (not finished) and puting the block on the board and outputting the array that would be used to print that. Also reduced the amount of redundancy in the code.

AC;01-10-19;
A lot of progress was made today! The blocks are now printed on the board separately and blinks to show the player which block they are on. When the player presses the up arrow key, they will have officially selected the block. I also wrote another child class of block as a placeholder when a player selects a block to place on the board.

AZ;01-10-19;
I tried to put the block on the board and make it move around and fixed a few issues with the compiling of the Board and then proceeded to spend most of my time trying to find ways to have lanterna work on my computer. The future is grim.
