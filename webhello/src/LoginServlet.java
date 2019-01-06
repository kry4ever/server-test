import entity.Error;
import utils.GsonUtils;
import utils.TokenUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("req is " + req.getQueryString());
        System.out.println("req is " + req.getMethod());
        System.out.println("req is " + req.getPathInfo());
//        System.out.println("req is " + req.getSession());
        System.out.println("req is " + req.getAuthType());
        System.out.println("req is " + req.getRequestURI());
        System.out.println("req is " + req.getServletPath());
        System.out.println("req is " + getServletInfo());
        System.out.println("req is " + getServletName());
        String query = req.getQueryString();
        String[] info = query.split("&");
        if (info.length == 2) {
            String[] nameInfo = info[0].split("=");
            String name = nameInfo[1];
            String[] passInfo = info[1].split("=");
            String password = passInfo[1];
            System.out.println("name is " + name + " pass is " + password);
            PrintWriter out = resp.getWriter();
            resp.setStatus(200);
            out.println(TokenUtil.genToken());
        } else {
            PrintWriter out = resp.getWriter();
            out.println(GsonUtils.gson.toJson(new Error("param error!!")));
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//        System.out.println("req is " + req.getQueryString());
//        System.out.println("req is " + req.getMethod());
//        System.out.println("req is " + req.getPathInfo());
////        System.out.println("req is " + req.getSession());
//        System.out.println("req is " + req.getAuthType());
//        System.out.println("req is " + req.getRequestURI());
//        System.out.println("req is " + req.getServletPath());
//        System.out.println("req is " + getServletInfo());
//        System.out.println("req is " + getServletName());
//        String query = req.getQueryString();
//        String[] info = query.split("&");
//        if (info.length == 2) {
//            String[] nameInfo = info[0].split("=");
//            String name = nameInfo[1];
//            String[] passInfo = info[1].split("=");
//            String password = passInfo[1];
//            System.out.println("name is " + name + " pass is " + password);
//            PrintWriter out = resp.getWriter();
//            resp.setStatus(200);
//            out.println(TokenUtil.genToken());
//        } else {
//            PrintWriter out = resp.getWriter();
//            out.println(GsonUtils.gson.toJson(new Error("param error!!")));
//        }
//    }
}
