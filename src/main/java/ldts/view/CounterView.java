package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;

public class CounterView extends View{
    private TextColor backGround;
    private TextColor foreGround;

    public String getUnits() {
        return units;
    }

    private String units;

    @Override
    public void draw(Position pos) throws IOException {}

    public CounterView(String backGround, String foreGround, String units) {
        this.backGround = stringToColor(backGround);
        this.foreGround = stringToColor(foreGround);
        this.units = units;
    }

    public void draw(Position pos, int quantity) throws IOException {
        graphics.setBackgroundColor(backGround);
        graphics.setForegroundColor(foreGround);

        String tmp = String.valueOf(quantity);
        tmp += " " + units;
        graphics.putString(pos.getX(), pos.getY(), tmp);
    }
}
