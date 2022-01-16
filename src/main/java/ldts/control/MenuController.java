package ldts.control;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.view.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuController implements InputObserver{
    private final MenuView menuView;
    private final PlayerView playerView;
    private final BackgroundView backgroundView;
    private final CoinView coinView;
    private final LaserView laserView;

    private final InputReader inputReader;

    public MenuController(PlayerView playerView, BackgroundView backgroundView, CoinView coinView, LaserView laserView) {
        this.playerView = playerView;
        this.backgroundView = backgroundView;
        this.coinView = coinView;
        this.laserView = laserView;
        this.menuView = new MenuView();

        inputReader = new InputReader(View.getScreen());
        inputReader.addObserver(this);
        inputReader.start();
    }

    public void step() {
        try {
            menuView.draw(playerView, backgroundView, laserView, coinView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void input(KeyStroke input) throws IOException, URISyntaxException, InterruptedException, FontFormatException {
        if (input.getKeyType() == KeyType.ArrowUp) {
            menuView.moveSelected(-1);
        } else if (input.getKeyType() == KeyType.ArrowDown) {
            menuView.moveSelected(1);
        }
        this.step();
        if (input.getKeyType() == KeyType.Enter) {
            if (menuView.getSelected() == 0) {
                inputReader.removeObserver(this);
                Controller.getInstance().run();
            }
            else if (menuView.getSelected() == 1) {
                inputReader.removeObserver(this);
                Controller.getInstance().runInstructions();
            }
            else if (menuView.getSelected() == 2)
                System.exit(0);
        }
    }
}
