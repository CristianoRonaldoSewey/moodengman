package game;

import static game.Main.getMainStage;

//import java.awt.*;




//import java.awt.event.KeyEvent;
//import javax.swing.ImageIcon;

//import javax.swing.Timer;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Dimension2D;

public class Model extends Canvas {
	
	private Dimension2D d;
    private final Font smallFont = Font.font("Arial",FontWeight.BOLD, 14);
    private boolean inGame = false;
    private boolean dying = false;
    private boolean gameOver = false;
    
    private final int CANVAS_WIDTH = 380;
    private final int CANVAS_HEIGHT = 400;
    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int MAX_GHOSTS = 12;
    private final int PACMAN_SPEED = 6;
    private final int FRAME_WIDTH = 24;
    private final int FRAME_HEIGHT= 24;
    private final int FRAME_COUNT = 4;
    
    private int N_GHOSTS = 6;
    private int lives, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image heart, ghost;
    private Image up,left,right,down;
    private Image background;
    private Image tree;
    
    private PauseScreen pauseScreen;
    

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;

    private short[] levelData;

    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;
    
    private int currentFrame = 0;
    private long lastUpdate = 0;
    private int currentSpeed = 3;
    private short[] screenData;
    private boolean gameLoopRunning = false;
//    private Timer timer;
    AnimationTimer gameLoop;
    private boolean isPaused = false; // Track pause state
    public void startGameLoop(Scene scene,GraphicsContext gc) {
    		
    	pauseScreen = new PauseScreen(CANVAS_WIDTH, CANVAS_HEIGHT);
		loadImages();
		initVariables();
		setFocusTraversable(true);
		initGame();
		renderGame(gc);
		Thread t=new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent e) {
					// TODO Auto-generated method stub
					handleKeyPressed(e);
				}
    			
    		});
			}
			
		}) ;
		t.start();
		
		gameLoop = new AnimationTimer() {
	        @Override
	        public void handle(long now) {
	        	
	            if (gameOver) {
	                showGameOverScreen(gc); // Show game over scene
	            }  // Render the game
	            else if(isPaused) {
	            	
	                pauseScreen.render(gc); // Render pause screen
	            }
	             else if (inGame) {
	                renderGame(gc);} // Render the game
	            else {
	                showIntroScreen(gc); // Show intro screen
	            }
	        }
	    };

	    gameLoop.start(); // Start the game loop
    }
    
    public void setGameLoopRunning(boolean status) {
    	this.gameLoopRunning = status;
    }

    
    public Image getBackground() {
    	return this.background;
    }
    
    public Image getTree() {
    	return this.tree;
    }
    
    
    
    public int getCanvasWidth() {
    	return CANVAS_WIDTH;
    }
    
    public int getCanvasHeight() {
    	return CANVAS_HEIGHT;
    }
    
    public int getCurrentFrame() {
    	return this.currentFrame;
    }
    
    public long getLastUpdate() {
    	return this.lastUpdate;
    }

    public boolean isGAMELOOPRUNNING() {
    	return gameLoopRunning;
    }
		
    
    public Image setImageByPath(String imagePath) {
        try {
            String classLoaderPath = ClassLoader.getSystemResource(imagePath).toString();
            return new Image(classLoaderPath);
        } catch (Exception e) {
            String classLoaderPath = ClassLoader.getSystemResource("heart.png").toString();
            return new Image(classLoaderPath);
        }
    }
    		
    private void loadImages() {
    	down = setImageByPath("down.gif");
    	up = setImageByPath("up.gif");
    	left = setImageByPath("left.gif");
    	right = setImageByPath("right.gif");
        ghost = setImageByPath("ghost.gif");
        heart = setImageByPath("heart.png");
        tree = setImageByPath("tree.png");
        background = setImageByPath("game-background.jpg");
        
        
    }
    private void initVariables() {

        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension2D(400, 400);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];
        

    }

    private void playGame(GraphicsContext gc) {

        if (dying) {

            death();

        } else {

            movePacman();
            drawPacman(gc);
            moveGhosts(gc);
            checkMaze();
        }
    }

    private void showIntroScreen(GraphicsContext gc) {
    	
    	String start = "Press SPACE to start";
    	
        gc.setFill(Color.BROWN);
        gc.fillText(start, (SCREEN_SIZE)/3, (SCREEN_SIZE)/2);
    }

    private void drawScore(GraphicsContext gc) {
        gc.setFont(smallFont);
        gc.setFill(new Color(0.0196, 0.7098, 0.3098, 1.0));
        String s = "Score: " + score;
        gc.fillText(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (int i = 0; i < lives; i++) {
            gc.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1);
        }
    }

    private void checkMaze() {

        int i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i]) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {

            score += 50;

            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initLevel();
        }
    }

    private void death() {

    	lives--;

        if (lives == 0) {
            inGame = false;
            gameOver = true;
        }

        continueLevel();
    }

    private void moveGhosts(GraphicsContext gc) {

        int pos;
        int count;

        for (int i = 0; i < N_GHOSTS; i++) {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else {
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }

            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            drawGhost(gc, ghost_x[i] + 1, ghost_y[i] + 1);

            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
                    && inGame) {

                dying = true;
            }
        }
    }

    private void drawGhost(GraphicsContext gc, int x, int y) {
    	gc.drawImage(ghost, x, y);
        }

    private void movePacman() {

        int pos;
        short ch;

        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];

            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                score++;
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }

            // Check for standstill
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        } 
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }

    private void drawPacman(GraphicsContext gc) {
    	
        if (req_dx == -1) {
        	gc.drawImage(left, pacman_x + 1, pacman_y + 1, BLOCK_SIZE, BLOCK_SIZE);
        } else if (req_dx == 1) {
        	gc.drawImage(right, pacman_x + 1, pacman_y + 1, BLOCK_SIZE, BLOCK_SIZE);
        } else if (req_dy == -1) {
        	gc.drawImage(up, pacman_x + 1, pacman_y + 1, BLOCK_SIZE, BLOCK_SIZE);
        } else {
        	gc.drawImage(down, pacman_x + 1, pacman_y + 1, BLOCK_SIZE, BLOCK_SIZE);
        }
    }

    private void drawMaze(GraphicsContext gc) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

