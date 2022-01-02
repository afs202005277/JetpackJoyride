

import com.googlecode.lanterna.*;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;



public class RocketView extends View {

    public RocketView() throws IOException, URISyntaxException, FontFormatException {
        initScreen();
    }

    @Override
    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.drawRectangle(new TerminalPosition(pos.getX(), pos.getY()), new TerminalSize(6,4), 'R');
        refresh();
    }
}
