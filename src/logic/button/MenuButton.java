package logic.button;

import javafx.scene.shape.Rectangle;

import logic.GameState;
import sharedObject.IRenderable;


public abstract class MenuButton extends Button {
    protected Rectangle hitBox;
    public MenuButton(double x, double y,int z, double width, int height) {
        super(x,y,z,width,height);

    }
    @Override
    public boolean isVisible() {
        if(GameState.state != GameState.MENU) {
            return false;
        }
        return visible;
    }

}
