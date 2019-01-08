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

public class Board{
  int spotCount;
  int blockCount;
  int score;


  public static void drawBoard(Terminal t){
    Square[][] board = new Square[10][10];

    String s="";//this is the string containing the entire board
    int c=10;
    while (c!=0){
          s=s+"+---+---+---+---+---+---+---+---+---+---+\n";
          if (c==7){
            s=s+"|   |   |   |   |   |   |   |   |   |   |           SCORE:0\n";
          }
          else if(c==5){
            s=s+"|   |   |   |   |   |   |   |   |   |   |           (Press M for Menu)\n";
          }
          else{
          s=s+"|   |   |   |   |   |   |   |   |   |   |\n";
        }
        c--;
    }
    s=s+"+---+---+---+---+---+---+---+---+---+---+\n"+"\n"+"\n";
    s=s+"PRESS ENTER TO START GAME";


    t.applyForegroundColor(Terminal.Color.BLACK);//the color of the board will be black
    t.moveCursor(0,0);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}


  }

  public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}

  /*public static String startGame(int x, int y){
    String s="";
    int c=10;
    while (c!=0){
          s=s+"+---+---+---+---+---+---+---+---+---+---+\n";
          if (c==7){
            s=s+"|   |   |   |   |   |   |   |   |   |   |           SCORE:0\n";
          }
          else if(c==5){
            s=s+"|   |   |   |   |   |   |   |   |   |   |           (Press R to restart)\n";
          }
          else{
          s=s+"|   |   |   |   |   |   |   |   |   |   |\n";
        }
        c--;
    }
    s=s+"+---+---+---+---+---+---+---+---+---+---+\n"+"\n"+"\n";
    s=s+"PRESS ENTER TO START GAME";
    return s;


  }*/

  public static void main(String[] args) {
    int x = 0;//coordinates for the cursor
		int y = 0;

    Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

    boolean running = true;
    int mode = 0;

    drawBoard(terminal);//the board will be drawn once only

    while(running){

      terminal.moveCursor(x,y);

			terminal.applyForegroundColor(Terminal.Color.BLACK);


      Key key = terminal.readInput();

			if (key != null){
        if (mode == 0) {
          if (key.getKind() == Key.Kind.Space) {
  					mode = 1;
  				}
        }

        if (mode == 1){
          if (key.getKind() == Key.Kind.Space) {
  					mode = 1;
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
