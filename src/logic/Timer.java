package logic;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Timer implements IRenderable {
    private Timer timer;
    private int min,sec;
    private double x,y;
    public Timer(double x,double y){
        this.x = x;
        this.y = y;
    }
    public void runTimer(){
        timer = this;
        Thread timeDecrease = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!timer.isEmpty()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if(timer.sec ==0){
                        timer.min -= 1;
                        timer.sec = 59;
                    }else {
                        timer.sec-=1;
                    }
                    if(timer.sec == 10 && timer.min == 0){
                        RenderableHolder.clockSound.play();
                    }
                }
                GameState.state = GameState.END;
            }
        });
        timeDecrease.start();
    }
    public void setTimer(int min,int sec){
        this.min = min;
        this.sec = sec;
    }
    @Override
    public int getZ() {
        return 10;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setFont(Font.font(20));
        gc.strokeText(timer.toString(),x,y);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return GameState.state == GameState.GAME;
    }
    public boolean isEmpty(){
        return this.min == 0 && this.sec == 0;
    }

    public int getMin(){
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public int getSec(){
        return sec;
    }
    public void setSec(int sec){
        this.sec = sec;
    }
    public String toString(){
        return String.format("%02d:%02d",min,sec);
    }
}
