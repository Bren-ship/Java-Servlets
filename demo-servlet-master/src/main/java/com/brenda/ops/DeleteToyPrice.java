package com.brenda.ops;

import com.brenda.dao.ToysDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteToyPrice {
    public static void deleteEntry(HttpServletRequest request, HttpServletResponse response, ToysDao dao) throws SQLException, IOException {
        String id = request.getParameter("name");
        dao.deleteEntry(id);
        response.sendRedirect("list");
    }


}