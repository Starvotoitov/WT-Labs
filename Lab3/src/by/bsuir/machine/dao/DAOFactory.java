package by.bsuir.machine.dao;

import by.bsuir.machine.dao.order.FileOrderDAO;
import by.bsuir.machine.dao.order.OrderDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final OrderDAO orderDAO = new FileOrderDAO();

    private DAOFactory() { };

    public static DAOFactory getInstance() {
        return instance;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }
}

