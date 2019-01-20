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

  public static void putString(int r, int c,Terminal t, String s, Terminal.Color forg){
    t.moveCursor(r,c);
    t.applyForegroundColor(forg);

    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public static void drawStartingScreen(Terminal t, TerminalSize s){
    String text = "1010!";
    String text2 = "Press ENTER to START GAME";
    int r = s.getColumns()/2 - text.length()/2;
    int c = 0;

    putString(r,c,t,text,Terminal.Color.WHITE);
    r = s.getColumns()/2 - text2.length()/2;
    c = 10;
    t.applySGR(Terminal.SGR.ENTER_BLINK);
    putString(r,c,t,text2,Terminal.Color.WHITE);
    t.applySGR(Terminal.SGR.EXIT_BLINK);
  }

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

  public static void startGame(Terminal t, Board B, Block a, Block b, Block c){
    refreshBoard(t, B);
    putBlock(t,a.toString(), 1,a.getColor());
    putBlock(t,b.toString(), 2,b.getColor());
    putBlock(t,c.toString(), 3,c.getColor());
  }

  public static void moveBlockOnBoard(Terminal t, Block b, int x, int y){
    //putString(90,5,t,""+b.getBlock()[0][0].getColor());
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

  public static boolean placeBlockOnBoard(Board B, Block b, int x, int y){
    int i = y/2;
    int j = x/4;
    return B.placeBlock(b, i, j);
  }

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

  public static void endGame(Terminal t){
    String G = "+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+\n|   |\n+---+\n|   |\n+---+       +---+---+\n|   |       |   |   |\n+---+	    +---+---+\n|   |           |   |\n+---+           +---+\n|   |           |   |\n+---+---+---+---+---+\n|   |   |   |   |   |\n+---+---+---+---+---+";
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
    putString(80, 10,t,"Press Esc to exit game");
  }

  public static void main(String[] args) {

    Board game = new Board();
    Square[][] temp=game.getBoard();

    Block a = new emptyBlock();
    Block b = new emptyBlock();
    Block c = new emptyBlock();
    int numBlocks = 0;
    int selectedBlock = 1;
    int flicker=1;
    Block theChosenOne = new emptyBlock();
    boolean aEmpty = true;
    boolean bEmpty = true;
    boolean cEmpty = true;
    boolean blockOnBoard = false;
    boolean gg=false;
    int blockX = 2;
    int blockY = 1;

    Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

    boolean running = true;
    int mode = 0;

    while(running){

			terminal.applyForegroundColor(Terminal.Color.WHITE);
      terminal.applyBackgroundColor(Terminal.Color.DEFAULT);


      Key key = terminal.readInput();


      if (mode == 0) {
        drawStartingScreen(terminal, size);
        if (key != null){
          if (key.getKind() == Key.Kind.Enter) {
            terminal.clearScreen();
  					mode = 1;
          }
				}
      }


      if (mode == 1){
        if (numBlocks == 0){
          a = game.generateBlock();
          aEmpty = false;
          b = game.generateBlock();
          bEmpty = false;
          c = game.generateBlock();
          cEmpty = false;
          startGame(terminal, game, a, b, c);
          refreshBoard(terminal, game);
          gg=(gg||game.GameOver(a,b,c));
          numBlocks = 3;
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

        if (key != null){
          if(key.getKind()==Key.Kind.F1){
            a = game.generateBlock();
            aEmpty = false;
            b = game.generateBlock();
            bEmpty = false;
            c = game.generateBlock();
            cEmpty = false;
            refreshBoard(terminal,game);
            putBlock(terminal,a.toString(), 1,a.getColor());
            putBlock(terminal,b.toString(), 2,b.getColor());
            putBlock(terminal,c.toString(), 3,c.getColor());
            gg=(gg||game.GameOver(a,b,c));
            numBlocks = 3;
            game.powerUps(1);
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
          }
          if (blockOnBoard == false){

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

            if (key.getKind() == Key.Kind.ArrowUp) {
              selectedBlock=flicker;
              if (flicker == 1){
                theChosenOne = a;
                moveBlockOnBoard(terminal, a, 2, 1);
                a = new emptyBlock();
                aEmpty = true;
                putBlock(terminal,a.toString(), 1,a.getColor());
                if(!bEmpty&&!cEmpty){
                  gg=gg&&game.BlockOver(b)&&game.BlockOver(c);
                }
                else if(!bEmpty){
                  gg=game.BlockOver(b);
                }
                else if(!cEmpty){
                  gg=game.BlockOver(c);
                }
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
                if(!bEmpty&&!cEmpty){
                  gg=gg&&game.BlockOver(a)&&game.BlockOver(c);
                }
                else if(!aEmpty){
                  gg=game.BlockOver(a);
                }
                else if(!cEmpty){
                  gg=game.BlockOver(c);
                }
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
                if(!bEmpty&&!cEmpty){
                  gg=gg&&game.BlockOver(b)&&game.BlockOver(c);
                }
                else if(!aEmpty){
                  gg=game.BlockOver(a);
                }
                else if(!bEmpty){
                  gg=game.BlockOver(b);
                }
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

          } else {

            if (key.getKind() == Key.Kind.ArrowUp) {
              putString(0,23,terminal,"                                                        ");
              if (blockY != 1) {
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockY -= 2;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23, terminal, "You have reached the top of the board");
              }
            }

            if (key.getKind() == Key.Kind.ArrowDown) {
              putString(0,23,terminal,"                                                        ");
              if (blockY != theChosenOne.getLength()*-2 + 21) {
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockY += 2;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23, terminal, "You have reached the bottom of the board");
              }
            }

            if (key.getKind() == Key.Kind.ArrowLeft) {
              putString(0,23,terminal,"                                                        ");
              if (blockX != 2){
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockX -= 4;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23, terminal, "You have reached the left side of the board");
              }
            }

            if (key.getKind() == Key.Kind.ArrowRight) {
              putString(0,23,terminal,"                                                        ");
              if (blockX != theChosenOne.getWidth()*-4 + 42) {
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockX += 4;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23, terminal, "You have reached the right side of the board");
              }
            }
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

            if (key.getKind() == Key.Kind.Enter) {
              if (placeBlockOnBoard(game, theChosenOne, blockX, blockY)){
                putString(0,23,terminal,"                                                        ");
                if (game.checkRows()){
                  putString(0,23,terminal,"You cleared a row");
                }
                if (game.checkCols()){
                  putString(0,23,terminal,"You cleared a column");
                }
                refreshBoard(terminal, game);
                //terminal.applyBackgroundColor(Terminal.Color.BLACK);
                numBlocks--;
                blockOnBoard = false;
              } else {
                putString(0,23,terminal,"                                                        ");
                putString(0,23,terminal,"Block cannot be placed here");
              }
                putString(58,7, terminal, ""+game.getScore());
                if(gg){
                  terminal.clearScreen();
                  mode = 3;
                }
                temp=game.getBoard();
            }

          }
          putString(0,45,terminal,"["+key.getCharacter() +"]" + selectedBlock);
        }
      }

      if (mode == 3){
        endGame(terminal);
      }

      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          running = false;
        }
      }
    }

  }
  //looping thorugh and adding the layout of the obard for background
}
