package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;

public class LaserView extends View{

    @Override
    public void draw(Position pos) throws IOException {}

    public void draw(Position pos1, Position pos2) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        //graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.drawLine(pos1.getX(),ROWS- pos1.getY(), pos2.getX(),ROWS - pos2.getY(), 'L');
    }
}
