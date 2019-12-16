package by.bsuir.machine.service.order;

import by.bsuir.machine.beans.order.Order;
import by.bsuir.machine.dao.DAOFactory;
import by.bsuir.machine.dao.ParsersType;
import by.bsuir.machine.dao.order.OrderDAO;

import java.util.ArrayList;

public class OrderServiceManager implements OrderService {
    private static final int PAGE_SIZE = 10;

    private int pageCount;
    private int currentPage;
    private ArrayList<Order> orderList;

    public OrderServiceManager() {
        pageCount = 0;
        currentPage = 0;
        orderList = null;
    }

    @Override
    public void setNewOrderList(String path, int parseType) {
        if ((path != null) && (parseType >= ParsersType.MIN_PARSER_VALUE) && (parseType <= ParsersType.MAX_PARSER_VALUE)) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            OrderDAO orderDAO = daoFactory.getOrderDAO();

            orderList = orderDAO.getOrderList(path, parseType);
            currentPage = 0;
            if (orderList.size() % PAGE_SIZE == 0) {
                pageCount = orderList.size() / PAGE_SIZE;
            } else {
                pageCount = orderList.size() / PAGE_SIZE + 1;
            }
        }
    }

    @Override
    public ArrayList<Order> getCurrentPage() {
        ArrayList<Order> resultList = new ArrayList<Order>();
        if (orderList != null) {
            int topBorder;
            if ((currentPage != pageCount - 1) || (orderList.size() % PAGE_SIZE == 0)) {
                topBorder = (currentPage + 1) * PAGE_SIZE;
            } else {
                topBorder = orderList.size();
            }

            for (int i = currentPage * PAGE_SIZE; i < topBorder; i++) {
                resultList.add(orderList.get(i));
            }
            return resultList;
        }
        return null;
    }

    @Override
    public ArrayList<Order> getNextPage() {
        if (currentPage != pageCount - 1) {
            currentPage += 1;
            return getCurrentPage();
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Order> getPreviousPage() {
        if (currentPage != 0) {
            currentPage -= 1;
            return getCurrentPage();
        } else {
            return null;
        }
    }

    @Override
    public boolean isFirstPage() {
        return currentPage == 0;
    }

    @Override
    public boolean isLastPage() {
        return currentPage == pageCount - 1;
    }
}
