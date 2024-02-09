import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enigma.console.TextAttributes;
import java.awt.Color;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
public class Game {
	private UserInterface ui;
	public enigma.console.Console cn;
    private static final int ROWS = 25;
    private static final int COLS = 55;
    private static final int GENPRBl = 40; 
    private static final String SOFTWARE_NAME = "Gravity V1.0";
    private static final int WINDOW_WIDTH = 85;
    private static final int WINDOW_HEIGHT = 30;
    private static final int FONT_SIZE = 19;
    private static final int NUM_BOULDERS = 180;
    private static final int NUM_TREASURES = 30;
    private static final int NUM_ROBOTS = 7;
    private static final int NUM_EMPTY = 200;
    public static Robot[]robots=new Robot[1000];
    private static int syc=0;
    private boolean gameOver = false;
    boolean playNewGame = true;
    private char[][] board;
    private Player player;
    private InputQueue inputQueue = new InputQueue(15);
    public KeyListener klis;
    public int keypr;   // key pressed
    public int rkey;    // key (for press/release)
    long startedTime = Gravity.startTime;
    public void init() {
    	this.ui = new UserInterface(SOFTWARE_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, FONT_SIZE);
        this.cn = ui.console;
        ui.begine();
        this.startedTime=System.currentTimeMillis();
        board = new char[ROWS][COLS+20];
        inputQueue = new InputQueue(15);
        robots = new Robot[1000];
        generateWalls();
        generateEarth();
        generateBoulders();
        generateTreasures();
        generateEmpty();
        generateRobots();
        placePlayer();
        this.ui = new UserInterface(SOFTWARE_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, FONT_SIZE);
        this.cn = ui.console;
    }

