package com.action.miaosha.redis;

/**
 * @author Dell
 * @create 2019-07-17 19:52
 */
public class GoodsKey extends BasePrefix {

    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");

    public static GoodsKey getGoodsList = new GoodsKey(60,"gl");
    public static GoodsKey getMiaoshaGoodsStock = new GoodsKey(0,"gs");
}
