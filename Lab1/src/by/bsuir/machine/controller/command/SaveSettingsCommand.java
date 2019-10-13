package by.bsuir.machine.controller.command;

import by.bsuir.machine.service.DataManager;
import by.bsuir.machine.service.ServiceFactory;

public class SaveSettingsCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DataManager dataManager = serviceFactory.getDataManager();

        dataManager.updateProfile();
        return true;
    }
}
