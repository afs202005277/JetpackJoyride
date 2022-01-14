package ldts.view;

import com.googlecode.lanterna.TextColor;
import ldts.model.Position;

import java.io.IOException;

public class InstructionsView extends View{

    @Override
    public void draw(Position pos) throws IOException { }

    public void draw(PlayerView playerView, BackgroundView backgroundView, LaserView laserView, CoinView coinView) throws IOException {
        screen.clear();
        backgroundView.draw(new Position(0, backgroundView.getLower()), 0);
        laserView.draw(new Position(5, 3), new Position(5, 8));
        laserView.draw(new Position(3, 12), new Position(8, 17));
        Position pos1 = new Position(15, 17);
        Position pos2 = new Position(15, 16);
        Position pos3 = new Position(15, 15);
        Position pos7 = new Position(15, 14);
        Position pos4 = new Position(16, 17);
        Position pos5 = new Position(16, 16);
        Position pos6 = new Position(16, 15);
        Position pos8 = new Position(16, 14);
        Position[] positions = {pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8};
        for (Position p:positions)
            coinView.draw(p);
        playerView.drawLarge(new Position(10, 4));
        graphics.setBackgroundColor(backgroundView.getBackGroundWall());
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(22, 3, "instructions:");
        graphics.putString(22, 8, "press space to fly.");
        graphics.putString(22, 9, "don't go against ");
        graphics.putString(22, 10, "lasers or rockets.");

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(40, 3, "-back");

        screen.refresh();
    }
}
