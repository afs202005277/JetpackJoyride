package ldts.view;

import com.googlecode.lanterna.TextColor;

import java.io.IOException;

import static java.lang.Math.abs;
import static ldts.view.View.graphics;

public class MenuView {
    private int selected = 0; // 0 - Play, 1 - Instructions, 2 - Quit
    private final int options = 3;


    public void moveSelected(int move) {
        selected += move;
        selected = abs(selected % options);
    }

    public int getSelected() {
        return selected;
    }

    public void draw(PlayerView playerView, BackgroundView backgroundView, LaserView laserView, CoinView coinView) throws IOException {
        View.screen.clear();

        MenuBackGround.draw(playerView, backgroundView, laserView, coinView);
        TextColor backGroundColor = backgroundView.getBackGroundWall();

        graphics.setBackgroundColor(backGroundColor);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(28, 3, "JETPACK JOYRIDE:");


        graphics.setBackgroundColor(TextColor.Factory.fromString(selected == 0 ? "#000000" : "#57AAF8"));
        graphics.putString(28, 8, "-play");


        graphics.setBackgroundColor(TextColor.Factory.fromString(selected == 1 ? "#000000" : "#57AAF8"));
        graphics.putString(28, 9, "-instructions");


        graphics.setBackgroundColor(TextColor.Factory.fromString(selected == 2 ? "#000000" : "#57AAF8"));
        graphics.putString(28, 10, "-exit");

        View.screen.refresh();
    }
}
