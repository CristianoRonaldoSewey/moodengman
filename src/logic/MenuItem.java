package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//import game.util.SoundLoader;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sharedObject.RenderableHolder;

public abstract class MenuItem extends StackPane {
    private String menuItemName;
    private Text text;

    public MenuItem()
    {
        Rectangle moderateCorners = new Rectangle(200, 130);
        Image backgroundImage = RenderableHolder.playButton;
        Image backgroundImageHover = RenderableHolder.playButtonHover;
        moderateCorners.setFill(new ImagePattern(backgroundImage));
        moderateCorners.setArcWidth(20);
        moderateCorners.setArcHeight(20);
        moderateCorners.setOpacity(1);

        text = new Text();
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Pixellari", 23));

        getChildren().addAll(moderateCorners, text);
        
        setOnMouseEntered(event ->{
        	moderateCorners.setFill(new ImagePattern(backgroundImageHover));});

        setOnMouseExited(event -> moderateCorners.setFill(new ImagePattern(backgroundImage)));
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
        text.setText(menuItemName);
    }

}
