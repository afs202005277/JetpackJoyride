package ldts.view;

import com.googlecode.lanterna.TextColor;

import java.io.IOException;

import static ldts.view.View.graphics;

public class InstructionsView {

    public void draw(PlayerView playerView, BackgroundView backgroundView, LaserView laserView, CoinView coinView) throws IOException {
        MenuBackGround.draw(playerView, backgroundView, laserView, coinView);
        graphics.setBackgroundColor(backgroundView.getBackGroundWall());
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(22, 3, "instructions:");
        graphics.putString(22, 8, "press space to fly.");
        graphics.putString(22, 9, "don't go against ");
        graphics.putString(22, 10, "lasers or rockets.");

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(40, 3, "-back");

        View.screen.refresh();
    }
}
