package ldts;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import ldts.control.PlayerController;
import ldts.model.Player;
import ldts.model.Position;
import ldts.view.PlayerView;
import ldts.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerControllerTest {
    private PlayerController playerController;
    private Player player;
    private PlayerView playerView;

    @BeforeEach
    void setUp() {
        player = new Player();
        playerView = Mockito.mock(PlayerView.class);
        playerController = new PlayerController(player, playerView);
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(50, 50));
        View.setScreen(screen);
    }

    @Test
    void inputNotCharacter() {
        KeyStroke k = Mockito.mock(KeyStroke.class);
        Mockito.when(k.getKeyType()).thenReturn(KeyType.Enter);
        Position prev = playerController.getPlayer().getPosition();
        playerController.input(k);
        Position after = playerController.getPlayer().getPosition();
        Assertions.assertEquals(prev, after);
    }

    @Test
    void inputCharacterSpace() {
        KeyStroke k = Mockito.mock(KeyStroke.class);
        Mockito.when(k.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(k.getCharacter()).thenReturn(' ');
        Position prev = new Position(playerController.getPlayer().getPosition());
        playerController.input(k);
        Position after = playerController.getPlayer().getPosition();
        Assertions.assertEquals(prev.getX(), after.getX());
        Assertions.assertEquals(prev.getY()+1, after.getY());
    }

    @Test
    void inputCharacterNotSpace() {
        KeyStroke k = Mockito.mock(KeyStroke.class);
        Mockito.when(k.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(k.getCharacter()).thenReturn('a');
        Position prev = playerController.getPlayer().getPosition();
        playerController.input(k);
        Position after = playerController.getPlayer().getPosition();
        Assertions.assertEquals(prev, after);
    }

    @Test
    void inputCharacterSpaceMaxHeight() {
        KeyStroke k = Mockito.mock(KeyStroke.class);
        Mockito.when(k.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(k.getCharacter()).thenReturn(' ');
        playerController.setPlayer(new Player(1, View.getScreen().getTerminalSize().getRows()));
        Position prev = playerController.getPlayer().getPosition();
        playerController.input(k);
        Position after = playerController.getPlayer().getPosition();
        Assertions.assertEquals(prev, after);
    }
}
