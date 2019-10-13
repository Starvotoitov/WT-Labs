package by.bsuir.machine;

import by.bsuir.machine.controller.ControlManager;

public class Main {
    public static void main(String[] args) {
        ControlManager controller = new ControlManager();
        controller.start();
    }
}