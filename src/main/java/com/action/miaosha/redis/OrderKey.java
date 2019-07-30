package com.action.miaosha.redis;

/**
 * @author Dell
 * @create 2019-07-17 19:52
 */
public class OrderKey extends BasePrefix {
    public OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
