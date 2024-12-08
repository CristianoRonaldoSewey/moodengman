package logic.button;

import javafx.scene.shape.Rectangle;

import sharedObject.IRenderable;


public abstract class Button implements IRenderable {
    protected double x,y;
    protected int z;
    private boolean isMouseOver = false;
    protected double width,height;
    protected boolean visible,destroyed;
    protected Rectangle hitBox;
    public Button(double x, double y,int z, double width, int height) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        setHitBox(x,y,width,height);
    }
    public void setHitBox(double x, double y, double width, double height) {
        hitBox = new Rectangle((int) x, (int) y, width, height);
    }
    public Rectangle getHitBox(){
        return hitBox;
    }
    public abstract void click();
    public abstract boolean isVisible();
    @Override
    public int getZ() {
        return z;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }



    public boolean isMouseOver() {
        return isMouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        isMouseOver = mouseOver;
    }
}