//                gc.setFill(new Color(0,0.2824,0.9843,1));
//                gc.setLineWidth(5.0);
                
                if ((levelData[i] == 0)) { 
//                	gc.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                	gc.drawImage(tree, x, y, BLOCK_SIZE, BLOCK_SIZE );
                 }

                if ((screenData[i] & 1) != 0) { 
                    gc.strokeLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) { 
                    gc.strokeLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) { 
                    gc.strokeLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) { 
                    gc.strokeLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) { 
                    gc.setFill(new Color(1.0,1.0,1.0,1));
                    gc.fillOval(x + 10, y + 10, 6, 6);
               }

                i++;
            }
        }
    }

    private void initGame() {

    	lives = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 3;
        currentSpeed = 3;
    }

    private void initLevel() {

        int i;
        for (i = 0; i < levelData.length; i++) {
            screenData[i] = levelData[i];
        }

        continueLevel();
    }

    private void continueLevel() {

    	int dx = 1;
        int random;

        for (int i = 0; i < N_GHOSTS; i++) {

            ghost_y[i] = 4 * BLOCK_SIZE; //start position
            ghost_x[i] = 4 * BLOCK_SIZE;
            ghost_dy[i] = 0;
            ghost_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }

            ghostSpeed[i] = validSpeeds[random];
        }

        pacman_x = 7 * BLOCK_SIZE;  //start position
        pacman_y = 11 * BLOCK_SIZE;
        pacmand_x = 0;	//reset direction move
        pacmand_y = 0;
        req_dx = 0;		// reset direction controls
        req_dy = 0;
        dying = false;
    }
    
    public Dimension2D getD() {
    	return d;
    }

 
    public void renderGame(GraphicsContext gc) {
        

        

//        gc.setFill(Color.BLACK);
//        gc.fillRect(0, 0, getD().getWidth(), getD().getHeight());
    	gc.drawImage(background, 0, 0, getCanvasWidth(),getCanvasHeight() );
        drawMaze(gc);
        drawScore(gc);

        if (inGame) {
            playGame(gc);
        } else {
            showIntroScreen(gc);
        }

//        Toolkit.getDefaultToolkit().sync();
//        g2d.dispose();
    }


    //controls
    private void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop(); // Stop the AnimationTimer
        }
        requestFocus();
    }
    
    private void resumeGameLoop() {
        if (gameLoop != null) {
            gameLoop.start(); // Resume the AnimationTimer
        }
        requestFocus();
    }

    public void handleKeyPressed(KeyEvent event) {
    	KeyCode key = event.getCode();

        // Handle game over state
        if (gameOver) {
            if (key == KeyCode.R) {
                gameOver = false;
                inGame = true;
                initGame(); // Restart the game
            } else if (key == KeyCode.Q) {
            	PageChanger.changeToMapSelection(getMainStage());
            }
            return; // Exit after handling game over state
        }

        // Handle pause and resume
        else if (key == KeyCode.E && inGame) {
            if (isPaused) {
                resumeGameLoop(); // Resume the game
                isPaused = false;
            } else {
                
                isPaused = true;
            }
            return; // Exit after handling pause/resume
        }

        if (isPaused) {
            if (key == KeyCode.E) {
                resumeGameLoop(); // Resume the game
                isPaused = false;
                System.out.println("Game Resumed");
            } else if (key == KeyCode.R) {
            	// Stop the current game loop
                stopGameLoop();
                // Reinitialize game state
                inGame = true;
                isPaused = false;
                gameOver = false;
                initGame(); // Reset variables and level
                // Restart the game loop
                gameLoop.start();
                System.out.println("Game Restarted");
            } else if (key == KeyCode.Q) {
                PageChanger.changeToMapSelection(getMainStage());
            }
            return; // Exit after handling paused state
        }

        // Handle in-game controls
        else if (inGame) {
            
            if (key == KeyCode.A) {
              req_dx = -1;
              req_dy = 0;
          } else if (key == KeyCode.D) {
              req_dx = 1;
              req_dy = 0;
          } else if (key == KeyCode.W) {
              req_dx = 0;
              req_dy = -1;
          } else if (key == KeyCode.S) {
              req_dx = 0;
              req_dy = 1;
          } else if (key == KeyCode.ESCAPE && isGAMELOOPRUNNING()) {
              inGame = false;
          } 
            }
        

        // Handle game start from intro screen
        else if (!inGame && key == KeyCode.SPACE) {
            inGame = true;
            initGame();
        }
        }
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    
    private void showGameOverScreen(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getCanvasWidth(), getCanvasHeight());

        String gameOverText = "GAME OVER";
        String scoreText = "Your Score: " + score;
        String restartText = "Press R to Restart or Q to Quit";
        

        gc.setFill(Color.RED);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        gc.fillText(gameOverText, SCREEN_SIZE / 4.5, SCREEN_SIZE / 3);

        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        gc.setFill(Color.WHITE);
        gc.fillText(scoreText, SCREEN_SIZE / 3.3, SCREEN_SIZE / 2);
        gc.fillText(restartText, SCREEN_SIZE / 16, SCREEN_SIZE / 1.5);
        
    }
    public void setLevelData(short[] levelData) {
        this.levelData = levelData;
    }
    
    
    
    
    
    
    
    
    
	}

	
   
    
		
	