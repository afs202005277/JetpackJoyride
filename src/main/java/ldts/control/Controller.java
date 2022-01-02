package ldts.control;

import ldts.model.Background;
import ldts.model.Player;
import ldts.model.Position;
import ldts.view.BackgroundView;
import ldts.view.PlayerView;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Controller {
    private boolean gameOver = false;
    private final Player player;
    private PlayerView playerView;
    private BackgroundView backgroundView;
    private static final int LOWER_LIMIT = 1;

    public Controller() throws IOException, URISyntaxException, FontFormatException {
        player = new Player();
        playerView = new PlayerView();
        backgroundView = new BackgroundView(LOWER_LIMIT);
    }

    public void run() throws IOException, InterruptedException {
        Command command = new Command(playerView.getScreen());
        command.start();
        int xMin = 0;
        while (!gameOver) {
            playerView.getScreen().clear();
            backgroundView.draw(new Position(player.getPosition().getX(), LOWER_LIMIT), xMin);
            playerView.draw(player.getPosition());
            Character keyPressed = command.useKey();
            if (keyPressed == ' '){
                if (player.getPosition().getY() < playerView.getScreen().getTerminalSize().getRows())
                    player.goHigher();
            }
            else {
                if (player.getPosition().getY() > LOWER_LIMIT + 1)
                    player.goLower();
            }
            xMin++;
            Thread.sleep(400);
        }
    }
}
