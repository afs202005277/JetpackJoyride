import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ldts.control.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

public class CommandTest {
    private Screen screen;
    private Command command;
    @BeforeEach
    public void getTestScreen() throws IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = null;
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
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
    public void inputReaderTest() throws IOException, AWTException {
        Robot robot = new Robot();
        robot.keyPress(32); // 32 is the code of space bar
        command.inputReader(screen);
        Assertions.assertEquals(' ', command.getKey());
    }
}
