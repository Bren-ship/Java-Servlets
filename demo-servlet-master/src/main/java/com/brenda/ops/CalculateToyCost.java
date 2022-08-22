package com.brenda.ops;

import com.brenda.dao.ToysDao;
import com.brenda.model.ToyModel;
import org.openide.util.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CalculateToyCost extends Task {

    public static void listToys(HttpServletRequest request, HttpServletResponse response, ToysDao dao) throws SQLException, IOException {
        List<ToyModel> toyModelList = dao.getAllToys();
        request.setAttribute("list_toys", toyModelList);
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
    }

}
