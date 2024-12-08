package game.menu;

import game.Main;
import game.PageChanger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sharedObject.RenderableHolder;

public class MapSelectionMenu {

    public Scene createContent() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");

        // Title
        Text title = new Text("Select Your Map");
        title.setFont(Font.font("Arial", 36));
        title.setStyle("-fx-fill: white;");

        // Map options
        HBox mapOptions = new HBox(20);
        mapOptions.setAlignment(Pos.CENTER);

        // Create buttons for each map
        Button map1Button = createMapButton("Map 1", "/mapButton1.jpg", 0);
        Button map2Button = createMapButton("Map 2", "/mapButton2.png", 1);
        Button map3Button = createMapButton("Map 3", "/mapButton3.png", 2);

        mapOptions.getChildren().addAll(map1Button, map2Button, map3Button);

        VBox layout = new VBox(30, title, mapOptions);
        layout.setAlignment(Pos.CENTER);

        root.getChildren().add(layout);

        return new Scene(root, 800, 600);
    }

    private Button createMapButton(String mapName, String imagePath, int mapIndex) {
        Button mapButton = new Button(mapName);

        // Add an image preview
        
        Image mapPreviewImage = new Image(PageChanger.class.getResourceAsStream(imagePath));
        ImageView mapPreview = new ImageView(mapPreviewImage);
        mapPreview.setFitWidth(200);
        mapPreview.setFitHeight(150);

        mapButton.setGraphic(mapPreview);
        mapButton.setStyle("-fx-background-color: #444; -fx-text-fill: white;");

        // On button click, load the selected map
        mapButton.setOnAction(e -> {
            System.out.println(mapName + " selected!");
            PageChanger.changeToGame(mapIndex); // Pass the map index
        });

        return mapButton;
    }
}