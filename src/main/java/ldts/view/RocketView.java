package ldts.view;

import com.googlecode.lanterna.*;
import ldts.model.Position;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class RocketView extends View {

    @Override
    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.drawRectangle(new TerminalPosition(pos.getX(),ROWS - pos.getY()), new TerminalSize(2,1), 'R');
        refresh();
    }
}

