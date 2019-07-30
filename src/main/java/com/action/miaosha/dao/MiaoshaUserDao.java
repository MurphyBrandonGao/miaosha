package com.action.miaosha.dao;

import com.action.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Dell
 * @create 2019-07-18 19:18
 */
@Mapper
public interface MiaoshaUserDao {
    @Select("SELECT * FROM  miaosha_user WHERE id=#{id} ")
    public MiaoshaUser getById(@Param("id") long id);

    @Update("update miaosha_user set password = #{password} where id = #{id}")
    public void update(MiaoshaUser toBeUpdate);
}
