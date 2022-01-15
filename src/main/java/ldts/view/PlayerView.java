package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;


public class PlayerView extends View {
    private final TextColor backGround;
    private final TextColor foreGround;
    private final String string;

    public PlayerView(String backGround, String foreGround, String string) {
        this.backGround = stringToColor(backGround);
        this.foreGround = stringToColor(foreGround);
        this.string = string;
    }

    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(backGround);
        graphics.setForegroundColor(foreGround);
        graphics.putString(pos.getX(), ROWS - pos.getY(), string);
        screen.refresh();
    }

    public void drawLarge(Position pos) {
        graphics.setBackgroundColor(backGround);
        graphics.setForegroundColor(foreGround);
        graphics.putString(pos.getX(), ROWS - pos.getY(), "&?(");
        graphics.putString(pos.getX(), ROWS - pos.getY() + 1, ")*+");
        graphics.putString(pos.getX(), ROWS - pos.getY() + 2, ",;/");
    }
}
