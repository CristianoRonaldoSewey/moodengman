package game;



import logic.MainMenu;
import game.Model;
import game.menu.MapSelectionMenu;
import javafx.application.*;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage mainStage;
	public void start(Stage primaryStage) {
//		Scene mapSelectionScene = new MapSelectionMenu().createContent();
//		
//		Scene mainScene = new Scene(new logic.MainMenu().createContent());
//
//        Cursor customCursor = new ImageCursor(new Image("/pointer.png"));
//
//        primaryStage.setScene(mainScene);
//        primaryStage.show();
//        primaryStage.getScene().setCursor(customCursor);
//        setMainStage(primaryStage);
		mainStage = primaryStage;

        // Start with map selection
        PageChanger.changeToMapSelection(primaryStage);

        primaryStage.show();
	}
	public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        Main.mainStage = mainStage;
    }
	
	
	
	public static void main(String[] args) {
		
		launch(args);
//		Pacman pac = new Pacman();
//		pac.setVisible(true);
//		pac.setTitle("Pacman");
//		pac.setSize(380,420);
//		pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		pac.setLocationRelativeTo(null);
		
	}
}
