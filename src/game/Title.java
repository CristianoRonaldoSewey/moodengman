package game;

import javafx.geometry.Pos;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Title extends StackPane {
    public Title(String name, int fontSize) {

        Text text=new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("super squad", FontWeight.MEDIUM,fontSize));


        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.5));

        text.setEffect(dropShadow);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(3);
        text.setStrokeType(StrokeType.OUTSIDE);

        setAlignment(Pos.CENTER);
        getChildren().addAll(text);
    }

}
