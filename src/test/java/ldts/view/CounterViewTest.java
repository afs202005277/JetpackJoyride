package ldts.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class CounterViewTest {
    private TextGraphics g;
    private CounterView counterView;
    private final TextColor backGround = TextColor.Factory.fromString("#595959");
    private final TextColor foreGround = TextColor.Factory.fromString("#DEAC4C");

    @BeforeEach
    void setUp(){
        g = Mockito.mock(TextGraphics.class);
        View.setGraphics(g);
        counterView = new CounterView("#595959", "#DEAC4C", "coins");
    }

    @Test
    void draw() throws IOException {
        counterView.draw(new Position(5, 7), 56);
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(backGround);
        Mockito.verify(g, Mockito.times(1)).setForegroundColor(foreGround);
        Mockito.verify(g, Mockito.times(1)).putString(5, 7, "56 coins");
    }
}
