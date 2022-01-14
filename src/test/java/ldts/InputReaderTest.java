package ldts;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import ldts.control.InputReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class InputReaderTest {
    private Screen screen;
    private InputReader inputReader;
    @BeforeEach
    public void getTestScreen() throws IOException {
        screen = Mockito.mock(Screen.class);
    }

    @BeforeEach
    public void getTestCommand(){
        inputReader = new InputReader(screen);}

    @Test
    public void ChangeKeyTest(){
        Assertions.assertEquals('0', inputReader.getKey());
        inputReader.changeKey('A');
        Assertions.assertEquals('A', inputReader.getKey());
    }

    @Test
    public void useKeyTest(){
        Assertions.assertEquals('0', inputReader.getKey());
        inputReader.changeKey('A');
        Assertions.assertEquals('A', inputReader.useKey());
        Assertions.assertEquals('0', inputReader.getKey());
    }

    @Test
    public void inputReaderTest() throws IOException {
        KeyStroke k = Mockito.mock(KeyStroke.class);
        Mockito.when(k.getCharacter()).thenReturn(' ');
        Mockito.when(k.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(screen.readInput()).thenReturn(k);
        //When space is pressed
        inputReader.inputReader(screen);
        Assertions.assertEquals(k.getCharacter(), inputReader.getKey());

        //When any other char is pressed
        Mockito.when(k.getCharacter()).thenReturn('a');
        inputReader.inputReader(screen);
        Assertions.assertEquals('a', inputReader.getKey());

        //When any other key is pressed (not character)
        Mockito.when(k.getKeyType()).thenReturn(KeyType.ArrowDown);
        inputReader.inputReader(screen);
        Assertions.assertEquals('0', inputReader.getKey());
    }
}
