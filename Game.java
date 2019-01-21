//API : http://mabe02.github.io/lanterna/apidocs/2.1/
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
import com.googlecode.lanterna.screen.Screen;

public class Game{

  public static void putString(int r, int c, Terminal t, String s){
		t.moveCursor(r,c);
    t.applyForegroundColor(Terminal.Color.WHITE);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}
  //puts text at certain position on terminal
  public static void putString(int r, int c,Terminal t, String s, Terminal.Color forg){
    t.moveCursor(r,c);
    t.applyForegroundColor(forg);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }
//puts text at certain position on interval with color
  public static void drawStartingScreen(Terminal t, TerminalSize s){
    String text = "1010!";
    String text2 = "Press 1 for ENDLESS MODE";
    String text3 = "Press 2 for TIMED MODE";
    int r = s.getColumns()/2 - text.length()/2;
    int c = 0;

    putString(r,c,t,text,Terminal.Color.WHITE);
    r = s.getColumns()/2 - text2.length()/2;
    c = 10;
    t.applySGR(Terminal.SGR.ENTER_BLINK);
    putString(r,c,t,text2,Terminal.Color.WHITE);
    r = s.getColumns()/2 - text3.length()/2;
    c = 15;
    putString(r,c,t,text3,Terminal.Color.WHITE);
    t.applySGR(Terminal.SGR.EXIT_BLINK);
  }
//draws starting screen before game appears
  public static void refreshBoard(Terminal t, Board b){
    putString(0,0,t,b.toString(),Terminal.Color.WHITE);
    int x = 2;
    int y = 1;
    Square[][] myBoard = b.getBoard();
    for (int i = 0; i < myBoard.length; i++){
      for (int j = 0; j < myBoard[0].length; j++){
        if (myBoard[i][j] != null){
          putString(x,y,t,"@",myBoard[i][j].getColor());
        } else {
          putString(x,y,t," ");
        }
        x += 4;
        if (j == myBoard[0].length - 1){
          x = 2;
          y += 2;
        }
      }
    }
  }
//updates info on the board
  public static void putBlock(Terminal t, String s, int num, Terminal.Color c){
    if (num == 1){
      int x = 0;
      int y = 30;
      int count = 1;
      t.moveCursor(x,y);
      for(int i = 0; i < s.length();i++){
        t.applyForegroundColor(c);
        t.putCharacter(s.charAt(i));
        if (s.charAt(i) == '\n'){
          t.moveCursor(x,y+count);
          count++;
        }
      }
    }
    if (num == 2){
      int x = 22;
      int y = 30;
      int count = 1;
      t.moveCursor(x,y);
      for(int i = 0; i < s.length();i++){
        t.applyForegroundColor(c);
        t.putCharacter(s.charAt(i));
        if (s.charAt(i) == '\n'){
          t.moveCursor(x,y+count);
          count++;
        }
      }
    }
    if (num == 3){
      int x = 44;
      int y = 30;
      int count = 1;
      t.moveCursor(x,y);
      for(int i = 0; i < s.length();i++){
        t.applyForegroundColor(c);
        t.putCharacter(s.charAt(i));
        if (s.charAt(i) == '\n'){
          t.moveCursor(x,y+count);
          count++;
        }
      }
    }
  }
//puts block on selection space
  public static void startGame(Terminal t, Board B, Block a, Block b, Block c){
    refreshBoard(t, B);
    putBlock(t,a.toString(), 1,a.getColor());
    putBlock(t,b.toString(), 2,b.getColor());
    putBlock(t,c.toString(), 3,c.getColor());
  }
//starting game mechanism
  public static void moveBlockOnBoard(Terminal t, Block b, int x, int y){
    int oriX = x;
    Square[][] myBlock = b.getBlock();
    for (int i = 0; i < myBlock.length; i++){
      for (int j = 0; j < myBlock[0].length; j++){
        if (myBlock[i][j] != null){
          t.moveCursor(x,y);
          t.applyForegroundColor(myBlock[i][j].getColor());
          t.putCharacter('@');
        }
        x += 4;
        if (j == myBlock[0].length - 1){
          x = oriX;
          y += 2;
        }
      }
    }
  }
//allows for updating the position of the block when it is already on board and player is controlling with up down right left arrows
  public static void eraseBlock(Terminal t, Block b, int x, int y){
    int oriX = x;
    Square[][] myBlock = b.getBlock();
    for (int i = 0; i < myBlock.length; i++){
      for (int j = 0; j < myBlock[0].length; j++){
        if (myBlock[i][j] != null){
          t.moveCursor(x,y);
          t.putCharacter(' ');
        }
        x += 4;
        if (j == myBlock[0].length - 1){
          x = oriX;
          y += 2;
        }
      }
    }
  }
//erasing block in selection after choosing it
  public static boolean placeBlockOnBoard(Board B, Block b, int x, int y){
    int i = y/2;
    int j = x/4;
    return B.placeBlock(b, i, j);
  }
//placing selected block on the board
  private static void putLetter(Terminal t, int x, int y, String s){
    int OriX = x;
    for (int i = 0; i < s.length(); i++){
      if (s.charAt(i) == '\n') {
        y++;
        x = OriX;
      } else {
        t.moveCursor(x,y);
        t.putCharacter(s.charAt(i));
        x++;
      }
    }
  }
//putting letters of game over on the end screen
  public static void endGame(Terminal t){
    String G = "+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+\n|   |\n+---+\n|   |\n+---+       +---+---+\n|   |       |   |   |\n+---+	      +---+---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+";
    String A = "+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+";
    String M = "+---+---+   +---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+\n|   |   |   |   |   |\n+---+   +---+   +---+\n|   |   |   |   |   |\n+---+   +---+   +---+\n|   |   |   |   |   |\n+---+   +---+   +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+";
    String E = "+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+\n|   |\n+---+\n|   |\n+---+---+---+\n|   |   |   |\n+---+---+---+\n|   |\n+---+\n|   |\n+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+";

    String O = "+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+";
    String V = "+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+---+---+---+---+\n    |   |   |   |   \n    +---+---+---+";

    String R = "+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+---+---+---+---+\n|   |   |   |   |\n+---+---+---+---+---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+           +---+";

    putLetter(t, 0, 0, G);
    putLetter(t, 24, 0, A);
    putLetter(t, 48, 0, M);
    putLetter(t, 72, 0, E);

    putLetter(t, 0, 16, O);
    putLetter(t, 24, 16, V);
    putLetter(t, 48, 16, E);
    putLetter(t, 72, 16, R);
    putString(36, 40,t,"Press Esc to exit game");
  }
  //printing of screen after game ends
  public static void endMenu(Terminal t,TerminalSize s){
    int r = s.getColumns()/2 - "No more moves! Use Powerup to Coninue Game?".length()/2;
    int c = 0;

    putString(r,c,t,"No more moves! Use Powerup to Coninue Game?",Terminal.Color.WHITE);
    t.applySGR(Terminal.SGR.ENTER_BLINK);
    putString(r,c+1,t,"Pay 300 points for New Selection of Blocks",Terminal.Color.WHITE);
    t.applySGR(Terminal.SGR.EXIT_BLINK);
    putString(r,c+2,t,"No, Restart Game",Terminal.Color.WHITE);
    putString(r,c+3,t,"No, End Game",Terminal.Color.WHITE);
  }
  public static void main(String[] args) {

    Board game = new Board();
    Square[][] temp=game.getBoard();

    Block a = new emptyBlock();
    Block b = new emptyBlock();
    Block c = new emptyBlock();
    int numBlocks = 0;
    //number of blocks in block seelction
    int selectedBlock = 1;
    //which of the three provided blocks are selected
    int flicker=1;
    //which one to blink on
    Block theChosenOne = new emptyBlock();
    //selected block
    boolean aEmpty = true;
    boolean bEmpty = true;
    boolean cEmpty = true;
    boolean blockOnBoard = false;
    boolean gg=false;
    //game over?
    int blockX = 2;
    int blockY = 1;

    Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();

		terminal.setCursorVisible(false);

    boolean running = true;
    int mode = 0;

    long tStart = 0; //starting time variable
    long lastSecond = 0; //second counter for timed mode

    while(running){

			terminal.applyForegroundColor(Terminal.Color.WHITE);
      terminal.applyBackgroundColor(Terminal.Color.DEFAULT);


      Key key = terminal.readInput();


      if (mode == 0) {
        drawStartingScreen(terminal, size);
        if (key != null){
          if (key.getCharacter() == '1') {
            terminal.clearScreen();
  					mode = 1;
          }
          if (key.getCharacter() == '2') {
            tStart = System.currentTimeMillis(); //timer starts
            terminal.clearScreen();
  					mode = 2;
          }
				}
      }
//the starting screen before entering into game

      if (mode == 1 || mode == 2){
        long tEnd = System.currentTimeMillis(); //timer starts when program runs
        long millis = tEnd - tStart;
        if(millis/1000 > lastSecond){
          lastSecond = millis / 1000;
          //one second has passed.
        }
        if (mode == 2){
          putString(52,2,terminal,"Time Left: "+ (10 - lastSecond));
          if (lastSecond == 10){
            mode = 3;
            flicker = 0;
            terminal.clearScreen();
          }
        }
        if (numBlocks == 0){
          a = game.generateBlock();
          aEmpty = false;
          b = game.generateBlock();
          bEmpty = false;
          c = game.generateBlock();
          cEmpty = false;
          startGame(terminal, game, a, b, c);
          refreshBoard(terminal, game);
          gg=game.GameOver(a,b,c);
          numBlocks = 3;
          flicker=1;
          //if no more blocks in selection, generate new ones and refresh board and calculate if game over with new selection of blocks
        } else {
          if (flicker == 1){
            terminal.applySGR(Terminal.SGR.ENTER_BLINK);
            putBlock(terminal,a.toString(), flicker,a.getColor());
            terminal.applySGR(Terminal.SGR.EXIT_BLINK);
          }
          if (flicker == 2){
            terminal.applySGR(Terminal.SGR.ENTER_BLINK);
            putBlock(terminal,b.toString(), flicker,b.getColor());
            terminal.applySGR(Terminal.SGR.EXIT_BLINK);
          }
          if (flicker == 3){
            terminal.applySGR(Terminal.SGR.ENTER_BLINK);
            putBlock(terminal,c.toString(), flicker,c.getColor());
            terminal.applySGR(Terminal.SGR.EXIT_BLINK);
          }
          temp=game.getBoard();
        }
// if blocks is not blink on blocks
        if (key != null){
          //if key is being pressed
          if(key.getKind()==Key.Kind.Delete){
            if (game.getScore()<300){
              putString(0,23,terminal,"                                                                                 ");
              putString(0,23,terminal,"Sorry! Your score is not high enough to purchase New Selection Power-up: 300");
            }
            //delete powerup won't work if score is less than 300
            else{
              a = new emptyBlock();
              b = new emptyBlock();
              c = new emptyBlock();
              putBlock(terminal,a.toString(), 1,a.getColor());
              putBlock(terminal,b.toString(), 2,b.getColor());
              putBlock(terminal,c.toString(), 3,c.getColor());
              a = game.generateBlock();
              aEmpty = false;
              b = game.generateBlock();
              bEmpty = false;
              c = game.generateBlock();
              cEmpty = false;
              putBlock(terminal,a.toString(), 1,a.getColor());
              putBlock(terminal,b.toString(), 2,b.getColor());
              putBlock(terminal,c.toString(), 3,c.getColor());
              game.powerUps(1);
              putString(58,7, terminal, "                            ");
              putString(58,7, terminal, ""+game.getScore());
              gg=game.GameOver(a,b,c);
              numBlocks = 3;
              putString(58,7, terminal, "                            ");
              putString(58,7, terminal, ""+game.getScore());
            }
            //score is high enough to get a newly generated block with score and gg calculated
          }
          if (key.getKind() == Key.Kind.Tab) {
            terminal.clearScreen();
            mode = 0;
            numBlocks = 0;
            game = new Board();
            selectedBlock = 1;
            aEmpty = true;
            bEmpty = true;
            cEmpty = true;
            blockOnBoard = false;
            temp=game.getBoard();
            lastSecond = 0;
          }
          //tab restarts everything
          if (blockOnBoard == false){
//if selected block isnt on board
            putString(0,23,terminal,"                                                                                ");
            if (key.getKind() == Key.Kind.ArrowRight){
              if (flicker == 1){
                putBlock(terminal,a.toString(), 1,a.getColor());
                if (!bEmpty){
                  flicker = 2;
                } else if (!cEmpty){
                  flicker = 3;
                }
              } else if (flicker == 2){
                putBlock(terminal,b.toString(), 2,b.getColor());
                if (!cEmpty){
                  flicker = 3;
                } else if (!aEmpty){
                  flicker = 1;
                }
              } else if (flicker == 3){
                putBlock(terminal,c.toString(), 3,c.getColor());
                if (!aEmpty){
                  flicker = 1;
                } else if (!bEmpty){
                  flicker = 2;
                }
              }
            }
//right arrow functions to blink on the next block (wraps after third block)
            if (key.getKind() == Key.Kind.ArrowLeft) {
              if (flicker == 1){
                putBlock(terminal,a.toString(), 1,a.getColor());
                if (!cEmpty){
                  flicker = 3;
                } else if (!bEmpty){
                  flicker = 2;
                }
              } else if (flicker == 2){
                putBlock(terminal,b.toString(), 2,b.getColor());
                if (!aEmpty){
                  flicker = 1;
                } else if (!cEmpty){
                  flicker = 3;
                }
              } else if (flicker == 3){
                putBlock(terminal,c.toString(), 3,c.getColor());
                if (!bEmpty){
                  flicker = 2;
                } else if (!aEmpty){
                  flicker = 1;
                }
              }
            }
//left arrow functions to blink on the previous block (wraps after you reach first block)
            if (key.getKind() == Key.Kind.ArrowUp) {
              selectedBlock=flicker;
              if (flicker == 1){
                theChosenOne = a;
                moveBlockOnBoard(terminal, a, 2, 1);
                a = new emptyBlock();
                aEmpty = true;
                putBlock(terminal,a.toString(), 1,a.getColor());
                if (!bEmpty){
                  flicker = 2;
                } else if (!cEmpty){
                  flicker = 3;
                }
              } else if (flicker == 2){
                theChosenOne = b;
                moveBlockOnBoard(terminal, b, 2, 1);
                b = new emptyBlock();
                bEmpty = true;
                putBlock(terminal,b.toString(), 2,b.getColor());
                if (!cEmpty){
                  flicker = 3;
                } else if (!aEmpty){
                  flicker = 1;
                }
              } else if (flicker == 3){
                theChosenOne = c;
                moveBlockOnBoard(terminal, c, 2, 1);
                c = new emptyBlock();
                cEmpty = true;
                putBlock(terminal,c.toString(), 3,c.getColor());
                if (!aEmpty){
                  flicker = 1;
                } else if (!bEmpty){
                  flicker = 2;
                }
              }
              blockX = 2;
              blockY = 1;
              blockOnBoard = true;
            }
//pressing up will make the block disapear but appear on the board
          } else {
//if block is already on board
            if (key.getKind() == Key.Kind.ArrowUp) {
              putString(0,23,terminal,"                                                                                 ");
              if (blockY != 1) {
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockY -= 2;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23, terminal, "You have reached the top of the board");
              }
            }
//up arrow functions as to shift the position of the chosen block up one until it reaches top which will output message
            if (key.getKind() == Key.Kind.ArrowDown) {
              putString(0,23,terminal,"                                                                                 ");
              if (blockY != theChosenOne.getLength()*-2 + 21) {
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockY += 2;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23, terminal, "You have reached the bottom of the board");
              }
            }
//down arrow functions as to shift the position of the chosen block down one until it reaches bottom which will output message
            if (key.getKind() == Key.Kind.ArrowLeft) {
              putString(0,23,terminal,"                                                                                 ");
              if (blockX != 2){
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockX -= 4;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23, terminal, "You have reached the left side of the board");
              }
            }
//left arrow functions as to shift the position of the chosen block left one until it reaches left border which will output message
            if (key.getKind() == Key.Kind.ArrowRight) {
              putString(0,23,terminal,"                                                                                 ");
              if (blockX != theChosenOne.getWidth()*-4 + 42) {
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockX += 4;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23, terminal, "You have reached the right side of the board");
              }
            }
            //right arrow functions as to shift the position of the chosen block right one until it reaches left border which will output message
            if (key.getKind() == Key.Kind.Backspace){
              blockOnBoard=false;
              game.setBoard(temp);
              refreshBoard(terminal,game);
              putBlock(terminal,theChosenOne.toString(),selectedBlock,theChosenOne.getColor());
              if(selectedBlock==1){
                a=theChosenOne;
                aEmpty=false;
              }
              else if (selectedBlock==2){
                b=theChosenOne;
                bEmpty=false;
              }
              else if(selectedBlock==3){
                c=theChosenOne;
                cEmpty=false;
              }
              theChosenOne=new emptyBlock();
            }
//backspace erases the chosen block on the board and makes it appear on the selection block again
            if (key.getKind() == Key.Kind.Enter) {
              if (placeBlockOnBoard(game, theChosenOne, blockX, blockY)){
                putString(0,23,terminal,"                                                                                ");
                if (game.checkRows()){
                  putString(0,23,terminal,"You cleared a row");
                }
                if (game.checkCols()){
                  putString(0,23,terminal,"You cleared a column");
                }
                refreshBoard(terminal, game);
                temp=game.getBoard();
                numBlocks--;
                blockOnBoard = false;
                if(!aEmpty){
                  gg=game.BlockOver(a);
                }
                if(!bEmpty){
                  gg=game.BlockOver(b);
                }
                if(!cEmpty){
                  gg=game.BlockOver(c);
                }
                if(!aEmpty&&!bEmpty){
                  gg=game.BlockOver(a)&&game.BlockOver(b);
                }
                if(!bEmpty&&!cEmpty){
                  gg=game.BlockOver(b)&&game.BlockOver(c);
                }
                if(!aEmpty&&!cEmpty){
                  gg=game.BlockOver(a)&&game.BlockOver(c);
                }
                if(!aEmpty&&!cEmpty&&!bEmpty){
                  gg=game.GameOver(a,b,c);
                }
                putString(0,23,terminal,"                                                                                ");
                putString(0,23,terminal,"You have placed a block");
              } else {
                putString(0,23,terminal,"                                                                                ");
                putString(0,23,terminal,"Block cannot be placed here");
              }
                putString(58,7, terminal, "                            ");
                putString(58,7, terminal, ""+game.getScore());
                if(gg){
                  terminal.clearScreen();
                  mode = 3;
                }
                temp=game.getBoard();
            }
//enter permanently places block in position unless there is already part of a block underneath it then it outputs message
          }
          putString(0,45,terminal,"["+key.getCharacter() +"]" + selectedBlock);
        }
      }

      if (mode == 3){
        /*endMenu(terminal);
        if (key != null){
          int blink=1;
          if (key.getKind() == Key.Kind.ArrowDown) {
            t.applySGR(Terminal.SGR.ENTER_BLINK);
            putString(r,c,t,text2,Terminal.Color.WHITE);
            t.applySGR(Terminal.SGR.EXIT_BLINK);
            } else {
            }
          }*/
        endGame(terminal);
      }

      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          running = false;
          //escaping the game
        }
      }
    }

  }
  //looping thorugh and adding the layout of the obard for background
}
