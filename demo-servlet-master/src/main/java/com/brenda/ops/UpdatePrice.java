package com.brenda.ops;

import com.brenda.dao.ToysDao;
import com.brenda.model.ToyModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdatePrice {
    public static void updateToy(HttpServletRequest request, HttpServletResponse response, ToysDao dao) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");

        ToyModel model = new ToyModel(name, Integer.parseInt(price), Integer.parseInt(quantity));
        dao.updateToys(model);
        response.sendRedirect("list");
    }

    public static void showEditForm(HttpServletRequest request, HttpServletResponse response, ToysDao dao) throws SQLException, IOException {
        try {
            String name = request.getParameter("name");
            ToyModel existingItem = dao.selectToy(name);
            RequestDispatcher dispatcher = request.getRequestDispatcher("new_toy.jsp");
            request.setAttribute("toy", existingItem);
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
    }

}