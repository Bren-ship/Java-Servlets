package com.brenda.ops;

import com.brenda.dao.ToysDao;
import com.brenda.model.ToyModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddItem {
    public static void insertItem(HttpServletRequest request, HttpServletResponse response, ToysDao dao) throws SQLException, IOException {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        ToyModel model = new ToyModel(name, Integer.parseInt(price), Integer.parseInt(quantity));
        dao.insertToysToTable(model);
        response.sendRedirect("list");
    }

    public static void showNewForm(HttpServletRequest request, HttpServletResponse response, ToysDao dao) throws SQLException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("new_toy.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
    }

}