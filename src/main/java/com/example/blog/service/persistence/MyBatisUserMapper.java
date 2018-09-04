package com.example.blog.service.persistence;

import com.example.blog.model.User;
import com.example.blog.service.api.UserService;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyBatisUserMapper extends UserService {
    @Override
    @Select("select * from t_user")
    List<User> findAll();

    @Override
    @Select("select * from t_user where id = #{id}")
    User findOne(Long id);

    @Override
    @Insert("insert into t_user (username, password) values (#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(User user);

    @Override
    @Delete("delete from t_user where id = #{id}")
    void delete(Long id);

    @Override
    @Update("update t_user set username = #{username}, password = #{password} where id = #{id}")
    User updateUser(User user, Long id);
}
