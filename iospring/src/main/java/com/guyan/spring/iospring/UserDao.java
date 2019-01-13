package com.guyan.spring.iospring;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserByIdAnno(@Param("id") int id);
}
