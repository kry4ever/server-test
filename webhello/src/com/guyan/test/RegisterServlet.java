package com.guyan.test;

import com.guyan.test.entity.Error;
import com.guyan.test.utils.GsonUtils;
import com.guyan.test.utils.TokenUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

    private Statement mStatement;


    @Override
    public void init() throws ServletException {
        super.init();
        Connection conn;
        // 注册 JDBC 驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            mStatement = conn.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processLogin(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processLogin(req, resp);
    }

    private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("req is " + req.getQueryString());
//        System.out.println("req is " + req.getMethod());
//        System.out.println("req is " + req.getPathInfo());
////        System.out.println("req is " + req.getSession());
//        System.out.println("req is " + req.getAuthType());
//        System.out.println("req is " + req.getRequestURI());
//        System.out.println("req is " + req.getServletPath());
//        System.out.println("req is " + getServletInfo());
//        System.out.println("req is " + getServletName());
//        System.out.println("context " + getServletContext().getAttribute("name"));
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
            out.print(TokenUtil.genToken());
        } else {
            PrintWriter out = resp.getWriter();
            out.println(GsonUtils.gson.toJson(new Error("param error!!")));
        }
    }
}
