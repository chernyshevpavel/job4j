package ru.job4j.tracker;

public class StartUI {
    /**
     * Получение данных от пользователя. Композиция
     */
    private final Input input;

    /**
     * Хранилище заявок. Композиция
     */
    private final ITracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        while (!exit) {
            menu.show();
            menu.select(this.input.ask("Введите пункт меню : ", menu.getRange()));
            if (menu.isExit()) {
                exit = true;
            }
        }
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
