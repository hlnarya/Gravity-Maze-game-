import enigma.core.Enigma;
import enigma.console.Console;
import enigma.console.TextAttributes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;


public class UserInterface{

  public Console console;
  private final TextAttributes defaultColor;
  private final TextAttributes wallColor;
  private final TextAttributes dirtColor;
  private final TextAttributes playerColor;
  private final TextAttributes boulderColor;
  private final TextAttributes robotColor;

  //Constructor of UserInterface class.
  public UserInterface(String name, int width, int height, int fontSize){

    this.console = Enigma.getConsole(name, width, height, fontSize, 0);
    this.defaultColor = new TextAttributes(new Color(230,230,230));
    this.wallColor = new TextAttributes(new Color(255,165,0));
    this.dirtColor = new TextAttributes(new Color(9,199,69));
    this.playerColor = new TextAttributes(new Color(242,199,24));
    this.boulderColor = new TextAttributes(new Color(230,230,230), new Color(50,50,50));
    this.robotColor = new TextAttributes(new Color(230,230,230), new Color(114,37,37));
  }

  //Writes output in red color.
  public void printWall(){
	  
    console.setTextAttributes(wallColor);
    System.out.print("#");
    console.setTextAttributes(defaultColor);
  }

  //Writes output in green color.
  public void printDirt(){
	  
    console.setTextAttributes(dirtColor);
    System.out.print(":");
    console.setTextAttributes(defaultColor);
    
  }
  //Writes output in mustard color.
  public void printPlayer(){

	    console.setTextAttributes(playerColor);
	    System.out.print("P");
	    console.setTextAttributes(defaultColor);
	    

	  }
  //Writes output in white color.
  public void print(char board){
	  
    System.out.print(board);
    

  }

  //Writes output with red background.
  public void printRobot(){

    console.setTextAttributes(robotColor);
    System.out.print("X");
    console.setTextAttributes(defaultColor);
  }

  //Writes output in gray color.
  public void printBoulder(){

    console.setTextAttributes(boulderColor);
    System.out.print("O");
    console.setTextAttributes(defaultColor);
  }

  //Clears console screen and puts cursor at the position of (0,0)
  public void clear(){

    for(int i = 0; i < console.getTextWindow().getRows(); i++)
      System.out.print("\n");

    console.getTextWindow().setCursorPosition(0, 0);
    
  }
 
