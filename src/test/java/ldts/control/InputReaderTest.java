package ldts.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class InputReaderTest {
    private Screen screen;
    private InputReader iReader;

    @BeforeEach
    public void getTestScreen() throws IOException {
        screen = Mockito.mock(Screen.class);
        iReader = new InputReader(screen);
    }

    @Test
    public void inputReaderTest() throws IOException, URISyntaxException, InterruptedException, FontFormatException {
        InputObserver inputObserver = Mockito.mock(InputObserver.class);
        iReader.addObserver(inputObserver);
        Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.Enter));
        iReader.inputReader(screen);
        Mockito.verify(inputObserver, Mockito.times(1)).input(new KeyStroke(KeyType.Enter));
    }
}
