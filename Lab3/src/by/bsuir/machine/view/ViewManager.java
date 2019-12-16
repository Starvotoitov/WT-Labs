package by.bsuir.machine.view;

import by.bsuir.machine.beans.order.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public interface ViewManager {
    public void viewOrderList(ArrayList<Order> orderList, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
