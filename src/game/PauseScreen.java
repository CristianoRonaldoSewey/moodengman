package game;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sharedObject.RenderableHolder;

public class PauseScreen {

    private final int canvasWidth;
    private final int canvasHeight;
    private final Font titleFont = Font.font("Book Antiqua", FontWeight.BOLD, 36);
    private final Font textFont = Font.font("Book Antiqua", FontWeight.NORMAL, 20);

    private final String pauseMessage = "PAUSE";
    private final String resumeMessage = "Press E to Resume";
    private final String restartMessage = "Press R to Restart";
    private final String quitMessage = "Press Q to Quit";

    public PauseScreen(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public void render(GraphicsContext gc) {
    	
        // Draw the pause screen background
        Image background  = RenderableHolder.pauseMenu;
        if (background != null) {
            
            gc.drawImage(background, 0, 0, canvasWidth, canvasHeight);
        } else {
            
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, canvasWidth, canvasHeight); // Default black background
        }
//        gc.drawImage(background, 0, 0, canvasWidth, canvasHeight);
        

        // Draw pause messages
        gc.setFill(Color.YELLOW);

        // Title
        gc.setFont(titleFont);
        gc.fillText(pauseMessage, canvasWidth / 2.75, canvasHeight / 3);

        // Instructions
        gc.setFont(textFont);
        gc.setFill(Color.WHITE);
        gc.fillText(resumeMessage, canvasWidth /3.5, canvasHeight / 2);
        gc.fillText(restartMessage, canvasWidth / 3.5, canvasHeight / 1.7);
        gc.fillText(quitMessage, canvasWidth / 3.5, canvasHeight / 1.5);
        
    }
}