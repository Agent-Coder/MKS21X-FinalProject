# MKS21X-FinalProject

Instructions to Run the Program:

1) Type javac -cp lanterna.jar:. Game.java to compile and java -cp lanterna.jar:. Game to start the program
2) Press ENTER to start the game
3) Use LEFT and RIGHT arrow keys to select a block
4) Press UP arrow key to move the selected block onto the board
5) Use UP DOWN LEFT RIGHT arrow keys to move the block around on the board
6) Press ENTER to place the block on the board
7) Press TAB at any time to restart the game
8) Press ESC to quit the game

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

AZ;01-11-19;
I tried to put the block on the board and make it move around and fixed a few issues with the compiling of the Board and then proceeded to spend most of my time trying to find ways to have lanterna work on my computer. The future is grim.

AZ;01-12-19;
Today, I went to the 12 hour-Stuy Hack thing and so I didn't have time to work on this, HOWEVER, I got more practice with lanterna...thinking of changing to lanterna 3

AC;01-13-19;
The Minimum Viable Product is completed!! I fixed a couple of bugs including one where the player would not be able to pick a block when there is only on block in the screen. I also wrote the code to move the selected block onto the board and a refresh method to redraw the board every time the block is moved. The player will also not be able to move the block outside the board and will receive a message when they try. Another bug for the placement of L blocks was also fixed. Finally, I implemented the clearRow and clearCol methods so the board will update when a block is placed.

AZ;01-14-19;
I was trying to implement the scoring correctly and adding it to each of the method so it would incoporate the scoring. I was also trying to incoporate random colors into the block but that didn't work out yet. I added comments on methods for reading purposes and then deleted uneccessary code/ made it more efficient. Then, I started making the powerUps file which is an extra feature and I am thinking of having the first powerUp be switching the selection part where you can switch out the blocks you have with new ones at the cost of scores.

AZ;01-15-19;
I implemented new random colors for the blocks in block selection, and is trying to make it so the blocks preserve their colors on the board. First part is completed but second part isn't. I also worked on the board score but the score is printing straight down instead of rewriting so its still a work in progress.

AC;01-15-19;
I fixed the bug where a block would change colors after being placed on the board. I had to rewrite the array for the random color generator and add new get color methods for the block. I also started cleaning up our code by deleting old, unnecessary methods.

AZ;01-16-19;
I made a new branch and tried to work on ending the board. I think I'm going to loop through the board when the blocks are generated to see if there are any spots left. So I incorporated variables called Spotsleft and numBlocks in each block. The quick end is when the number of spots can't fit the spots but the hard one is the long looping through the board to find if you can actually place the board anywhere else, which I am still working on.

AZ;01-16-19;
I got the end game to work although there might still be bugs... I have to run a few more cases but I got the bulge of the function working.

AC;01-16-19;
I hard coded the Strings for the word "GAME OVER" for the end screen and started the method putting the words on the board. This methods needs a couple more for loops to work. I also updated this README.md with instructions for the game.
