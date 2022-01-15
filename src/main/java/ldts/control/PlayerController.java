package ldts.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.model.Player;
import ldts.view.PlayerView;
import ldts.view.View;

import java.io.IOException;

public class PlayerController implements InputObserver {
    private Player player;
    private final PlayerView playerView;

    public PlayerController(Player player, PlayerView playerView) {
        this.player = player;
        this.playerView = playerView;
    }

    @Override
    public synchronized void input(KeyStroke input) {
        if (input.getKeyType() != KeyType.Character)
            return;
        if (input.getCharacter() == ' ' && player.getPosition().getY() < View.getScreen().getTerminalSize().getRows()) {
            player.goHigher();
        }
    }

    public synchronized void step(int lower) throws IOException {
        if (player.getPosition().getY() > lower + 1) {
            player.goLower();
        }
        playerView.draw(player.getPosition());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }
}
