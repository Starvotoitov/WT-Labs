package by.bsuir.machine.view;

import by.bsuir.machine.beans.order.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Viewer implements ViewManager{
    @Override
    public void viewOrderList(ArrayList<Order> orderList, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("orderList", orderList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        requestDispatcher.forward(request, response);
    }
}
