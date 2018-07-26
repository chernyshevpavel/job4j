package ru.job4j.tracker;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private ArrayList<UserAction> userActions = new ArrayList<>();
    private boolean exit = false;


    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void select(int key) {
        for (UserAction action: this.userActions) {
            if (action != null && action.key() == key) {
                action.execute(this.input, this.tracker);
            }
        }
    }

    public int[] getRange() {
        int[] range = new int[this.userActions.size()];
        int count = 0;
        for (UserAction action: this.userActions) {
            range[count++] = action.key();
        }
        return range;
    }

    public void fillActions() {
        this.userActions.add(new AddItem(0, "Добавить новую заявку"));
        this.userActions.add(new ShowItems(1, "Показать заявки"));
        this.userActions.add(new EditItem(2, "Редактировать заявку"));
        this.userActions.add(new DeleteItem(3, "Удалить заявку"));
        this.userActions.add(new FindById(4, "Найти заявку по идентификатору"));
        this.userActions.add(new FindByName(5, "Найти заявку по названию"));
        this.userActions.add(new Exit(6, "Выйти из программы"));
    }

    public void show() {
        printMenuHead();
        for (UserAction action: this.userActions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    public boolean isExit() {
        return exit;
    }

    private void setExit(boolean exit) {
        this.exit = exit;
    }

    private void printMenuHead() {
        System.out.println("Меню.");
    }

    private void printNotItemFound() {
        System.out.println("------------ Ну удалось найти заявку --------------");
    }

    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }
    }

    private class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Отображение всех заявок --------------");
            Item[] items = tracker.findAll();
            for (Item item: items) {
                System.out.println(item.toString());
            }
            if (items.length == 0) {
                System.out.println("------------ Заявок нет. --------------");
            }
        }
    }

    private class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Изменение заявки --------------");
            String id = input.ask("Введите идентификатор заявки :");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            if (tracker.replace(id, item)) {
                System.out.println("------------ Завка изменена --------------");
            } else {
                printNotItemFound();
            }
        }
    }

    private class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите идентификатор заявки :");
            if (tracker.delete(id)) {
                System.out.println("------------ Заявка удалена --------------");
            } else {
                printNotItemFound();
            }
        }
    }

    private class FindById extends BaseAction {
        public FindById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по идентификатору --------------");
            String id = input.ask("Введите идентификатор заявки :");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(item.toString());
            } else {
                printNotItemFound();
            }
        }
    }

    private class FindByName extends BaseAction {
        public FindByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по наименованию --------------");
            String name = input.ask("Введите имя заявки :");
            Item[] items = tracker.findByName(name);
            for (Item item: items) {
                System.out.println(item.toString());
            }
            if (items.length == 0) {
                printNotItemFound();
            }
        }
    }

    private class Exit extends BaseAction {
        public Exit(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            setExit(true);
        }
    }
}
