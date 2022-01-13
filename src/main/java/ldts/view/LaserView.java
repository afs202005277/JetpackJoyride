package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;

public class LaserView extends View{
    private TextColor backGround;
    private char character;

    @Override
    public void draw(Position pos) throws IOException {}

    public LaserView(String backGround, char character) {
        this.backGround = stringToColor(backGround);
        this.character = character;
    }

    public void draw(Position pos1, Position pos2) throws IOException {
        graphics.setBackgroundColor(backGround);
        graphics.drawLine(pos1.getX(),ROWS- pos1.getY(), pos2.getX(),ROWS - pos2.getY(), character);
    }
}
