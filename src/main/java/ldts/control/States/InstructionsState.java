package ldts.control.States;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.control.Controller;
import ldts.control.PlayerController;
import ldts.view.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class InstructionsState extends State {
    private final PlayerController playerController;
    private BackgroundView backgroundView;
    private final CoinView coinView;
    private final LaserView laserView;
    private final InstructionsView instructionsView;

    public InstructionsState(PlayerController playerController) {
        this.playerController = playerController;
        this.backgroundView = new BackgroundView("#595959", "#57AAF8", ' ', ' ', 1);
        this.coinView = new CoinView("#57AAF8", "#DEAC4C", "#");
        this.laserView = new LaserView("#fffb54", ' ');
        this.instructionsView = new InstructionsView();
    }

    public void setBackgroundView(BackgroundView backgroundView) {
        this.backgroundView = backgroundView;
    }

    @Override
    public void step() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        try {
            instructionsView.draw(playerController.getPlayerView(), backgroundView, laserView, coinView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            KeyStroke k = View.getScreen().readInput();
            if (k.getKeyType() == KeyType.Enter) {
                break;
            }
        }
        Controller.getInstance().runMenu();
    }
}
