package ldts.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.Coin;
import ldts.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class CoinViewTest {
    private TextGraphics g;
    private CoinView coinView;
    private TextColor backGround = TextColor.Factory.fromString("#57AAF8");
    private TextColor foreGround = TextColor.Factory.fromString("#DEAC4C");

    @BeforeEach
    void setUp(){
        g = Mockito.mock(TextGraphics.class);
        View.setGraphics(g);
        coinView = new CoinView("#57AAF8", "#DEAC4C", "#");
    }

    @Test
    void draw() throws IOException {
        coinView.draw(new Position(5, 5));
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(backGround);
        Mockito.verify(g, Mockito.times(1)).setForegroundColor(foreGround);
        Mockito.verify(g, Mockito.times(1)).putString(5, 18-5, "#");
    }
}
