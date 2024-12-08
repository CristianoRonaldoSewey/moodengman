package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PageChanger {
	
	public static void changeToMapSelection(Stage stage) {
        // Root pane for the map selection page
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");

        // Title
        Text title = new Text("Select Your Map");
        title.setFont(Font.font("Arial", 36));
        title.setStyle("-fx-fill: white;");

        // Map selection buttons
        HBox mapButtons = new HBox(20);
        mapButtons.setStyle("-fx-padding: 50;");
        mapButtons.setSpacing(20);

        // Create buttons for each map
        Button map1Button = createMapButton("Map 1", "/map1_preview.png", stage, 0);
        Button map2Button = createMapButton("Map 2", "/map2_preview.png", stage, 1);
        Button map3Button = createMapButton("Map 3", "/map3_preview.png", stage, 2);

        mapButtons.getChildren().addAll(map1Button, map2Button, map3Button);

        // Layout
        VBox layout = new VBox(30, title, mapButtons);
        layout.setStyle("-fx-alignment: center;");

        root.getChildren().add(layout);

        // Set the scene
        Scene mapSelectionScene = new Scene(root, 800, 600);
        stage.setTitle("Select Map");
        stage.setScene(mapSelectionScene);
        stage.centerOnScreen();
    }

    private static Button createMapButton(String mapName, String imagePath, Stage stage, int mapIndex) {
        Button mapButton = new Button(mapName);

        // Add image preview
        ImageView mapPreview = new ImageView(new Image(imagePath));
        mapPreview.setFitWidth(200);
        mapPreview.setFitHeight(150);

        mapButton.setGraphic(mapPreview);
        mapButton.setStyle("-fx-background-color: #444; -fx-text-fill: white;");
        mapButton.setOnAction(e -> {
            System.out.println(mapName + " selected!");
            PageChanger.changeToGame(mapIndex); // Transition to game page with the selected map index
        });

        return mapButton;
    }
	
	
	public static void changeToGame(int mapIndex) {
		Stage stage = Main.getMainStage(); // Get the Stage from Main
		Pane root = new Pane();

		Scene gameScene = new Scene(root, 380, 400);
		stage.setTitle("Pacman");
		stage.setScene(gameScene);
		stage.centerOnScreen();
		stage.setOnCloseRequest(event -> {
			System.out.println("Exit application");
		});
		Canvas canvas = new Canvas(380, 400);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		root.getChildren().add(canvas);
		

		Model model = new Model();
		//model.loadMap(mapIndex); // Load the selected map
		model.startGameLoop(gameScene, gc);

		stage.setResizable(false);
	}

}
