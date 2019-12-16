package by.bsuir.machine.service;

import by.bsuir.machine.service.order.OrderService;
import by.bsuir.machine.service.order.OrderServiceManager;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final OrderService orderService = new OrderServiceManager();

    public ServiceFactory() {};

    public static ServiceFactory getInstance() {
        return instance;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
