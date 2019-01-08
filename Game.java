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

  public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}

  public static void drawStartingScreen(Terminal t){

  }

  public static void startGame(Terminal t, String s){
    t.applyForegroundColor(Terminal.Color.BLACK);//the color of the board will be black
    t.moveCursor(0,0);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}


  }

  public static void main(String[] args) {
    Board game = new Board();

    int x = 0;//coordinates for the cursor
		int y = 0;

    Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

    boolean running = true;
    int mode = 0;

    //drawBoard(terminal);//the board will be drawn once only

    while(running){

      terminal.moveCursor(x,y);

			terminal.applyForegroundColor(Terminal.Color.BLACK);


      Key key = terminal.readInput();

			if (key != null){

        if (mode == 0) {
          drawStartingScreen(terminal);
          if (key.getKind() == Key.Kind.Enter) {
            startGame(terminal, game.toString());
  					mode = 1;
  				}
        }

        if (mode == 1){

          if (key.getKind() == Key.Kind.Tab) {
  					mode = 2;
  				}
        }

        if (mode == 2){
          if (key.getKind() == Key.Kind.Escape) {
  					terminal.exitPrivateMode();
  					running = false;
  				}
        }


          if (key.getKind() == Key.Kind.Enter){//the first enter will erase the text
            for (int i = 0; i < 25; i++){
              terminal.moveCursor(i,24);
              terminal.putCharacter(' ');
            }
          }
      }

    }

  }
  //looping thorugh and adding the layout of the obard for background
}
