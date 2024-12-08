package logic;



import static game.Main.getMainStage;

import game.PageChanger;
import javafx.scene.layout.StackPane;

class MenuItemPlay extends MenuItem
{
	
    public MenuItemPlay()
    {
    	
        setMenuItemName("PLAY");
        
        
        setOnMouseClicked(event ->
        {
            //SoundLoader.playClickSound();
//            StackPane stackPane = (StackPane) getMainStage().getScene().getRoot();
            PageChanger.changeToMapSelection(getMainStage());
        });
    }
}
