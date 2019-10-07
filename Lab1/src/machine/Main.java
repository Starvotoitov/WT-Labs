package machine;

import machine.controller.ControlManager;

public class Main {
    public static void main(String[] args) {
        ControlManager controller = new ControlManager();
        controller.start();
    }
}