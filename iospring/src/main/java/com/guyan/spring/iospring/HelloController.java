package com.guyan.spring.iospring;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@EnableAutoConfiguration
@RestController
public class HelloController {

    @Autowired
    private UserDao dao;

    private Logger logger = LogManager.getLogger("TRACE_ALL");

    @RequestMapping("/hello")
    public String sayHello() {


        logger.info("receiver hello");
        User user = dao.getUserByIdAnno(2);
        logger.info("use hello " + user);

        return user.toString();
    }

    @RequestMapping("/insert")
    public void insertOne() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            System.out.println("load res success");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = factory.openSession();
            System.out.println("open session success");
//            Connection connection = session.getConnection();
//            System.out.println("get con success");
            //---------------
            int res = session.insert("insertUser", new User(2, "kry", "chongqing")); //参数一：namespace.id
            System.out.println("res is " + res);
            session.commit();
            //--------------
//            connection.close();
            session.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
