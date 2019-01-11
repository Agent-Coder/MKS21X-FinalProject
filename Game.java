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

  public static void putBlock(int r, int c, Terminal t, String s){
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
      //putBlock(10,30,t,s);
    }
    if (blockNum == 3) {
      //putBlock(20,30,t,s);
    }
  }

  public static void startGame(Terminal t, Board B, Block a, Block b, Block c){
    drawBoard(t, B.toString());
    //putString(0,30,t,a.toString());
    drawBlock(t, a.toString(), 1);
    //drawBlock(t, b.toString(), 2);
    //drawBlock(t, c.toString(), 3);
  }

  public static void main(String[] args) {

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

        Board game = new Board();

        Block a;
        Block b;
        Block c;

        int numBlocks = 0;

        if (numBlocks == 0){
          a = game.generateBlock();
          b = game.generateBlock();
          c = game.generateBlock();
          //putString(0,30,terminal,a.toString());
          String s = a.toString();
          putString(0,0,terminal,""+(s.equals(a.toString())));
          //startGame(terminal, game, a, b, c);
          numBlocks = 3;
        }
        //putString(40, 0, terminal, "" + numBlocks);
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
