package com.guyan.test;

import com.google.gson.Gson;
import com.guyan.test.entity.User;
import com.guyan.test.utils.DbUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/user_info")
public class UserServlet extends HttpServlet {

    private User user;
    private Gson gson = new Gson();


    private Statement mStatement;

    @Override
    public void init() throws ServletException {
        super.init();
        mStatement = DbUtils.getConnect();
        user = new User("guyan", 27, "shangdi.inc");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        resp.setContentType("text/html");

        //设置逻辑实现
        PrintWriter out = resp.getWriter();
        try {
            ResultSet rs = mStatement.executeQuery("SELECT * FROM user");
            while (rs.next()){
                System.out.println("id " + rs.getInt("id"));
                System.out.println("name " + rs.getString("name"));
                System.out.println("address " + rs.getString("address"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println(gson.toJson(user));
    }

    @Override
    public void destroy() {
        super.destroy();
        DbUtils.close(mStatement);
    }
}
