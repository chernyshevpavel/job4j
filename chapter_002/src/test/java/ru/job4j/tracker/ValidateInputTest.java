package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 *
 * @author Pavel CHernyshev (mailto:titan100695@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        input.ask("Введите пункт меню : ", new int[] {1});
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Введите число%n")
                )
        );
    }

    @Test
    public void whenOutOfRangeMenuInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"-9", "1"})
        );
        input.ask("Введите пункт меню : ", new int[] {1});
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Выберите ключ из меню%n")
                )
        );
    }
}