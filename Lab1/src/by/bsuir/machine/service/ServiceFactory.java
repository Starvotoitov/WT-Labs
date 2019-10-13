package by.bsuir.machine.service;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final DataManager dataManager = new DataManager();

    private ServiceFactory(){};

    public static ServiceFactory getInstance() {
        return instance;
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
