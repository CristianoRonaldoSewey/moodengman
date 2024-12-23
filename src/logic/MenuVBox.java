package logic;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class MenuVBox extends VBox{
    public MenuVBox(MenuItem... items){
        getChildren().add(createLineSeparator());
        for(MenuItem item : items){
            getChildren().addAll(item, createLineSeparator());
        }
    }

    private Line createLineSeparator(){
        Line lineSeparator = new Line();
        lineSeparator.setEndX(220);
        lineSeparator.setStroke(Color.TRANSPARENT);
        return lineSeparator;
    }
}
