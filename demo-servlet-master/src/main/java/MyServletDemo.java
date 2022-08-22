import com.brenda.dao.ToysDao;
import com.brenda.ops.AddItem;
import com.brenda.ops.CalculateToyCost;
import com.brenda.ops.DeleteToyPrice;
import com.brenda.ops.UpdatePrice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * subclass of GenericServlet
 */
@WebServlet("/")
public class MyServletDemo extends HttpServlet {

    private ToysDao dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new ToysDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        String action = req.getServletPath();

        try {
            switch (action) {
                case "/new":
                    AddItem.showNewForm(req, resp, dao);
                    break;
                case "/insert":
                    AddItem.insertItem(req, resp, dao);
                    break;
                case "/delete":
                    DeleteToyPrice.deleteEntry(req, resp, dao);
                    break;
                case "/edit":
                    UpdatePrice.showEditForm(req, resp, dao);
                    break;
                case "/update":
                    UpdatePrice.updateToy(req, resp, dao);
                    break;
                default:
                    CalculateToyCost.listToys(req, resp, dao);
                    break;
            }
        } catch (SQLException | IOException ex) {
            if (ex instanceof SQLException) {
                throw new ServletException(ex);
            } else
                ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        doGet(req, resp);
    }
}