  public void begine(){
	  
	  
	  char[][] boabeg= {{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			  			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			  			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			  			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','/','\\',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			  			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','/','\\',' ',' ','/','/','\\','\\',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			  			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','/','\\',' ',' ',' ',' ','/','/','\\','\\','/','/','/','\\','\\','\\',' ',' ',' ',' ',' ',' ',' ',' ','/','\\',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
					    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','/','/','\\','\\',' ',' ','/','/','/','\\','/','/','/','/','\\','\\','\\','\\',' ',' ','/','\\',' ',' ','/','/','\\','\\',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			  			{' ',' ',' ',' ',' ',' ',' ',' ',' ','/','\\',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','/',' ',' ','O',' ','\\','/','O',' ','O','/','O',' ',' ','O',' ',' ','O',' ','\\','/','O',' ','\\','/',' ',' ','O',' ','\\',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
						{' ',' ',' ',' ',' ',' ',' ',' ','/',' ','O','\\',' ',' ',' ',' ','/','\\',' ',' ','/',' ','O',' ',' ',' ','/',' ',' ','O','/',' ','O',' ','O',' ','O',' ',' ',' ','O','\\',' ','O','/',' ',' ','O','O',' ',' ','\\',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
						{' ',' ',' ',' ',' ',' ',' ','/','O',' ',' ',' ','\\',' ',' ','/',' ','O','\\','/',' ','O',' ','O',' ',' ',' ','O',' ','/',' ','O',' ',' ','O',' ',' ',' ',' ','O',' ',' ','\\','/',' ','O',' ',' ',' ','O',' ',' ','\\',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
						{' ',' ',' ',' ',' ',' ','/',' ',' ','O',' ','O',' ','\\','/','O',' ',' ','O','\\',' ','O',' ','O',' ','O',' ',' ',' ','O',' ',' ','O',' ',' ',' ','O',' ',' ',' ','-','-','-','-',' ',' ','O',' ',' ',' ','O',' ',' ','\\',' ',' ',' ',' ',' ','/','|','\\',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
						{' ',' ',' ',' ',' ','/',' ','O',' ','O',' ',' ','O',' ','\\',' ','O',' ',' ','-','\\','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','|',' ',' ','|','-','-','-','-','-','O',' ','O',' ',' ','\\',' ',' ',' ','/','|','|',':','\\',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
						{' ',' ',' ',' ','/',' ','O','O',' ',' ','O',' ','O',' ','O','\\',' ',' ','/','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','\\',' ','O',' ','O',' ','\\',' ','/','|',':','|','|','|','\\',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
						{' ',' ',' ','/',' ',' ','O',' ',' ','O','O',' ','O',' ','O',' ',' ','/','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','\\',' ',' ','O',' ',' ','/','|','|','|','|','|',':','|','\\',' ',' ',' ',' ',' ',' ',' ',' ',' '},
						{' ',' ','/','O',' ','O',' ',' ','O',' ','O','O',' ',' ','O',' ',' ',' ',' ','|','|','-','-','-','|','-','-','-','|','|','|','|','|','|','|','|','|','|','|','|','-','-','-','|','-','-','|','|','|',' ',' ',' ',' ',' ',' ','/','|','|',':','|','|','|','|','|','|','\\',' ',' ',' ',' ',' ',' ',' ','|'},
						{' ','/',' ','O',' ',' ',' ','O',' ',' ',' ','O',' ',' ',' ',' ','O',' ',' ','|','|','-','-','-','|','-','-','-','|','|','|','|','|','|','|','|','|','|','|','|','-','-','-','|','-','-','|','|','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
						{'/',' ','O',' ','O',' ','O',' ',' ','O',' ',' ','O',' ',' ','O',' ',' ',' ','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|',':',':',':',':',':',':',':',':',':',':','|',' ','|',':',':',':',':',':',':',':',' ',' ','|',' ',' '},
						{':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',':',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};
	  long lastUpdateTime = System.currentTimeMillis();
      long lastInsertTime = System.currentTimeMillis();
      long currentTime = System.currentTimeMillis();
      do{
          currentTime = System.currentTimeMillis();

          // Update game state every 100ms (10 frames per second)
          if (currentTime - lastUpdateTime >= 200) {
        	  for(int i=0;i<18;i++) {
        		  for(int j = 0; j < boabeg[0].length;j++) {
        			  
        					console.setTextAttributes(getRandomText());
      					    System.out.print(boabeg[i][j]);
      					    console.setTextAttributes(defaultColor);
      					    
        		  }
        		  System.out.print("\n");
        	  }
        	  
        	  
        	  
          }
          if (currentTime - lastUpdateTime >= 1000) {
        	  for(int i=17;i>=0;i--) {
        		  for(int j = 0; j < boabeg[0].length;j++) {
        			  if(boabeg[i][j]=='O' && i<17) {
        				  boabeg[i+1][j]='O';
        				  boabeg[i][j]='*';
        			  }
        	  
        		  }
        	  }
        	  try {
        			Thread.sleep(100);
        		} catch (InterruptedException e) {
        			// 
        			e.printStackTrace();
        		}
          }
          
          try {
  			Thread.sleep(50);
  		} catch (InterruptedException e) {
  			// 
  			e.printStackTrace();
  		}
  	    clear();
          }while (currentTime - lastInsertTime < 5000);
      
      
      String[] gravity = {
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		    "  /$$$$$$  /$$$$$$$   /$$$$$$  /$$    /$$ /$$$$$$ /$$$$$$$$ /$$     /$$",
    		    " /$$__  $$| $$__  $$ /$$__  $$| $$   | $$|_  $$_/|__  $$__/|  $$   /$$/",
    		    "| $$  \\__/| $$  \\ $$| $$  \\ $$| $$   | $$  | $$     | $$    \\  $$ /$$/", 
    		    "| $$ /$$$$| $$$$$$$/| $$$$$$$$|  $$ / $$/  | $$     | $$     \\  $$$$/",  
    		    "| $$|_  $$| $$__  $$| $$__  $$ \\  $$ $$/   | $$     | $$      \\  $$/",   
    		    "| $$  \\ $$| $$  \\ $$| $$  | $$  \\  $$$/    | $$     | $$       | $$",    
    		    "|  $$$$$$/| $$  | $$| $$  | $$   \\  $/    /$$$$$$   | $$       | $$",    
    		    " \\______/ |__/  |__/|__/  |__/    \\_/    |______/   |__/       |__/"    ,
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       ",
    		  	"                                                                       "
    		  	
    		};

      
      
      
      String[] gravity2 = {
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		    " $$$$$$\\  $$$$$$$\\   $$$$$$\\  $$\\    $$\\ $$$$$$\\ $$$$$$$$\\ $$\\     $$\\ ",
  		    "$$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |   $$ |\\_$$  _|\\__$$  __|\\$$\\   $$  |",
  		    "$$ /  \\__|$$ |  $$ |$$ /  $$ |$$ |   $$ |  $$ |     $$ |    \\$$\\ $$  / ", 
  		    "$$ |$$$$\\ $$$$$$$  |$$$$$$$$ |\\$$\\  $$  |  $$ |     $$ |     \\$$$$  /  ",  
  		    "$$ |\\_$$ |$$  __$$< $$  __$$ | \\$$\\$$  /   $$ |     $$ |      \\$$  /   ",   
  		    "$$ |  $$ |$$ |  $$ |$$ |  $$ |  \\$$$  /    $$ |     $$ |       $$ |    ",    
  		    "\\$$$$$$  |$$ |  $$ |$$ |  $$ |   \\$  /   $$$$$$\\    $$ |       $$ |    ",    
  		    " \\______/ \\__|  \\__|\\__|  \\__|    \\_/    \\______|   \\__|       \\__|    "    ,
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       "
  		  	
  		};

    
    
    String[] gravity4 = {
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		    "  ______   _______    ______   __     __  ______  ________  __      __ ",
  		    " /      \\ |       \\  /      \\ |  \\   |  \\|      \\|        \\|  \\    /  \\",
  		    "|  $$$$$$\\| $$$$$$$\\|  $$$$$$\\| $$   | $$ \\$$$$$$ \\$$$$$$$$ \\$$\\  /  $$", 
  		    "| $$ __\\$$| $$__| $$| $$__| $$| $$   | $$  | $$     | $$     \\$$\\/  $$ ",  
  		    "| $$|    \\| $$    $$| $$    $$ \\$$\\ /  $$  | $$     | $$      \\$$  $$  ",   
  		    "| $$ \\$$$$| $$$$$$$\\| $$$$$$$$  \\$$\\  $$   | $$     | $$       \\$$$$   ",    
  		    "| $$__| $$| $$  | $$| $$  | $$   \\$$ $$   _| $$_    | $$       | $$    ",    
  		    " \\$$    $$| $$  | $$| $$  | $$    \\$$$   |   $$ \\   | $$       | $$    "    ,
  		  	"  \\$$$$$$  \\$$   \\$$ \\$$   \\$$     \\$     \\$$$$$$    \\$$        \\$$    ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       "
  		  	
  		};

    
    console.setTextAttributes(defaultColor);
    
    String[] gravity3 = {
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		    "  ______   _______    ______   __     __  ______  ________  __      __ ",
  		    " /      \\ /       \\  /      \\ /  |   /  |/      |/        |/  \\    /  |",
  		    "/$$$$$$  |$$$$$$$  |/$$$$$$  |$$ |   $$ |$$$$$$/ $$$$$$$$/ $$  \\  /$$/ ", 
  		    "$$ | _$$/ $$ |__$$ |$$ |__$$ |$$ |   $$ |  $$ |     $$ |    $$  \\/$$/  ",  
  		    "$$ |/    |$$    $$< $$    $$ |$$  \\ /$$/   $$ |     $$ |     $$  $$/   ",   
  		    "$$ |$$$$ |$$$$$$$  |$$$$$$$$ | $$  /$$/    $$ |     $$ |      $$$$/    ",    
  		    "$$ \\__$$ |$$ |  $$ |$$ |  $$ |  $$ $$/    _$$ |_    $$ |       $$ |    ",    
  		    "$$    $$/ $$ |  $$ |$$ |  $$ |   $$$/    / $$   |   $$ |       $$ |    "    ,
  		  	" $$$$$$/  $$/   $$/ $$/   $$/     $/     $$$$$$/    $$/        $$/     ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       ",
  		  	"                                                                       "
  		  	
  		};
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity2[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity3[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);

    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity4[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity2[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity3[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);

    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity4[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity2[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity3[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);

    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity4[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity2[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);
    
    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity3[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);

    lastUpdateTime = System.currentTimeMillis();
    lastInsertTime = System.currentTimeMillis();
    currentTime = System.currentTimeMillis();
    console.setTextAttributes(getRandomText());
    do{
        currentTime = System.currentTimeMillis();
        
        // Update game state every 100ms (10 frames per second)
        if (currentTime - lastUpdateTime >= 200) {
      	  for(int i=0;i<30;i++) {
      		  
				    System.out.print(gravity4[i]);
				    
      		  System.out.print("\n");
      	  }
      	  
      	  
      	  
        }
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
	    //clear();
        }while (currentTime - lastInsertTime < 250);
    console.setTextAttributes(defaultColor);

      
  }
  private TextAttributes getRandomText() {
	    Random random = new Random();
	    int r = random.nextInt(256);
	    int g = random.nextInt(256);
	    int b = random.nextInt(256);
	    return new TextAttributes(new Color(r,g,b));
	}
public void nextLine() {
	System.out.print("\n");
}
}
