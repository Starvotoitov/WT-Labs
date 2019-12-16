package by.bsuir.machine.view;

public class ViewFactory {
    private static final ViewFactory instance = new ViewFactory();
    private final ViewManager viewManager = new Viewer();

    public ViewFactory() {};

    public static ViewFactory getInstance() {
        return instance;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }
}
