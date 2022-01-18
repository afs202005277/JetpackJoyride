package ldts.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

import static java.lang.Math.abs;

public class GameOverView extends View
{
    private int selected = 0; // 0 - Replay, 1 - MAIN MENU
    private final int options = 2;

    public void moveSelected(int move) {
        selected = move+selected<0 ? options-abs(selected+move) : move+selected>options-1 ? (selected+move)-options : selected+move;
    }

    public int getSelected() {
        return selected;
    }


    public void draw() throws IOException {
        int currentNCols = screen.getTerminalSize().getColumns();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.fillRectangle(new TerminalPosition(currentNCols/2-6, ROWS/2-3), new TerminalSize(14, 6), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(currentNCols/2-4, ROWS/2-2), "GAMEOVER");

        graphics.setBackgroundColor(TextColor.Factory.fromString(selected == 0 ? "#111111" : "#FFFFFF"));
        graphics.setForegroundColor(TextColor.Factory.fromString(selected == 0 ? "#FFFFFF" : "#000000"));
        graphics.putString(new TerminalPosition(currentNCols/2-4, ROWS/2-1), "- REPLAY");

        graphics.setBackgroundColor(TextColor.Factory.fromString(selected == 0 ? "#FFFFFF" : "#111111"));
        graphics.setForegroundColor(TextColor.Factory.fromString(selected == 0 ? "#000000" : "#FFFFFF"));
        graphics.putString(new TerminalPosition(currentNCols/2-4, ROWS/2), "- MAIN MENU");
        screen.refresh();
    }
}
