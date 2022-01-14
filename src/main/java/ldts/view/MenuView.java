package ldts.view;

import com.googlecode.lanterna.TextColor;

import java.io.IOException;

import static ldts.view.View.graphics;

public class MenuView {
    public void draw(PlayerView playerView, BackgroundView backgroundView, LaserView laserView, CoinView coinView) throws IOException {
        MenuBackGround.draw(playerView, backgroundView, laserView, coinView);
        graphics.setBackgroundColor(backgroundView.getBackGroundWall());
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(28, 3, "JETPACK JOYRIDE:");
        graphics.putString(28, 8, "-play");
        graphics.putString(28, 9, "-instructions");
        graphics.putString(28, 10, "-exit");

        View.screen.refresh();
    }
}
