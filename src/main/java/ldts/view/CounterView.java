package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;

public class CounterView extends View {
    private final TextColor backGround;
    private final TextColor foreGround;
    private final String units;

    public CounterView(String backGround, String foreGround, String units) {
        this.backGround = stringToColor(backGround);
        this.foreGround = stringToColor(foreGround);
        this.units = units;
    }

    public String getUnits() {
        return units;
    }

    public void draw(Position pos, int quantity) throws IOException {
        graphics.setBackgroundColor(backGround);
        graphics.setForegroundColor(foreGround);

        String tmp = quantity + " " + units;
        graphics.putString(pos.getX(), pos.getY(), tmp);
    }
}
