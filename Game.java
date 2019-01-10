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

public class Game{

  public static void putString(int r, int c, Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
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

  public static void drawBlock(Terminal t, String s, int blockNum){
    t.applyForegroundColor(Terminal.Color.GREEN);
    if (blockNum == 1) {
      putString(0,30,t,s);
    }
    if (blockNum == 2) {
      putString(5,30,t,s);
    }
    if (blockNum == 3) {
      putString(10,30,t,s);
    }
  }

  public static int runGame(Terminal t, Board B, int numB){
    Block a = new Block();
    Block b = new Block();
    Block c = new Block();
    int num = numB;
    if (num == 0){
      a = B.generateBlock();
      b = B.generateBlock();
      c = B.generateBlock();
      num = 3;
    }
    drawBoard(t, B.toString());
    drawBlock(t, a.toString(), 1);
    //drawBlock(t, b.toString(), 2);
    //drawBlock(t, c.toString(), 3);
    return num;
  }

  public static void main(String[] args) {
    Board game = new Board();

    int numBlocks = 0;

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
        numBlocks = runGame(terminal, game, numBlocks);
        if (key != null){
          if (key.getKind() == Key.Kind.Tab) {
            terminal.clearScreen();
            mode = 0;
            numBlocks = 0;
          }

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
