package com.action.miaosha.util;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;

/**
 * @author Dell
 * @create 2020-02-13 10:59
 */
@Slf4j
public class RedisDistributedLockUtil {
    @Resource
    private Logger log;
    /*// 错误的加锁方式一
    public static void wrongLock(Jedis jedis, String key, String uniqueId, int expireTime) {
        Long result = jedis.setnx(key, uniqueId);
        if (1 == result) {
            // 如果该redis实例崩溃，那就无法设置过期时间了
            jedis.expire(key, expireTime);
        }
    }*/

    /*// 错误的加锁方式二
    public static boolean wongLock(Jedis jedis, String key, String uniqueId, int expireTime) {
        long expireTs = System.currentTimeMillis() + expireTime;
        // 锁不存在，当前线程加锁成果
        if (jedis.setnx(key, String.valueOf(expireTs)) == 1) {
            return true;
        }

        String value = jedis.get(key);
        if (value != null && NumberUtils.toLong(value) < System.currentTimeMillis()) {
            // 锁过期，设置新的过期时间
            String oldValue = jedis.getSet(key, String.valueOf(expireTs));
            if (oldValue != null && oldValue.equals(value)) {
                // 多线程并发下，只有一个线程会设置成功
                // 设置成功的这个线程，key的旧值一定和设置之前的key的值一致
                return true;
            }
        }
            // 其他情况，加锁成功
            return true;
    }*/

    /*// 错误的解锁方式一
    public static void wrongReleaseLock(Jedis jedis, String key) {
        // 不是自己加锁的key，也会被释放
        jedis.del(key);
    }*/

    /*// 错误的解锁方式二
    public static void wrongReleaseLock(Jedis jedis, String key, String uniqueId) {
        if (uniqueId.equals(jedis.get(key))) {
            // 如果这时锁过期自动释放，又被其他线程加锁，该线程就会释放不属于自己的锁
            jedis.del(key);
        }
    }*/

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    // 锁的超时时间
    private static int EXPIRE_TIME = 5 * 1000;
    // 锁的等待时间
    private static int WAIT_TIME = 1 * 1000;

    private Jedis jedis;
    private String key;

    public RedisDistributedLockUtil(Jedis jedis, String key) {
        this.jedis = jedis;
        this.key = key;
    }

    // 不断尝试加锁
    public String lock() {
        try {
            // 超过等待时间，加载失败
            long waitEnd = System.currentTimeMillis() + WAIT_TIME;
            String value = UUID.randomUUID().toString();
            while (System.currentTimeMillis() < waitEnd) {
                String result = jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, EXPIRE_TIME);
                if (LOCK_SUCCESS.equals(result)) {
                    return value;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception exception) {
            System.out.println("lock error" + exception);
        }
        return null;
    }

    public boolean release(String value) {
        if (value == null) {
            return false;
        }
        // 判断key存在并且删除key必须是一个原子操作
        // 而且谁拥有锁，谁释放
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = new Object();
        try {
            result = jedis.eval(script, Collections.singletonList(key),
                    Collections.singletonList(value));
            if (RELEASE_SUCCESS.equals(result)) {
                log.info("release lock success, value:{}", value);
                return true;
            }
        } catch (Exception e) {
            log.error("release lock error", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        log.info("release lock failed, value:{}, result:{}", value, result);
        return false;
    }

}
