package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class MenuBackground implements IRenderable {

    @Override
    public int getZ() {
        return -9999;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.croppedMenuBackground,0,0);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return GameState.state == GameState.MENU;
    }
}
