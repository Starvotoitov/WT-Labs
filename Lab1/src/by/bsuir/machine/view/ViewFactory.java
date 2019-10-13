package by.bsuir.machine.view;

public class ViewFactory {
    private static final ViewFactory instance = new ViewFactory();
    private ViewManager viewManager = new ViewManager();

    private ViewFactory(){};

    public static ViewFactory getInstance() {
        return instance;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }
}
