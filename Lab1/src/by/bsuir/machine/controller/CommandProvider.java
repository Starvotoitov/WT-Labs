package by.bsuir.machine.controller;

import by.bsuir.machine.controller.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, объект которого используется для хранения доступных команд и поиска их по названию.
 */
public class CommandProvider {
    private Map<String, Command> repository;

    /**
     * Создается объект, инициализируется список доступных команд.
     */
    public CommandProvider() {
        repository = new HashMap<>();
        repository.put("add", new AddCommand());
        repository.put("choose", new ChooseCommand());
        repository.put("delete", new DeleteCommand());
        repository.put("exit", new ExitCommand());
        repository.put("save",new SaveToFileCommand());
        repository.put("save_settings", new SaveSettingsCommand());
        repository.put("show", new ShowCommand());
        repository.put("show_coffee", new ShowCoffeeCommand());
        repository.put("show_sort", new ShowSortTypesCommand());
        repository.put("sort", new SortCommand());
        repository.put("update", new UpdateOrderCommand());
        repository.put("wrong_request", new WrongRequestCommand());
    }

    /**
     * Осуществляет поиск необходимой команды.
     * @param args Аргументы командной строки.
     * @return Возвращает найденную команду. Если команда не была найдена - возвращает команду, выполняемую при
     *         неверном запросе.
     */
    public Command getCommand(String[] args) {
        Command command;
        try {
            String commandName = args[0].toLowerCase();
            if (commandName.compareTo("") != 0) {
                command = repository.get(commandName);
                if (command == null) {
                    command = repository.get("wrong_request");
                }
            } else {
                command = repository.get("wrong_request");
            }
        } catch (IllegalArgumentException | NullPointerException ex) {
            command = repository.get("wrong_request");
        }
        return command;
    }
}
