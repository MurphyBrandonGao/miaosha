package com.action.miaosha.redis;

/**
 * @author Dell
 * @create 2019-07-17 19:52
 */
public class UserKey extends BasePrefix {

    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
