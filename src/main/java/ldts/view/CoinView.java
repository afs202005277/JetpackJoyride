package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;

public class CoinView extends View{
    private TextColor backGround;
    private TextColor foreGround;
    private String string;

    public CoinView(String backGround, String foreGround, String character) {
        this.backGround = stringToColor(backGround);
        this.foreGround = stringToColor(foreGround);
        this.string = character;
    }

    @Override
    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(backGround);
        graphics.setForegroundColor(foreGround);
        graphics.putString(pos.getX(), ROWS-pos.getY(), string);
    }
}
