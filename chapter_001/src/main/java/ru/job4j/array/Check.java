package ru.job4j.array;

/**
 * @author Pavel Chernyshev (titan100695@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int index = 1; index < data.length; index++) {
            if (data[0] != data[index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
