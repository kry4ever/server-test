package com.guyan.spring.iospring;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

    @RequestMapping("/hello")
    public String sayHello() {

        User user = dao.getUserByIdAnno(2);



//        String resource = "mybatis-config.xml";
//        InputStream inputStream = null;
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//            System.out.println("load res success");
//            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
//            SqlSession session = factory.openSession();
//            System.out.println("open session success");
////            Connection connection = session.getConnection();
////            System.out.println("get con success");
//            //---------------
//            //User user = session.selectOne("Mapper1.queryFirstUser", 10); //参数一：namespace.id
//            UserMapper mapper = session.getMapper(UserMapper.class);
//            User user = mapper.getUserById(10);
//            System.out.println("user is " + user);
//            //--------------
////            connection.close();
//            session.close();
//
//            return user.toString();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        return user.toString();
    }

    @RequestMapping("/insert")
    public void insertOne(){
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
