package ldts.control;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import ldts.control.states.InstructionsState;
import ldts.model.Player;
import ldts.view.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class InstructionStateTest {
    private InstructionsState state;
    private Screen screen;
    private TextGraphics graphics;
    private Controller controller;
    private InstructionsView view;


    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(51,18));
        View.setScreen(screen);
        View.setGraphics(graphics);
        controller = Mockito.mock(Controller.class);
        view = Mockito.mock(InstructionsView.class);

        Controller.setSingleton(controller);

        state = new InstructionsState(new PlayerController(new Player(), new PlayerView("#57AAF8", "#D5433C", "!")));
    }

    @Test
    void step() throws IOException, URISyntaxException, FontFormatException, InterruptedException, AWTException {
        Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.Enter));
        state.setInstructionsView(view);
        state.step();
        Mockito.verify(view, Mockito.times(1)).draw(Mockito.any(PlayerView.class),Mockito.any(BackgroundView.class),Mockito.any(LaserView.class),Mockito.any(CoinView.class));
        Mockito.verify(controller,Mockito.times(1)).runMenu();
    }




}
