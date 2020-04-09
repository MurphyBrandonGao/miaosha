package com.action.miaosha.service;

import com.action.miaosha.dao.MiaoshaUserDao;
import com.action.miaosha.domain.MiaoshaUser;
import com.action.miaosha.exception.GlobalException;
import com.action.miaosha.redis.MiaoshaUserKey;
import com.action.miaosha.redis.RedisService;
import com.action.miaosha.result.CodeMsg;
import com.action.miaosha.util.MD5Util;
import com.action.miaosha.util.UUIDUtil;
import com.action.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dell
 * @create 2019-07-18 19:21
 */
@Service
public class MiaoshaUserService {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id) {
        // 从缓存中取
        MiaoshaUser user = redisService.get(MiaoshaUserKey.getById, "" + id, MiaoshaUser.class);
        if (user != null) {
            return user;
        }
        // 从数据库中取
        user = miaoshaUserDao.getById(id);
        if (user != null)
            redisService.set(MiaoshaUserKey.getById, "" + id, user);
        return user;
    }

    // 修改用户密码
    public boolean updatePassword(String token, long id, String formPass) {
        // 取user
        MiaoshaUser user = getById(id);
        if (user == null)
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);

        // 先更新数据库
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        miaoshaUserDao.update(toBeUpdate);
        // 先删除缓存。再添加缓存
        redisService.delete(MiaoshaUserKey.getById, "" + id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(MiaoshaUserKey.token, token, user);

        return true;
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null)
            throw new GlobalException(CodeMsg.SERVER_SERROR);

        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        // 判断手机号码是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null)
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);

        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token))
            return null;


        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    public void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        //生成cookie
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
