package ru.job4j.generics;

/***
 * @author Павел Чернышев (pavel.chernyshev.work@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserStore extends AbstractStore<User> implements Store<User> {
    public UserStore(int size) {
        super(new SimpleArray<>(size));
    }
}
