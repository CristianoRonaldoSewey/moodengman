//package game.menu;
//
//import game.Main;
//import game.tile.LevelTile;
//import game.token.SokobanToken;
//import game.util.ImageLoader;
//import game.util.SoundLoader;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.ContentDisplay;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//
//import static game.Main.getMainStage;
//
//
//public class MenuLevelSelector {
//
//    private SokobanToken sokobanToken;
//    private static final int CHARACTER_COUNT = 2;
//    private static final int MAX_ROW_COUNT = 3;
//    private static final int MAX_COLUMN_COUNT = 9;
//    public StackPane createLevelSelectorStackPane()
//    {
//        int numberOfMaps = 18;
//
//        StackPane stackPaneLevelSelector = new StackPane();
//        stackPaneLevelSelector.setPrefSize(1050, 600);
//
//        ImageView imageViewBackground = new ImageView(ImageLoader.getImageBackground());
//        imageViewBackground.setFitWidth(stackPaneLevelSelector.getPrefWidth());
//        imageViewBackground.setFitHeight(stackPaneLevelSelector.getPrefHeight());
//
//        Rectangle background = new Rectangle(1050, 600);
//        background.setFill(Color.BLACK);
//        background.setOpacity(0.5);
//
//        stackPaneLevelSelector.getChildren().addAll(imageViewBackground,background);
//
//        VBox vBox = new VBox();
//        vBox.setSpacing(10);
//        vBox.setPadding(new Insets(80, 0, 0, 0));
//
//        Title menuTitle1 = new Title("SELECT PLAYER",30);
//        Title menuTitle2 = new Title("SELECT LEVEL",30);
//
//        ImageView backButtonImage = new ImageView(ImageLoader.getImageBackButton());
//        backButtonImage.setFitWidth(40);
//        backButtonImage.setFitHeight(40);
//
//        HBox rowBox = new HBox(20);
//        rowBox.setAlignment(Pos.CENTER);
//
//        for (int i = 0; i < CHARACTER_COUNT; i++) {
//            Image characterImage = new Image("/player" + (i + 1) + ".gif");
//
//            ImageView characterImageView = new ImageView(characterImage);
//            characterImageView.setFitWidth(40);
//            characterImageView.setFitHeight(40);
//
//            Button characterButton = new Button();
//            characterButton.setGraphic(characterImageView);
//            characterButton.setPrefWidth(60);
//            characterButton.setPrefHeight(60);
//            characterButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 15;");
//            characterButton.setContentDisplay(ContentDisplay.CENTER);
//            int finalI = i;
//            characterButton.setOnAction(e -> {
//                SoundLoader.playClickSound();
//                SokobanToken.selectCharacter(finalI);
//                for (Node node : rowBox.getChildren()) {
//                    if (node instanceof Button) {
//                        node.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 15;");
//                    }
//                }
//                characterButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-background-radius: 15;");
//            });
//            rowBox.getChildren().add(characterButton);
//        }
//
//
//        Button backButtonToMainMenu = new Button();
//        backButtonToMainMenu.setGraphic(backButtonImage);
//        backButtonToMainMenu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
//
//        Pane paneLevelTilesContainer = new Pane();
//
//        LevelTile[][] levelTiles = new LevelTile[MAX_ROW_COUNT][MAX_COLUMN_COUNT];
//
//        for (int i = 0; i < MAX_ROW_COUNT; i++)
//        {
//            for (int j = 0; j < MAX_COLUMN_COUNT; j++)
//            {
//                if (numberOfMaps == 0)
//                    break;
//
//                int levelNumber = ((i) * MAX_COLUMN_COUNT) + j + 1;
//
//                levelTiles[i][j] = new LevelTile(String.valueOf(levelNumber), (100 + 10) * j + 30, (100 + 10) * i);
//                paneLevelTilesContainer.getChildren().addAll(levelTiles[i][j]);
//                numberOfMaps--;
//            }
//        }
//
//        paneLevelTilesContainer.setPrefHeight((MAX_ROW_COUNT) * 100);
//
//        VBox.setMargin(menuTitle1,new Insets(-50, 0, 0, 0));
//        VBox.setMargin(menuTitle2, new Insets(60, 0, 0, 0));
//        VBox.setMargin(backButtonToMainMenu, new Insets(-70, 0, 0, 30));
//
//        backButtonToMainMenu.setOnAction(e -> {
//            SoundLoader.playClickSound();
//            backToMainMenu();}
//        );
//
//        vBox.getChildren().addAll(menuTitle1,rowBox,menuTitle2, paneLevelTilesContainer, backButtonToMainMenu);
//        stackPaneLevelSelector.getChildren().addAll(vBox);
//
//        return stackPaneLevelSelector;
//    }
//
//    private void backToMainMenu()
//    {
//        StackPane stackPane = (StackPane) getMainStage().getScene().getRoot();
//        stackPane.getChildren().remove(stackPane.getChildren().size() - 1);
//    }
//}
