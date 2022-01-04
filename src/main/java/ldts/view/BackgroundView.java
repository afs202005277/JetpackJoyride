package ldts.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;
import java.util.ArrayList;

public class BackgroundView extends View{
    private final int lower;

    public BackgroundView(int lower) {
        this.lower = lower;
    }

    private void drawBase(Position pos){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#808080")); // GREY
        graphics.fillRectangle(new TerminalPosition(pos.getX(), ROWS - pos.getY()), new TerminalSize(COLUMNS, lower), ' ');
    }

    private void drawWall(Position pos) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000C66"));  // DARK BLUE
        graphics.fillRectangle(new TerminalPosition(pos.getX(), pos.getY()), screen.getTerminalSize(), ' ');
    }

    public void draw(Position pos, int xMin) throws IOException {
        drawWall(new Position(pos.getX(), 0));
        drawBase(pos);
    }

    @Override
    public void draw(Position pos) throws IOException {}
}
