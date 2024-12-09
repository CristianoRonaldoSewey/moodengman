package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.MainMenu;
import sharedObject.RenderableHolder;

public class PageChanger {
	
	public static void changeToMainMenu(Stage stage) {
		MainMenu m = new MainMenu();
		// Set custom cursor
	    
		stage.setScene(new Scene(m.createContent()));
	}
	
	public static void changeToMapSelection(Stage stage) {
		BorderPane root = new BorderPane();
		// Set background image
	    Image backgroundImage = RenderableHolder.mapselectionMenu;
	    BackgroundImage bgImage = new BackgroundImage(backgroundImage, null, null, null, null);
	    root.setPrefSize(360, 400);
	    root.setBackground(new Background(bgImage));

	    // Back Button
	    Button backButton = new Button();
	    Image backImage = RenderableHolder.mapBackButton; 
	    ImageView backImageView = new ImageView(backImage);
	    backImageView.setFitWidth(50); // Set the width of the image
	    backImageView.setFitHeight(50);
	    backButton.setGraphic(backImageView);
	    backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
	    backButton.setOnAction(e -> PageChanger.changeToMainMenu(stage));
	    BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
	    BorderPane.setMargin(backButton, new Insets(10)); // Add spacing for the back button

	    // Title
	    Text title = new Text("Select Your Map");
	    title.setFont(Font.font("Book Antiqua", 34));
	    title.setStyle("-fx-fill: red;");
	    VBox titleBox = new VBox(title);
	    titleBox.setAlignment(Pos.TOP_CENTER);
	    titleBox.setPadding(new Insets(20, 0, 20, 0)); // Space above and below the title

	    // Map Buttons
	    HBox mapButtons = new HBox(0); // Horizontal box for map buttons
	    mapButtons.setAlignment(Pos.CENTER); // Center align horizontally
	    Button map1Button = createMapButton("Map 1", "mapButton1.png", stage, 0);
	    Button map2Button = createMapButton("Map 2", "mapButton2.png", stage, 1);
	    Button map3Button = createMapButton("Map 3", "mapButton3.png", stage, 2);
	    mapButtons.getChildren().addAll(map1Button, map2Button, map3Button);

	    // Main Layout
	    VBox mainLayout = new VBox(titleBox, mapButtons);
	    mainLayout.setAlignment(Pos.TOP_CENTER); // Align everything to the top center
	    mainLayout.setSpacing(30); // Space between title and buttons
	    mainLayout.setPadding(new Insets(10, 0, 0, 0)); // Move layout further down if needed

	    // Combine Back Button and Main Layout
	    root.setTop(backButton); // Back button remains at the top-left
	    root.setCenter(mainLayout); // Center the rest of the layout below

	    // Set the scene
	    Scene mapSelectionScene = new Scene(root, 360, 400);
	 // Set custom cursor
	    Cursor customCursor = new ImageCursor(new Image("/pointer.png"));
	    mapSelectionScene.setCursor(customCursor);
	    stage.setTitle("Select Map");
	    stage.setScene(mapSelectionScene);
	    stage.centerOnScreen();
    }

    private static Button createMapButton(String mapName, String imagePath, Stage stage, int mapIndex) {
        Button mapButton = new Button(mapName);
        mapButton.setPrefWidth(85);  // Set width of the button
        mapButton.setPrefHeight(85);  // Set height of the button
        // Add image preview
        ImageView mapPreview = new ImageView(new Image(imagePath));
        mapPreview.setFitWidth(85);
        mapPreview.setFitHeight(85);
        mapButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        mapButton.setGraphic(mapPreview);
        
        mapButton.setOnAction(e -> {
            System.out.println(mapName + " selected!");
            changeToGame(mapIndex); // Transition to game page with the selected map index
        });

        return mapButton;
    }
    private static short[] getMap1Data() {
        return new short[]{
        		19, 18, 18, 18, 18, 18, 18, 18, 18, 26, 26, 26, 18, 18, 22,
    	        17, 16, 16, 16, 16, 24, 16, 16, 20, 0, 0, 0, 0, 17, 20,
    	        25, 24, 24, 24, 28, 0, 17, 16, 16, 18, 18, 18, 18, 16, 20,
    	        0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
    	        19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20,
    	        17, 24, 24, 16, 16, 16, 16, 24, 16, 20, 0,  0,  0,   0, 21,
    	        21, 0, 0, 17, 16, 16, 20, 0, 17, 20, 0,  0,  0,   0, 21,
    	        17, 18, 18, 16, 24, 16, 16, 18, 16, 20, 0,  0,  0,   0, 21,
    	        17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 18, 18, 18, 18, 20,
    	        17, 24, 24, 28, 0, 25, 24, 24, 16, 16, 16, 16, 24, 24, 20,
    	        21, 0,  0,  0,  0,  0,  0,   0, 17, 16, 16, 20, 0, 0, 21,
    	        17, 18, 18, 22, 0, 19, 18, 18, 16, 16, 16, 20,  0,  19, 20,
    	        17, 16, 16, 20, 0,  17, 16, 16, 16, 24, 24, 28,  0, 17, 20,
    	        17, 16, 16, 20, 0,  17, 16, 16, 20,  0,  0,  0,  0, 17, 20,
    	        25, 24, 24, 24, 26, 24, 24, 24, 24, 26, 26, 26, 26, 24, 28
            // Add full map data for Map 1
        };
    }

