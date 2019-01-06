import com.google.gson.Gson;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user_info")
public class UserServlet extends HttpServlet {

    private User user;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        super.init();
        user = new User("guyan", 27, "shangdi.inc");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        resp.setContentType("text/html");

        //设置逻辑实现
        PrintWriter out = resp.getWriter();
        out.println(gson.toJson(user));
    }
}
