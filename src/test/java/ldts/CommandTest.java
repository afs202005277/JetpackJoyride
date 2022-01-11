package ldts;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import ldts.control.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class CommandTest {
    private Screen screen;
    private Command command;
    @BeforeEach
    public void getTestScreen() throws IOException {
        screen = Mockito.mock(Screen.class);
    }

    @BeforeEach
    public void getTestCommand(){command = new Command(screen);}

    @Test
    public void ChangeKeyTest(){
        Assertions.assertEquals('0', command.getKey());
        command.changeKey('A');
        Assertions.assertEquals('A', command.getKey());
    }

    @Test
    public void useKeyTest(){
        Assertions.assertEquals('0', command.getKey());
        command.changeKey('A');
        Assertions.assertEquals('A', command.useKey());
        Assertions.assertEquals('0', command.getKey());
    }

    @Test
    public void inputReaderTest() throws IOException {
        KeyStroke k = Mockito.mock(KeyStroke.class);
        Mockito.when(k.getCharacter()).thenReturn(' ');
        Mockito.when(k.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(screen.readInput()).thenReturn(k);
        //When space is pressed
        command.inputReader(screen);
        Assertions.assertEquals(k.getCharacter(), command.getKey());

        //When any other char is pressed
        Mockito.when(k.getCharacter()).thenReturn('a');
        command.inputReader(screen);
        Assertions.assertEquals('a', command.getKey());

        //When any other key is pressed (not character)
        Mockito.when(k.getKeyType()).thenReturn(KeyType.ArrowDown);
        command.inputReader(screen);
        Assertions.assertEquals('0', command.getKey());
    }
}
