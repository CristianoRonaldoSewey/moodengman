package logic;




import static game.Main.getMainStage;


import game.PageChanger;


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
