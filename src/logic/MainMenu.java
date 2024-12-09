package logic;

import game.Main;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sharedObject.RenderableHolder;

public class MainMenu {

    public Parent createContent(){
        StackPane root = new StackPane();
        root.setPrefSize(360,400);
        
        ImageView imageView = new ImageView(RenderableHolder.croppedMenuBackground);
        
        imageView.setFitWidth(root.getPrefWidth());
        imageView.setFitHeight(root.getPrefHeight());
        root.getChildren().add(imageView);

        Rectangle background = new Rectangle(360, 400);
        
        background.setOpacity(0);
        root.getChildren().add(background);
     // Set custom cursor
        

        MenuVBox vBoxMenuBox = new MenuVBox(
                new MenuItemPlay(),
                
                new MenuItemExit());
        
        vBoxMenuBox.setSpacing(-30);
        vBoxMenuBox.setTranslateY(50);
        vBoxMenuBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(vBoxMenuBox);
        Image customCursorImage = new Image("/pointer.png"); // Path to your cursor image
        ImageCursor customCursor = new ImageCursor(customCursorImage);
        root.setCursor(customCursor);

//        ImageView musicOnImage = new ImageView(ImageLoader.getSoundOn());
//        ImageView musicOffImage = new ImageView(ImageLoader.getSoundOff());
//        ImageView musicImageView = new ImageView();
//        musicImageView.setImage(musicOnImage.getImage());
//        musicImageView.setFitWidth(70);
//        musicImageView.setFitHeight(70);
//        musicImageView.setTranslateY(250);
//        musicImageView.setTranslateX(-400);
//        musicImageView.setStyle("-fx-background-color: black;");
//
//        musicImageView.setOnMouseClicked(event -> {
//            if (Main.backgroundMusicPlayer.isMute()) {
//                Main.backgroundMusicPlayer.setMute(false);
//                musicImageView.setImage(musicOnImage.getImage());
//            } else {
//                Main.backgroundMusicPlayer.setMute(true);
//                musicImageView.setImage(musicOffImage.getImage());
//            }
//        });
//
//        root.getChildren().add(musicImageView);


        return root;
    }
}
