package ldts.control;

import ldts.model.Player;
import ldts.view.View;

public class PlayerController implements InputObserver{
    private Player player;
    private InputReader inputReader;

    public PlayerController(Player player, InputReader inputReader) {
        this.player = player;
        this.inputReader = inputReader;
    }

    @Override
    public void input(char input) {
        if (input == ' ' && player.getPosition().getY() < View.getScreen().getTerminalSize().getRows())
            player.goHigher();
    }

    @Override
    public void stopReadingInput() {
        inputReader.interrupt();
    }

    public void step(int lower){
        if (player.getPosition().getY() > lower + 1) {
            player.goLower();
        }
    }
}
