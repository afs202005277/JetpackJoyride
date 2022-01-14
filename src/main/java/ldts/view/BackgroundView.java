package ldts.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;
import java.util.ArrayList;

public class BackgroundView extends View{
    private final int lower;
    private TextColor backGroundBase;
    private TextColor backGroundWall;
    private char characterWall;
    private char characterBase;

    public BackgroundView(String backGroundBase, String backGroundWall, char characterWall, char characterBase, int lower){
        this.backGroundBase = stringToColor(backGroundBase);
        this.backGroundWall = stringToColor(backGroundWall);
        this.characterWall = characterWall;
        this.lower = lower;
        this.characterBase = characterBase;
    }

    public TextColor getBackGroundWall() {
        return backGroundWall;
    }

    public int getLower() {
        return lower;
    }

    private void drawBase(Position pos){
        graphics.setBackgroundColor(backGroundBase); // GREY
        graphics.fillRectangle(new TerminalPosition(pos.getX(), ROWS - pos.getY()), new TerminalSize(screen.getTerminalSize().getColumns(), lower), characterBase);
    }

    private void drawWall(Position pos) throws IOException {
        graphics.setBackgroundColor(backGroundWall);  // DARK BLUE
        graphics.fillRectangle(new TerminalPosition(pos.getX(), pos.getY()), screen.getTerminalSize(), characterWall);
    }

    public void draw(Position pos, int xMin) throws IOException {
        drawWall(new Position(pos.getX(), 0));
        drawBase(pos);
    }

    @Override
    public void draw(Position pos) throws IOException {}
}
