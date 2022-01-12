package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;

public class DistanceCounterView extends View{
    @Override
    public void draw(Position pos) throws IOException {}

    public void draw(int distance) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#808080"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        String tmp = String.valueOf(distance);
        tmp += " metros";
        graphics.putString(18, 0, tmp);
    }
}
