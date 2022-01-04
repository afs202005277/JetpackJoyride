package ldts.view;

import com.googlecode.lanterna.*;
import ldts.model.Position;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class PlayerView extends View{
    public PlayerView() throws IOException, URISyntaxException, FontFormatException {
        initScreen();
    }

    @Override
    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000C66"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.putString(pos.getX(), ROWS-pos.getY(), "k");
    }
}
