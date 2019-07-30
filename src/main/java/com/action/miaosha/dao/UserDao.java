package com.action.miaosha.dao;

import com.action.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Dell
 * @create 2019-07-14 16:01
 */
@Mapper
public interface UserDao {
    
    @Select("select* from user where id = #{id}")
    public User getById(@Param("id") int id);

    @Insert("INSERT into user(id,name) VALUES(#{id},#{name})")
    int insert(User user);
}
