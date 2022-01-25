package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;


public class RocketView extends View {
    private final TextColor backGround;
    private final TextColor foreGround;
    private final String string;

    public RocketView(String backGround, String foreGround, String string) {
        this.backGround = stringToColor(backGround);
        this.foreGround = stringToColor(foreGround);
        this.string = string;
    }

    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(backGround);
        graphics.setForegroundColor(foreGround);
        graphics.putString(pos.getX(), numberRows - pos.getY(), string);
    }
}

