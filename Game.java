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
    t.applyForegroundColor(Terminal.Color.BLACK);

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

    t.applyForegroundColor(Terminal.Color.BLACK);
    putString(r,c,t,text);
    r = s.getColumns()/2 - text2.length()/2;
    c = 10;
    t.applySGR(Terminal.SGR.ENTER_BLINK);
    putString(r,c,t,text2);
    t.applySGR(Terminal.SGR.EXIT_BLINK);
  }

  public static void drawBoard(Terminal t, String s){
    t.applyForegroundColor(Terminal.Color.BLACK);//the color of the board will be black
    putString(0,0,t,s);
  }

  public static void putBlock(Terminal t, String s, int num){
    if (num == 1){
      int x = 0;
      int y = 30;
      int count = 1;
      t.moveCursor(x,y);
      for(int i = 0; i < s.length();i++){
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
    putBlock(t,a.toString(), 1);
    putBlock(t,b.toString(), 2);
    putBlock(t,c.toString(), 3);
  }

  public static void main(String[] args) {

    Board game = new Board();
    Block a = new emptyBlock();
    Block b = new emptyBlock();
    Block c = new emptyBlock();
    int numBlocks = 0;
    int selectedBlock = 1;

    Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

    boolean running = true;
    int mode = 0;

    while(running){

			terminal.applyForegroundColor(Terminal.Color.BLACK);


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
          b = game.generateBlock();
          c = game.generateBlock();
          startGame(terminal, game, a, b, c);
          numBlocks = 3;
        } else {
          if (selectedBlock == 1){
            terminal.applySGR(Terminal.SGR.ENTER_BLINK);
            putBlock(terminal,a.toString(), selectedBlock);
            terminal.applySGR(Terminal.SGR.EXIT_BLINK);
          }
          if (selectedBlock == 2){
            terminal.applySGR(Terminal.SGR.ENTER_BLINK);
            putBlock(terminal,b.toString(), selectedBlock);
            terminal.applySGR(Terminal.SGR.EXIT_BLINK);
          }
          if (selectedBlock == 3){
            terminal.applySGR(Terminal.SGR.ENTER_BLINK);
            putBlock(terminal,c.toString(), selectedBlock);
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
          }

          if (key.getKind() == Key.Kind.ArrowRight){
            if (selectedBlock == 1){
              putBlock(terminal,a.toString(), 1);
              selectedBlock = 2;
            } else if (selectedBlock == 2){
              putBlock(terminal,b.toString(), 2);
              selectedBlock = 3;
            } else if (selectedBlock == 3){
              putBlock(terminal,c.toString(), 3);
              selectedBlock = 1;
            }
          }

          if (key.getKind() == Key.Kind.ArrowLeft) {
            if (selectedBlock == 1){
              putBlock(terminal,a.toString(), 1);
              selectedBlock = 3;
            } else if (selectedBlock == 2){
              putBlock(terminal,b.toString(), 2);
              selectedBlock = 1;
            } else if (selectedBlock == 3){
              putBlock(terminal,c.toString(), 3);
              selectedBlock = 2;
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
