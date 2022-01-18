package ldts.control;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.view.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuController {
    private MenuView menuView;
    private final PlayerView playerView;
    private final BackgroundView backgroundView;
    private final CoinView coinView;
    private final LaserView laserView;
    private boolean keepRunning;

    public MenuController(PlayerView playerView, BackgroundView backgroundView, CoinView coinView, LaserView laserView) {
        this.playerView = playerView;
        this.backgroundView = backgroundView;
        this.coinView = coinView;
        this.laserView = laserView;
        this.menuView = new MenuView();
        keepRunning = false;
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }

    @SuppressWarnings("CatchAndPrintStackTrace")
    public void step() {
        try {
            keepRunning = false;
            menuView.draw(playerView, backgroundView, laserView, coinView);
            this.input(View.getScreen().readInput());
        } catch (IOException | URISyntaxException | InterruptedException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public boolean isKeepRunning() {
        return keepRunning;
    }

    public void input(KeyStroke input) throws IOException, URISyntaxException, InterruptedException, FontFormatException {
        if (input.getKeyType() == KeyType.ArrowUp) {
            menuView.moveSelected(-1);
        } else if (input.getKeyType() == KeyType.ArrowDown) {
            menuView.moveSelected(1);
        }
        else if (input.getKeyType() == KeyType.Enter) {
            keepRunning = true;
            if (menuView.getSelected() == 0) {
                keepRunning = false;
                Controller.getInstance().run();
            }
            else if (menuView.getSelected() == 1) {
                do {
                    Controller.getInstance().runInstructions();
                }while(View.getScreen().readInput().getKeyType() != KeyType.Enter);
                keepRunning = false;
            }
            else if (menuView.getSelected() == 2)
                System.exit(0);
        }
        menuView.draw(playerView, backgroundView, laserView, coinView);
    }
}
