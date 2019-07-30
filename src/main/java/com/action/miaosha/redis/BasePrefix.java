package com.action.miaosha.redis;

/**
 * @author Dell
 * @create 2019-07-17 19:47
 */
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds; // 过期时间
    private String prefix; // 前缀

    public BasePrefix(String prefix) { // 0代表永不过期
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() { // 默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
