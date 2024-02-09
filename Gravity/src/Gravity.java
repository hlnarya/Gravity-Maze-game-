import java.io.IOException;

public class Gravity {
	static long startTime = System.currentTimeMillis();
	public static void main(String[] args) throws IOException {
		 Game game = new Game();
		 boolean newGame = game.playNewGame;
		 
	     while(game.playNewGame){
	    	 //game.init();
	   	     game.play();
	     }
	     System.exit(0);

	}

}
