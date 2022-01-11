package ldts.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;

public class GameOverView extends View
{
    private int selected = 0; // 0 - Replay, 1 - QUIT
    private final int options = 2;

    public void moveSelected(int move)
    {
        selected += move;
        if (selected < 0) selected = options - selected;
        else if (selected >= options) selected -= options;
    }

    public int getSelected() {
        return selected;
    }

    @Override
    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.fillRectangle(new TerminalPosition(COLUMNS/2-6, ROWS/2-3), new TerminalSize(12, 6), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(COLUMNS/2-4, ROWS/2-2), "GAMEOVER");

        graphics.setBackgroundColor(TextColor.Factory.fromString(selected == 0 ? "#111111" : "#FFFFFF"));
        graphics.putString(new TerminalPosition(COLUMNS/2-4, ROWS/2-1), "- REPLAY");

        graphics.setBackgroundColor(TextColor.Factory.fromString(selected == 0 ? "#FFFFFF" : "#111111"));
        graphics.putString(new TerminalPosition(COLUMNS/2-4, ROWS/2), "- QUIT");
        screen.refresh();
    }
}
