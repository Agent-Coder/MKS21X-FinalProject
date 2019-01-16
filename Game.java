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
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}

  public static void putString(int r, int c,Terminal t, String s, Terminal.Color forg, Terminal.Color back ){
    t.moveCursor(r,c);
    t.applyBackgroundColor(forg);
    t.applyForegroundColor(Terminal.Color.WHITE);

    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
    t.applyBackgroundColor(Terminal.Color.DEFAULT);
    t.applyForegroundColor(Terminal.Color.DEFAULT);
  }

  public static void drawStartingScreen(Terminal t, TerminalSize s){
    String text = "1010!";
    String text2 = "Press ENTER to START GAME";
    int r = s.getColumns()/2 - text.length()/2;
    int c = 0;

    t.applyForegroundColor(Terminal.Color.WHITE);
    putString(r,c,t,text);
    r = s.getColumns()/2 - text2.length()/2;
    c = 10;
    t.applySGR(Terminal.SGR.ENTER_BLINK);
    putString(r,c,t,text2);
    t.applySGR(Terminal.SGR.EXIT_BLINK);
  }

  public static void drawBoard(Terminal t, String s){
    t.applyForegroundColor(Terminal.Color.WHITE);//the color of the board will be black
    putString(0,0,t,s);
  }

  public static void refreshBoard(Terminal t, Board b){
    t.applyForegroundColor(Terminal.Color.WHITE);
    int x = 2;
    int y = 1;
    Square[][] myBoard = b.getBoard();
    for (int i = 0; i < myBoard.length; i++){
      for (int j = 0; j < myBoard[0].length; j++){
        if (myBoard[i][j] != null){
          t.moveCursor(x,y);
          t.putCharacter('@');
        } else {
          t.moveCursor(x,y);
          t.putCharacter(' ');
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
    drawBoard(t, B.toString());
    putBlock(t,a.toString(), 1,a.getColor());
    putBlock(t,b.toString(), 2,b.getColor());
    putBlock(t,c.toString(), 3,c.getColor());
  }

  public static void moveBlockOnBoard(Terminal t, Block b, int x, int y){
    int oriX = x;
    Square[][] myBlock = b.getBlock();
    for (int i = 0; i < myBlock.length; i++){
      for (int j = 0; j < myBlock[0].length; j++){
        if (myBlock[i][j] != null){
          t.moveCursor(x,y);
          t.applyForegroundColor(b.getColor());
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

  public static void main(String[] args) {

    Board game = new Board();
    Block a = new emptyBlock();
    Block b = new emptyBlock();
    Block c = new emptyBlock();
    int numBlocks = 0;
    int selectedBlock = 1;
    Block theChosenOne = new emptyBlock();
    boolean aEmpty = true;
    boolean bEmpty = true;
    boolean cEmpty = true;
    boolean blockOnBoard = false;
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
          numBlocks = 3;
          selectedBlock = 1;
        } else {
          if (selectedBlock == 1){
            terminal.applySGR(Terminal.SGR.ENTER_BLINK);
            putBlock(terminal,a.toString(), selectedBlock,a.getColor());
            terminal.applySGR(Terminal.SGR.EXIT_BLINK);
          }
          if (selectedBlock == 2){
            terminal.applySGR(Terminal.SGR.ENTER_BLINK);
            putBlock(terminal,b.toString(), selectedBlock,b.getColor());
            terminal.applySGR(Terminal.SGR.EXIT_BLINK);
          }
          if (selectedBlock == 3){
            terminal.applySGR(Terminal.SGR.ENTER_BLINK);
            putBlock(terminal,c.toString(), selectedBlock,c.getColor());
            terminal.applySGR(Terminal.SGR.EXIT_BLINK);
          }
        }

        if (key != null){

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
          }

          if (blockOnBoard == false){

            if (key.getKind() == Key.Kind.ArrowRight){
              if (selectedBlock == 1){
                putBlock(terminal,a.toString(), 1,a.getColor());
                if (!bEmpty){
                  selectedBlock = 2;
                } else if (!cEmpty){
                  selectedBlock = 3;
                }
              } else if (selectedBlock == 2){
                putBlock(terminal,b.toString(), 2,b.getColor());
                if (!cEmpty){
                  selectedBlock = 3;
                } else if (!aEmpty){
                  selectedBlock = 1;
                }
              } else if (selectedBlock == 3){
                putBlock(terminal,c.toString(), 3,c.getColor());
                if (!aEmpty){
                  selectedBlock = 1;
                } else if (!bEmpty){
                  selectedBlock = 2;
                }
              }
            }

            if (key.getKind() == Key.Kind.ArrowLeft) {
              if (selectedBlock == 1){
                putBlock(terminal,a.toString(), 1,a.getColor());
                if (!cEmpty){
                  selectedBlock = 3;
                } else if (!bEmpty){
                  selectedBlock = 2;
                }
              } else if (selectedBlock == 2){
                putBlock(terminal,b.toString(), 2,b.getColor());
                if (!aEmpty){
                  selectedBlock = 1;
                } else if (!cEmpty){
                  selectedBlock = 3;
                }
              } else if (selectedBlock == 3){
                putBlock(terminal,c.toString(), 3,c.getColor());
                if (!bEmpty){
                  selectedBlock = 2;
                } else if (!aEmpty){
                  selectedBlock = 1;
                }
              }
            }

            if (key.getKind() == Key.Kind.ArrowUp) {
              if (selectedBlock == 1){
                theChosenOne = a;
                moveBlockOnBoard(terminal, a, 2, 1);
                a = new emptyBlock();
                aEmpty = true;
                putBlock(terminal,a.toString(), 1,a.getColor());
                if (!bEmpty){
                  selectedBlock = 2;
                } else if (!cEmpty){
                  selectedBlock = 3;
                }
              } else if (selectedBlock == 2){
                theChosenOne = b;
                moveBlockOnBoard(terminal, b, 2, 1);
                b = new emptyBlock();
                bEmpty = true;
                putBlock(terminal,b.toString(), 2,b.getColor());
                if (!cEmpty){
                  selectedBlock = 3;
                } else if (!aEmpty){
                  selectedBlock = 1;
                }
              } else if (selectedBlock == 3){
                theChosenOne = c;
                moveBlockOnBoard(terminal, c, 2, 1);
                c = new emptyBlock();
                cEmpty = true;
                putBlock(terminal,c.toString(), 3,c.getColor());
                if (!aEmpty){
                  selectedBlock = 1;
                } else if (!bEmpty){
                  selectedBlock = 2;
                }
              }
              blockX = 2;
              blockY = 1;
              blockOnBoard = true;
            }

          } else {

            if (key.getKind() == Key.Kind.ArrowUp) {
              if (blockY != 1) {
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockY -= 2;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23,terminal,"                                                        ");
                putString(0,23, terminal, "You have reached the top of the board");
              }
            }

            if (key.getKind() == Key.Kind.ArrowDown) {
              if (blockY != theChosenOne.getLength()*-2 + 21) {
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockY += 2;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23,terminal,"                                                        ");
                putString(0,23, terminal, "You have reached the bottom of the board");
              }
            }

            if (key.getKind() == Key.Kind.ArrowLeft) {
              if (blockX != 2){
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockX -= 4;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23,terminal,"                                                        ");
                putString(0,23, terminal, "You have reached the left side of the board");
              }
            }

            if (key.getKind() == Key.Kind.ArrowRight) {
              if (blockX != theChosenOne.getWidth()*-4 + 42) {
                eraseBlock(terminal,theChosenOne, blockX, blockY);
                blockX += 4;
                refreshBoard(terminal, game);
                moveBlockOnBoard(terminal,theChosenOne, blockX, blockY);
              } else {
                putString(0,23,terminal,"                                                        ");
                putString(0,23, terminal, "You have reached the right side of the board");
              }
            }

            if (key.getKind() == Key.Kind.Enter) {
              if (placeBlockOnBoard(game, theChosenOne, blockX, blockY)){
                blockOnBoard = false;
                putString(0,23,terminal,"                                                        ");
                numBlocks--;
                if (game.checkRows()){
                  putString(0,23,terminal,"                                                        ");
                  putString(0,23,terminal,"You cleared a row");
                }
                if (game.checkCols()){
                  putString(0,23,terminal,"                                                        ");
                  putString(0,23,terminal,"You cleared a column");
                }
                refreshBoard(terminal, game);
                putString(57,7, terminal, ""+game.getScore());
              } else {
                putString(0,23,terminal,"                                                        ");
                putString(0,23,terminal,"Block cannot be placed here");
              }
            }

          }

          putString(0,45,terminal,"["+key.getCharacter() +"]" + selectedBlock);
        }
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