    private void generateWalls() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (i == 0 || i == ROWS - 1 || j == 0 || j == COLS - 1||(i==8&&j<COLS-5)||(i==16&&j>5) ) {
                    board[i][j] = '#';
                }
            }
        }
    }

    private void generateEarth() {
        for (int i = 1; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if (board[i][j] != '#') {
                    board[i][j] = ':';
                }
            }
        }
    }

    private void generateBoulders() {
        Random random = new Random();
        int bouldersPlaced = 0;
        while (bouldersPlaced < NUM_BOULDERS) {
            int row = random.nextInt(ROWS - 2) + 1;
            int col = random.nextInt(COLS - 2) + 1;

            if (board[row][col] == ':') {
                board[row][col] = 'O';
                bouldersPlaced++;
            }
        }
    }

    private void generateTreasures() {
        Random random = new Random();
        int treasuresPlaced = 0;
        while (treasuresPlaced < NUM_TREASURES) {
            int row = random.nextInt(ROWS - 2) + 1;
            int col = random.nextInt(COLS - 2) + 1;

            if (board[row][col] == ':') {
                int treasure = random.nextInt(3) + 1;
                board[row][col] =  (char) ('0' + treasure); 
                treasuresPlaced++;
            }
        }
    }

    private void generateEmpty() {
        Random random = new Random();
        int emptyPlaced = 0;
        while (emptyPlaced < NUM_EMPTY) {
            int row = random.nextInt(ROWS - 2) + 1;
            int col = random.nextInt(COLS - 2) + 1;

            if (board[row][col] == ':') {
                board[row][col] = ' ';
                emptyPlaced++;
            }
        }
    }

    private void generateRobots() {
        Random random = new Random();
        int robotsPlaced = 0;
        while (robotsPlaced < NUM_ROBOTS) {
            int row = random.nextInt(ROWS - 2) + 1;
            int col = random.nextInt(COLS - 2) + 1;

            if (board[row][col] == ' ') {
                board[row][col] = 'X';
                Robot robot=new Robot(row,col);
                for (int k = 0; k < robots.length; k++) {
					if(robots[k]==null) {
						robots[k]=robot;
						syc+=1;
						break;
					}
					else
						continue;
                }
                robotsPlaced++;
            }
        }
    }

    private void placePlayer() {
        Random random = new Random();
        while (true) {
            int row = random.nextInt(ROWS - 2) + 1;
            int col = random.nextInt(COLS - 2) + 1;

            if ((board[row][col] == ':') && (board[row + 1][col] == ':') && (board[row -1][col] == ':') && (board[row][col +1] == ':') && (board[row][col - 1] == ':')) {
                board[row][col] = 'P';
                player = new Player(row, col);
                break;
            }
        }
    }

    public void play() throws IOException {
    	
        initializeGame();
        
        long lastUpdateTime = System.currentTimeMillis();
        long lastInsertTime = System.currentTimeMillis();

        while (!gameOver) {
            long currentTime = System.currentTimeMillis();

            // Update game state every 100ms (10 frames per second)
            if (currentTime - lastUpdateTime >= 100) {
            	moveRobots(robots);
                handleFallingBoulders();
                checkGameOver();
                lastUpdateTime = currentTime;
            }

            // Insert new element from the input queue every 3000ms
            if (currentTime - lastInsertTime >= 3000) {
                insertElementFromInputQueue();
                lastInsertTime = currentTime;
            }

            // Read user input and update player's position accordingly
            klis=new KeyListener() {
                public void keyTyped(KeyEvent e) {}
                public void keyPressed(KeyEvent e) {
                   if(keypr==0) {
                      keypr=1;
                      rkey=e.getKeyCode();
                   }
                }
                public void keyReleased(KeyEvent e) {}
             };
             cn.getTextWindow().addKeyListener(klis);
             handlePlayerInput(keypr,rkey);
             keypr=0;
            // Redraw the game screen
             if(gameOver) {
            	 drawGameScreen();
            	 ui.clear();
            	 ui.console.getTextWindow().setCursorPosition(35, 10);
            	 System.out.println("Game Over!");
            	 ui.console.getTextWindow().setCursorPosition(20, 11);
            	 System.out.print("(Do you want to play new game(Y or N):)");
            	 boolean rightAnswer = false;
            	 String answer = "";
            	 while(!rightAnswer) {
            		 Scanner scan = new Scanner(System.in);
                	 answer = scan.next().toLowerCase();
                	 if(answer.equals("y") || answer.equals("n")) {
                		 rightAnswer = true;
                	 }else {
                    	 ui.clear();
                    	 ui.console.getTextWindow().setCursorPosition(35, 10);
                    	 System.out.println("Game Over!");
                    	 ui.console.getTextWindow().setCursorPosition(20, 11);
                    	 System.out.print("(Do you want to play new game(\"Y\" or \"N\"):)");
                	 }
            	 }
            	 
            	 if(answer.equals("n")) {
            		 playNewGame = false;
            	 }else if(answer.equals("y")) {
            		 playNewGame = true;
            		 gameOver = false;
            		 init();
            		 startedTime = System.currentTimeMillis();
            	 }
             }
             else {
            	 drawGameScreen();
             }
               
        }
    }


	private void drawGameScreen() throws IOException {
		 // Clear the console screen
		ui.clear();
	    int iM=0 , jM=COLS + 2;
	    
	   //*******************************************************
	    //Draw InputQueue content here,
		iM++;
		String inputQueueStr = "InputQueue";
		for (int i=0;i< inputQueueStr.length();i++) {
			board[iM][jM+i]=inputQueueStr.charAt(i);
		}
		iM++;
	    
		String less = "<<<<<<<<<<<<<<";
		for (int i=0;i< less.length();i++) {
			board[iM][jM+i]=less.charAt(i);
		}
		iM++;
		for(int i = 0;i<inputQueue.size();i++) {
			char peek = inputQueue.peek();
			if(peek == ' ')
				board[iM][jM+i]='e';
			else
				board[iM][jM+i]=peek;
			inputQueue.enqueue(inputQueue.dequeue());
		}
		iM++;
		for (int i=0;i< less.length();i++) {
			board[iM][jM+i]=less.charAt(i);
		}
		iM++;
	    
	    
		//******************************************************* 
	    // Draw backpack content
	    
	   
	    jM=jM+6;
	    iM=iM+8;
	    for(int i = 0; i<8 ;i++) {
	    	board[iM][jM-2]='|';
	    	board[iM][jM]=(char)( player.getBackpack().getItems()[i]+'0');
	    	board[iM][jM+2]='|';
	    	iM--;
	    }
	    iM=iM+9;
	    board[iM][jM-2]='+';
	    board[iM][jM-1]='-';
	    board[iM][jM]='-';
	    board[iM][jM+1]='-';
	    jM=jM+2;
	    board[iM][jM]='+';
	    
	    
	    iM++;
	    jM=jM-5;
		String backpackHeader = "Backpack";
		for (int i=0;i< backpackHeader.length();i++) {
			board[iM][jM+i]=backpackHeader.charAt(i);
		}
		iM++; // Move to the next line
		jM=jM-4;
		iM++;
	    // Draw remaining teleport count
		String teleportHeader = " Teleport:";
		for (int i = 0 ; i< teleportHeader.length();i++) {
			board[iM][jM+i]=teleportHeader.charAt(i);
		}
		jM+=10;
		String strTeleportRights =String.valueOf(player.getTeleportRights());
		for (int i = 0 ; i< strTeleportRights.length();i++) {
			board[iM][jM+i]=strTeleportRights.charAt(i);

		}
		jM-=10;
		iM++;
	    // Draw player score
		String scoreHeader = " Score   :";
		for (int i = 0 ; i< scoreHeader.length();i++) {
			board[iM][jM+i]=scoreHeader.charAt(i);
		}
		
		jM+=10;
		String strScore =String.valueOf(player.getScore());
		for (int i = 0 ; i< strScore.length();i++) {
			board[iM][jM+i]=strScore.charAt(i);
		}
		
		jM-=10;
		iM++;
	    // Draw elapsed time (in seconds)
	    long currentTime = System.currentTimeMillis();
	    long elapsedTime = (currentTime - startedTime) / 1000; //???????????
		String timeHeader = " time    :";
		for (int i = 0 ; i< timeHeader.length();i++) {
			board[iM][jM+i]=timeHeader.charAt(i);
		}
		String eT = Long.toString(elapsedTime);
		jM+=10;
		for (int i = 0 ; i< eT.length();i++) {
			board[iM][jM+i]=eT.charAt(i);
		}
		jM-=10;
		
		for(int i = 0 ; i<board.length;i++)
		{
			for(int j = 0 ; j<board[1].length;j++)
			{
				if(board[i][j]=='\0') { //????????
					board[i][j]=' ';
				}
					
			}
		}
		
	    // Draw the maze
		for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[i].length; j++) {
	            switch (board[i][j]) {
	                case 'X': 
	                    ui.printRobot();
	                    break;
	                case 'P': 
	                    ui.printPlayer();
	                    break;
	                case ':': 
	                    ui.printDirt();
	                    break;
	                case '#': 
	                    ui.printWall();
	                    break;
	                case 'O': 
	                	ui.printBoulder();
	                    break;
	                default:
	                    ui.print(board[i][j]);
	            }

	            
	        }
	        ui.nextLine();
	    }	
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
		if(gameOver) {
			 ui.console.getTextWindow().setCursorPosition(35, 27);
			 System.out.print("GAME OVER :(");
     		try {
     			
    			Thread.sleep(5000);
    		} catch (InterruptedException e) {
    			// 
    			e.printStackTrace();
    		}
			
		}
	    
		
	}	
	/*
	private TextColor getRandomColor() {
	    Random random = new Random();
	    int r = random.nextInt(256);
	    int g = random.nextInt(256);
	    int b = random.nextInt(256);
	    return new TextColor.RGB(r, g, b);
	}
	*/	
	private void handlePlayerInput(int keypr,int rkey) {
		
		if(keypr==1) {
			int newRow = player.getRow();
		    int newCol = player.getCol();
		    boolean oMove = false;
		    switch (rkey) {
		        case KeyEvent.VK_UP:
		            newRow--;
		            break;
		        case KeyEvent.VK_LEFT:
		            newCol--;
		            oMove=true;
		            break;
		        case KeyEvent.VK_DOWN:
		            newRow++;
		            break;
		        case KeyEvent.VK_RIGHT:
		            newCol++;
		            oMove=true;
		            break;
		        case KeyEvent.VK_SPACE:
		    		if(player.getTeleportRights() > 0) {
		    			player.setTeleportRights(player.getTeleportRights()-1);
		    			int countOfEarthSquare = 0;
		    			for(int i = 1 ; i < ROWS - 1; i++) {
		    				for(int j = 1; j < COLS - 1; j++) {
		    					if(board[i][j] == ':')
		    						countOfEarthSquare++; 
		    				}
		    			}
		    			Random random = new Random();
		    			int randomSquarePoint = random.nextInt(countOfEarthSquare);
		    			

		    			for(int i = 1 ; i < ROWS - 1; i++) {
		    				for(int j = 1; j < COLS - 1; j++) {
		    					if(board[i][j] == ':') {
		    						randomSquarePoint--;
		    					}
		    					if(randomSquarePoint == 0&&board[i][j] == ':') {
		    						board[newRow][newCol] = ' ';
		    						newRow = i;
		    						newCol = j;
		    						player.setRow(newRow);
		    						player.setCol(newCol);
		    						board[newRow][newCol] = 'P';
		    						break;
		    					}
		    				}
		    				if(countOfEarthSquare == 0) {
		    					break;
		    				}
		    			}
		    		}
		        	break;
		        default:
		            return;
		    }
		    // Update player's position
		    if (board[newRow][newCol] != '#' && board[newRow][newCol] != 'X' ) {
		    	if(board[newRow][newCol] == 'O'&&oMove ) {
		    		if(board[newRow][newCol-1] == ' ' && board[newRow][newCol+1] == 'P') {
		    		   board[player.getRow()][player.getCol()] = ' ';
		    		   board[newRow][newCol] = 'P';
		    		   board[newRow][newCol-1] = 'O';   
		    		   player.setRow(newRow);
				       player.setCol(newCol);
		    		}
		    		if(board[newRow][newCol+1] == ' '&& board[newRow][newCol-1] == 'P') {
			    		   board[newRow][newCol+1] = 'O';
			    		   board[player.getRow()][player.getCol()] = ' ';
				           board[newRow][newCol] = 'P';
				           player.setRow(newRow);
					       player.setCol(newCol);
			    		 }
		    		oMove=false;
		    	}
		    	else if(board[newRow][newCol] != 'O' ) {
		    	int score =	player.getScore();
		    	switch(board[newRow][newCol]) {
		    	case '1' :
		    		player.addItemToBackpack(1);
		    		if(player.getBackpack().hasMatchingTopTwoItems()) {
		    			player.setScore(score + 10);
		    			player.getBackpack().removeTopTwoItems();
		    		}
		    		board[player.getRow()][player.getCol()] = ' ';
			        board[newRow][newCol] = 'P';
			        player.setRow(newRow);
			        player.setCol(newCol);
			        break;
		    	case '2' :
		    		player.addItemToBackpack(2);
		    		if(player.getBackpack().hasMatchingTopTwoItems()) {
		            	player.setScore(score + 40);
		    			player.getBackpack().removeTopTwoItems();
		    		}
		    		board[player.getRow()][player.getCol()] = ' ';
			        board[newRow][newCol] = 'P';
			        player.setRow(newRow);
			        player.setCol(newCol);
			        break;
			    case '3' :
			    	player.addItemToBackpack(3);
		    		if(player.getBackpack().hasMatchingTopTwoItems()) {
		            	player.setScore(score + 90);
		            	player.setTeleportRights(player.getTeleportRights()+1);
		    			player.getBackpack().removeTopTwoItems();
		    		}
			   		board[player.getRow()][player.getCol()] = ' ';
			        board[newRow][newCol] = 'P';
			        player.setRow(newRow);
			        player.setCol(newCol);
			        break;
		       default:
			    	board[player.getRow()][player.getCol()] = ' ';
			        board[newRow][newCol] = 'P';
			        player.setRow(newRow);
			        player.setCol(newCol);
			        break;
		    	}

		    }
		}
    }
		
		
		
	}
	//*******************************************************
	private void insertElementFromInputQueue() {
		
        Random random = new Random();
        char randomInputElement = 'b';
        int countOfTreasure123 = 0;
        int countOfRobot = 0;
        int countOfBoulder = 0;
        int countOfEarthSquare= 0;
        int countOfEmptySquare= 0;
        
        //Specifies the character to be added to the queue
        while(!inputQueue.isFull()) {
        	
        	 int randomElementNum = random.nextInt(GENPRBl) + 1;
             if(randomElementNum <= 6)
             	randomInputElement = '1';
             else if(randomElementNum > 6 && randomElementNum <= 11)
             	randomInputElement = '2';
             else if(randomElementNum > 11 && randomElementNum <= 15)
             	randomInputElement = '3';
             else if(randomElementNum > 15 && randomElementNum <= 16)
             	randomInputElement = 'X';
             else if(randomElementNum > 16 && randomElementNum <= 26)
             	randomInputElement = 'O';
             else if(randomElementNum > 26 && randomElementNum <= 35)
             	randomInputElement = ':';
             else if(randomElementNum > 35 && randomElementNum <= 40)
             	randomInputElement = ' ';
             
             inputQueue.enqueue(randomInputElement);
             

        }
        
        randomInputElement = inputQueue.dequeue();
        

        // Determines count of elements one by one
		for(int i = 1 ; i < ROWS - 1 ; i++) {
			for(int j = 1; j < COLS - 1; j++) {
		        if(String.valueOf(board[i][j]).equals("1") || String.valueOf(board[i][j]).equals("2") || String.valueOf(board[i][j]).equals("3")) {
		        	countOfTreasure123++;
		        }else if(String.valueOf(board[i][j]).equals("X")) {
		        	countOfRobot++;
		        }else if(String.valueOf(board[i][j]).equals("O")) {
		        	countOfBoulder++;
		        }else if(String.valueOf(board[i][j]).equals(":")) {
		        	countOfEarthSquare++;
		        }else if(String.valueOf(board[i][j]).equals(" ")) {
		        	countOfEmptySquare++;
		        }
			}
		}
		
       
        // Determines the character to be deleted based on the character to be added to the "queue".
        if(String.valueOf(randomInputElement).equals("1") || String.valueOf(randomInputElement).equals("2") || String.valueOf(randomInputElement).equals("3")) {
        	int randomRemoveElementFromBoard = random.nextInt(countOfEarthSquare + countOfEmptySquare);
        	int counter = 0;
        	if(String.valueOf(randomInputElement).equals("1")) {
        		for(int i = 1 ; i < ROWS - 1; i++) {
        			for(int j = 1; j < COLS - 1; j++) {
        				if(String.valueOf(board[i][j]).equals(" ") || String.valueOf(board[i][j]).equals(":")) {
        					counter++;
        				}
        				if(counter == randomRemoveElementFromBoard) {
        					board[i][j] = '1';
        					break;
        				}
        			}
        			if(counter == randomRemoveElementFromBoard) {
        				break;
        			}
        		}
        	}else if(String.valueOf(randomInputElement).equals("2")) {
        		for(int i = 1 ; i < ROWS - 1; i++) {
        			for(int j = 1; j < COLS - 1; j++) {
        				if(String.valueOf(board[i][j]).equals(" ") || String.valueOf(board[i][j]).equals(":")) {
        					counter++;
        				}
        				if(counter == randomRemoveElementFromBoard) {
        					board[i][j] = '2';
        					break;
        				}
        			}
        			if(counter == randomRemoveElementFromBoard) {
        				break;
        			}
        		}
        	}else if(String.valueOf(randomInputElement).equals("3")) {
        		for(int i = 1 ; i < ROWS - 1; i++) {
        			for(int j = 1; j < COLS - 1; j++) {
        				if(String.valueOf(board[i][j]).equals(" ") || String.valueOf(board[i][j]).equals(":")) {
        					counter++;
        				}
        				if(counter == randomRemoveElementFromBoard) {
        					board[i][j] = '3';
        					break;
        				}
        			}
        			if(counter == randomRemoveElementFromBoard) {
        				break;
        			}
        		}
        	}

        	
        }else if(String.valueOf(randomInputElement).equals("X")) {
          	int randomRemoveElementFromBoard = random.nextInt(countOfEarthSquare + countOfEmptySquare);
          	int counter = 0;
    		for(int i = 1 ; i < ROWS - 1; i++) {
    			for(int j = 1; j < COLS - 1; j++) {
    				if(String.valueOf(board[i][j]).equals(" ") || String.valueOf(board[i][j]).equals(":")) {
    					counter++;
    				}
    				if(counter == randomRemoveElementFromBoard) {
    					board[i][j] = 'X';
    					Robot robot=new Robot(i,j);
    					for (int k = 0; k < robots.length; k++) {
    						if(robots[k]==null) {
    							robots[k]=robot;
    							syc+=1;
    							break;
    						}
    						else
    							continue;
    	                }
    					break;
    				}
    			}
    			if(counter == randomRemoveElementFromBoard) {
    				break;
    			}
    		}
        	
          	
          	
        }else if(String.valueOf(randomInputElement).equals("O")) {
          	int randomRemoveElementFromBoard = random.nextInt(countOfEarthSquare + countOfEmptySquare);
          	int counter = 0;
    		for(int i = 1 ; i < ROWS - 1; i++) {
    			for(int j = 1; j < COLS - 1; j++) {
    				if(String.valueOf(board[i][j]).equals(" ") || String.valueOf(board[i][j]).equals(":")) {
    					counter++;
    				}
    				if(counter == randomRemoveElementFromBoard) {
    					board[i][j] = 'O';
    					break;
    				}
    			}
    			if(counter == randomRemoveElementFromBoard) {
    				break;
    			}
    		}
    		
    		
    		//Keeps the number of "boulder" constant
          	int randomRemoveElementFromBoard1 = random.nextInt(countOfBoulder);
          	int counter1 = 0;
    		for(int i = 1 ; i < ROWS - 1; i++) {
    			for(int j = 1; j < COLS - 1; j++) {
    				if(String.valueOf(board[i][j]).equals("O")) {
    					counter1++;
    				}
    				if(counter1 == randomRemoveElementFromBoard1) {
    					board[i][j] = ':';
    					break;
    				}
    			}
    			if(counter1 == randomRemoveElementFromBoard1) {
    				break;
    			}
    		}
    		
    		
        }else if(String.valueOf(randomInputElement).equals(":")) {
         	int randomRemoveElementFromBoard = random.nextInt(countOfEmptySquare);
          	int counter = 0;
    		for(int i = 1 ; i < ROWS - 1; i++) {
    			for(int j = 1; j < COLS - 1; j++) {
    				if(String.valueOf(board[i][j]).equals(" ")) {
    					counter++;
    				}
    				if(counter == randomRemoveElementFromBoard) {
    					board[i][j] = ':';
    					break;
    				}
    			}
    			if(counter == randomRemoveElementFromBoard) {
    				break;
    			}
    		}
    		
        }else if(String.valueOf(randomInputElement).equals(" ")) {
         	int randomRemoveElementFromBoard = random.nextInt(countOfEarthSquare);
          	int counter = 0;
    		for(int i = 1 ; i < ROWS - 1; i++) {
    			for(int j = 1; j < COLS - 1; j++) {
    				if(String.valueOf(board[i][j]).equals(":")) {
    					counter++;
    				}
    				if(counter == randomRemoveElementFromBoard) {
    					board[i][j] = ' ';
    					break;
    				}
    			}
    			if(counter == randomRemoveElementFromBoard) {
    				break;
    			}
    		}
        }
        
		
	}
	//*******************************************************
	private void checkGameOver() {
		if (!player.isDead()) {
	        gameOver = true;
	    }
	}
	
	//*******************************************************
	//Handle Falling Boulders and game over

	Random random = new Random();
	int randint_boulders = random.nextInt(2) +1;
	private void handleFallingBoulders() {
		for (int i = ROWS - 2; i > 0; i--) {
	        for (int j = 1; j < COLS - 1; j++) {
	        	int count=0;
	            if (board[i][j] == 'O' && board[i + 1][j] == ' ') {
	            	 if(i < 22 && board[i + 3][j] =='P'){
	            		 player.setLive(false);
	            	 }
	            	 else if(i < 22 && board[i + 2][j] =='X'){
	            		 for (int k = 0; k < robots.length; k++) {
	            				if(syc==count) {
	            					break;
	            				}
	            				else {
	            					if(robots[k]==null) {
	            						continue;
	            					}
	            					else if(robots[k].getRow()==i+2 && robots[k].getCol()==j) {
	            						robots[k]=null;
	            						board[i+2][j]=' ';
	            						player.setScore(player.getScore()+900);
	            						syc--;
	            					}
	            					else {
	            						count++;
	            						continue;
	            					}
	            				}
	            		 }
	            	 }
	            	board[i + 1][j] = 'O';
	                board[i][j] = ' ';
	            }
	             if(board[i][j] == 'O' && board[i+1][j] == 'O' && board[i+1][j+1] == ' ' &&  board[i+1][j-1] != ' ' && board[i][j+1] == ' ') {
	            	 if(i < 23 && board[i+2][j+1] == 'P'){
	            		 player.setLive(false);
	            	 }
	            	 else if(i < 23 && board[i + 1][j+1] =='X'){
	            		 for (int k = 0; k < robots.length; k++) {
	            				if(syc==count) {
	            					break;
	            				}
	            				else {
	            					if(robots[k]==null) {
	            						continue;
	            					}
	            					else if(robots[k].getRow()==i+1 && robots[k].getCol()==j+1) {
	            						robots[k]=null;
	            						board[i+1][j+1]=' ';
	            						player.setScore(player.getScore()+900);
	            						syc--;
	            					}
	            					else {
	            						count++;
	            						continue;
	            					}
	            				}
	            		 }
	            	 }
	            	 board[i+1][j+1] = 'O';
	            	 board[i][j] = ' ';
	             }
	            if(board[i][j] == 'O' && board[i+1][j] == 'O' && board[i+1][j-1] == ' ' && board[i+1][j+1] != ' '  && board[i][j-1] == ' ') {
	            	 if(i < 23 && board[i+2][j-1] == 'P'){
	            		 player.setLive(false);
	            	 }
	            	 else if(i < 23 && board[i + 1][j-1] =='X'){
	            		 for (int k = 0; k < robots.length; k++) {
	            				if(syc==count) {
	            					break;
	            				}
	            				else {
	            					if(robots[k]==null) {
	            						continue;
	            					}
	            					else if(robots[k].getRow()==i+1 && robots[k].getCol()==j-1) {
	            						robots[k]=null;
	            						board[i+1][j-1]=' ';
	            						player.setScore(player.getScore()+900);
	            						syc--;
	            					}
	            					else {
	            						count++;
	            						continue;
	            					}
	            				}
	            		 }
	            	 }
	            	 board[i+1][j-1] = 'O';
		           	 board[i][j] = ' ';
	            	 
	             }
	            if(board[i][j] == 'O' && board[i+1][j] == 'O' && board[i+1][j-1] == ' '  && board[i+1][j+1] == ' ') {
	            	if(randint_boulders ==1   && board[i][j-1] == ' ') {
		            	 if(i < 23 && board[i+2][j-1] == 'P'){
		            		 player.setLive(false);
		            	 }
		            	 else if(i < 23 && board[i + 1][j-1] =='X'){
		            		 for (int k = 0; k < robots.length; k++) {
		            				if(syc==count) {
		            					break;
		            				}
		            				else {
		            					if(robots[k]==null) {
		            						continue;
		            					}
		            					else if(robots[k].getRow()==i+1 && robots[k].getCol()==j-1) {
		            						robots[k]=null;
		            						board[i+1][j-1]=' ';
		            						player.setScore(player.getScore()+900);
		            						syc--;
		            					}
		            					else {
		            						count++;
		            						continue;
		            					}
		            				}
		            		 }
		            	 }
	            		board[i+1][j-1] = 'O';
		           	 	board[i][j] = ' ';
	            	}
	            	if(randint_boulders ==2   && board[i][j+1] == ' ') {
		            	 if(i < 23 && board[i+2][j+1] == 'P'){
		            		 player.setLive(false);
		            	 } 
		            	 else if(i < 22 && board[i + 1][j+1] =='X'){
		            		 for (int k = 0; k < robots.length; k++) {
		            				if(syc==count) {
		            					break;
		            				}
		            				else {
		            					if(robots[k]==null) {
		            						continue;
		            					}
		            					else if(robots[k].getRow()==i+1 && robots[k].getCol()==j+1) {
		            						robots[k]=null;
		            						board[i+1][j+1]=' ';
		            						player.setScore(player.getScore()+900);
		            						syc--;
		            					}
		            					else {
		            						count++;
		            						continue;
		            					}
		            				}
		            		 }
		            	 }
	            		board[i+1][j+1] = 'O';
			       	 	board[i][j] = ' ';
	            	}
	            }
	        }
		}
	}
		

	
	private void moveRobots(Robot[]a) {
		Random rand=new Random();
		
		int count=0;
		for (int i = 0; i < a.length; i++) {
			int direction=rand.nextInt(4);
			if(syc==count) {
				break;
			}
			else {
				if(a[i]==null) {
					continue;
				}
				else if(direction==0 && ((board[a[i].getRow()+1][a[i].getCol()]==' ') || (board[a[i].getRow()+1][a[i].getCol()]=='P'))){
					board[a[i].getRow()][a[i].getCol()]=' ';
					if(board[a[i].getRow()+1][a[i].getCol()]=='P') {
						player.setLive(false);
					}
					board[a[i].getRow()+1][a[i].getCol()]='X';
					a[i].setRow(a[i].getRow()+1);
					count++;
				}
				else if(direction==1 && ((board[a[i].getRow()-1][a[i].getCol()]==' ') || (board[a[i].getRow()-1][a[i].getCol()]=='P'))){
					board[a[i].getRow()][a[i].getCol()]=' ';
					if(board[a[i].getRow()-1][a[i].getCol()]=='P') {
						player.setLive(false);
					}
					board[a[i].getRow()-1][a[i].getCol()]='X';
					a[i].setRow(a[i].getRow()-1);
					count++;
				}
				else if(direction==2 && ((board[a[i].getRow()][a[i].getCol()-1]==' ') || (board[a[i].getRow()][a[i].getCol()-1]=='P'))){
					board[a[i].getRow()][a[i].getCol()]=' ';
					if(board[a[i].getRow()][a[i].getCol()-1]=='P') {
						player.setLive(false);
					}
					board[a[i].getRow()][a[i].getCol()-1]='X';
					a[i].setCol(a[i].getCol()-1);
					count++;
				}
				else if(direction==3 && (board[a[i].getRow()][a[i].getCol()+1]==' ' || board[a[i].getRow()][a[i].getCol()+1]=='P')){
					board[a[i].getRow()][a[i].getCol()]=' ';
					if(board[a[i].getRow()][a[i].getCol()+1]=='P') {
						player.setLive(false);
					}
					board[a[i].getRow()][a[i].getCol()+1]='X';
					a[i].setCol(a[i].getCol()+1);
					count++;
				}
				else {
					count++;
					continue;
				}
					
				
			}	
		}

	}

	private void initializeGame() {
		
		init();
		
	    try {
			drawGameScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}