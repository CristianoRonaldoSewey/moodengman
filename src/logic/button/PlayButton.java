package logic.button;

import javafx.scene.canvas.GraphicsContext;
import logic.GameState;
import sharedObject.RenderableHolder;

public class PlayButton extends MenuButton{
    public PlayButton(double x, double y, int z, int width, int height) {
        super(x, y, z, width, height);
        visible = true;
        destroyed = false;
    }

    @Override
    public void click() {
        GameState.state = GameState.GAME;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if(isMouseOver())
            gc.drawImage(RenderableHolder.playButtonHover,x,y);
        else{
            gc.drawImage(RenderableHolder.playButton,x,y);
        }
    }
}
