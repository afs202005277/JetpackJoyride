package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;

public class CoinView extends View {
    private final TextColor backGround;
    private final TextColor foreGround;
    private final String string;

    public CoinView(String backGround, String foreGround, String character) {
        this.backGround = stringToColor(backGround);
        this.foreGround = stringToColor(foreGround);
        this.string = character;
    }

    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(backGround);
        graphics.setForegroundColor(foreGround);
        graphics.putString(pos.getX(), numberRows - pos.getY(), string);
    }
}
