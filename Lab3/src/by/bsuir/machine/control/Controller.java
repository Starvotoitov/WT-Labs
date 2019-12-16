package by.bsuir.machine.control;

import by.bsuir.machine.beans.order.Order;
import by.bsuir.machine.service.ServiceFactory;
import by.bsuir.machine.service.order.OrderService;
import by.bsuir.machine.view.ViewFactory;
import by.bsuir.machine.view.ViewManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Controller extends HttpServlet {
    private static final String filePath = "D://OrderList.xml";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Order> orderList = null;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        String param = req.getParameter("page");
        if (param != null) {
            switch (param) {
                case "prev":
                    orderList = orderService.getPreviousPage();
                    break;
                case "next":
                    orderList = orderService.getNextPage();
                    break;
            }
        }
        String parser = req.getParameter("parser");
        if (parser != null) {
            orderService.setNewOrderList(filePath, Integer.parseInt(parser));
            orderList = orderService.getCurrentPage();
        }

        ViewFactory viewFactory = ViewFactory.getInstance();
        ViewManager viewManager = viewFactory.getViewManager();
        viewManager.viewOrderList(orderList, req, resp);
    }
}
