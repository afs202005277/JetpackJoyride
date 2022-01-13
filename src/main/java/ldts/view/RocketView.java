package ldts.view;

import com.googlecode.lanterna.*;
import ldts.model.Obstacle;
import ldts.model.Position;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class RocketView extends View {
    private TextColor backGround;
    private TextColor foreGround;
    private String string;

    public RocketView(String backGround, String foreGround, String string) {
        this.backGround = stringToColor(backGround);
        this.foreGround = stringToColor(foreGround);
        this.string = string;
    }

    @Override
    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(backGround);
        graphics.setForegroundColor(foreGround);
        graphics.putString(pos.getX(), ROWS - pos.getY(), string);
    }
}

