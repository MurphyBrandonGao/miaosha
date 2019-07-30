package com.action.miaosha.access;

import com.action.miaosha.domain.MiaoshaUser;

/**
 * @author Dell
 * @create 2019-07-29 13:54
 */
public class UserContext {

    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();

    public static void setUser(MiaoshaUser user) {
        userHolder.set(user);
    }

    public static MiaoshaUser getUser() {
        return userHolder.get();
    }

}
