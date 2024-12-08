package logic;

//import game.util.SoundLoader;

public class MenuItemExit extends MenuItem{
    public MenuItemExit(){

        setMenuItemName("EXIT");
        setOnMouseClicked(event ->{
            //SoundLoader.playClickSound();
            System.exit(0);
        } );
    }
}
