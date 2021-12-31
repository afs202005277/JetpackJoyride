package ldts.control;

import ldts.model.Player;
import ldts.model.Position;
import ldts.view.BackgroundView;
import ldts.view.PlayerView;

import java.io.IOException;

public class Controller {
    private boolean gameOver = false;
    private final Player player;
    private PlayerView playerView;
    private BackgroundView backgroundView;
    private static final int LOWER_LIMIT = 2;

    public Controller() throws IOException {
        player = new Player();
        playerView = new PlayerView();
        backgroundView = new BackgroundView(LOWER_LIMIT);
    }

    public void run() throws IOException, InterruptedException {
        Command command = new Command(playerView.getScreen());
        command.start();
        while (!gameOver) {
            playerView.getScreen().clear();
            backgroundView.draw(new Position(0, LOWER_LIMIT));
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
            Thread.sleep(400);
        }
    }
}
