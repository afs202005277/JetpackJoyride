package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;

public class CounterView extends View{
    private TextColor backGround;
    private TextColor foreGround;
    private String string;

    @Override
    public void draw(Position pos) throws IOException {}

    public CounterView(String backGround, String foreGround) {
        this.backGround = stringToColor(backGround);
        this.foreGround = stringToColor(foreGround);
    }

    public void draw(int distance, String units) throws IOException {
        graphics.setBackgroundColor(backGround);
        graphics.setForegroundColor(foreGround);

        String tmp = String.valueOf(distance);
        tmp += " " + units;
        graphics.putString(screen.getTerminalSize().getColumns() - tmp.length() - 10, 0, tmp);
    }
}
