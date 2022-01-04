package ldts.view;

import com.googlecode.lanterna.*;
import ldts.model.Position;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class RocketView extends View {

    @Override
    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000C66"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#ff1f1f"));
        graphics.putString(pos.getX(), ROWS - pos.getY(), "xX");
    }
}