    private static short[] getMap2Data() {
        return new short[]{
        		19, 18, 26, 26, 26, 26, 26, 18, 26, 26, 26, 26, 26, 18, 22,
                17, 20,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0,  0, 17, 20,
                17, 20,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0,  0, 17, 20,
                17 ,16, 18, 18, 18, 18,  18, 16, 18, 18, 18, 18, 18, 16, 20,
                17, 16, 16, 16, 16, 16,  24, 24, 24, 16, 16, 16, 16, 16, 20,
                17, 16, 16, 16, 16, 20,   0,  0,  0, 17, 16, 16, 16, 16, 20,
                17, 16, 16, 128, 16, 20,   0, 263,  0, 17, 16, 64, 16, 16, 20,
                17, 16, 16, 16, 16, 20,   0, 21,  0, 17, 16, 16, 16, 16, 20,
                17, 16, 16, 16, 16, 20,   0, 21,  0, 17, 16, 16, 16, 16, 20,
                17, 16, 24, 16, 16, 20,   0, 21,  0, 17, 16, 16, 16, 16, 20,
                17, 20,  0, 17, 16, 16,  18, 16, 18, 16, 16, 16, 24, 16, 20,
                17, 20,  0, 17, 16, 16,  16, 16, 16, 16, 16, 20,  0, 17, 20,
                17, 20,  0, 25, 24, 24,  24, 24, 24, 24, 24, 28,  0, 17, 20,
                17, 20,  0,  0,  0,  0,   0,  0,  0,  0,  0,  0,  0, 17, 20,
                25, 24, 26, 26,  26,26,  26, 42, 26, 26, 26, 26, 26, 24, 28
            // Add full map data for Map 2
        };
    }

    private static short[] getMap3Data() {
        return new short[]{
            // Add full map data for Map 3
        		 19, 26, 26, 26, 26, 26, 26, 18, 26, 26, 26, 26, 26, 26, 22,
                 21,  0,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0,  0,  0, 21,
                 17, 18, 22,  0, 35, 18, 18, 16, 18, 18, 22,  0, 259, 18, 20,
                 17, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20,  0, 17, 16, 20,
                 17, 16, 20,  0, 17, 16, 16, 24, 16, 16, 20,   0, 17,16, 20,
                 17, 16, 20,  0, 17, 16, 20,  0, 17, 16, 20,   0, 17, 16, 20,
                 17, 16, 16, 18, 16, 16, 20,  0, 17, 16, 16, 18, 16, 16, 20,
                 17, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20,
                 17, 16, 16, 24, 16, 16, 20,  0, 17, 16, 16, 24, 16, 16, 20,
                 17, 16, 20,  0, 17, 16, 20,  0, 17, 16, 20,  0, 17, 16,  20,
                 17, 16, 20,  0, 17, 16, 16, 18, 16, 16, 20,  0, 17, 16,  20,
                 17, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20,  0, 17, 16,  20,
                 17, 24, 76,  0, 25, 24, 24, 16, 24, 24, 140,  0, 25, 24,  20,
                 21, 0,  0,   0,  0,  0,  0, 21,  0,  0,  0,  0,  0,  0,  21,
                 25, 26, 26, 26, 26, 26, 26, 24, 26, 26, 26, 26, 26, 26, 28
        };
    }
	
	public static void changeToGame(int mapIndex) {
		Stage stage = Main.getMainStage(); // Get the Stage from Main
	    Pane root = new Pane();

	    Scene gameScene = new Scene(root, 360, 400);
	 // Set custom cursor
	    Cursor customCursor = new ImageCursor(new Image("/pointer.png"));
	    gameScene.setCursor(customCursor);
	    stage.setTitle("Pacman");
	    stage.setScene(gameScene);
	    stage.centerOnScreen();
	    stage.setOnCloseRequest(event -> {
	        System.out.println("Exit application");
	    });

	    Canvas canvas = new Canvas(360, 400);
	    GraphicsContext gc = canvas.getGraphicsContext2D();

	    root.getChildren().add(canvas);

	    Model model = new Model();

	    // Dynamically set the map data based on mapIndex
	    switch (mapIndex) {
	        case 0:
	            model.setLevelData(getMap1Data()); // Use map 1 data
	            break;
	        case 1:
	            model.setLevelData(getMap2Data()); // Use map 2 data
	            break;
	        case 2:
	            model.setLevelData(getMap3Data()); // Use map 3 data
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid map index");
	    }

	    model.startGameLoop(gameScene, gc);

	    stage.setResizable(false);
	}

}
